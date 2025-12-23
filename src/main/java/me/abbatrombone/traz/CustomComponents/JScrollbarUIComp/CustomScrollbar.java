package me.abbatrombone.traz.CustomComponents.JScrollbarUIComp;

import javax.swing.*;
import java.awt.*;

public class CustomScrollbar extends JScrollBar {

    public CustomScrollbar(){
        setUI(new ScrollBarUI());
        setPreferredSize(new Dimension(8,8));
    }
}
