package me.abbatrombone.traz.Utilities;

import me.abbatrombone.traz.Main;
import me.abbatrombone.traz.Managers.OSManager;
import me.abbatrombone.traz.Managers.SettingsManager;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.Logger;

public class LoggerSetup {
    public static void initializeLogger() {
        Logger rootLogger = java.util.logging.Logger.getLogger(""); // Root logger
        final SettingsManager settingsManager = new SettingsManager();
        Level level;
        switch(settingsManager.getLogLevel()){
            case "all"     -> level = Level.ALL;
            case "config"  -> level = Level.CONFIG;
            case "fine"    -> level = Level.FINE;
            case "finer"   -> level = Level.FINER;
            case "finest"  -> level = Level.FINEST;
            case "Info"    -> level = Level.INFO;
            case "off"     -> level = Level.OFF;
            case "servere" -> level = Level.SEVERE;
            case "warning" -> level = Level.WARNING;
            default -> level = Level.WARNING;
        }

        // Remove all default handlers (avoids double logging)
        Handler[] handlers = rootLogger.getHandlers();
        for (Handler handler : handlers) {
            rootLogger.removeHandler(handler);
        }

        try {
            // File logging
            File logFile = OSManager.getLogFile();
            System.out.println("File location: "+logFile);
            FileHandler fileHandler = new FileHandler(logFile.getAbsolutePath(), true); // Append to log file
            //FileHandler fileHandler = new FileHandler("app.log", true); // Append to log file
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(level);
            rootLogger.addHandler(fileHandler);

            // Console logging
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            rootLogger.addHandler(consoleHandler);

            // Set global logging level
            rootLogger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Logger setup failed: " + e.getMessage());
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error IOException", e);
        }
    }
}
