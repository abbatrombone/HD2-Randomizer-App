package me.abbatrombone.traz.CustomComponents;

import me.abbatrombone.traz.Managers.OSManager;
import me.abbatrombone.traz.Managers.SettingsManager;
import me.abbatrombone.traz.Panels.ButtonActions.RandomLoadOut;

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private final List<int[]> hoverRanges = new ArrayList<>();
    private final JWindow tooltipWindow = new JWindow();
    private final JLabel tooltipLabel = new JLabel();

    private static final Logger logger = Logger.getLogger(OutputJTextPane.class.getName());
    private static final SettingsManager settingsManager = new SettingsManager();
    private final Color hoverColor = settingsManager.getColor("Label_Color","#ffffff");
    private final Color textColor = settingsManager.getColor("Text_Color","#ffffff");

    public OutputJTextPane() {

        StyleConstants.setBold(attributeSet, true);
        StyleConstants.setForeground(attributeSet, Color.BLACK);
        setFont(new Font("FS Sinclair", Font.BOLD, 12)); //Swiss 721 Extended
        setOpaque(false);
        setEditable(false);


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

        setupTooltipUI();

        addMouseMotionListener(new HoverHandler());
        addMouseListener(new ClickHandler());


    }

    public void updateText(){
        StyledDocument doc = getStyledDocument();
        //StyleConstants.setBackground(attributeSet, Color.ORANGE);

        if(!getText().isEmpty()){
            setText("");
        }

        try {
            doc.insertString(doc.getLength(), RandomLoadOut.result(), attributeSet); //moved add hover to button panel
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
        hoverMessages.put(word, message);
        scanForHoverRanges();
    }

    private void scanForHoverRanges() {
        hoverRanges.clear();
        String text = getText();
        for (String word : hoverMessages.keySet()) {
            int index = 0;
            while ((index = text.indexOf(word, index)) != -1) {
                hoverRanges.add(new int[]{index, index + word.length(), word.hashCode()});
                index += word.length();
            }
        }
    }

    private void setupTooltipUI() {
        tooltipWindow.setBackground(new Color(0, 0, 0, 0));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        panel.setLayout(new GridBagLayout());

        tooltipLabel.setForeground(hoverColor);
        tooltipLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(tooltipLabel);

        tooltipWindow.add(panel);
        tooltipLabel.addPropertyChangeListener("text", evt -> {
            FontMetrics fm = tooltipLabel.getFontMetrics(tooltipLabel.getFont());
            int textWidth = fm.stringWidth(tooltipLabel.getText());
            int textHeight = fm.getHeight();

            int paddingW = 20;
            int paddingH = 14;

            tooltipWindow.setSize(textWidth + paddingW, textHeight + paddingH);
            tooltipWindow.pack();
        });

        tooltipWindow.pack();
    }
    private class HoverHandler extends MouseMotionAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
            int pos = viewToModel2D(e.getPoint());
            StyledDocument doc = getStyledDocument();

            boolean hovering = false;
            for (int[] range : hoverRanges) {
                if (pos >= range[0] && pos < range[1]) {

                    String word = getText().substring(range[0], range[1]);
                    String msg = hoverMessages.get(word);
                    doc.setCharacterAttributes(range[0], range[1] - range[0], hoverStyle, true);
                    tooltipLabel.setText(msg);

                    Point p = e.getLocationOnScreen();
                    tooltipWindow.setLocation(p.x + 12, p.y + 18);
                    tooltipWindow.setVisible(true);
                    putClientProperty("hoverCursor", true);
                    hovering = true;
                } else {
                    doc.setCharacterAttributes(range[0], range[1] - range[0], normalStyle, true);
                }
            }

            if (!hovering) {
                tooltipWindow.setVisible(false);
                putClientProperty("hoverCursor", false);
            }
        }
    }

    private class ClickHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            int pos = viewToModel2D(e.getPoint());
            for (int[] range : hoverRanges) {
                if (pos >= range[0] && pos < range[1]) {
                    String word = getText().substring(range[0], range[1]);

                    //String url = "https://helldivers.fandom.com/wiki/"+word;
                    String url = "https://helldivers.wiki.gg/wiki/"+word;
                    openURL(url.replace(" ","_"));
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
