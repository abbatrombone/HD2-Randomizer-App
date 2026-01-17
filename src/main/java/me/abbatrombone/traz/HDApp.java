package me.abbatrombone.traz;

import me.abbatrombone.traz.CustomComponents.CustomCursor;
import me.abbatrombone.traz.CustomComponents.TabPane.MyComponent;
import me.abbatrombone.traz.CustomComponents.TabPane.MyTabbedPane;
import me.abbatrombone.traz.Panels.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HDApp {
    private static final Logger logger = Logger.getLogger(HDApp.class.getName());

    private final JFrame frame = new JFrame();
    private final MainPanel mainPanel = new MainPanel();
    private final RulesPanel rulesPanel = new RulesPanel();
    private final SettingsPanel settingsPanel = new SettingsPanel();

    final private Properties settings = new Properties();
    final private File settingsFile = new File("settings.properties");

    public HDApp() {
        logger.info("HDApp Starting");
        //OSManager.storeData("Helldivers2_Randomizer");
        //OSManager.getCacheDirectory();
        //OSManager.getConfigDirectory();
        //OSManager.getLogDirectory();
        loadSettings();
        initComponents();

    }
    private void initComponents() {

        frame.getContentPane().setBackground(new Color(51,51,51));
        frame.setTitle("Helldivers 2 Randomizer");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1042, 540);
        setFrameIcon();

        MyTabbedPane tabPanel = new MyTabbedPane(new Dimension(1042, 540));
        tabPanel.setBackground(new Color(51,51,51));
        tabPanel.setBackground(new Color(51,51,51));
        tabPanel.setForeground(Color.WHITE);
        tabPanel.setBorder(null);

        //settingsPanel.setSettingPanelSize(frame.getPreferredSize().height,frame.getPreferredSize().width);

        JPanel randomizerPage = mainPanel.getMainPanel();
        JScrollPane rulePage = rulesPanel.getjScrollPane();
        JPanel settingsPage = settingsPanel.getPanel();

        tabPanel.addTab(randomizerPage, new MyComponent("Randomizer" ));
        tabPanel.addTab(rulePage, new MyComponent("Randomzier Rules" ));
        //tabPanel.addTab(settingsPage,new MyComponent("Settings"));

        frame.add(tabPanel);
        CustomCursor cursor = new CustomCursor();
        frame.setCursor(cursor.create(CustomCursor.Type.CUSTOM_ARROW));
        frame.setVisible(true);
    }
    private void loadSettings() {
        if (settingsFile.exists()) {
            try (FileInputStream fis = new FileInputStream(settingsFile)) {
                settings.load(fis);
            } catch (IOException e) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error IOException", e);
                JOptionPane.showMessageDialog(null, "An error occurred loading the settings file.",
                        "Read File Error", JOptionPane.ERROR_MESSAGE);
                logger.log(Level.WARNING,"An error occurred loading the settings file.");
            }
        }
    }
    private void setFrameIcon() {
        try {
            BufferedImage icon = ImageIO.read(Objects.requireNonNull(getClass().getResource("/HD2App.png")));

            int targetSize = 128;
            int w = icon.getWidth();
            int h = icon.getHeight();

            float ratio = (float) w / h;
            int newW = ratio >= 1 ? targetSize : (int) (targetSize * ratio);
            int newH = ratio < 1 ? targetSize : (int) (targetSize / ratio);

            Image scaled = icon.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);

            BufferedImage finalIcon = new BufferedImage(targetSize, targetSize, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = finalIcon.createGraphics();

            int x = (targetSize - newW);
            int y = (targetSize - newH) / 2;

            g2.drawImage(scaled, x, y, null);
            g2.dispose();

            frame.setIconImage(finalIcon);

        } catch (IOException e) {
            logger.log(Level.WARNING,"Desktop icon did not load");
        }
    }
}
