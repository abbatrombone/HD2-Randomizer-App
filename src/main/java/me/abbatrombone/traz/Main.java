package me.abbatrombone.traz;

import me.abbatrombone.traz.Utilities.LoggerSetup;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test Push");
        LoggerSetup.initializeLogger();
        SwingUtilities.invokeLater(HDApp::new);
    }
}


