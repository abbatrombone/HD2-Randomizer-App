package me.abbatrombone.traz.Panels.ButtonActions;

import me.abbatrombone.traz.GameItems.Warbonds;
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
    private static String enemyType = "";
    private static String stratgems = "";
    private final static StringParser p = new StringParser();

    public static String result(List<String> selectedBondNames) {
        warbondArmor(selectedBondNames);
        warbondPrimary(selectedBondNames);
        warbondThrowable(selectedBondNames);
        warbondSecondary(selectedBondNames);
        warbondBooster(selectedBondNames);
        warbondStratagems(selectedBondNames);
        enemyType();
        String difficulty = diff();
        StringBuilder sb = new StringBuilder();
        sb.append( "Hello Helldiver, General Brash has demanded that you use the following on your next ");
        sb.append(operation(enemyType));
        sb.append(" operation against the ");
        sb.append(enemyType);
        sb.append("\n\nDifficulty: ");
        sb.append(difficulty);
        sb.append("\n");
        sb.append("Your weapon is: ").append(p.parsePrimaryName(primaryWeapon)).append("\n");
        sb.append( "Your secondary is: ").append(p.parseSecondaryName(secondaryWeapon)).append("\n");
        sb.append("Your throwable is: ").append(p.parseThrowable(throwable)).append("\n");
        sb.append( "Armor: ").append(armorLevel).append(" armor with the ").append(armorPassive).append(" Passive.").append("\n\n");
        sb.append("Your stratagems will be the following:\n").append(stratgems).append("\n\n");
        sb.append( "Your Booster is: \n").append(boosterFormat()).append("\n");
        sb.append(randomPhrase());

        return sb.toString();
    }
    public static List<String> getSelectedBondNames() {
        SelectBondsPanel panel = MainPanel.getSelectBondsPanel();
        List<String> selected = new ArrayList<>();

        for (JCheckBox box : panel.getCheckBoxes()) {
            if (box.isSelected()) {
                selected.add(box.getName());
            }
        }
        return selected;
    }

public static void warbondStratagems(List<String> selectedBondNames) {
    List<String> stratagemsList = new ArrayList<>();
    Set<String> seen = new HashSet<>();
    StringParser stringParser = new StringParser();

    for (String name : selectedBondNames) {
        Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);
        for (String strat : bond.getStratagem()) {
            if (!strat.isBlank() && seen.add(strat)) {
                stratagemsList.add(stringParser.parseStrategem(strat));
            }
        }
    }

    for (String s : Warbonds.Bonds.Cadet_Loadout.getStratagem()) {
        stratagemsList.add(stringParser.parseStrategem(s));
    }

    Collections.shuffle(stratagemsList, ThreadLocalRandom.current());

    String patriot = "EXO-45 Patriot Exosuit";
    String emancipator = "EXO-49 Emancipator Exosuit";

    int count = Math.min(4, stratagemsList.size());
    List<String> top4 = new ArrayList<>(stratagemsList.subList(0, count));

    if (top4.contains(patriot) && top4.contains(emancipator) && stratagemsList.size() > 5) {
        top4.remove(ThreadLocalRandom.current().nextBoolean()
                ? patriot
                : emancipator);
        top4.add(stratagemsList.get(5));
    }
    stratgems = String.join("\n", top4);
}
    public static void enemyType(){
        int emenyInt = ThreadLocalRandom.current().nextInt(1, 4); //bound is exclusive

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
        int diffInt = ThreadLocalRandom.current().nextInt(1, 8); //bound is exclusive

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
    public static void warbondBooster(List<String> selectedBondNames) {
        Set<String> boosters = new HashSet<>();

        for (String name : selectedBondNames) {
            Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);

            for (String booster : bond.getOpt()) {
                if (!booster.isBlank()) {
                    boosters.add(booster);
                }
            }
        }

        List<String> boosterListTemp = new ArrayList<>(boosters);
        Collections.shuffle(boosterListTemp, ThreadLocalRandom.current());

        boosterList = boosterListTemp
                .stream()
                .limit(4)
                .toList();
    }
    public static String boosterFormat(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < boosterList.size(); i++) {
            sb.append(i + 1).append(". ").append(boosterList.get(i)).append("\n");
        }
        return sb.toString();
    }
    public static String operation(String enemy){
        String operation = "";

        if(enemy.equals("Terminids") || enemy.equals("Automatons")){
            int operInt = ThreadLocalRandom.current().nextInt(1, 3);
            switch (operInt){
                case 1 -> operation = "Peacekeeping";
                case 2 -> operation = "Defend Planet";
            }
        } else {operation = "Defend Planet";}

        return operation;
    }
    public static String randomPhrase(){
        String phrase = "";

        int phrasefranInt = ThreadLocalRandom.current().nextInt(1, 8);

        switch (phrasefranInt) {
            case 1 -> phrase = "Now go distribute some managed democracy!";
            case 2 -> phrase = "Show them what democracy's arsenal looks like!";
            case 3 -> phrase = "Make sure to show up alive, for your next assignment helldiver!";
            case 4 -> phrase = "The ministry of science looks forward to your results!";
            case 5 -> phrase = "NOW DIVE! DIVE! DIVE! HELLDIVER!!!";
            case 6 -> phrase = "Your victory is assured with this load out!";
            case 7 -> phrase = "The only thing more powerful than this is democracy!";

        }
        return phrase;
    }
    public static void warbondArmor(List<String> selectedBondNames){
        ArrayList<String> armorList = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (String name : selectedBondNames) {
            Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);
            for (String armor : bond.getArmor()) {
                if (seen.add(armor)) {
                    armorList.add(armor);
                }
            }
        }

        armorList.add(Arrays.toString(Warbonds.Bonds.Cadet_Loadout.getArmor()));
        String selectedArmor = armorList.get(ThreadLocalRandom.current().nextInt(armorList.size()));

        armorLevel = p.parseArmorLevel(selectedArmor);
        armorPassive = p.parseArmorPassive(selectedArmor);
    }
    public static void warbondPrimary(List<String> selectedBondNames){

        ArrayList<String> primaryList = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (String name : selectedBondNames) {
            Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);
            for (String primary : bond.getPrimary()) {
                if (seen.add(primary) && !primary.isBlank()) {
                    primaryList.add(primary);
                }
            }
        }

        primaryList.add(Arrays.toString(Warbonds.Bonds.Cadet_Loadout.getPrimary()));
        primaryWeapon = primaryList.get(ThreadLocalRandom.current().nextInt(primaryList.size()));
    }
    public static void warbondThrowable(List<String> selectedBondNames){

        ArrayList<String> throwableList = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (String name : selectedBondNames) {
            Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);
            for (String thrown : bond.getThrowable()) {
                if (seen.add(thrown) && !thrown.isBlank()) {
                    throwableList.add(thrown);
                }
            }
        }

        throwableList.add(Arrays.toString(Warbonds.Bonds.Cadet_Loadout.getThrowable()));
        throwable = throwableList.get(ThreadLocalRandom.current().nextInt(throwableList.size()));
    }
    public static void warbondSecondary(List<String> selectedBondNames){
        ArrayList<String> secondaryList = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (String name : selectedBondNames) {
            Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);
            for (String secondary : bond.getSecondary()) {
                if (seen.add(secondary) && !secondary.isBlank()) {
                    secondaryList.add(secondary);
                }
            }
        }
        secondaryList.add(Arrays.toString(Warbonds.Bonds.Cadet_Loadout.getSecondary()));
        secondaryWeapon = secondaryList.get(ThreadLocalRandom.current().nextInt(secondaryList.size()));
    }
    public static String getPrimaryWeapon() {
        return primaryWeapon;
    }

    public static String getSecondaryWeapon() {
        return secondaryWeapon;
    }

    public static String getThrowable() {
        return throwable;
    }

    public static String getArmorLevel() {
        return armorLevel;
    }

    public static String getArmorPassive() {
        return armorPassive;
    }

    public static String getEnemyType() {
        return enemyType;
    }
    public static String getStratgems() {
        return stratgems;
    }
    public static List<String> getBoosterList() {
        return boosterList;
    }
}
