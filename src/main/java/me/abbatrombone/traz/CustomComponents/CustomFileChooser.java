package me.abbatrombone.traz.CustomComponents;

import me.abbatrombone.traz.CustomComponents.JScrollbarUIComp.ScrollBarUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.io.File;

public class CustomFileChooser {
    private final JFileChooser chooser;
    private static JDialog dialog;

    public CustomFileChooser(Component parent) {
        Color bg = new Color(51, 51, 51);
        Color fg = Color.WHITE;

        Window owner = SwingUtilities.getWindowAncestor(parent);
        dialog = new JDialog(owner, "Select File", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.getContentPane().setBackground(bg);
        dialog.getRootPane().setBackground(bg);

        UIManager.put("FileChooser.background", bg);
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

        dialog = new JDialog(owner, "Select File", Dialog.ModalityType.APPLICATION_MODAL);

        chooser = new JFileChooser();
        fixComboBoxes(chooser);
        applyCustomScrollBar(chooser);
        chooser.setOpaque(true);
        chooser.setBackground(bg);

        dialog.setContentPane(chooser);
        dialog.getContentPane().setBackground(bg);
        dialog.getRootPane().setBackground(bg);

        chooser.addActionListener(e -> {
            dialog.setVisible(false);
            dialog.dispose();
        });

        dialog.pack();
        dialog.setLocationRelativeTo(parent);
    }

    public int showOpenDialog() {
        dialog.setVisible(true);
        return chooser.getDialogType();
    }

    public File getSelectedFile() {
        return chooser.getSelectedFile();
    }

    private void fixComboBoxes(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JComboBox<?> combo) {
                combo.setUI(new WhiteArrowComboBoxUI());
                combo.setBackground(new Color(51, 51, 51));
                combo.setForeground(Color.WHITE);
            }
            if (c instanceof Container cont) {
                fixComboBoxes(cont);
            }
        }
    }
    private void applyCustomScrollBar(Container container) {
        for (Component c : container.getComponents()) {

            if (c instanceof JScrollPane scrollPane) {
                JScrollBar v = scrollPane.getVerticalScrollBar();
                JScrollBar h = scrollPane.getHorizontalScrollBar();

                if (v != null) {
                    v.setUI(new ScrollBarUI());
                    v.setPreferredSize(new Dimension(12, 12));
                }
                if (h != null) {
                    h.setUI(new ScrollBarUI());
                    h.setPreferredSize(new Dimension(12, 12));
                }
            }

            if (c instanceof Container child) {
                applyCustomScrollBar(child);
            }
        }
    }

    private static class WhiteArrowComboBoxUI extends BasicComboBoxUI {

        @Override
        protected JButton createArrowButton() {
            JButton button = new JButton();
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setBackground(new Color(51, 51, 51));
            button.setOpaque(true);

            button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

                    int w = c.getWidth();
                    int h = c.getHeight();

                    g2.setColor(Color.WHITE);
                    int size = Math.min(w, h) / 3;

                    Polygon arrow = new Polygon(
                            new int[]{w / 2 - size, w / 2 + size, w / 2},
                            new int[]{h / 2 - size / 2, h / 2 - size / 2, h / 2 + size},
                            3
                    );

                    g2.fill(arrow);
                    g2.dispose();
                }
            });
            return button;
        }
    }
}
