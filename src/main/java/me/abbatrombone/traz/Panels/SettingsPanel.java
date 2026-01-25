package me.abbatrombone.traz.Panels;

import me.abbatrombone.traz.CustomComponents.*;
import me.abbatrombone.traz.Managers.SettingsManager;
import me.abbatrombone.traz.Panels.IsoGraphics.IsoPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class SettingsPanel{
    private final JPanel panel = new JPanel();
    private final IsoPanel isoPanel = new IsoPanel();
    private final Color bgColor = new Color(51,51,51);
    private static final SettingsManager settingsManager = new SettingsManager();
    private final Color fgColor = settingsManager.getColor("Label_Color","#ffffff");
    private final CustomButton imageButton = new CustomButton("Select Image",fgColor);
    private final CustomButton standardCursorButton = new CustomButton("Default",fgColor);
    private final CustomButton hellDiversCursorButton = new CustomButton("HD2",fgColor);
    private final CustomButton guiColorButton = new CustomButton("Select Color",fgColor);
    private final CustomButton textColorButton = new CustomButton("Select Color",fgColor);
    private final CustomButton checkmarksButton = new CustomButton("Use Democratic Checkmarks",fgColor);
    private final CustomButton soundsButton = new CustomButton("Sound is Disabled",fgColor);
    String[] options = { "all","config","fine","finer","finest","Info","off","servere","warning"};
    private final CustomComboBox dropdown = new CustomComboBox(options);

    private final CustomFileChooser fileChooser =  new CustomFileChooser(getPanel());
    private final CustomColorChooser customColorChooser = new CustomColorChooser(getPanel());

    public SettingsPanel() {

        JPanel cursorRow = makeRow("Cursor Settings:", imageButton);
        JPanel guiColorRow = makeRow("Change GUI Color:", guiColorButton);
        JPanel textColorRow = makeRow("Change Text Color",textColorButton);
        JPanel checkMarkRow = makeRow("Use Default Checkmarks:", checkmarksButton);
        JPanel logRow = makeRow("Change Log level:",dropdown);
        JPanel soundsRow = makeRow("Enable/Disable Sounds:",soundsButton);
        dropdown.setSelectedIndex(options.length -1);

        imageButton.addHoverWord("Make sure its democracy approved");
        guiColorButton.addHoverWord("Using a color unaffiliated with super earth is treason");
        textColorButton.addHoverWord("Using a color unaffiliated with super earth is treason");
        checkmarksButton.addHoverWord("Your willingness to commit treason is noted helldiver");
        soundsButton.addHoverWord("What the button says");
        standardCursorButton.addHoverWord("This is what you have your computers cursor as");
        hellDiversCursorButton.addHoverWord("Will use the Helldivers 2 cursor");

        isoPanel.setPreferredSize(new Dimension(500, 600));
        panel.setBackground(bgColor);

        standardCursorButton.addActionListener(e-> settingsManager.setCursorSetting("std"));
        hellDiversCursorButton.addActionListener(e -> settingsManager.setCursorSetting("hd2"));
        imageButton.addActionListener(e ->{


            fileChooser.showOpenDialog();
            if (fileChooser.getSelectedFile() != null) {
                String selectedFile = fileChooser.getSelectedFile().toPath().toString();
                settingsManager.setCursorSetting(selectedFile);
            }
        });
        guiColorButton.addActionListener(e ->{
            Color newColor = customColorChooser.showDialog(getPanel(),fgColor);
            settingsManager.setColor("Label_Color", newColor);

        });
        textColorButton.addActionListener(e ->{
            Color newColor = customColorChooser.showDialog(getPanel(),fgColor);
            settingsManager.setColor("Text_Color", newColor);
        });
        checkmarksButton.addActionListener(e -> {
            changeCheckboxButtonText((JButton) e.getSource());
            changeCheckboxButtonSetting();
        });

        soundsButton.addActionListener(e -> {
            changeSoundsButtonText((JButton) e.getSource());
            changeSoundsSetting();
        });

        JLabel hd2Symbol = makeHD2Symbol();
        JLabel noteLabel = makeNoteLabel();

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
                                .addComponent(guiColorRow)
                                .addComponent(textColorRow)
                                .addComponent(checkMarkRow)
                                .addComponent(logRow)
                                .addComponent(soundsRow)
                                .addComponent(hd2Symbol)
                                .addComponent(noteLabel)
                        )
                        .addGap(0, 0, Short.MAX_VALUE) // pushes everything else right
        );

        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup()
                        .addComponent(isoPanel)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(cursorRow)
                                .addComponent(guiColorRow)
                                .addComponent(textColorRow)
                                .addComponent(checkMarkRow)
                                .addComponent(logRow)
                                .addComponent(soundsRow)
                                .addComponent(hd2Symbol)
                                .addComponent(noteLabel)
                        )
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
        l.setForeground(fgColor);

        if(label.equals("Cursor Settings:")){
            p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
            p.add(l);
            p.add(Box.createRigidArea(new Dimension(5,0)));
            p.add(component);
            p.add(Box.createRigidArea(new Dimension(5,0)));
            p.add(standardCursorButton);
            p.add(Box.createRigidArea(new Dimension(5,0)));
            p.add(hellDiversCursorButton);
        }else{
            p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
            p.add(l);
            p.add(Box.createRigidArea(new Dimension(5,0)));
            p.add(component);
        }

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
    private JLabel makeHD2Symbol(){
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/HD2_header.png")));
        Image scaled = icon.getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH);

        JLabel label = new JLabel(new ImageIcon(scaled));
        label.setPreferredSize(new Dimension(400,100));
        label.setOpaque(false);
        return label;
    }
    private JLabel makeNoteLabel(){
        JLabel label = new JLabel("Restart App To Load New Settings");
        label.setFont(new Font("FS Sinclair", Font.BOLD, 20));
        return label;
    }
}
