package me.abbatrombone.traz.CustomComponents;

import me.abbatrombone.traz.CustomComponents.CustomMouseItems.HoverHandler;
import me.abbatrombone.traz.Managers.SettingsManager;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    private Color baseColor;
    private boolean hover = false;
    private boolean pressed = false;

    Tooltip tooltip = new Tooltip();
    private static final SettingsManager settingsManager = new SettingsManager();
    private final Color fgColor = settingsManager.getColor("Label_Color","#ffffff");

    public CustomButton(String text, Color baseColor) {
        super(text);
        this.baseColor = baseColor;
        putClientProperty("hoverCursor", true);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        setForeground(fgColor);
        setBaseColor(new Color(51,51,51));
        setFont(new Font("Segoe UI", Font.BOLD + Font.ITALIC, 14));

        HoverHandler hoverHandler = new HoverHandler(tooltip, tooltip.getTooltipLabel());

        addMouseMotionListener(hoverHandler);
        addMouseListener(hoverHandler);
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

        g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.YELLOW);
        g2.drawRoundRect(1, offset + 1, width - 3, height - 4, arc, arc);

        // Shadow / depth
        g2.setColor(new Color(0, 0, 0, 80));
        g2.drawRoundRect(0, pressed ? 2 : 0, width - 1, height - 3, arc, arc);

        g2.dispose();
        super.paintComponent(g);
    }
    public void setHoverWord(String message) {
        putClientProperty("customTooltip", message);
    }
}
