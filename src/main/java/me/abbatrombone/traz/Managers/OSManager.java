package me.abbatrombone.traz.Managers;

import java.io.File;
import java.io.IOException;

public class OSManager {
    public static void storeData(String appName){
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("OS: "+os);
        String baseDir;

        if(getOperatingSystem().contains("win")){baseDir = System.getenv("Helldivers2_Randomizer");}
        else if (getOperatingSystem().contains("mac")) {baseDir = System.getProperty("user.home") + "/Library/Application Support";}
        else {baseDir = System.getProperty("user.home") + "/.local/share/";}

        System.out.println("Dir: "+baseDir);
        File appDataDir = new File(baseDir, appName);

        if (appDataDir.mkdirs()) {System.out.println("Diretory created: " + appDataDir.getName());}
    }
    public static File getBaseAppDataDirectory(){
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("OS: "+os);
        String baseDir;

        if(getOperatingSystem().contains("win")){baseDir = System.getenv("Helldivers2_Randomizer");}
        else if (getOperatingSystem().contains("mac")) {baseDir = System.getProperty("user.home") + "/Library/Application Support";}
        else {baseDir = System.getProperty("user.home") + "/.local/share/";}

        System.out.println("Dir: "+baseDir);
        return new File(baseDir, "Helldivers2_Randomizer");
    }
    public static File getConfigDirectory() {
        File configDir = new File(getBaseAppDataDirectory(), "config");
        if(configDir.mkdirs()){System.out.println("Diretory created: " + configDir.getName());}

        return configDir;
    }
    public static File getCacheDirectory() {
        File cacheDir = new File(getBaseAppDataDirectory(), "cache");
        if (cacheDir.mkdirs()) {System.out.println("Diretory created: " + cacheDir.getName());}
        return cacheDir;
    }
    public static File getLogDirectory() {
        File logDir = new File(getBaseAppDataDirectory(), "logs");
        if (logDir.mkdirs()) {System.out.println("Diretory created: " + logDir.getName());}

        File logFile = new File(logDir, "app.log");
        try {
            if(logFile.createNewFile()){System.out.println("File created: " + logFile.getName());}
            else {System.out.println("File already exists.");}
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return logDir;
    }
    public static String getOperatingSystem() {
        return System.getProperty("os.name").toLowerCase();
    }
    public static File getLogFile() {
        return new File(getLogDirectory(), "app.log");
    }

    public static File getAppDataDirectory() {
        return getBaseAppDataDirectory();
    }
}