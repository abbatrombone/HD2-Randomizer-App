package me.abbatrombone.traz.Panels;

import me.abbatrombone.traz.CustomComponents.OutputJTextPane;
import me.abbatrombone.traz.Managers.SettingsManager;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class MainPanel {

    private final JPanel mainPanel = new JPanel();
    private final LabelPanel labelPanel = new LabelPanel();
    private final JPanel labelPanelFull = new JPanel();
    private static final OutputJTextPane output = new OutputJTextPane();
    private static final SelectBondsPanel selectBondsPanel = new SelectBondsPanel();
    private final ButtonPanel buttonPanel = new ButtonPanel(output);
    private final Color bgColor = new Color(51,51,51);

    private static final Logger logger = Logger.getLogger(MainPanel.class.getName());
    private static final SettingsManager settingsManager = new SettingsManager();


    public MainPanel(){
        output.setOpaque(true);
        labelPanelFull.setBackground(bgColor);
        mainPanel.setBackground(bgColor);

        GroupLayout mainPanelLayout = new GroupLayout(labelPanelFull);

        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(labelPanel.getPanel())
                        )
        );

        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(labelPanel.getPanel())
                        )
        );

        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup()
                                                .addComponent(output,647,647,647)
                                                .addComponent(buttonPanel.getPanel())
                                        )
                                        .addComponent(selectBondsPanel.getJScrollPane())
                        )
                        .addComponent(labelPanelFull)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup() //GroupLayout.Alignment.LEADING
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(labelPanelFull, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup() //GroupLayout.Alignment.LEADING
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(output, 350,350,350)
                                                .addComponent(buttonPanel.getPanel())
                                        )
                                        .addComponent(selectBondsPanel.getJScrollPane(),430,430,430)
                                        )
                        )
        );
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public LabelPanel getLabelPanel() {
        return labelPanel;
    }

    public JPanel getLabelPanelFull() {
        return labelPanelFull;
    }

    public OutputJTextPane getOutput() {
        return output;
    }

    public static SelectBondsPanel getSelectBondsPanel() {
        return selectBondsPanel;
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }
    public static OutputJTextPane getOutputTextPane(){
        return output;
    }
}
