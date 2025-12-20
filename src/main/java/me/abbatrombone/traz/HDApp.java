package me.abbatrombone.traz;

import me.abbatrombone.traz.Panels.*;
import javax.swing.*;

public class HDApp {
    private final JFrame frame = new JFrame();

    private final JTabbedPane tabPanel = new JTabbedPane();
    private final MainPanel mainPanel = new MainPanel();
    private final GearPanel gearPanel = new GearPanel();
    private final RulesPanel rulesPanel = new RulesPanel();

    public HDApp() {
        initComponents();
    }
    private void initComponents() {

        frame.setTitle("Helldivers 2 Randomizer");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1042, 540);

        JPanel randomizerPage = mainPanel.getMainPanel();
        tabPanel.addTab("Randomizer", randomizerPage);

        JPanel gearPage = gearPanel.getPanel();
        tabPanel.addTab("Gear Information", gearPage);

        JScrollPane rulePage = rulesPanel.getjScrollPane();
        tabPanel.addTab("Randomzier Rules", rulePage);

        frame.add(tabPanel);
        frame.setVisible(true);
    }
}
