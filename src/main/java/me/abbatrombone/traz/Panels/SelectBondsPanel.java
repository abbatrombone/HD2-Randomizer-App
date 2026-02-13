package me.abbatrombone.traz.Panels;
import com.fasterxml.jackson.core.type.TypeReference;
import me.abbatrombone.traz.CustomComponents.JScrollbarUIComp.CustomScrollbar;
import me.abbatrombone.traz.CustomComponents.WarbondCheckBox;
import me.abbatrombone.traz.GameItems.Warbonds;
import me.abbatrombone.traz.JSONTools.JSONReader;
import me.abbatrombone.traz.Managers.SettingsManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SelectBondsPanel {
    private final JPanel panel = new JPanel();
    private final JScrollPane jScrollPane = new JScrollPane(panel);
    private final JSONReader reader = new JSONReader();
    private final ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    private final JPanel[] lines;
    private final Color backgroundColor = new Color(51, 51, 51);
    private static final SettingsManager settingsManager = new SettingsManager();
    private final Color fgColor = settingsManager.getColor("Label_Color","#ff6699");

    public SelectBondsPanel(){

        jScrollPane.getVerticalScrollBar().setUI(new CustomScrollbar().getUI());
        jScrollPane.getHorizontalScrollBar().setUI(new CustomScrollbar().getUI());

        //fixes uncolored corner when both scrollbars exist
        JPanel corner = new JPanel();
        corner.setBackground(new Color(51,51,51));
        jScrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, corner);

        JPanel labelHeaderPanel = new JPanel();
        labelHeaderPanel.setBackground(backgroundColor);
        labelHeaderPanel.setLayout(new BoxLayout(labelHeaderPanel, BoxLayout.X_AXIS));
        labelHeaderPanel.add(Box.createHorizontalGlue());

        JLabel labelHeader = new JLabel("Warbonds");
        labelHeader.setForeground(fgColor);
        labelHeaderPanel.add(labelHeader);
        labelHeaderPanel.add(Box.createHorizontalGlue());

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        panel.setBackground(backgroundColor);
        labelHeader.setAlignmentX(GroupLayout.Alignment.CENTER.ordinal());
        labelHeader.setOpaque(false);

        Warbonds.Bonds[] bonds = Warbonds.Bonds.values();
        lines = new JPanel[bonds.length];

        for (int i = 0; i < bonds.length; i++) {
            lines[i] = makeRow(bonds[i]);
        }

        GroupLayout.ParallelGroup h = layout.createParallelGroup(); //removed GroupLayout.Alignment.LEADING
        GroupLayout.SequentialGroup v = layout.createSequentialGroup();

        h.addComponent(labelHeaderPanel);
        v.addComponent(labelHeaderPanel);

        for (JPanel line : lines) {
            h.addComponent(line);
            v.addComponent(line);
        }

        layout.setHorizontalGroup(h);
        layout.setVerticalGroup(v);

        panel.revalidate();
        panel.setPreferredSize(panel.getPreferredSize());
    }

    private JPanel makeRow(Warbonds.Bonds bond) {
        JPanel p = new JPanel();

        p.setBackground(backgroundColor);

        if(!bond.toString().equals("Cadet_Loadout")){
            p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));

            String name = bond.toString();
            String nameWithSpace = name.replace("_"," ");

            JCheckBox checkBox = settingsManager.defaultCheckboxIsOn() ? new JCheckBox() : new WarbondCheckBox() ;
            checkBox.setBackground(backgroundColor);
            JLabel label = new JLabel(nameWithSpace);
            label.setForeground(fgColor);
            checkBox.setName(name);

            checkBox.setSelected(reader.readValue(bond.toString()));

            checkBoxes.add(checkBox);

            p.add(checkBox);
            p.add(Box.createRigidArea(new Dimension(5, 0)));
            p.add(label);
        }
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
    public ArrayList<JCheckBox> getCheckBoxes() {
        return checkBoxes;
    }
}
