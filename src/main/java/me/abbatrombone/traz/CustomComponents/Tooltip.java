package me.abbatrombone.traz.CustomComponents;

import me.abbatrombone.traz.Managers.SettingsManager;

import javax.swing.*;
import java.awt.*;

public class Tooltip extends JWindow{
    private JLabel tooltipLabel = new JLabel();
    private static final SettingsManager settingsManager = new SettingsManager();
    private final Color hoverColor = settingsManager.getColor("Label_Color","#ffffff");

    Tooltip(){
        setBackground(new Color(0, 0, 0, 0));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        panel.setLayout(new GridBagLayout());

        tooltipLabel.setForeground(hoverColor);
        tooltipLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(tooltipLabel);

        add(panel);
        tooltipLabel.addPropertyChangeListener("text", evt -> {
            FontMetrics fm = tooltipLabel.getFontMetrics(tooltipLabel.getFont());
            int textWidth = fm.stringWidth(tooltipLabel.getText());
            int textHeight = fm.getHeight();

            int paddingW = 20;
            int paddingH = 14;

            setSize(textWidth + paddingW, textHeight + paddingH);
            setAlwaysOnTop(true);
            pack();
        });

        pack();
    }
    public JLabel getTooltipLabel() {
        return tooltipLabel;
    }
}
