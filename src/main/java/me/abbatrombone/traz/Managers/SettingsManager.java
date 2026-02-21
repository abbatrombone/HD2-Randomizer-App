package me.abbatrombone.traz.Managers;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsManager {
    private final Properties settings = new Properties();
    private final File settingsFile = new File(OSManager.getConfigDirectory() + "/settings.properties");

    public SettingsManager() {
        loadSettings();
    }

    public Color getColor(String key, String defaultHex) {
        try {
            return Color.decode(settings.getProperty(key, defaultHex));
        } catch (NumberFormatException e) {
            return Color.decode(defaultHex);
        }
    }

    public void setColor(String key, Color color) {
        settings.setProperty(key, "#" + Integer.toHexString(color.getRGB()).substring(2));
        saveSettings();
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
    public String getCursorSettings() {
        return settings.getProperty("Cursor", "HD2");
    }
    public void setCursorSetting(String cursor){
        settings.setProperty("Cursor",cursor);
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
