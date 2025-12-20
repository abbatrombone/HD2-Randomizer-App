package me.abbatrombone.traz.Panels;

import me.abbatrombone.traz.Challenges;
import me.abbatrombone.traz.CustomComponents.OutputJTextPane;
import me.abbatrombone.traz.SemiRandomLoadOut;
import me.abbatrombone.traz.Tips;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ButtonPanel {

    private JPanel panel = new JPanel();
    private JButton random = new JButton();
    private JButton semirandom = new JButton();
    private JButton clear = new JButton();
    private JButton challenges = new JButton();
    private JButton tips = new JButton();
    private OutputJTextPane output;

    public ButtonPanel(OutputJTextPane output){
        this.output = output;


        random.setFont(new Font("Segoe UI", Font.BOLD + Font.ITALIC, 14)); // NOI18N
        random.setText("Full Random");
        random.addActionListener(this::randomButtonActionPerformed);

        semirandom.setFont(new Font("Segoe UI", Font.BOLD + Font.ITALIC, 14)); // NOI18N
        semirandom.setText("Semi Random");
        semirandom.addActionListener(this::semiRandomButtonActionPerformed);

        clear.setFont(new Font("Segoe UI", Font.BOLD + Font.ITALIC, 14)); // NOI18N
        clear.setText("Clear");
        clear.addActionListener(this::clearButtonActionPerformed);

        challenges.setFont(new Font("Segoe UI", Font.BOLD + Font.ITALIC, 14)); // NOI18N
        challenges.setText("Challenges");
        challenges.addActionListener(this::challengeButtonActionPerformed);

        tips.setFont(new Font("Segoe UI", Font.BOLD + Font.ITALIC, 14));
        tips.setText("Tips");
        tips.addActionListener(this::tipButtonActionPerformed);

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
    }

    private void clearButtonActionPerformed(ActionEvent evt) {
        output.setText("");
        output.updateImage("super Earth");
    }

    private void semiRandomButtonActionPerformed(ActionEvent evt) {
        output.setText(SemiRandomLoadOut.result());
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
