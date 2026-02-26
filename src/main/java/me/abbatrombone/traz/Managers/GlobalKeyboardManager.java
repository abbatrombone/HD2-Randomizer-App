package me.abbatrombone.traz.Managers;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;

public class GlobalKeyboardManager {
    private JButton randomButton;
    private JButton semiButton;
    private JButton winButton;
    private JButton loseButton;

    public GlobalKeyboardManager(){
        GlobalScreen.setEventDispatcher(new SwingDispatchService());
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
                @Override
                public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
                    NativeKeyListener.super.nativeKeyReleased(nativeEvent);
                    switch (nativeEvent.getKeyCode()){
                        case NativeKeyEvent.VC_MINUS ->  {if(loseButton != null){loseButton.doClick();}}
                        case NativeKeyEvent.VC_EQUALS -> {if(winButton != null){winButton.doClick();}}
                        case NativeKeyEvent.VC_F11 ->    {if(semiButton != null){semiButton.doClick();}}
                        case NativeKeyEvent.VC_F10 ->    {if(randomButton != null){randomButton.doClick();}}
                    }
                }
            });
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
    }
    public JButton getRandomButton() {
        return randomButton;
    }

    public void setRandomButton(JButton randomButton) {
        this.randomButton = randomButton;
    }

    public JButton getSemiButton() {
        return semiButton;
    }

    public void setSemiButton(JButton semiButton) {
        this.semiButton = semiButton;
    }

    public JButton getWinButton() {
        return winButton;
    }

    public void setWinButton(JButton winButton) {
        this.winButton = winButton;
    }

    public JButton getLoseButton() {
        return loseButton;
    }

    public void setLoseButton(JButton loseButton) {
        this.loseButton = loseButton;
    }
}
