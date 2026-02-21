package me.abbatrombone.traz.Panels;

import me.abbatrombone.traz.CustomComponents.IssuesTextArea;
import me.abbatrombone.traz.HDApp;
import me.abbatrombone.traz.Managers.OSManager;
import me.abbatrombone.traz.Managers.SettingsManager;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IssuesPanel {
    private static final JPanel panel = new JPanel();
    private final JScrollPane jScrollPane = new JScrollPane(panel);
    private static final SettingsManager settingsManager = new SettingsManager();
    private static final Logger logger = Logger.getLogger(IssuesPanel.class.getName());

    public IssuesPanel(){
        //reused
        IssuesTextArea textArea = new IssuesTextArea();
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(1022, 490));

        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet linkStyle = new SimpleAttributeSet();
        StyleConstants.setForeground(linkStyle, Color.BLUE);
        StyleConstants.setUnderline(linkStyle, true);
        linkStyle.addAttribute("https://github.com/abbatrombone/HD2-Randomizer-App", "https://github.com/abbatrombone/HD2-Randomizer-App");
        try {
            doc.insertString(doc.getLength(),
                    "1) go to ", null);
            doc.insertString(doc.getLength(),
                    "https://github.com/abbatrombone/HD2-Randomizer-App", linkStyle);
            doc.insertString(doc.getLength(),
                    """
                
                2) Go to the Issues tab
                3) Click "New Issues":
                    Be specific as you can be, and give as much detail and context as you are able.
                    If you see anything in your error logs that can be helpful as well""", null);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }


//        textArea.setLineWrap(true);
//        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(150,200,5,5));
        Color fgColor = settingsManager.getColor("Text_Color", "#ffffff");
        textArea.setForeground(fgColor.equals(Color.WHITE) ? Color.BLACK : fgColor);

        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int pos = textArea.viewToModel2D(e.getPoint());
                StyledDocument doc = textArea.getStyledDocument();
                Element elem = doc.getCharacterElement(pos);
                AttributeSet attrs = elem.getAttributes();

                if (attrs.isDefined("https://github.com/abbatrombone/HD2-Randomizer-App")) {
                    openURL("https://github.com/abbatrombone/HD2-Randomizer-App");
                }
            }
        });

        panel.add(textArea);
        panel.setBackground(new Color(51,51,51));

        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
    public static JPanel getPanel() {
        return panel;
    }
    public JScrollPane getjScrollPane() {
        return jScrollPane;
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
