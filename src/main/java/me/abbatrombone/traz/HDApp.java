package me.abbatrombone.traz;

import me.abbatrombone.traz.CustomComponents.CustomCursor;
import me.abbatrombone.traz.CustomComponents.TabPane.MyComponent;
import me.abbatrombone.traz.CustomComponents.TabPane.MyTabbedPane;
import me.abbatrombone.traz.JSONTools.JSONCheckboxes;
import me.abbatrombone.traz.Managers.CursorManager;
import me.abbatrombone.traz.Managers.GlobalKeyboardManager;
import me.abbatrombone.traz.Managers.SettingsManager;
import me.abbatrombone.traz.Panels.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HDApp {
    private final GlobalKeyboardManager g = new GlobalKeyboardManager();

    private final JFrame frame = new JFrame();
    private final MainPanel mainPanel = new MainPanel(g);
    private final SettingsPanel settingsPanel = new SettingsPanel();
    private final RulesPanel rulesPanel = new RulesPanel();
    private final IssuesPanel issuesPanel = new IssuesPanel();
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final StatsPanel statsPanel = new StatsPanel();

    private static final Logger logger = Logger.getLogger(HDApp.class.getName());
    private static final SettingsManager settingsManager = new SettingsManager();

    private final CustomCursor cursor = new CustomCursor();
    private Cursor arrowCursor = getNormalCursor();
    private Cursor arrowHandCursor = getHoverCursor();

    public HDApp() {
        initComponents();
    }
    private void initComponents() {
        frame.getContentPane().setBackground(new Color(51,51,51));
        frame.setTitle("Helldivers 2 Randomizer");
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JSONCheckboxes saveCheckboxes = new JSONCheckboxes(MainPanel.getSelectBondsPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        frame.setSize(1042, 575);

        CursorManager cursorManager = new CursorManager(
                this::getNormalCursor,
                this::getHoverCursor
        );

        toolkit.addAWTEventListener(
            e -> {
            if (e instanceof MouseEvent me && me.getID() == MouseEvent.MOUSE_MOVED) {
                cursorManager.mouseMoved(me);
            }
                },
            AWTEvent.MOUSE_MOTION_EVENT_MASK
        );

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/HD2App.png")));

        MyTabbedPane tabPanelz = new MyTabbedPane(new Dimension(1042, 540));
        tabPanelz.setBackground(new Color(51,51,51));
        tabPanelz.setBackground(new Color(51,51,51));
        tabPanelz.setForeground(Color.WHITE);
        tabPanelz.setBorder(null);
        tabPanelz.setCursor(arrowHandCursor);

        SquadRandomPanel squadPanel = new SquadRandomPanel(g);

        JPanel randomizerPage = mainPanel.getMainPanel();
        JPanel squadPage = squadPanel.getPanel();
        JScrollPane rulePage = rulesPanel.getjScrollPane();
        JPanel settingsPage = settingsPanel.getPanel();
        JPanel issuesPage = IssuesPanel.getPanel();
        JPanel statsPage = statsPanel;

        tabPanelz.addTab(randomizerPage, new MyComponent("Randomizer" ));
        tabPanelz.addTab(squadPage, new MyComponent("Squad"));
        tabPanelz.addTab(rulePage, new MyComponent("Randomzier Rules" ));
        tabPanelz.addTab(statsPage, new MyComponent("Stats"));
        tabPanelz.addTab(settingsPage, new MyComponent("Settings"));
        tabPanelz.addTab(issuesPage, new MyComponent("Report Issue"));

        frame.add(tabPanelz);
        frame.setIconImage(icon.getImage());
        frame.setVisible(true);
    }
    private JTabbedPane makeTabPane(){

        //For some reason UIManager needs to be done before making the object
        UIManager.put("TabbedPane.selected", Color.GRAY);
        UIManager.put("TabbedPane.hoverColor", Color.YELLOW);
        UIManager.put("TabbedPane.hoverForeground", Color.YELLOW);
        UIManager.put("TabbedPane.selectedInsets", Color.WHITE);
        UIManager.put("TabbedPane.tabSelectedInsets", Color.WHITE);
        UIManager.put("TabbedPane.underlineColor", Color.WHITE);
        UIManager.put("TabbedPane.tabSelectionHeight", 5);
        UIManager.put("TabbedPane.cardTabSelectionHeight", 5);
        return new JTabbedPane();
    }
    private Cursor getHoverCursor() {
        String setting = settingsManager.getCursorSettings();

        return switch (setting) {
            case "std" -> Cursor.getDefaultCursor();
            case "hd2" -> cursor.create(CustomCursor.Type.CUSTOM_HAND_ARROW);
            default -> loadCursorSafely(setting);
        };
    }
    private Cursor getNormalCursor() {
        String setting = settingsManager.getCursorSettings();
        return switch (settingsManager.getCursorSettings()) {
            case "std" -> arrowCursor = Cursor.getDefaultCursor();
            case "hd2" -> arrowCursor = cursor.create(CustomCursor.Type.CUSTOM_ARROW);
            default -> loadCursorSafely(setting);
        };
    }
    private Cursor loadCursorSafely(String pathStr) {
        try {
            if (pathStr == null || pathStr.isBlank()) {return Cursor.getDefaultCursor();}

            Path path = Paths.get(pathStr);
            if (!Files.isRegularFile(path)) {return Cursor.getDefaultCursor();}

            if (!Files.isRegularFile(path)) {return Cursor.getDefaultCursor();}

            BufferedImage source = ImageIO.read(path.toFile());
            if (source == null) {return Cursor.getDefaultCursor();}

            Dimension bestSize = toolkit.getBestCursorSize(32, 32);

            int width  = bestSize.width  > 0 ? bestSize.width  : 32;
            int height = bestSize.height > 0 ? bestSize.height : 32;

            GraphicsConfiguration gc =
                    GraphicsEnvironment.getLocalGraphicsEnvironment()
                            .getDefaultScreenDevice()
                            .getDefaultConfiguration();

            BufferedImage cursorImage = gc.createCompatibleImage(width, height, Transparency.TRANSLUCENT);

            Graphics2D g2d = cursorImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(source, 0, 0, width, height, null);
            g2d.dispose();

            Point hotspot = new Point(0, 0);

            return toolkit.createCustomCursor(cursorImage, hotspot, "userCursor");

        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to load custom cursor", e);
            return Cursor.getDefaultCursor();
        }
    }
}
