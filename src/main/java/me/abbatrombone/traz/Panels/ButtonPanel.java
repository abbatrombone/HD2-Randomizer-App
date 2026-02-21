package me.abbatrombone.traz.Panels;

import me.abbatrombone.traz.CustomComponents.CustomButton;
import me.abbatrombone.traz.Managers.SettingsManager;
import me.abbatrombone.traz.Managers.SoundManager;
import me.abbatrombone.traz.Panels.ButtonActions.Challenges;
import me.abbatrombone.traz.CustomComponents.OutputJTextPane;
import me.abbatrombone.traz.Panels.ButtonActions.RandomLoadOut;
import me.abbatrombone.traz.Panels.ButtonActions.SemiRandomLoadOut;
import me.abbatrombone.traz.Panels.ButtonActions.Tips;
import me.abbatrombone.traz.Utilities.StringParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ButtonPanel {

    private final JPanel panel = new JPanel();
    private static final SettingsManager settingsManager = new SettingsManager();
    private final Color fgColor = settingsManager.getColor("Label_Color","#ffffff");
    private final CustomButton challenges = new CustomButton("Challenges",fgColor);
    private final CustomButton tips = new CustomButton("Tips",fgColor);
    private final OutputJTextPane output;
    private static final Logger logger = Logger.getLogger(ButtonPanel.class.getName());


    public ButtonPanel(OutputJTextPane output){
        this.output = output;

        Color backgroundColor = new Color(51, 51, 51);
        panel.setBackground(backgroundColor);

        CustomButton random = new CustomButton("Full Random", fgColor);
        random.addActionListener(this::randomButtonActionPerformed);
        random.setHoverWord("Provides random weapons, armor, and stratagems based on selected warbonds");
        random.setName("Random");

        CustomButton semirandom = new CustomButton("Semi Random", fgColor);
        semirandom.addActionListener(this::semiRandomButtonActionPerformed);
        semirandom.setHoverWord("Provides random weapons, armor, and stratagems based on selected warbonds. Stratagmes will have a more even mix");
        semirandom.setName("SemiRandom");

        CustomButton clear = new CustomButton("Clear", fgColor);
        clear.addActionListener(this::clearButtonActionPerformed);
        clear.setHoverWord("Clears text panel");

        challenges.addActionListener(this::challengeButtonActionPerformed);
        challenges.setHoverWord("Provides extra challenges for a run");

        tips.addActionListener(this::tipButtonActionPerformed);
        tips.setHoverWord("Tips provided by your local democracy officer");

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
    private void playSound(){
        String[] s ={"Enter Fray.wav","For Freedom.wav","Fuel Liberties Future.wav","Heroic Intervention.wav","Liberation.wav","Mission Coord.wav","Near Operational Completion.wav","Sacrifice.wav","Small Moon.wav","War Rages On.wav"};
        SoundManager.playSound(s[ThreadLocalRandom.current().nextInt(s.length)]);
    }

    private void randomButtonActionPerformed(ActionEvent evt) {
        playSound();
        List<String> selectedBonds = RandomLoadOut.getSelectedBondNames();

        new SwingWorker<String, Void>() {

            @Override
            protected String doInBackground() {
                return RandomLoadOut.result(selectedBonds);
            }

            @Override
            protected void done() {
                try {
                    output.setLastButton("Random");
                    String resultText = get();
                    output.updateText(resultText);

                    StringParser p = new StringParser();

                    output.addHoverWord(
                            p.parsePrimaryName(RandomLoadOut.getPrimaryWeapon()),
                            RandomLoadOut.getPrimaryWeapon()
                    );
                    output.addHoverWord(
                            p.parseSecondaryName(RandomLoadOut.getSecondaryWeapon()),
                            RandomLoadOut.getSecondaryWeapon()
                    );
                    output.addHoverWord(
                            p.parseThrowable(RandomLoadOut.getThrowable()),
                            RandomLoadOut.getThrowable()
                    );
                    output.rebuildHoverRanges();

                } catch (Exception e) {
                    logger.log(Level.WARNING,e.toString());
                }
            }

        }.execute();
    }

    private void clearButtonActionPerformed(ActionEvent evt) {
        output.setText("");
        output.updateImage("super Earth");
    }

    private void semiRandomButtonActionPerformed(ActionEvent evt) {
        //output.setText(SemiRandomLoadOut.result());
        output.updateText(SemiRandomLoadOut.result());
        output.setLastButton("SemiRandom");

        StringParser p = new StringParser();

        output.addHoverWord(p.parseSecondaryName(SemiRandomLoadOut.getSecondaryWeapon()),SemiRandomLoadOut.getSecondaryWeapon());
        output.addHoverWord(p.parseThrowable(SemiRandomLoadOut.getThrowable()),SemiRandomLoadOut.getThrowable());
        output.rebuildHoverRanges();
    }
    private void challengeButtonActionPerformed(ActionEvent evt) {
        UIManager.put("OptionPane.messageForeground", fgColor);
        JOptionPane.showMessageDialog(challenges, Challenges.challenges());
    }
    private void tipButtonActionPerformed(ActionEvent evt) {
        UIManager.put("OptionPane.messageForeground", fgColor);
        JOptionPane.showMessageDialog(tips, Tips.tips());
    }

    public JPanel getPanel() {
        return panel;
    }
}
