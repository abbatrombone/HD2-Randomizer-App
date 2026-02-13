package me.abbatrombone.traz.CustomComponents.CustomMouseItems;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HoverHandler extends MouseAdapter {

        private final JWindow tooltipWindow;
        private final JLabel tooltipLabel;

        public HoverHandler(JWindow tooltipWindow, JLabel tooltipLabel) {
            this.tooltipWindow = tooltipWindow;
            this.tooltipLabel = tooltipLabel;
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            JComponent comp = (JComponent) e.getSource();
            Object text = comp.getClientProperty("customTooltip");

            if (text instanceof String && comp.contains(e.getPoint())) {
                tooltipLabel.setText((String) text);
                tooltipWindow.setLocation(
                        e.getXOnScreen() + 12,
                        e.getYOnScreen() + 18
                );
                tooltipWindow.setVisible(true);
            } else {
                tooltipWindow.setVisible(false);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            tooltipWindow.setVisible(false);
        }
}
