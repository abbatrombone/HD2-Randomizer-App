package me.abbatrombone.traz.Panels;

import me.abbatrombone.traz.CustomComponents.RulesTextArea;

import javax.swing.*;
import java.awt.*;

public class RulesPanel {

    private final JPanel panel = new JPanel();
    private final JScrollPane jScrollPane = new JScrollPane(panel);

    public RulesPanel(){

        RulesTextArea textArea = new RulesTextArea();

        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(1022, 460));

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(150,200,5,5));

        textArea.setText("""
                1) Once given the order by General Brash that is you must use that load out to the best of your ability.
                    1a) If missing the correct armor do the same armor rating (light, med, heavy) and pick your passive.
                    1b) If missing the correct weapon use the same type of weapon (AR, shotgun, etc) with the same level armor pen if possible.
                    1c) If missing the throwable use a throwable from the same category.
                    1d) If missing a stratagem use one of the same color.
                2) Use the highest number booster, should that one be taken use the next highest.
                3) Click Challenges to make it even harder
                4) Win for Democracy!
                """);
        panel.add(textArea);

        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    public JPanel getPanel() {
        return panel;
    }
    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

}
