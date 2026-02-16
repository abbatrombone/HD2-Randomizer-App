package me.abbatrombone.traz.CustomComponents;

import me.abbatrombone.traz.CustomComponents.CustomMouseItems.HoverHandler;
import me.abbatrombone.traz.Managers.SettingsManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WarbondCheckBox extends JCheckBox {
    private final Color backgroundColor = new Color(51, 51, 51);
//    private final JWindow tooltipWindow = new JWindow();
//    private final JLabel tooltipLabel = new JLabel();
    private final Map<String, String> hoverMessages = new HashMap<>();
    Tooltip tooltip = new Tooltip();

    ImageIcon unchecked ;
    ImageIcon checked;
    private boolean hover = false;

    private static final SettingsManager settingsManager = new SettingsManager();
    private final Color fgColor = settingsManager.getColor("Label_Color","#ffffff");

    public WarbondCheckBox(){
        putClientProperty("hoverCursor", true);
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
            //addHoverWord("Warbond is selected");
            //addActionListener(e -> addHoverWord(isSelected() ? "Warbond is selected" : "Warbond is not selected"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        putClientProperty("customTooltip", isSelected() ? "Warbond is selected" : "Warbond is not selected");
        addItemListener(e -> putClientProperty("customTooltip", isSelected() ? "Warbond is selected" : "Warbond is not selected"));
        //setupTooltipUI();
        //addMouseMotionListener(new WarbondCheckBox.CheckBoxHoverHandler());
        HoverHandler hoverHandler = new HoverHandler(tooltip, tooltip.getTooltipLabel());

        addMouseMotionListener(hoverHandler);
        addMouseListener(hoverHandler);

//        addMouseListener(
//                new MouseAdapter() {
//                    @Override
//                    public void mouseEntered(MouseEvent e) {
//                        hover = true;
//                        repaint();
//                    }
//
//                    @Override
//                    public void mouseExited(MouseEvent e) {
//                        hover = false;
//                        repaint();
//                        tooltipWindow.setVisible(false); // close tooltip when leaving the button
//                        putClientProperty("hoverCursor", false);
//                    }
//
//                    @Override
//                    public void mousePressed(MouseEvent e) {
//                        repaint();
//                    }
//
//                    @Override
//                    public void mouseReleased(MouseEvent e) {
//                        repaint();
//                    }
//                });
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

        g.setColor(fgColor);
        g.fillRect(0, 0, size, size);

        g.setColor(Color.BLACK); // optional border
        g.drawRect(0, 0, size - 1, size - 1);

        g.dispose();

        return new ImageIcon(img);
    }

    public void addHoverWord(String message) {
        hoverMessages.put(this.getText(), message);
    }

//    private void setupTooltipUI() {
//        tooltipWindow.setBackground(new Color(0, 0, 0, 0));
//        JPanel panel = new JPanel();
//        panel.setBackground(new Color(30, 30, 30));
//        panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
//        panel.setLayout(new GridBagLayout());
//
//        tooltipLabel.setForeground(fgColor);
//        tooltipLabel.setFont(new Font("Arial", Font.BOLD, 12));
//        panel.add(tooltipLabel);
//
//        tooltipWindow.add(panel);
//        tooltipLabel.addPropertyChangeListener("text", evt -> {
//            FontMetrics fm = tooltipLabel.getFontMetrics(tooltipLabel.getFont());
//            int textWidth = fm.stringWidth(tooltipLabel.getText());
//            int textHeight = fm.getHeight();
//
//            int paddingW = 20;
//            int paddingH = 14;
//
//            tooltipWindow.setSize(textWidth + paddingW, textHeight + paddingH);
//            tooltipWindow.pack();
//        });
//
//        tooltipWindow.pack();
//    }

//    private class CheckBoxHoverHandler extends MouseMotionAdapter {
//
//        @Override
//        public void mouseMoved(MouseEvent e) {
//
//            String key = getText();
//
//            boolean hovering = false;
//
//            if (contains(e.getPoint()) && hoverMessages.containsKey(key)) {
//                tooltipLabel.setText(hoverMessages.get(key));
//                tooltipWindow.setLocation(e.getXOnScreen() + 12, e.getYOnScreen() + 18);
//                tooltipWindow.setVisible(true);
//                putClientProperty("hoverCursor", true);
//                hovering = true;
//            }
//
//            if (!hovering) {
//                tooltipWindow.setVisible(false);
//                putClientProperty("hoverCursor", false);
//            }
//            //helps mitigate phantom tool tips but does not resolve.
//            if(!contains(e.getPoint())){
//                tooltipWindow.setVisible(false);
//                putClientProperty("hoverCursor", true);
//            }
//        }
//    }
}
