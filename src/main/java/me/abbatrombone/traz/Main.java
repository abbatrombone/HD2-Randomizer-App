package me.abbatrombone.traz;

import me.abbatrombone.traz.Utilities.LoggerSetup;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        LoggerSetup.initializeLogger();
        SwingUtilities.invokeLater(HDApp::new);
//        feel();
//        SwingUtilities.invokeLater( () -> {
//            new HDApp();
//        } );
//    }
//
//    static void feel() {
//        try {
//            for( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() ) {
//                if( "Nimbus".equals( info.getName() ) ) {
//                    javax.swing.UIManager.setLookAndFeel( info.getClassName() );
//                    break;
//
//                }
//            }
//        }
//        catch( ClassNotFoundException | InstantiationException
//               | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex ) {
//            java.util.logging.Logger.getLogger( HDApp.class
//                            .getName() )
//                    .log( java.util.logging.Level.SEVERE, null, ex );
//        }
//
    }
}


