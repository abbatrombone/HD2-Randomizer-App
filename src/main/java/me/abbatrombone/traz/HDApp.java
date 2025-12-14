package me.abbatrombone.traz;

import me.abbatrombone.traz.CustomComponents.OutputJTextPane;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HDApp {
    private final JFrame frame = new JFrame();
    private JButton random;
    private JButton semirandom;
    private JButton clear;
    private JButton challenges;
    private JButton tips;
    private JLabel titleLabel;
    private JPanel mainPanel;
    private OutputJTextPane output;

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
        random = new JButton();
        semirandom = new JButton();
        clear = new JButton();
        challenges = new JButton();
        tips = new JButton();
        output = new OutputJTextPane();

        output.setPreferredSize(new Dimension(450, 350));

        mainPanel.setBackground(new Color(51, 51, 51));

        titleLabel.setBackground(new Color(255, 255, 255));
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24)); // NOI18N
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setText("Helldivers 2 Randomizer");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(titleLabel)
                                //.addContainerGap(161, Short.MAX_VALUE))
                        )
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(titleLabel)
                                //.addContainerGap(23, Short.MAX_VALUE))
                        )
        );


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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                //.addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(output, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        )
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                //.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(challenges)
                                        .addComponent(tips)
                                        .addComponent(random)
                                        .addComponent(semirandom)
                                        .addComponent(clear))
                        )
                                //.addGap(15, 5, 5))
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(output, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(challenges)
                                                .addComponent(tips)
                                                .addGap(200)
                                                .addComponent(random)
                                                .addComponent(semirandom)
                                                .addComponent(clear)
                                        ))
                              )
        );

        frame.pack();
        frame.setVisible(true);

    }

    private void randomButtonActionPerformed(ActionEvent evt) {
        StyledDocument doc = output.getStyledDocument();

        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        StyleConstants.setForeground(attributeSet, Color.BLACK);
        //StyleConstants.setBackground(attributeSet, Color.ORANGE);
        if(!output.getText().isEmpty()){
            output.setText("");
        }

        try {
            doc.insertString(doc.getLength(), RandomLoadOut.result(), attributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void clearButtonActionPerformed(ActionEvent evt) {
        output.setText("");
    }

    private void semiRandomButtonActionPerformed(ActionEvent evt) {
        output.setText(SemiRandomLoadOut.result());
    }
    private void challengeButtonActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(challenges,Challenges.challenges());
    }
    private void tipButtonActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(tips,Tips.tips());
    }
}
