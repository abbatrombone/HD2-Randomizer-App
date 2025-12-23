package me.abbatrombone.traz.Panels.ButtonActions;

import me.abbatrombone.traz.GameItems.Stratagems;
import me.abbatrombone.traz.GameItems.Warbonds;
import me.abbatrombone.traz.Panels.GearPanel;
import me.abbatrombone.traz.Panels.MainPanel;
import me.abbatrombone.traz.Panels.SelectBondsPanel;
import me.abbatrombone.traz.Utilities.StringParser;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomLoadOut {

    private static String primaryWeapon;
    private static String secondaryWeapon;
    private static String throwable;
    private static String armorLevel;
    private static String armorPassive;
    private static List<String> boosterList;
    private static String enemyType;

    public static String result() {
        warbondArmor();
        warbondPrimary();
        warbondThrowable();
        warbondSecondary();
        warbondBooster();
        enemyType();
        String difficulty = diff();

        return "Hello Helldiver, General Brash has demanded that you use the following on your next " + operation(enemyType) + " operation against the " + enemyType + "\n\n" +
                "Difficulty: " + difficulty + "\n" +
                "Your weapon is: " + primaryWeapon + "\n" +
                "Your secondary is: " + secondaryWeapon + "\n" +
                "Your throwable is: " + throwable + "\n" +
                "Armor: " + armorLevel + " armor with the " + armorPassive + " Passive." + "\n\n" +
                "Your stratagems will be the following:\n" + warbondStratagems() + "\n\n" +
                "Your Booster is: \n" + boosterFormat() + "\n" +
                randomPhrase();
    }
    public static String warbondStratagems() {
        SelectBondsPanel selectBondsPanel = MainPanel.getSelectBondsPanel();
        List<String> stratagemsList = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        StringParser stringParser = new StringParser();

        for (JCheckBox box : selectBondsPanel.getCheckBoxes()) {
            if (box.isSelected()) {
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(box.getName());
                for (String strat : bond.getStratagem()) {
                    if (!strat.isBlank() && seen.add(strat)) {
                        stratagemsList.add(stringParser.parseStrategem(strat));
                    }
                }
            }
        }

        Collections.shuffle(stratagemsList, ThreadLocalRandom.current());

        String patriot = "EXO-45 Patriot Exosuit";
        String emancipator = "EXO-49 Emancipator Exosuit";

        List<String> top4 = new ArrayList<>(stratagemsList.subList(0, 4));

        if (top4.contains(patriot) && top4.contains(emancipator)) {
            top4.remove(ThreadLocalRandom.current().nextBoolean()
                    ? patriot
                    : emancipator);
            //this exists if a 3rd mech is added
//            for (int i = 4; i < stratagemsList.size(); i++) {
//                String candidate = stratagemsList.get(i);
//                if (!top4.contains(candidate)) {
//                    top4.add(candidate);
//                    break;
//                }
//            }
            top4.add(stratagemsList.get(5)); //replaces combo with next item on the list.
        }
        return String.join("\n", top4);
    }
    public static void enemyType(){

        Random enemyran = new Random();
        int emenyInt = enemyran.nextInt(3 -1 +1) +1;

        switch (emenyInt){
            case 1-> enemyType = "Terminids";
            case 2-> enemyType = "Automatons";
            case 3-> enemyType = "Illuminate";
            default -> enemyType = "Automatons";
        }
        MainPanel.getOutputTextPane().updateImage(enemyType);
    }

    public static String diff(){
        String diff = "";
        Random diffran = new Random();
        int diffInt = diffran.nextInt(7 -1 +1) +1;

        switch (diffInt){
            case 1-> diff = "Challenging";
            case 2-> diff = "Hard";
            case 3-> diff = "Extreme";
            case 4-> diff = "Sui Mission";
            case 5-> diff = "Impossible";
            case 6-> diff = "Helldive";
            case 7-> diff = "Super Helldive";
        }
        return diff;
    }
    public static void warbondBooster(){
        SelectBondsPanel selectBondsPanel = MainPanel.getSelectBondsPanel();
        ArrayList<String> List = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for(JCheckBox box : selectBondsPanel.getCheckBoxes()){
            if(box.isSelected()){
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(box.getName());
                for(String booster : bond.getOpt()){
                    if (seen.add(booster) && !booster.isBlank()) {
                        List.add(booster);
                    }
                }
            }
        }
        Collections.shuffle(List, ThreadLocalRandom.current());

        boosterList = List.subList(0,4);
        //opt = boosterList.get(ThreadLocalRandom.current().nextInt(boosterList.size()));
    }
    public static String boosterFormat(){
        return "1. " + boosterList.get(0) + "\n" +  "2. " + boosterList.get(1) + "\n" + "3. " + boosterList.get(2) + "\n" + "4. " + boosterList.get(3);
    }
    public static String operation(String enemy){
        String operation = "";
        Random operationran = new Random();

        if(enemy.equals("Terminids") || enemy.equals("Automatons")){
            int operInt = operationran.nextInt(2 - 1 + 1) +1;
            switch (operInt){
                case 1 -> operation = "Peacekeeping";
                case 2 -> operation = "Defend Planet";
            }
        } else {operation = "Defend Planet";}

        return operation;
    }
    public static String randomPhrase(){
        String phrase = "";

        Random phraseran = new Random();
        int phrasefranInt = phraseran.nextInt(7 -1 +1) +1;

        switch (phrasefranInt) {
            case 1 -> phrase = "Now go distribute some managed democracy!";
            case 2 -> phrase = "Show them what democracy's arsenal looks like";
            case 3 -> phrase = "Make sure to show up alive, for your next assignment helldiver";
            case 4 -> phrase = "The ministry of science looks forward to your results";
            case 5 -> phrase = "NOW DIVE! DIVE! DIVE! HELLDIVER!!!";
            case 6 -> phrase = "Your victory is assured with this load out";
            case 7 -> phrase = "The only thing more powerful than this is democracy";

        }
        return phrase;
    }
    public static void warbondArmor(){
        SelectBondsPanel selectBondsPanel = MainPanel.getSelectBondsPanel();
        ArrayList<String> armorList = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        StringParser stringParser = new StringParser();

        for(JCheckBox box : selectBondsPanel.getCheckBoxes()){
            if(box.isSelected()){
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(box.getName());
//                String[] armors = bond.getArmor();
//
//                for (String armor : armors) {
//                    if (armor != null && !armor.isBlank() && !armorList.contains(armor)) {
//                        armorList.add(armor);
//                    }
//                }
                for (String armor : bond.getArmor()) {
                    if (seen.add(armor)) {
                        armorList.add(armor);
                    }
                }
            }
        }
        String selectedArmor = armorList.get(ThreadLocalRandom.current().nextInt(armorList.size()));

        armorLevel = stringParser.parseArmorLevel(selectedArmor);
        armorPassive = stringParser.parseArmorPassive(selectedArmor);
    }
    public static void warbondPrimary(){
        SelectBondsPanel selectBondsPanel = MainPanel.getSelectBondsPanel();
        ArrayList<String> primaryList = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for(JCheckBox box : selectBondsPanel.getCheckBoxes()){
            if(box.isSelected()){
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(box.getName());
                for(String primary : bond.getPrimary()){
                    if (seen.add(primary) && !primary.isBlank()) {
                        primaryList.add(primary);
                    }
                }
            }
        }
        primaryWeapon = primaryList.get(ThreadLocalRandom.current().nextInt(primaryList.size()));
    }
    public static void warbondThrowable(){
        SelectBondsPanel selectBondsPanel = MainPanel.getSelectBondsPanel();
        ArrayList<String> throwableList = new ArrayList<>();
        StringParser stringParser = new StringParser();
        Set<String> seen = new HashSet<>();

        for(JCheckBox box : selectBondsPanel.getCheckBoxes()){
            if(box.isSelected()){
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(box.getName());
                for(String thrown : bond.getThrowable()){
                    if (seen.add(thrown) && !thrown.isBlank()) {
                        throwableList.add(thrown);
                    }
                }
            }
        }
        throwable = stringParser.parseThrowable(throwableList.get(ThreadLocalRandom.current().nextInt(throwableList.size())));
    }
    public static void warbondSecondary(){
        SelectBondsPanel selectBondsPanel = MainPanel.getSelectBondsPanel();
        ArrayList<String> secondaryList = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for(JCheckBox box : selectBondsPanel.getCheckBoxes()){
            if(box.isSelected()){
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(box.getName());
                for(String secondary : bond.getSecondary()){
                    if (seen.add(secondary) && !secondary.isBlank()) {
                        secondaryList.add(secondary);
                    }
                }
            }
        }
        secondaryWeapon = secondaryList.get(ThreadLocalRandom.current().nextInt(secondaryList.size()));
    }
}
