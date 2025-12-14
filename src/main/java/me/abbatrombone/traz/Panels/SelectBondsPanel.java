package me.abbatrombone.traz.Panels;
import me.abbatrombone.traz.Warbonds;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SelectBondsPanel {
    private JPanel panel = new JPanel();
    private JScrollPane jScrollPane = new JScrollPane(panel);
    private final ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    private JPanel[] lines = new JPanel[Warbonds.Bonds.values().length];
    JPanel labelHeaderPanel = new JPanel();
    JLabel labelHeader = new JLabel("Warbonds");

    public SelectBondsPanel(){

        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        labelHeaderPanel.setLayout(new BoxLayout(labelHeaderPanel, BoxLayout.X_AXIS));
        labelHeaderPanel.add(Box.createHorizontalGlue());
        labelHeaderPanel.add(labelHeader);
        labelHeaderPanel.add(Box.createHorizontalGlue());

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        labelHeader.setAlignmentX(GroupLayout.Alignment.CENTER.ordinal());
        labelHeader.setOpaque(false);


        Warbonds.Bonds[] bonds = Warbonds.Bonds.values();
        lines = new JPanel[bonds.length];

        for (int i = 0; i < bonds.length; i++) {
            lines[i] = makeRow(bonds[i]);
        }



        GroupLayout.ParallelGroup h = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        GroupLayout.SequentialGroup v = layout.createSequentialGroup();

        h.addComponent(labelHeaderPanel);
        v.addComponent(labelHeaderPanel);

        for (JPanel line : lines) {
            h.addComponent(line);
            v.addComponent(line);
        }

        layout.setHorizontalGroup(h);
        layout.setVerticalGroup(v);
    }

    private JPanel makeRow(Warbonds.Bonds bond) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));

        String name = bond.toString();
        String nameWithSpace = name.replace("_"," ");

        JCheckBox checkBox = new JCheckBox();
        JLabel label = new JLabel(nameWithSpace);
        checkBox.setSelected(true);

        checkBoxes.add(checkBox);

        p.add(checkBox);
        p.add(Box.createRigidArea(new Dimension(5, 0)));
        p.add(label);

        return p;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JPanel[] getLines() {
        return lines;
    }
    public JScrollPane getJScrollPane() {
        return jScrollPane;
    }
}
