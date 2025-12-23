package me.abbatrombone.traz.CustomComponents;

import me.abbatrombone.traz.Panels.ButtonActions.RandomLoadOut;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class OutputJTextPane extends JTextPane {
    private BufferedImage backgroundImage;
    private final float imageOpacity = 0.3f;
    private final static SimpleAttributeSet attributeSet = new SimpleAttributeSet();

    public OutputJTextPane() {

        StyleConstants.setBold(attributeSet, true);
        StyleConstants.setForeground(attributeSet, Color.BLACK);
        setFont(new Font("FS Sinclair", Font.BOLD, 12)); //Swiss 721 Extended

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Super_Earth_Flag.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setOpaque(false);
        setEditable(false);
    }
    public void updateText(){
        StyledDocument doc = getStyledDocument();

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
    public void updateImage(String enemy){
        switch (enemy){
            case "Terminids"  -> {
                try {
                    backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Terminids.png")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Automatons" -> {
                try {
                    backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bots.png")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Illuminate" -> {
                try {
                    backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Squids.png")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> {
                try {
                    backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Super_Earth_Flag.jpg")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
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
