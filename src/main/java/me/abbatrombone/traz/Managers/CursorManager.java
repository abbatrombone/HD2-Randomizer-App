package me.abbatrombone.traz.Managers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Supplier;

public final class CursorManager extends MouseAdapter {

    private final Supplier<Cursor> normalCursor;
    private final Supplier<Cursor> hoverCursor;

    public CursorManager(Supplier<Cursor> normalCursor,
                         Supplier<Cursor> hoverCursor) {
        this.normalCursor = normalCursor;
        this.hoverCursor = hoverCursor;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component c = SwingUtilities.getDeepestComponentAt(
                e.getComponent(), e.getX(), e.getY()
        );

        JComponent owner = findCursorOwner(c);

        if (owner != null) {
            owner.setCursor(hoverCursor.get());
        } else {
            e.getComponent().setCursor(normalCursor.get());
        }
    }
    private JComponent findCursorOwner(Component c) {
        while (c != null) {
            if (c instanceof JComponent jc &&
                    Boolean.TRUE.equals(jc.getClientProperty("hoverCursor"))) {
                return jc;
            }
            c = c.getParent();
        }
        return null;
    }
}