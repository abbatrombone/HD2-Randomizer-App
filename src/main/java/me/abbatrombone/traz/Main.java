package me.abbatrombone.traz;

import me.abbatrombone.traz.Utilities.LoggerSetup;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        LoggerSetup.initializeLogger();
        SwingUtilities.invokeLater(HDApp::new);
    }
}


