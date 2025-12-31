package me.abbatrombone.traz.CustomComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.Map;

public class CustomButton extends JButton {
    private Color baseColor;
    private boolean hover = false;
    private boolean pressed = false;

    private final Map<String, String> hoverMessages = new HashMap<>();
    private final JWindow tooltipWindow = new JWindow();
    private final JLabel tooltipLabel = new JLabel();

    public CustomButton(String text, Color baseColor) {
        super(text);
        this.baseColor = baseColor;

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setBaseColor(new Color(51,51,51));
        setFont(new Font("Segoe UI", Font.BOLD + Font.ITALIC, 14));

        setupTooltipUI();
        addMouseMotionListener(new CustomButton.ButtonHoverHandler());

        addMouseListener(
        new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                pressed = false;
                repaint();
                tooltipWindow.setVisible(false); // close tooltip when leaving the button
                setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                repaint();
            }

        });
    }

    public void setBaseColor(Color color) {
        this.baseColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int arc = 12;
        int offset = pressed ? 2 : 0;

        Color top = baseColor.brighter();
        Color bottom = baseColor.darker();

        if (hover) {
            top = top.brighter();
            bottom = bottom.brighter();
        }

        if (pressed) {
            Color temp = top;
            top = bottom;
            bottom = temp;
        }

        GradientPaint gp = new GradientPaint(0, 0, top, 0, height, bottom);
        g2.setPaint(gp);
        g2.fillRoundRect(0, pressed ? 2 : 0, width, height - 2, arc, arc);

        // Yellow border
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.YELLOW);
        g2.drawRoundRect(1, offset + 1, width - 3, height - 4, arc, arc);

        // Shadow / depth
        g2.setColor(new Color(0, 0, 0, 80));
        g2.drawRoundRect(0, pressed ? 2 : 0, width - 1, height - 3, arc, arc);

        g2.dispose();
        super.paintComponent(g);
    }

    public void addHoverWord(String message) {
        hoverMessages.put(this.getText(), message);
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
    private class ButtonHoverHandler extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {

            String key = getText();

            boolean hovering = false;

            if (contains(e.getPoint()) && hoverMessages.containsKey(key)) {
                tooltipLabel.setText(hoverMessages.get(key));
                tooltipWindow.setLocation(e.getXOnScreen() + 12, e.getYOnScreen() + 18);
                tooltipWindow.setVisible(true);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                hovering = true;
            }

            if (!hovering) {
                tooltipWindow.setVisible(false);
                setCursor(Cursor.getDefaultCursor());
            }

        }
    }
}
