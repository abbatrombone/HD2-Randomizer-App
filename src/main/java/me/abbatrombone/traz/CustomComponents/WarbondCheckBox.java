package me.abbatrombone.traz.CustomComponents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class WarbondCheckBox extends JCheckBox {
    private final Color backgroundColor = new Color(51, 51, 51);
    ImageIcon unchecked ;
    ImageIcon checked;

    public WarbondCheckBox(){
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
            setToolTipText("Warbond is selected");
            addActionListener(e -> setToolTipText(isSelected() ? "Warbond is selected" : "Warbond is not selected"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    private ImageIcon createWhiteBoxIcon(int size) {
        BufferedImage img =
                new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = img.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size, size);

        g.setColor(Color.BLACK); // optional border
        g.drawRect(0, 0, size - 1, size - 1);

        g.dispose();

        return new ImageIcon(img);
    }
}
