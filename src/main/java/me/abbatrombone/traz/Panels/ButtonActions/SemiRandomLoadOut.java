package me.abbatrombone.traz.Panels.ButtonActions;

import me.abbatrombone.traz.GameItems.Warbonds;
import me.abbatrombone.traz.Panels.MainPanel;
import me.abbatrombone.traz.Panels.SelectBondsPanel;
import me.abbatrombone.traz.Utilities.StringParser;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SemiRandomLoadOut {

    private static String secondaryWeapon;
    private static String throwable;
    private static String armorLevel;
    private static List<String> boosterList;
    private static String enemyType;
    private static String supportWeapon;
    private static String secondStrat;
    private static String thirdStrat;
    private static String forthStrat;
    private static final StringParser stringParser = new StringParser();
    public static String result() {

        enemyType = enemyType();
        warbondSecondary();
        warbondThrowable();
        warbondBooster();
        supportWeapon();
        OtherStratagems();
        armorLevel = armorLevel();
        System.out.println(secondaryWeapon);
        System.out.println();
        System.out.println(throwable);
        System.out.println();

        return "Hello Helldiver, General Brash has demanded that you use the following on your next " + operation(enemyType) + " operation against the " + enemyType+ "\n\n" +
                "Difficulty: " + diff() + "\n" +
                "Your weapon category is: " + primaryCategory() + "\n" +
                "Your secondary is: " +  stringParser.parseSecondaryName(secondaryWeapon) + "\n" +
                "Your throwable is: " + stringParser.parseThrowable(throwable) + "\n" +
                "Armor: " + armorLevel + " armor with any passive \n\n" +
                "Your stratagems will be the following:\n" + stringParser.parseStrategem(supportWeapon) + "\n" +
                stringParser.parseStrategem(secondStrat) + "\n" +
                stringParser.parseStrategem(thirdStrat) + "\n" +
                stringParser.parseStrategem(forthStrat) + "\n\n" +
                "Your Booster is: \n" + boosterFormat() +"\n" +
                randomPhrase();
    }

    public static String armorLevel(){
        String armorLevel = "";
        Random armortyperan = new Random();
        int armorLevelInt = armortyperan.nextInt(3-1 + 1) +1;
        switch (armorLevelInt) {
            case 1-> armorLevel = "Light";
            case 2-> armorLevel = "Medium";
            case 3-> armorLevel = "Heavy";
        }

        return armorLevel;
    }
    public static String primaryCategory(){
        SelectBondsPanel selectBondsPanel = MainPanel.getSelectBondsPanel();
        ArrayList<String> primaryList = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for(JCheckBox box : selectBondsPanel.getCheckBoxes()){
            if(box.isSelected()){
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(box.getName());
                for(String primary : bond.getPrimary()){
                    if (seen.add(stringParser.parsePrimaryType(primary)) && !stringParser.parsePrimaryType(primary).isBlank()) {
                        primaryList.add(stringParser.parsePrimaryType(primary));
                    }
                }
            }
        }
        StringParser p = new StringParser();
        Collections.addAll(primaryList,p.parsePrimaryType(Arrays.toString(Warbonds.Bonds.Cadet_Loadout.getPrimary())));
        return primaryList.get(ThreadLocalRandom.current().nextInt(primaryList.size()));
    }

    public static String warbondStratagems() {
        //Need to update to be semi random
        SelectBondsPanel selectBondsPanel = MainPanel.getSelectBondsPanel();
        List<String> stratagemsList = new ArrayList<>();
        Set<String> seen = new HashSet<>();

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
        String[] basic = Warbonds.Bonds.Cadet_Loadout.getStratagem();
        for(String s : basic){
            stratagemsList.add(stringParser.parseStrategem(s));
        }

        Collections.shuffle(stratagemsList, ThreadLocalRandom.current());

        String patriot = "EXO-45 Patriot Exosuit";
        String emancipator = "EXO-49 Emancipator Exosuit";

        List<String> top4 = new ArrayList<>(stratagemsList.subList(0, 4));

        if (top4.contains(patriot) && top4.contains(emancipator)) {
            top4.remove(ThreadLocalRandom.current().nextBoolean()
                    ? patriot
                    : emancipator);
            top4.add(stratagemsList.get(5)); //replaces combo with next item on the list.
        }
        return String.join("\n", top4);
    }
    public static String enemyType(){

        Random enemyran = new Random();
        int emenyInt = enemyran.nextInt(3 -1 +1) +1;

        switch (emenyInt){
            case 1-> enemyType = "Terminids";
            case 2-> enemyType = "Automatons";
            case 3-> enemyType = "Illuminate";
        }
        MainPanel.getOutputTextPane().updateImage(enemyType);
        return  enemyType;
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
    public static void warbondThrowable(){
        SelectBondsPanel selectBondsPanel = MainPanel.getSelectBondsPanel();
        ArrayList<String> throwableList = new ArrayList<>();
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

        Collections.addAll(throwableList,Warbonds.Bonds.Cadet_Loadout.getThrowable());
        throwable = throwableList.get(ThreadLocalRandom.current().nextInt(throwableList.size()));
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

        Collections.addAll(secondaryList,Warbonds.Bonds.Cadet_Loadout.getSecondary());
        secondaryWeapon = secondaryList.get(ThreadLocalRandom.current().nextInt(secondaryList.size()));
    }
    public static void supportWeapon() {

        SelectBondsPanel selectBondsPanel = MainPanel.getSelectBondsPanel();
        List<String> sList = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for(JCheckBox box : selectBondsPanel.getCheckBoxes()){
            if (box.isSelected()) {
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(box.getName());
                for(String st: bond.getStratagem()){
                    if(seen.add(st) && (stringParser.parseStrategemSubtype(st).equals("WEAPON") || stringParser.parseStrategemSubtype(st).equals("WEAPON_WITH_BACKPACK"))){
                        sList.add(st);
                    }
                }
            }
        }
        String[] basic = Warbonds.Bonds.Cadet_Loadout.getStratagem();
        for(String s : basic){
            if(seen.add(s) && (stringParser.parseStrategemSubtype(s).equals("WEAPON") || stringParser.parseStrategemSubtype(s).equals("WEAPON_WITH_BACKPACK"))){
                sList.add(s);
            }
        }
        supportWeapon = sList.get(ThreadLocalRandom.current().nextInt(sList.size()));
    }
    public static void OtherStratagems() {
        SelectBondsPanel selectBondsPanel = MainPanel.getSelectBondsPanel();
        List<String> sList = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        String stratOneSub = stringParser.parseStrategemSubtype(supportWeapon);
        if(stratOneSub.equals("WEAPON")){
            for(JCheckBox box : selectBondsPanel.getCheckBoxes()){
                if (box.isSelected()) {
                    Warbonds.Bonds bond = Warbonds.Bonds.valueOf(box.getName());
                    for(String st: bond.getStratagem()){
                        if(seen.add(st) && stringParser.parseStrategemSubtype(st).equals("BACKPACK")){
                            sList.add(st);
                        }
                    }
                }
            }
            secondStrat = sList.get(ThreadLocalRandom.current().nextInt(sList.size()));
            thirdStrat = generateGreenStrat();
            forthStrat = generateRedStrat();
        }
        else{
            secondStrat = generateGreenStrat();
            thirdStrat = generateRedStrat();
            forthStrat = ThreadLocalRandom.current().nextInt() % 2 == 0 ? generateGreenStrat() : generateRedStrat();
        }

    }
    public static String generateGreenStrat(){
        SelectBondsPanel selectBondsPanel = MainPanel.getSelectBondsPanel();
        List<String> sList = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for(JCheckBox box : selectBondsPanel.getCheckBoxes()){
            if (box.isSelected()) {
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(box.getName());
                for(String st: bond.getStratagem()){
                    if(seen.add(st) && stringParser.parseStrategemColor(st).equals("GREEN")){
                        sList.add(st);
                    }
                }
            }
        }
        String[] basic = Warbonds.Bonds.Cadet_Loadout.getStratagem();
        for(String s : basic){
            if(seen.add(s) && stringParser.parseStrategemColor(s).equals("GREEN")){
                sList.add(s);
            }
        }
        return sList.get(ThreadLocalRandom.current().nextInt(sList.size()));
    }
    public static String generateRedStrat(){
        SelectBondsPanel selectBondsPanel = MainPanel.getSelectBondsPanel();
        List<String> sList = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for(JCheckBox box : selectBondsPanel.getCheckBoxes()){
            if (box.isSelected()) {
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(box.getName());
                for(String st: bond.getStratagem()){
                    if(seen.add(st) && stringParser.parseStrategemColor(st).equals("RED")){
                        sList.add(st);
                    }
                }
            }
        }
        String[] basic = Warbonds.Bonds.Cadet_Loadout.getStratagem();
        for(String s : basic){
            if(seen.add(s) && stringParser.parseStrategemColor(s).equals("RED")){
                sList.add(s);
            }
        }
        return sList.get(ThreadLocalRandom.current().nextInt(sList.size()));
    }
    public static String getThrowable() {
        return throwable;
    }

    public static String getSecondaryWeapon() {
        return secondaryWeapon;
    }
    public static List<String> getBoosterList() {
        return boosterList;
    }

    public static String getEnemyType() {
        return enemyType;
    }

    public static String getSupportWeapon() {
        return supportWeapon;
    }

    public static String getSecondStrat() {
        return secondStrat;
    }

    public static String getThirdStrat() {
        return thirdStrat;
    }

    public static String getForthStrat() {
        return forthStrat;
    }
    public static String getArmorLevel() {
        return armorLevel;
    }
}
