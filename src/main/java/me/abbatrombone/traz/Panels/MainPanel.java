package me.abbatrombone.traz.Panels;

import me.abbatrombone.traz.CustomComponents.OutputJTextPane;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class MainPanel {

    private final JPanel mainPanel = new JPanel();
    private static final OutputJTextPane output = new OutputJTextPane();
    private static final SelectBondsPanel selectBondsPanel = new SelectBondsPanel();

    private static final Logger logger = Logger.getLogger(MainPanel.class.getName());

    public MainPanel(){
        output.setOpaque(true);
        JPanel labelPanelFull = new JPanel();
        Color bgColor = new Color(51, 51, 51);
        labelPanelFull.setBackground(bgColor);
        mainPanel.setBackground(bgColor);

        GroupLayout mainPanelLayout = new GroupLayout(labelPanelFull);

        LabelPanel labelPanel = new LabelPanel();
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
        ButtonPanel buttonPanel = new ButtonPanel(output);
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
                                        .addComponent(selectBondsPanel.getJScrollPane(),475,475,475)
                                        )
                        )
        );
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static SelectBondsPanel getSelectBondsPanel() {
        return selectBondsPanel;
    }
    public static OutputJTextPane getOutputTextPane(){
        return output;
    }
}
