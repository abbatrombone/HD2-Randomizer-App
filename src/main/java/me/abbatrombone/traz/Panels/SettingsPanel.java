package me.abbatrombone.traz.Panels;

import me.abbatrombone.traz.CustomComponents.CustomButton;
import me.abbatrombone.traz.CustomComponents.CustomComboBox;
import me.abbatrombone.traz.CustomComponents.CustomCursor;
import me.abbatrombone.traz.Managers.SettingsManager;
import me.abbatrombone.traz.Panels.IsoGraphics.IsoPanel;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel{
    private final JPanel panel = new JPanel();
    private final IsoPanel isoPanel = new IsoPanel();
    private final Color bgColor = new Color(51,51,51);
    private final CustomButton imageButton = new CustomButton("Select Image",Color.WHITE);
    private final CustomButton colorButton = new CustomButton("Select Color",Color.WHITE);
    private final CustomButton checkmarksButton = new CustomButton("Use Democratic Checkmarks",Color.WHITE);
    private final CustomButton soundsButton = new CustomButton("Sound is Disabled",Color.WHITE);
    String[] options = { "all","config","fine","finer","finest","Info","off","servere","warning"};
    private final CustomComboBox dropdown = new CustomComboBox(options);

    private final SettingsManager settingsManager =new SettingsManager();

    public SettingsPanel() {

        JPanel cursorRow = makeRow("Use Custom Cursor:", imageButton);
        JPanel textColorRow = makeRow("Change Text Color:", colorButton);
        JPanel checkMarkRow = makeRow("Use Default Checkmarks:", checkmarksButton);
        JPanel logRow = makeRow("Change Log level:",dropdown);
        JPanel soundsRow = makeRow("Enable/Disable Sounds:",soundsButton);
        dropdown.setSelectedIndex(options.length -1);

        imageButton.addHoverWord("Make sure its democracy approved");
        colorButton.addHoverWord("Using a color unaffiliated with super earth is treason");
        checkmarksButton.addHoverWord("Your willingness to commit treason is noted helldiver");
        soundsButton.addHoverWord("What the button says");

        isoPanel.setPreferredSize(new Dimension(500, 600));
        panel.setBackground(bgColor);

        checkmarksButton.addActionListener(e -> {
            changeCheckboxButtonText((JButton) e.getSource());
            changeCheckboxButtonSetting();
        });

        soundsButton.addActionListener(e -> {
            changeSoundsButtonText((JButton) e.getSource());
            changeSoundsSetting();
        });

        GroupLayout groupLayout = new GroupLayout(panel);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(isoPanel)
                        )
                        .addGroup(groupLayout.createParallelGroup()
                                .addComponent(cursorRow)
                                .addComponent(textColorRow)
                                .addComponent(checkMarkRow)
                                .addComponent(logRow)
                                .addComponent(soundsRow))
                        .addGap(0, 0, Short.MAX_VALUE) // pushes everything else right
        );

        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup()
                        .addComponent(isoPanel)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(cursorRow)
                                .addComponent(textColorRow)
                                .addComponent(checkMarkRow)
                                .addComponent(logRow)
                                .addComponent(soundsRow))
        );

        panel.setLayout(groupLayout);
    }

    public JPanel getPanel() {
        return panel;
    }
    public void setSettingPanelSize(int h, int w){
        isoPanel.setPreferredSize(new Dimension(h,w));
    }
    public JPanel makeRow(String label, JComponent component){
        JPanel p = new JPanel();
        p.setBackground(bgColor);
        JLabel l = new JLabel(label);

        l.setBackground(bgColor);
        l.setForeground(Color.WHITE);

        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(l);
        p.add(Box.createRigidArea(new Dimension(5,0)));
        p.add(component);
        return p;
    }
    private void changeCheckboxButtonText(JButton button){
        if (button.getText().equals("Use Democratic Checkmarks")) {
            button.setText("Use Basic Checkmarks");
        } else {
            button.setText("Use Democratic Checkmarks");
        }
    }
    private void changeCheckboxButtonSetting() {
        String truthy = Boolean.toString(settingsManager.defaultCheckboxIsOn());
        if(truthy.equals("true")){
            settingsManager.setCheckboxSetting("false");
        }else{
            settingsManager.setCheckboxSetting("true");
        }
    }
    private void changeSoundsButtonText(JButton button){
        if (button.getText().equals("Sound is Disabled")) {
            button.setText("Sound is Enabled");
        } else {
            button.setText("Sound is Disabled");
        }
    }
    private void changeSoundsSetting() {
        String truthy = Boolean.toString(settingsManager.soundIsOn());
        if(truthy.equals("false")){
            settingsManager.setSoundSetting("true");
        }else{
            settingsManager.setSoundSetting("false");
        }
    }
}
