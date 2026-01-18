package me.abbatrombone.traz.CustomComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.*;

public class CustomComboBox extends JComboBox<String>  {
    Color bgColor = new Color(51,51,51);
    private final JWindow tooltipWindow = new JWindow();
    private final JLabel tooltipLabel = new JLabel();
    private String hoverMessages = "Choosing \"All\" may TANK preformance!";
    private boolean hovering = false;
    private CustomCursor cursor = new CustomCursor();

    public CustomComboBox(String[] options){
        super(options);

        UIManager.put("ComboBox.background", bgColor);
        UIManager.put("ComboBox.foreground", Color.WHITE);
        UIManager.put("ComboBox.selectionBackground", new Color(51,51,51));
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);

        setBackground(bgColor);
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(10,10));
        setFont(new Font("FS Sinclair", Font.BOLD, 14));
        setBorder(new EmptyBorder(5, 10, 5, 10));
        setPreferredSize(new Dimension(140, 28));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        setMinimumSize(new Dimension(140, 28));
//        setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

        setupTooltipUI();
        addMouseMotionListener(new CustomComboBox.BoxHoverHandler());
        addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setRenderer(getRenderer());
            }
    });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                tooltipWindow.setVisible(false);
                setCursor(cursor.create(CustomCursor.Type.CUSTOM_ARROW));
                //setCursor(Cursor.getDefaultCursor());
                hovering = false;
            }
        });

        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBackground(isSelected ? new Color(70, 70, 70) : bgColor);
                label.setForeground(Color.WHITE);
                label.setBorder(new EmptyBorder(5, 10, 5, 10));

                return label;
            }
        });

        setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton btn = new JButton("▼");
                btn.setFont(new Font("FS Sinclair", Font.BOLD, 12));
                btn.setForeground(Color.WHITE);
                btn.setBackground(bgColor);
                btn.setBorder(null);
                btn.setFocusable(false);
                return btn;
            }

            @Override
            protected void installComponents() {
                super.installComponents();
                if (arrowButton != null) {
                    arrowButton.setPreferredSize(new Dimension(5, 5)); // control arrow size
                    arrowButton.setMaximumSize(new Dimension(32, 5));
                    arrowButton.setMinimumSize(new Dimension(5, 5));
                }
            }

            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.setColor(new Color(100, 100, 100));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
                super.paint(g, c);
            }

            @Override
            protected LayoutManager createLayoutManager() {
                return new LayoutManager() {
                    @Override public void addLayoutComponent(String name, Component comp) {}
                    @Override public void removeLayoutComponent(Component comp) {}
                    @Override public Dimension preferredLayoutSize(Container parent) { return parent.getPreferredSize(); }
                    @Override public Dimension minimumLayoutSize(Container parent) { return parent.getMinimumSize(); }

                    @Override
                    public void layoutContainer(Container parent) {
                        int w = parent.getWidth();
                        int h = parent.getHeight();
                        int arrowWidth = 32;
                        int arrowHeight = 18;

                        if (arrowButton != null) {
                            arrowButton.setBounds(w - arrowWidth - 6, (h - arrowHeight) / 2, arrowWidth, arrowHeight);
                        }

                        if (editor != null) {
                            editor.setBounds(6, 4, w - arrowWidth - 14, h - 8);
                        }
                    }
                };
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bgColor);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setStroke(new BasicStroke(4f));
                g2.setColor(Color.YELLOW);
                g2.drawRect(0, 0, getWidth(), getHeight());
            }

            @Override
            protected void installDefaults() {
                super.installDefaults();
                comboBox.setBackground(bgColor);
                comboBox.setForeground(Color.WHITE);
                comboBox.setFont(new Font("FS Sinclair", Font.BOLD, 14));
            }

        });
    }
    public void setHoverWord(String message) {
        hoverMessages = message;
    }

    private void setupTooltipUI() {
        tooltipWindow.setBackground(new Color(0, 0, 0, 0));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        panel.setLayout(new GridBagLayout());

        tooltipLabel.setForeground(Color.WHITE);
        tooltipLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(tooltipLabel);

        tooltipWindow.add(panel);
        tooltipLabel.addPropertyChangeListener("text", evt -> {
            FontMetrics fm = tooltipLabel.getFontMetrics(tooltipLabel.getFont());
            int textWidth = fm.stringWidth(tooltipLabel.getText());
            int textHeight = fm.getHeight();

            int paddingW = 20;
            int paddingH = 14;

            tooltipWindow.setSize(textWidth + paddingW, textHeight + paddingH);
            tooltipWindow.pack();
        });

        tooltipWindow.pack();
    }
    private class BoxHoverHandler extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {

            boolean inside = contains(e.getPoint());

            if (inside) {
                tooltipLabel.setText(hoverMessages);
                tooltipWindow.setLocation(e.getXOnScreen() + 12, e.getYOnScreen() + 18);
                tooltipWindow.setVisible(true);
                setCursor(cursor.create(CustomCursor.Type.CUSTOM_HAND_ARROW));
                hovering = true;
            } else {
                tooltipWindow.setVisible(false);
                setCursor(cursor.create(CustomCursor.Type.CUSTOM_ARROW));
                hovering = false;
            }
        }
    }
}
