package me.abbatrombone.traz.CustomComponents;

import me.abbatrombone.traz.CustomComponents.CustomMouseItems.HoverHandler;
import me.abbatrombone.traz.Managers.SettingsManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class WarbondCheckBox extends JCheckBox {
    Tooltip tooltip = new Tooltip();

    ImageIcon unchecked ;
    ImageIcon checked;

    private static final SettingsManager settingsManager = new SettingsManager();
    private final Color fgColor = settingsManager.getColor("Label_Color","#ffffff");

    public WarbondCheckBox(){
        putClientProperty("hoverCursor", true);
        Color backgroundColor = new Color(51, 51, 51);
        setBackground(backgroundColor);
        setOpaque(true);
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Icon.png")));
            Image scaled = getScaledImage(img, 20, 20);
            checked = new ImageIcon(scaled);
            unchecked = createWhiteBoxIcon(20);

            setIcon(unchecked);
            setSelectedIcon(checked);
            setFocusPainted(false);
            setBorderPainted(false);
            setSelected(true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        putClientProperty("customTooltip", isSelected() ? "Warbond is selected" : "Warbond is not selected");
        addItemListener(e -> putClientProperty("customTooltip", isSelected() ? "Warbond is selected" : "Warbond is not selected"));


        HoverHandler hoverHandler = new HoverHandler(tooltip, tooltip.getTooltipLabel());

        addMouseMotionListener(hoverHandler);
        addMouseListener(hoverHandler);

    }
    //Even though w,h and size are always 20 this left incase rescaling is wanted in the future
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    private ImageIcon createWhiteBoxIcon(int size) {
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = img.createGraphics();

        g.setColor(fgColor);
        g.fillRect(0, 0, size, size);

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, size - 1, size - 1);

        g.dispose();

        return new ImageIcon(img);
    }
}
