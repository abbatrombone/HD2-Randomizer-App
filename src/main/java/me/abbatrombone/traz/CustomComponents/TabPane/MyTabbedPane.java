package me.abbatrombone.traz.CustomComponents.TabPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MyTabbedPane extends JPanel {
    private final Dimension prefSize;

    Container tabsConteiner, panelsContainer;
    List<MyComponent> tabs;
    List<JComponent> panels;
    int margin = 0;

    public MyTabbedPane( Dimension dim ) {
        this.prefSize = dim;

        setSize(dim);
        tabs = new ArrayList<>();
        panels = new ArrayList<>();

        setLayout(null); //has fixed position

        tabsConteiner = new JPanel();
        tabsConteiner.setBackground( new Color(51,51,51));
        tabsConteiner.setLocation( 0, 0 );
        tabsConteiner.setLayout( null );
        tabsConteiner.setSize( dim.width, 28 );

        panelsContainer = new JPanel();
        panelsContainer.setLayout(null);
        panelsContainer.setLocation(0, 28);
        panelsContainer.setSize(getWidth(),getHeight() - 28);

        add(tabsConteiner);
        add(panelsContainer);
    }

    public void addTab(JComponent pan, MyComponent tab) {

        pan.setSize(panelsContainer.getSize());
        tab.setLocation(margin + 2, 2);

        tabsConteiner.add(tab);
        tabs.add(tab);

        margin += tab.getWidth() + 2; //draw next tab in correct spot

        panels.add(pan);

        if( tabs.size() == 1 ) {
            tab.setSelected(true);
            panelsContainer.removeAll();
            panelsContainer.add(pan);
            panelsContainer.repaint();
        }

        tab.addMouseListener(new MouseAdapter() {
            @Override
            public void  mousePressed( MouseEvent e )  {

                int index = 0;
                for( int i = 0; i < tabs.size(); i ++ ) {
                    if( tabs.get(i).equals(tab)) {
                        tabs.get(i).setSelected(true);
                        index = i;
                    }
                    else {
                        tabs.get(i).setSelected(false);
                    }
                    tabs.get(i).repaint();
                }
                panelsContainer.removeAll();
                panelsContainer.add(panels.get(index));
                panelsContainer.revalidate();
                panelsContainer.repaint();
            }
        });
    }
    @Override
    public Dimension getPreferredSize() {
        return prefSize;
    }
}
