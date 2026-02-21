package me.abbatrombone.traz.Managers;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OSManager {
    private static final Logger logger = Logger.getLogger(OSManager.class.getName());

    public static File getBaseAppDataDirectory(){
        String os = System.getProperty("os.name").toLowerCase();
        logger.log(Level.FINER,"OS: "+os);
        String baseDir;

        if(getOperatingSystem().contains("win")){baseDir = System.getenv("Helldivers2_Randomizer");}
        else if (getOperatingSystem().contains("mac")) {baseDir = System.getProperty("user.home") + "/Library/Application Support";}
        else {baseDir = System.getProperty("user.home") + "/.local/share/";}

        logger.log(Level.FINER,"Base App Data Dir: "+baseDir);
        return new File(baseDir, "Helldivers2_Randomizer");
    }
    public static File getConfigDirectory() {
        File configDir = new File(getBaseAppDataDirectory(), "config");
        if(configDir.mkdirs()){logger.log(Level.FINER,"Diretory created: " + configDir.getName());}

        return configDir;
    }
    public static File getCacheDirectory() {
        File cacheDir = new File(getBaseAppDataDirectory(), "cache");
        if (cacheDir.mkdirs()) {logger.info("Made Cache Diretory");}
        return cacheDir;
    }
    public static File getLogDirectory() {
        File logDir = new File(getBaseAppDataDirectory(), "logs");
        if (logDir.mkdirs()) {logger.log(Level.FINER,"Diretory created: " + logDir.getName());}

        File logFile = new File(logDir, "app.log");
        try {
            if(logFile.createNewFile()){logger.log(Level.FINER,"File created: " + logFile.getName());}
            else {logger.log(Level.FINER,("File already exists."));}
        } catch (IOException e) {
            logger.log(Level.FINER,"An error occurred.");
            logger.log(Level.WARNING, e.toString());
        }

        return logDir;
    }
    public static String getOperatingSystem() {
        return System.getProperty("os.name").toLowerCase();
    }
    public static File getLogFile() {
        return new File(getLogDirectory(), "app.log");
    }
}