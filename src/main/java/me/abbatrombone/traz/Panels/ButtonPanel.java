package me.abbatrombone.traz.Panels;

import me.abbatrombone.traz.CustomComponents.CustomButton;
import me.abbatrombone.traz.Panels.ButtonActions.Challenges;
import me.abbatrombone.traz.CustomComponents.OutputJTextPane;
import me.abbatrombone.traz.Panels.ButtonActions.RandomLoadOut;
import me.abbatrombone.traz.Panels.ButtonActions.SemiRandomLoadOut;
import me.abbatrombone.traz.Panels.ButtonActions.Tips;
import me.abbatrombone.traz.Utilities.StringParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ButtonPanel {

    private final JPanel panel = new JPanel();
    private final CustomButton random = new CustomButton("Full Random",Color.WHITE);
    private final CustomButton semirandom = new CustomButton("Semi Random",Color.WHITE);
    private final CustomButton clear = new CustomButton("Clear",Color.WHITE);
    private final CustomButton challenges = new CustomButton("Challenges",Color.WHITE);
    private final CustomButton tips = new CustomButton("Tips",Color.WHITE);
    private final OutputJTextPane output;
    private final Color backgroundColor = new Color(51, 51, 51);

    public ButtonPanel(OutputJTextPane output){
        this.output = output;

        panel.setBackground(new Color(51, 51, 51));

        random.addActionListener(this::randomButtonActionPerformed);
        random.addHoverWord("Provides random weapons, armor, and stratagems based on selected warbonds");

        semirandom.addActionListener(this::semiRandomButtonActionPerformed);
        semirandom.addHoverWord("Provides random weapons, armor, and stratagems based on selected warbonds. Stratagmes will have a more even mix");

        clear.addActionListener(this::clearButtonActionPerformed);
        clear.addHoverWord("Clears text panel");

        challenges.addActionListener(this::challengeButtonActionPerformed);
        challenges.addHoverWord("Provides extra challenges for a run");

        tips.addActionListener(this::tipButtonActionPerformed);
        tips.addHoverWord("Tips provided by your local democracy officer");

        panel.setMaximumSize(new Dimension(200,300));
        panel.setPreferredSize(new Dimension(200,300));

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(challenges)
                                .addComponent(tips)
                        )
                        .addGap(380)//this puts it on the edge there has to be a better way to do this
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(random)
                                .addComponent(semirandom)
                                .addComponent(clear)
                        )
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(challenges)
                                .addComponent(tips)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(random)
                                .addComponent(semirandom)
                                .addComponent(clear)
                        )
        );
    }

    private void randomButtonActionPerformed(ActionEvent evt) {
        output.updateText();
        StringParser p = new StringParser();
         output.addHoverWord(p.parsePrimaryName(RandomLoadOut.getPrimaryWeapon()),RandomLoadOut.getPrimaryWeapon());
         output.addHoverWord(p.parseSecondaryName(RandomLoadOut.getSecondaryWeapon()),RandomLoadOut.getSecondaryWeapon());
         output.addHoverWord(p.parseThrowable(RandomLoadOut.getThrowable()),RandomLoadOut.getThrowable());
    }

    private void clearButtonActionPerformed(ActionEvent evt) {
        output.setText("");
        output.updateImage("super Earth");
    }

    private void semiRandomButtonActionPerformed(ActionEvent evt) {
        output.setText(SemiRandomLoadOut.result());

        StringParser p = new StringParser();
        System.out.println(SemiRandomLoadOut.getSecondaryWeapon());
        output.addHoverWord(p.parseSecondaryName(SemiRandomLoadOut.getSecondaryWeapon()),SemiRandomLoadOut.getSecondaryWeapon());
        output.addHoverWord(p.parseThrowable(SemiRandomLoadOut.getThrowable()),SemiRandomLoadOut.getThrowable());
    }
    private void challengeButtonActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(challenges, Challenges.challenges());
    }
    private void tipButtonActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(tips, Tips.tips());
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getRandom() {
        return random;
    }

    public JButton getSemirandom() {
        return semirandom;
    }

    public JButton getClear() {
        return clear;
    }

    public JButton getChallenges() {
        return challenges;
    }

    public JButton getTips() {
        return tips;
    }
}
