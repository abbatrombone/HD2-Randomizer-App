package me.abbatrombone.traz.CustomComponents;

import me.abbatrombone.traz.Managers.OSManager;
import me.abbatrombone.traz.Managers.SettingsManager;

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
    private BufferedImage backgroundImage;
    private final float imageOpacity = 0.3f;
    private final static SimpleAttributeSet attributeSet = new SimpleAttributeSet();

    private final Style normalStyle;
    private final Style hoverStyle;
    private final Map<String, String> hoverMessages = new HashMap<>();
    //private final List<int[]> hoverRanges = new ArrayList<>();
//    private final JWindow tooltipWindow = new JWindow();
//    private final JLabel tooltipLabel = new JLabel();

    private static final Logger logger = Logger.getLogger(OutputJTextPane.class.getName());
    private static final SettingsManager settingsManager = new SettingsManager();
    private final Color hoverColor = settingsManager.getColor("Label_Color","#ffffff");
    private final Color textColor = settingsManager.getColor("Text_Color","#ffffff");
    //private final Map<String, BufferedImage> imageCache = new HashMap<>();

    //private int[] activeRange = null;
    private final List<HoverRange> hoverRanges = new ArrayList<>();
    private HoverRange activeRange = null;

    Tooltip tooltip = new Tooltip();

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

        //setupTooltipUI();

        addMouseMotionListener(new HoverHandler());
        addMouseListener(new ClickHandler());
    }

    public void updateText(String text){
        StyledDocument doc = getStyledDocument();
        //StyleConstants.setBackground(attributeSet, Color.ORANGE);

        if(!getText().isEmpty()){
            setText("");
        }

        try {
            doc.insertString(doc.getLength(), text, attributeSet); //moved add hover to button panel
        } catch (BadLocationException e) {
            logger.log(Level.WARNING,"Unable to update Text" + e);
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
        //logger.info("Hover words: " + hoverMessages.keySet());
    }
    public void rebuildHoverRanges() {
        activeRange = null;
        //tooltipWindow.setVisible(false);
        //tooltipLabel.setVisible(false);
        scanForHoverRanges();
    }

//    private void scanForHoverRanges() {
//        hoverRanges.clear();
//        String text = getText();
//        for (String word : hoverMessages.keySet()) {
//            int index = 0;
//            while ((index = text.indexOf(word, index)) != -1) {
//                hoverRanges.add(new int[]{index, index + word.length(), word.hashCode()});
//                index += word.length();
//            }
//        }
//    }
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

//    private void setupTooltipUI() {
//        tooltipWindow.setBackground(new Color(0, 0, 0, 0));
//        JPanel panel = new JPanel();
//        panel.setBackground(new Color(30, 30, 30));
//        panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
//        panel.setLayout(new GridBagLayout());
//
//        tooltipLabel.setForeground(hoverColor);
//        tooltipLabel.setFont(new Font("Arial", Font.BOLD, 12));
//        panel.add(tooltipLabel);
//
//        tooltipWindow.add(panel);
//        tooltipLabel.addPropertyChangeListener("text", evt -> {
//            FontMetrics fm = tooltipLabel.getFontMetrics(tooltipLabel.getFont());
//            int textWidth = fm.stringWidth(tooltipLabel.getText());
//            int textHeight = fm.getHeight();
//
//            int paddingW = 20;
//            int paddingH = 14;
//
//            tooltipWindow.setSize(textWidth + paddingW, textHeight + paddingH);
//            tooltipWindow.setAlwaysOnTop(true);
//            tooltipWindow.pack();
//        });
//
//        tooltipWindow.pack();
//    }

    private static class HoverRange {
        final int start;
        final int end;
        final String word;

        HoverRange(int start, int end, String word) {
            this.start = start;
            this.end = end;
            this.word = word;
        }
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
}
