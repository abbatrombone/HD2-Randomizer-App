package me.abbatrombone.traz.CustomComponents;

import me.abbatrombone.traz.JSONTools.JSONStats;
import me.abbatrombone.traz.Managers.OSManager;
import me.abbatrombone.traz.Managers.SettingsManager;
import me.abbatrombone.traz.Panels.ButtonActions.RandomLoadOut;
import me.abbatrombone.traz.Panels.ButtonActions.SemiRandomLoadOut;
import me.abbatrombone.traz.Utilities.StringParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OutputJTextPane extends JTextPane {
       private String lastButton = "";
    private BufferedImage backgroundImage;
    private final float imageOpacity = 0.3f;
    private final static SimpleAttributeSet attributeSet = new SimpleAttributeSet();

    private final Style normalStyle;
    private final Style hoverStyle;
    private Style buttonStyle;
    private final Map<String, String> hoverMessages = new HashMap<>();
    private final Color fgColor = settingsManager.getColor("Label_Color","#ffffff");

    private final CustomButton wonRun = new CustomButton("Won",fgColor);
    private final CustomButton lostRun = new CustomButton("Lost",fgColor);

    private static final Logger logger = Logger.getLogger(OutputJTextPane.class.getName());
    private static final SettingsManager settingsManager = new SettingsManager();

    private final Color hoverColor = settingsManager.getColor("Label_Color","#ffffff");
    private final Color textColor = settingsManager.getColor("Text_Color","#ffffff");

    private final List<HoverRange> hoverRanges = new ArrayList<>();
    private HoverRange activeRange = null;
    private final Tooltip tooltip = new Tooltip();

    public OutputJTextPane() {

        StyleConstants.setBold(attributeSet, true);
        StyleConstants.setForeground(attributeSet, Color.BLACK);
        setFont(new Font("FS Sinclair", Font.BOLD, 12)); //Swiss 721 Extended
        setOpaque(false);
        setEditable(false);
        setText(" ");

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Super_Earth_Flag.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        normalStyle = addStyle("normal", null);
        StyleConstants.setForeground(normalStyle, textColor.equals(Color.WHITE) ? Color.BLACK : textColor);
        StyleConstants.setFontSize(normalStyle, 12);

        hoverStyle = addStyle("hover", null);
        StyleConstants.setForeground(hoverStyle, Color.BLUE);
        StyleConstants.setUnderline(hoverStyle, true);
        StyleConstants.setFontSize(hoverStyle, 12);

        wonRun.setPreferredSize(new Dimension(20,20));
        wonRun.setHoverWord("Won Randomizer Operation");
        wonRun.addActionListener(e -> {
            StringParser p = new StringParser();

            ArrayList<String> temp = new ArrayList<>();


            if(lastButton.equals("Random")){
                Collections.addAll(temp,RandomLoadOut.getStratgems().split("\n"));

                JSONStats jsonStats = new JSONStats(JSONStats.filePaths.randomStats);
                jsonStats.addWin("Primary",p.parsePrimaryName((RandomLoadOut.getPrimaryWeapon())));
                jsonStats.addWin("Secondary",p.parseSecondaryName((RandomLoadOut.getSecondaryWeapon())));
                jsonStats.addWin("Throwable",p.parseThrowable((RandomLoadOut.getThrowable())));
                jsonStats.addWin("Armor_Level",RandomLoadOut.getArmorLevel());
                jsonStats.addWin("Armor_Passive",RandomLoadOut.getArmorPassive());
                jsonStats.addWin("Booster",RandomLoadOut.getBoosterList().getFirst());
                jsonStats.addWin("Enemy",RandomLoadOut.getEnemyType());

                for(String s : temp){
                    jsonStats.addWin("Stratagem",s);
                }
            }else{
                JSONStats jsonStats = new JSONStats(JSONStats.filePaths.semiRandomStat);
                jsonStats.addWin("Secondary",p.parseSecondaryName((SemiRandomLoadOut.getSecondaryWeapon())));
                jsonStats.addWin("Throwable",p.parseThrowable((SemiRandomLoadOut.getThrowable())));
                jsonStats.addWin("Armor_Level",SemiRandomLoadOut.getArmorLevel());
                jsonStats.addWin("Booster",SemiRandomLoadOut.getBoosterList().getFirst());
                jsonStats.addWin("Enemy",SemiRandomLoadOut.getEnemyType());
                jsonStats.addWin("Stratagem",p.parseStrategem(SemiRandomLoadOut.getSupportWeapon()));
                jsonStats.addWin("Stratagem",p.parseStrategem(SemiRandomLoadOut.getSecondStrat()));
                jsonStats.addWin("Stratagem",p.parseStrategem(SemiRandomLoadOut.getThirdStrat()));
                jsonStats.addWin("Stratagem",p.parseStrategem(SemiRandomLoadOut.getForthStrat()));
            }
            removeButtons();
        });

        lostRun.setPreferredSize(new Dimension(20,20));
        lostRun.setHoverWord("Lost Randomizer Operation");
        lostRun.addActionListener(e -> {
            StringParser p = new StringParser();

            ArrayList<String> temp = new ArrayList<>();
            Collections.addAll(temp,RandomLoadOut.getStratgems().split("\n"));

            JSONStats jsonStats = new JSONStats(JSONStats.filePaths.randomStats);
            jsonStats.addLose("Primary",p.parsePrimaryName((RandomLoadOut.getPrimaryWeapon())));
            jsonStats.addLose("Secondary",p.parseSecondaryName((RandomLoadOut.getSecondaryWeapon())));
            jsonStats.addLose("Throwable",p.parseThrowable((RandomLoadOut.getThrowable())));
            jsonStats.addLose("Armor_Level",RandomLoadOut.getArmorLevel());
            jsonStats.addLose("Armor_Passive",RandomLoadOut.getArmorPassive());
            jsonStats.addLose("Booster",RandomLoadOut.getBoosterList().getFirst());
            jsonStats.addLose("Enemy",RandomLoadOut.getEnemyType());

            for(String s : temp){
                jsonStats.addLose("Stratagem",s);
            }
            removeButtons();
        });

        addMouseMotionListener(new HoverHandler());
        addMouseListener(new ClickHandler());
    }

    public void updateText(String text){
        StyledDocument doc = getStyledDocument();
        //StyleConstants.setBackground(attributeSet, Color.ORANGE);
        buttonStyle = addStyle("button", null);
        StyleConstants.setComponent(buttonStyle,wonRun);

        if(!getText().isEmpty()){
            setText("");
        }

        try {
            doc.insertString(doc.getLength(), text, attributeSet); //moved add hover to button panel
            Style wonStyle = addStyle("wonButton", null);
            StyleConstants.setComponent(wonStyle, wonRun);
            doc.insertString(doc.getLength(), " ", wonStyle);

            // Add spacing between buttons
            doc.insertString(doc.getLength(), "  ", normalStyle);

            // Create style for Lost button
            Style lostStyle = addStyle("lostButton", null);
            StyleConstants.setComponent(lostStyle, lostRun);
            doc.insertString(doc.getLength(), " ", lostStyle);
        } catch (BadLocationException e) {
            logger.log(Level.WARNING,"Unable to update Text" + e);
        }
    }
    private void removeButtons() {
        StyledDocument doc = getStyledDocument();

        try {
            int len = doc.getLength();
            doc.remove(len - 4, 4);  // remove last 4 chars: " button space space button"

        } catch (BadLocationException e) {
            logger.log(Level.WARNING, e.toString());
        }
    }

    public void updateImage(String enemy){
        switch (enemy){
            case "Terminids"  -> {
                try {
                    backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Terminids.png")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Automatons" -> {
                try {
                    backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bots.png")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Illuminate" -> {
                try {
                    backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Squids.png")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> {
                try {
                    backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Super_Earth_Flag.jpg")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // MUST be first

        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, imageOpacity));
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }

    public void addHoverWord(String word, String message) {
        if (word == null || word.isBlank()) {
            return;
        }
        hoverMessages.put(word, message);
    }
    public void rebuildHoverRanges() {
        activeRange = null;
        scanForHoverRanges();
    }
private void scanForHoverRanges() {
    hoverRanges.clear();
    String text = getText();

    for (String word : hoverMessages.keySet()) {

        if (word == null || word.isBlank()) {
            continue;
        }

        int index = 0;
        while ((index = text.indexOf(word, index)) != -1) {
            hoverRanges.add(
                    new HoverRange(index, index + word.length(), word)
            );
            index += word.length();
        }
    }
}

    private record HoverRange(int start, int end, String word) {
        boolean contains(int pos) {
                return pos >= start && pos < end;
            }

        int length() {
                return end - start;
            }
        }
    private class HoverHandler extends MouseMotionAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
            int pos = viewToModel2D(e.getPoint());
            if (pos < 0) {
                return;
            }
            StyledDocument doc = getStyledDocument();

            HoverRange newRange = null;

            for (HoverRange range : hoverRanges) {
                if (range.contains(pos)) {
                    newRange = range;
                    break;
                }
            }

            // No change → do nothing
            if (activeRange == newRange) {
                if (tooltip.isVisible()) {
                    Point p = e.getLocationOnScreen();
                    tooltip.setLocation(p.x + 12, p.y + 18);
                }
                return;
            }

            // Clear old hover
            if (activeRange != null) {
                doc.setCharacterAttributes(
                        activeRange.start,
                        activeRange.length(),
                        normalStyle,
                        true
                );
                //tooltipWindow.setVisible(false);
                tooltip.setVisible(false);
            }

            activeRange = newRange;

            // Apply new hover
            if (newRange != null) {
//                tooltipLabel.setText(hoverMessages.get(newRange.word));
//                tooltip.setTooltipLabel(tooltipLabel);
                tooltip.getTooltipLabel().setText(hoverMessages.get(newRange.word));


                doc.setCharacterAttributes(
                        newRange.start,
                        newRange.length(),
                        hoverStyle,
                        true
                );

                Point p = e.getLocationOnScreen();
                //tooltipWindow.setLocation(p.x + 12, p.y + 18);
                tooltip.setLocation(p.x + 12, p.y + 18);
                //tooltipWindow.setVisible(true);
                tooltip.setVisible(true);
            }
        }
    }

    private class ClickHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            int pos = viewToModel2D(e.getPoint());

            for (HoverRange range : hoverRanges) {
                if (range.contains(pos)) {
                    openURL(
                            ("https://helldivers.wiki.gg/wiki/" + range.word).replace(" ", "_")
                    );
                    break;
                }
            }
        }
    }

    private void openURL(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(URI.create(url));
                logger.log(Level.INFO, "Failed to open URL using Desktop API: " + url);
                return;
            }

        } catch (Exception e) {
            if(!OSManager.getOperatingSystem().equals("linux")){ //make sure OS is not linux
            logger.log(Level.SEVERE, "Desktop API failed for URL: " + url, e);
            }
        }

        try {
            new ProcessBuilder("xdg-open", url).start();
            logger.log(Level.INFO, "Failed to open URL using Desktop API: " + url);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "xdg-open failed for URL: " + url, e);
            JOptionPane.showMessageDialog(null, "Cannot open browser.\nURL: " + url);
        }
    }
    public String getLastButton() {
        return lastButton;
    }

    public void setLastButton(String lastButton) {
        this.lastButton = lastButton;
    }

}
