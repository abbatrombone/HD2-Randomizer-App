package me.abbatrombone.traz.CustomComponents;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class RulesTextArea extends JTextArea {
    private final BufferedImage backgroundImage;

    public RulesTextArea() {

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/HD2RulesGB.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setOpaque(true);
        setEditable(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            float imageOpacity = 0.3f;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, imageOpacity));
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}
