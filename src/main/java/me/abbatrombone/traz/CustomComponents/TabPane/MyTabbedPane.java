package me.abbatrombone.traz.CustomComponents.TabPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MyTabbedPane extends JPanel {

    Container tabsConteiner, panelsContainer;
    List<MyComponent> tabs;
    List<JComponent> panels;
    int margin = 0;

    public MyTabbedPane( Dimension dim ) {


        // here I set the same frame size, but that depends on your needs.
        setSize( dim );
        tabs = new ArrayList<>();
        panels = new ArrayList<>();

        // since the internal panels will have a fixed position...
        setLayout( null );

        tabsConteiner = new JPanel();
        tabsConteiner.setBackground( new Color(51,51,51));
        tabsConteiner.setLocation( 0, 0 );
        tabsConteiner.setLayout( null );
        tabsConteiner.setSize( dim.width, 28 );

        panelsContainer = new JPanel();
        panelsContainer.setLayout( null );
        panelsContainer.setLocation( 0, 28 );
        panelsContainer.setSize( getWidth(), getHeight() - 28 );

        add( tabsConteiner );
        add( panelsContainer );
    }

    public void addTab(JComponent pan, MyComponent tab) {

        // we assign the same container size
        pan.setSize( panelsContainer.getSize() );
        tab.setLocation( margin + 2, 2 );

        // we add the tabs to the panel and to the corresponding array.
        tabsConteiner.add( tab );
        tabs.add( tab );

        // we update the value so that the next
        // tab is drawn in the right place.
        margin += tab.getWidth() + 2;

        // we add the panel to the corresponding list
        panels.add( pan );

        // Iif it is the first assignment, we set the
        // tab as selected and add the panel to "panelsContainer"
        if( tabs.size() == 1 ) {
            tab.setSelected(true);
            panelsContainer.removeAll();
            panelsContainer.add(pan);
            panelsContainer.repaint();
        }

        // we add a “mouse” event listener. When an event occurs,
        // the list of tabs will be scanned, and based on whether
        // it is selected or not, the value of “setSelected()”
        // will be set to select the corresponding colors.
        // then we remove the panel that is in “panelsContainer”
        // and assign it the new one.
        tab.addMouseListener( new MouseAdapter() {
            @Override
            public void  mousePressed( MouseEvent e )  {

                int index = 0;
                for( int i = 0; i < tabs.size(); i ++ ) {
                    if( tabs.get( i ).equals( tab )) {
                        tabs.get( i ).setSelected( true );
                        index = i;
                    }
                    else {
                        tabs.get( i ).setSelected( false );
                    }
                    tabs.get( i ).repaint();
                }
                panelsContainer.removeAll();
                panelsContainer.add(panels.get(index));
                panelsContainer.revalidate();
                panelsContainer.repaint();
            }
        });
    }
}
