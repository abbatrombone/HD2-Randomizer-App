package me.abbatrombone.traz.CustomComponents.TabPane;

import javax.swing.*;
import java.awt.*;

public class MyComponent extends JLabel {

    Color back, fore, line;
    String title;
    Color bg = new Color(51,51,51);

    public MyComponent( String tit ) {
        title = tit;

        FontMetrics fm = getFontMetrics(getFont());
        int textWidth = fm.stringWidth(tit);
        int paddingX = 20;

        setSize(textWidth + paddingX, 26);

        back = bg;
        fore = Color.WHITE;
        line = bg;
    }

    @Override
    public void paintComponent( Graphics g ) {
        Graphics2D gra = (Graphics2D) g;
        int distanceOfMargin = 0;

        gra.fillRoundRect( 2, 2, getWidth(), getHeight(), 8, 8 );
        gra.setColor( back );
        //  gra.fillRect( 3, 3, getWidth(), getHeight() );
        gra.fillRoundRect( 3, 3, getWidth() -4, getHeight(), 5, 5 );

        gra.setColor( line );
        gra.drawLine( 7, 23, getWidth() - 7, 23 );

        gra.setColor( fore );
        gra.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON );
        gra.drawString( title, distanceOfMargin + 11, 18 );
        gra.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF );
    }

    void setSelected( boolean selected ) {
        if( selected ) {
            back = bg;
            fore = Color.white;
            line = Color.WHITE;
        }
        else {
            back = bg;
            fore = Color.WHITE;
            line = bg;
        }
    }
}
