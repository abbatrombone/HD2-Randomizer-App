package me.abbatrombone.traz;

import me.abbatrombone.traz.Panels.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class HDApp {
    private final JFrame frame = new JFrame();
    private final JTabbedPane tabPanel = makeTabPane();
    private final MainPanel mainPanel = new MainPanel();
    private final GearPanel gearPanel = new GearPanel();
    private final RulesPanel rulesPanel = new RulesPanel();
    JPanel cards;

    public HDApp() {
        initComponents();
    }
    private void initComponents() {

        frame.getContentPane().setBackground(new Color(51,51,51));
        frame.setTitle("Helldivers 2 Randomizer");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1042, 540);

        tabPanel.setBackground(new Color(51,51,51));
        tabPanel.setBackground(new Color(51,51,51));
        tabPanel.setForeground(Color.WHITE);
        tabPanel.setBorder(null);

        JPanel randomizerPage = mainPanel.getMainPanel();
        tabPanel.addTab("Randomizer", randomizerPage);

        JPanel gearPage = gearPanel.getPanel();
        tabPanel.addTab("Gear Information", gearPage);

        JScrollPane rulePage = rulesPanel.getjScrollPane();
        tabPanel.addTab("Randomzier Rules", rulePage);

        frame.add(tabPanel);
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
}
