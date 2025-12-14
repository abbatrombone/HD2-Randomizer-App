package me.abbatrombone.traz.CustomComponents;

import me.abbatrombone.traz.RandomLoadOut;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class OutputJTextPane extends JTextPane {
    private BufferedImage backgroundImage;
    private float imageOpacity = 0.3f;

    public OutputJTextPane() {
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Super_Earth_Flag.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setOpaque(false);
    }
    public void updateText(){
        StyledDocument doc = getStyledDocument();

        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        StyleConstants.setForeground(attributeSet, Color.BLACK);
        //StyleConstants.setBackground(attributeSet, Color.ORANGE);
        if(!getText().isEmpty()){
            setText("");
        }

        try {
            doc.insertString(doc.getLength(), RandomLoadOut.result(), attributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // MUST be first

        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, imageOpacity));
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}
