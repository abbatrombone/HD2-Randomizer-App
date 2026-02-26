package me.abbatrombone.traz.Panels;

import me.abbatrombone.traz.CustomComponents.CustomButton;
import me.abbatrombone.traz.CustomComponents.OutputJTextPane;
import me.abbatrombone.traz.Managers.GlobalKeyboardManager;
import me.abbatrombone.traz.Managers.SettingsManager;
import me.abbatrombone.traz.Panels.ButtonActions.SquadRandomizer;
import me.abbatrombone.traz.Panels.ButtonActions.SquadSemiRandomizer;

import javax.swing.*;
import java.awt.*;



public class SquadRandomPanel {
    private final JPanel panel = new JPanel();
    private final JLabel topLabel = new JLabel("Enemy: Difficulty");
    private final SquadRandomizer squadRandomizer = new SquadRandomizer();
    private final SquadSemiRandomizer squadSemiRandomizer = new SquadSemiRandomizer();

    private final OutputJTextPane squadMember1;
    private final OutputJTextPane squadMember2;
    private final OutputJTextPane squadMember3;
    private final OutputJTextPane squadMember4;

    private static final SelectBondsPanel warbondsPanel = new SelectBondsPanel();

    public SquadRandomPanel(GlobalKeyboardManager g){

        squadMember1 = new OutputJTextPane(g);
        squadMember2 = new OutputJTextPane(g);
        squadMember3 = new OutputJTextPane(g);
        squadMember4 = new OutputJTextPane(g);

        squadMember1.setOpaque(true);
        squadMember2.setOpaque(true);
        squadMember3.setOpaque(true);
        squadMember4.setOpaque(true);

        CustomButton randomizeButton = makeRandomizerButton();
        CustomButton semiRandomizeButton = makeSemiRandomizerButton();
        CustomButton clearButton = makeClearButton();

        GroupLayout squadPanelLayout = new GroupLayout(panel);
        squadMember1.setText("1");
        squadMember2.setText("2");
        squadMember3.setText("3");
        squadMember4.setText("4");

        squadPanelLayout.setHorizontalGroup(
                squadPanelLayout.createSequentialGroup()
                        .addGroup(squadPanelLayout.createParallelGroup()
                                .addGroup(squadPanelLayout.createParallelGroup()
                                        .addComponent(topLabel)
                                )
                                .addGroup(squadPanelLayout.createSequentialGroup()
                                        .addComponent(squadMember1,350,350,350)
                                        .addGap(10)
                                        .addComponent(squadMember2,350,350,350)
                                )
                                .addGroup(squadPanelLayout.createSequentialGroup()
                                        .addComponent(squadMember3,350,350,350)
                                        .addGap(10)
                                        .addComponent(squadMember4,350,350,350)
                                )
                                .addGroup(squadPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGap(710)
                                        .addComponent(randomizeButton,100,100,100)
                                        .addComponent(semiRandomizeButton,150,150,150)
                                        .addComponent(clearButton,100,100,100)
                                )
                        )
                        .addComponent(warbondsPanel.getJScrollPane())
        );

        squadPanelLayout.setVerticalGroup(
                squadPanelLayout.createParallelGroup()
                        .addGroup(squadPanelLayout.createSequentialGroup()
                                .addComponent(topLabel)
                                .addGroup(squadPanelLayout.createParallelGroup()
                                        .addGroup(squadPanelLayout.createSequentialGroup()
                                                .addComponent(squadMember1,200,200,200)
                                                .addGap(10)
                                                .addComponent(squadMember3,200,200,200)
                                        )
                                        .addGap(10)
                                        .addGroup(squadPanelLayout.createSequentialGroup()
                                                .addComponent(squadMember2,200,200,200)
                                                .addGap(10)
                                                .addComponent(squadMember4,200,200,200)
                                        )
                                )
                                .addGroup(squadPanelLayout.createSequentialGroup()
                                        .addComponent(randomizeButton,30,30,30)
                                        .addComponent(semiRandomizeButton,30,30,30)
                                        .addComponent(clearButton,25,25,25)
                                )
                        )
                        .addComponent(warbondsPanel.getJScrollPane())

        );
        panel.setLayout(squadPanelLayout);
    }

    private CustomButton makeRandomizerButton() {
        SettingsManager settingsManager = new SettingsManager();
        Color fgColor = settingsManager.getColor("Text_Color", "#ffffff");
        CustomButton randomizeButton = new CustomButton("Randomize",fgColor);

        randomizeButton.addActionListener(e -> {
            squadRandomizer.createRunData();
            String enemy = squadRandomizer.getEnemyType();

            topLabel.setText(enemy + ": " + SquadRandomizer.getDiff());

            squadMember1.setText(squadRandomizer.makeSqaudtext(1));
            squadMember1.setBackgroundImage(enemy);
            squadRandomizer.shuffleStrats();

            squadMember2.setText(squadRandomizer.makeSqaudtext(2));
            squadMember2.setBackgroundImage(enemy);
            squadRandomizer.shuffleStrats();

            squadMember3.setText(squadRandomizer.makeSqaudtext(3));
            squadMember3.setBackgroundImage(enemy);
            squadRandomizer.shuffleStrats();

            squadMember4.setText(squadRandomizer.makeSqaudtext(4));
            squadMember4.setBackgroundImage(enemy);
        });
        return randomizeButton;
    }
    private CustomButton makeSemiRandomizerButton() {
        SettingsManager settingsManager = new SettingsManager();
        Color fgColor = settingsManager.getColor("Text_Color", "#ffffff");
        CustomButton semirandomizeButton = new CustomButton("Semi-Randomize",fgColor);

        semirandomizeButton.addActionListener(e -> {
            squadSemiRandomizer.createRunData();
            String enemy = squadSemiRandomizer.getEnemyType();

            topLabel.setText(enemy + ": " + squadSemiRandomizer.getDiff());

            squadMember1.setText(squadSemiRandomizer.makeSqaudtext(1));
            squadMember1.setBackgroundImage(enemy);

            squadMember2.setText(squadSemiRandomizer.makeSqaudtext(2));
            squadMember2.setBackgroundImage(enemy);

            squadMember3.setText(squadSemiRandomizer.makeSqaudtext(3));
            squadMember3.setBackgroundImage(enemy);

            squadMember4.setText(squadSemiRandomizer.makeSqaudtext(4));
            squadMember4.setBackgroundImage(enemy);
        });
        return semirandomizeButton;
    }
    private CustomButton makeClearButton() {
        SettingsManager settingsManager = new SettingsManager();
        Color fgColor = settingsManager.getColor("Text_Color", "#ffffff");
        CustomButton clearButton = new CustomButton("Clear",fgColor);

        clearButton.addActionListener(e -> {

            topLabel.setText(squadRandomizer.getEnemyType() + ": " + SquadRandomizer.getDiff());

            squadMember1.setText("1");
            squadMember1.setBackgroundImage("Super_Earth_Flag");

            squadMember2.setText("2");
            squadMember2.setBackgroundImage("Super_Earth_Flag");

            squadMember3.setText("3");
            squadMember3.setBackgroundImage("Super_Earth_Flag");

            squadMember4.setText("4");
            squadMember4.setBackgroundImage("Super_Earth_Flag");
        });
        return clearButton;
    }

    public JPanel getPanel() {
        return panel;
    }
    public JLabel getLabel() {
        return topLabel;
    }
    public void setLabel(String s) {
        topLabel.setText(s);
    }
    public OutputJTextPane getSquadMember1() {
        return squadMember1;
    }

    public OutputJTextPane getSquadMember2() {
        return squadMember2;
    }

    public OutputJTextPane getSquadMember3() {
        return squadMember3;
    }

    public OutputJTextPane getSquadMember4() {
        return squadMember4;
    }

    public static SelectBondsPanel getWarbondsPanel() {
        return warbondsPanel;
    }

}
