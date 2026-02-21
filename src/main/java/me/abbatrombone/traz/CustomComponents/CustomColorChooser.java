package me.abbatrombone.traz.CustomComponents;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class CustomColorChooser {
    private JColorChooser chooser;
    private static JDialog dialog;

    public CustomColorChooser(Component parent){
        Color bg = new Color(51, 51, 51);
        Color fg = Color.WHITE;

        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        JButton reset = new JButton("Reset");

        ok.addActionListener(e -> {dialog.dispose();});
        cancel.addActionListener(e -> dialog.dispose());
        reset.addActionListener(e -> chooser.setColor(Color.WHITE));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.setBackground(bg);
        buttons.add(reset);
        buttons.add(cancel);
        buttons.add(ok);

        for (JButton b : new JButton[]{ok, cancel, reset}) {
            b.setBackground(new Color(70,70,70));
            b.setForeground(Color.WHITE);
            b.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
            b.setFocusPainted(false);
        }

        Window owner = SwingUtilities.getWindowAncestor(parent);
        dialog = new JDialog(owner, "Select Color", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.getContentPane().setBackground(bg);
        dialog.getRootPane().setBackground(bg);

        UIManager.put("JColorChooser.background", bg);
        UIManager.put("Panel.background", bg);
        //UIManager.put("Panel.border", BorderFactory.createLineBorder(Color.YELLOW));
        UIManager.put("Label.foreground", fg);
        UIManager.put("Button.background", new Color(70, 70, 70));
        UIManager.put("Button.foreground", fg);
        UIManager.put("Button.border", BorderFactory.createLineBorder(Color.YELLOW));
        UIManager.put("List.background", new Color(45, 45, 45));
        UIManager.put("List.foreground", fg);
        UIManager.put("List.border", BorderFactory.createLineBorder(Color.YELLOW));
        UIManager.put("List.selectionBackground", new Color(75, 110, 175));
        UIManager.put("List.selectionForeground", fg);
        UIManager.put("OptionPane.background", bg);
        UIManager.put("OptionPane.messageArea.background", bg);
        UIManager.put("OptionPane.buttonArea.background", bg);
        //UIManager.put("OptionPane.border", BorderFactory.createLineBorder(Color.YELLOW));
        UIManager.put("RootPane.background", bg);

        UIManager.put("TextField.background", bg);
        UIManager.put("TextField.foreground", fg);
        UIManager.put("TextField.caretForeground", fg);
        UIManager.put("TextField.border", BorderFactory.createLineBorder(Color.YELLOW));

        UIManager.put("ComboBox.background", bg);
        UIManager.put("ComboBox.foreground", fg);
        UIManager.put("ComboBox.border", BorderFactory.createLineBorder(Color.YELLOW));
        UIManager.put("ComboBox.selectionBackground", new Color(75, 110, 175));
        UIManager.put("ComboBox.selectionForeground", fg);

        UIManager.put("ComboBox.buttonBackground", bg);
        //UIManager.put("ComboBox.buttonForeground", fg);
        UIManager.put("ComboBox.buttonDarkShadow", Color.WHITE);
        UIManager.put("ComboBox.buttonHighlight", Color.WHITE);
        UIManager.put("ComboBox.buttonShadow", Color.WHITE);
        UIManager.put("ComboBox.arrowButtonBackground", bg);

        UIManager.put("ToolBar.background", bg);
        UIManager.put("ToolBar.foreground", fg);

        UIManager.put("ToggleButton.select", new Color(75, 110, 175));
        UIManager.put("ToggleButton.border", BorderFactory.createLineBorder(Color.YELLOW));
        UIManager.put("ToggleButton.background", new Color(70, 70, 70));
        UIManager.put("ToggleButton.foreground", fg);
        UIManager.put("ToggleButton.select", Color.DARK_GRAY);

        UIManager.put("Table.background", new Color(45, 45, 45));
        UIManager.put("Table.foreground", Color.WHITE);
        UIManager.put("Table.selectionBackground", new Color(75, 110, 175));
        UIManager.put("Table.selectionForeground", Color.WHITE);
        UIManager.put("Table.border", BorderFactory.createLineBorder(Color.YELLOW));

        UIManager.put("TableHeader.background", new Color(51, 51, 51));
        UIManager.put("TableHeader.foreground", Color.WHITE);
        UIManager.put("TableHeader.font", UIManager.getFont("Label.font"));
        UIManager.put("TableHeader.border", BorderFactory.createLineBorder(Color.YELLOW));
        //fix tabs

        UIManager.put("Slider.background", Color.WHITE);
        UIManager.put("Slider.foreground", Color.YELLOW);

        UIManager.put("Slider.trackColor",  Color.WHITE);
        UIManager.put("Slider.thumb", new Color(180, 180, 180));

        UIManager.put("Slider.highlight",  Color.YELLOW);
        UIManager.put("Slider.shadow", new Color(30, 30, 30));

        UIManager.put("RadioButton.background", bg);
        UIManager.put("RadioButton.foreground", fg);
        UIManager.put("RadioButton.select", new Color(75, 110, 175));

        UIManager.put("Panel.background", bg);
        UIManager.put("Label.foreground", fg);

        dialog = new JDialog(owner, "Select Color", Dialog.ModalityType.APPLICATION_MODAL);

        chooser = new JColorChooser();
        chooser.setOpaque(true);
        chooser.setBackground(bg);

        chooser = new JColorChooser();
        chooser.setOpaque(true);
        chooser.setBackground(bg);
        fixSliders(chooser);
        fixChooserButtons(chooser);

        dialog.getContentPane().setBackground(bg);
        dialog.getRootPane().setBackground(bg);

        dialog.pack();
        dialog.setLocationRelativeTo(parent);

    }
    public Color showDialog(Component parent, Color initialColor) {
        final Color[] selected = new Color[1];

        JDialog dialog = JColorChooser.createDialog(
                parent,
                "Select Color",
                true,
                chooser,
                e -> selected[0] = chooser.getColor(),
                null
        );

        dialog.setVisible(true);
        return selected[0];
    }
    public static class DarkSliderUI extends BasicSliderUI {

        public DarkSliderUI(JSlider slider) {
            super(slider);
        }

        @Override
        public void paintTrack(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.GRAY);
            g2.fill(trackRect);
        }

        @Override
        public void paintThumb(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.WHITE);
            g2.fillOval(thumbRect.x, thumbRect.y,
                    thumbRect.width, thumbRect.height);
        }
    }
    private void fixSliders(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JSlider slider) {
                slider.setUI(new DarkSliderUI(slider));
                slider.setOpaque(false);
            }
            if (c instanceof Container child) {
                fixSliders(child);
            }
        }
    }
    private void fixChooserButtons(Container container) {
        for (Component c : container.getComponents()) {

            if (c instanceof JButton btn) {
                btn.setBackground(new Color(70, 70, 70));
                btn.setForeground(Color.WHITE);
                btn.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                btn.setFocusPainted(false);
                btn.setOpaque(true);
            }

            if (c instanceof Container child) {
                fixChooserButtons(child);
            }
        }
    }
}
