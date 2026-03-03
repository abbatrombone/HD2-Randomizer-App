package me.abbatrombone.traz;

import me.abbatrombone.traz.Managers.OSManager;
import me.abbatrombone.traz.Utilities.LoggerSetup;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        LoggerSetup.initializeLogger();
        createEssentialFiles();
        SwingUtilities.invokeLater(HDApp::new);
    }
    private static void createEssentialFiles(){
        String checkBoxFilePath = OSManager.getConfigDirectory() + "/checkbox.json";

        File checkBoxFile = new File(checkBoxFilePath);
        Path checkBoxPath = Paths.get(checkBoxFilePath);

        if(!checkBoxFile.exists()){
            try {
                if (checkBoxFile.createNewFile()) {
                    InputStream in = Main.class.getClassLoader().getResourceAsStream("checkbox.json");
                    assert in != null;
                    Files.copy(in, checkBoxPath, StandardCopyOption.REPLACE_EXISTING);
                    Logger.getAnonymousLogger().log(Level.WARNING,"File created: " + checkBoxFile.getName());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


