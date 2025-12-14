package me.abbatrombone.traz;

import me.abbatrombone.traz.CustomComponents.OutputJTextPane;
import me.abbatrombone.traz.Panels.ButtonPanel;
import me.abbatrombone.traz.Panels.LabelPanel;
import me.abbatrombone.traz.Panels.SelectBondsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HDApp {
    private final JFrame frame = new JFrame();

    private JLabel titleLabel;
    private JPanel mainPanel;
    private OutputJTextPane output = new OutputJTextPane();
    private final LabelPanel labelPanel = new LabelPanel();
    private final SelectBondsPanel selectBondsPanel = new SelectBondsPanel();
    private final ButtonPanel buttonPanel = new ButtonPanel(output);

    public HDApp() {
        initComponents();
    }
    private void initComponents() {

        frame.setTitle("Helldivers 2 Randomizer");
        frame.setMinimumSize(new Dimension(725, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1042, 380);

        mainPanel = new JPanel();
        titleLabel = new JLabel();

        output.setPreferredSize(new Dimension(450, 350));

        mainPanel.setBackground(new Color(51, 51, 51));

        titleLabel.setBackground(new Color(255, 255, 255));
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24)); // NOI18N
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setText("Helldivers 2 Randomizer");

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);

        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(142)
                                .addComponent(labelPanel.getPanel())
                        )
        );

        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(19)
                                .addComponent(labelPanel.getPanel())
                        )
        );

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup()
                                                .addComponent(output)
                                                .addComponent(buttonPanel.getPanel())
                                        )
                                        .addComponent(selectBondsPanel.getJScrollPane())
                        )
                .addComponent(mainPanel)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(output, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(buttonPanel.getPanel())
                                        )
                                        .addComponent(selectBondsPanel.getJScrollPane())
                                        .addGroup(layout.createSequentialGroup()
                                        ))
                        )
        );

        frame.pack();
        frame.setVisible(true);

    }
}
