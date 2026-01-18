package me.abbatrombone.traz.Managers;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsManager {
    private final Properties settings = new Properties();
    private final File settingsFile = new File("settings.properties");

    public SettingsManager() {
        loadSettings();
    }

    public Color getTextColor() {
        return getColor("textColor", "#000000");
    }

    public Color getBackgroundColor() {
        return getColor("bgColor", "#FFFFFF");
    }
    public boolean isEditable() {
        return Boolean.parseBoolean(settings.getProperty("editable", "true"));
    }

    public void setTextColor(Color color) {
        setColor("textColor", color);
        saveSettings();
    }

    public void setBackgroundColor(Color color) {
        setColor("bgColor", color);
        saveSettings();
    }

    private Color getColor(String key, String defaultHex) {
        try {
            return Color.decode(settings.getProperty(key, defaultHex));
        } catch (NumberFormatException e) {
            return Color.decode(defaultHex);
        }
    }

    private void setColor(String key, Color color) {
        settings.setProperty(key, "#" + Integer.toHexString(color.getRGB()).substring(2));
    }

    private void loadSettings() {
        if (settingsFile.exists()) {
            try (FileInputStream fis = new FileInputStream(settingsFile)) {
                settings.load(fis);
            } catch (IOException e) {
                Logger.getLogger(SettingsManager.class.getName()).log(Level.SEVERE, "Error loading settings", e);
                JOptionPane.showMessageDialog(null, "Error loading settings.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveSettings() {
        try (FileOutputStream fos = new FileOutputStream(settingsFile)) {
            settings.store(fos, null);
        } catch (IOException e) {
            Logger.getLogger(SettingsManager.class.getName()).log(Level.SEVERE, "Error saving settings", e);
            JOptionPane.showMessageDialog(null, "Error saving settings.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public String getLogLevel() {
        return settings.getProperty("logLevel", "warning");
    }
    public void setLogLevel(String level) {
        settings.setProperty("logLevel",level);
        saveSettings();
    }
    public String getBackgroundImg(){
        return settings.getProperty("ImagiePath", Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/Screenshot from 2025-06-14 00-03-27.png")).toString()));
    }
    public void setBackgroundImg(String absolutePath){
        settings.setProperty("imagiePath",absolutePath);
        saveSettings();
    }
    public boolean isHardHatCursor() {
        return Boolean.parseBoolean(settings.getProperty("Cursor_On/Off", "true"));
    }
    public void setCursorSetting(String truthy){
        settings.setProperty("Cursor_On/Off",truthy);
        saveSettings();
    }
    public boolean defaultCheckboxIsOn(){return Boolean.parseBoolean(settings.getProperty("Checkbox_On/Off", "true"));}
    public void setCheckboxSetting(String truthy){
        settings.setProperty("Checkbox_On/Off",truthy);
        saveSettings();
    }
    public boolean soundIsOn(){return Boolean.parseBoolean(settings.getProperty("Sound_On/Off", "false"));}
    public void setSoundSetting(String truthy){
        settings.setProperty("Sound_On/Off",truthy);
        saveSettings();
    }
}
