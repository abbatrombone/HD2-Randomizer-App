package me.abbatrombone.traz.Panels.ButtonActions;

import me.abbatrombone.traz.GameItems.Warbonds;
import me.abbatrombone.traz.Panels.MainPanel;
import me.abbatrombone.traz.Panels.SelectBondsPanel;
import me.abbatrombone.traz.Panels.SquadRandomPanel;
import me.abbatrombone.traz.Utilities.StringParser;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SquadRandomizer {
    private static ArrayList<String> primaryList = new ArrayList<>();
    private static ArrayList<String> secondaryList = new ArrayList<>();
    private static ArrayList<String> throwableList = new ArrayList<>();
    private static ArrayList<String> armorList = new ArrayList<>();
    private static List<String> boosterList = new ArrayList<>();
    private static String enemyType = "";
    private static String diff = "";
    private static String stratgems = "";
    private static List<String> strategemList = new ArrayList<>();
    private final static StringParser p = new StringParser();

    public void createRunData() {
        List<String> selectedBondNames = getSelectedBondNames();
        enemyType();
        operation(enemyType);
        diff();
        warbondBooster(selectedBondNames);
        warbondPrimary(selectedBondNames);
        warbondSecondary(selectedBondNames);
        warbondThrowable(selectedBondNames);
        warbondArmor(selectedBondNames);
        warbondBooster(selectedBondNames);
        warbondStratagems(selectedBondNames);
    }
    public String makeSqaudtext(int i){
        StringBuilder sb = new StringBuilder();

        int primarySafeGuard   = primaryList.size() <= i ?   0 : i;
        int secondarySafeGuard = secondaryList.size() <= i ? 0 : i;
        int throwableSafeGuard = throwableList.size() <= i ? 0 : i;
        int armorSafeGuard     = armorList.size() <= i ?     0 : i;
        int boosterSafeGuard   = boosterList.size() <= i ?   0 : i;

        String primary = p.parsePrimaryName(primaryList.get(primarySafeGuard));
        String secondary = p.parseSecondaryName(secondaryList.get(secondarySafeGuard));
        String throwable = p.parseThrowable(throwableList.get(throwableSafeGuard));

        String armorLevel = p.parseArmorLevel(armorList.get(armorSafeGuard));
        String armorPassive = p.parseArmorPassive(armorList.get(armorSafeGuard));

        int count = Math.min(4, strategemList.size());
        List<String> top4 = new ArrayList<>(strategemList.subList(0, count));

        String patriot = "EXO-45 Patriot Exosuit";
        String emancipator = "EXO-49 Emancipator Exosuit";

        if (top4.contains(patriot) && top4.contains(emancipator) && strategemList.size() > 5) {
            top4.remove(ThreadLocalRandom.current().nextBoolean()
                    ? patriot
                    : emancipator);
            top4.add(strategemList.get(5));
        }
        stratgems = String.join("\n", top4);

        sb.append("Primary: ").append(primary).append("\n");
        sb.append("Secondary: ").append(secondary).append("\n");
        sb.append("Throwable: ").append(throwable).append("\n\n");
        sb.append("Armor: ").append(armorLevel).append(": ").append(armorPassive).append("\n\n");
        sb.append(stratgems).append("\n\n");
        if(boosterList.isEmpty()){
            sb.append("Booster: ").append(" ");
        }else{
            sb.append("Booster: ").append(boosterList.get(boosterSafeGuard));
        }

        return sb.toString();
    }
    public List<String> getSelectedBondNames() {
        SelectBondsPanel panel = SquadRandomPanel.getWarbondsPanel();
        List<String> selected = new ArrayList<>();

        for (JCheckBox box : panel.getCheckBoxes()) {
            if (box.isSelected()) {
                selected.add(box.getName());
            }
        }
        return selected;
    }

    public void warbondStratagems(List<String> selectedBondNames) {

        Set<String> seen = new HashSet<>();
        StringParser stringParser = new StringParser();

        for (String name : selectedBondNames) {
            Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);
            for (String strat : bond.getStratagem()) {
                if (!strat.isBlank() && seen.add(strat)) {
                    strategemList.add(stringParser.parseStrategem(strat));
                }
            }
        }

        for (String s : Warbonds.Bonds.Cadet_Loadout.getStratagem()) {
            strategemList.add(stringParser.parseStrategem(s));
        }

        Collections.shuffle(strategemList, ThreadLocalRandom.current());

//        String patriot = "EXO-45 Patriot Exosuit";
//        String emancipator = "EXO-49 Emancipator Exosuit";

//        int count = Math.min(4, stratagemsList.size());
//        List<String> top4 = new ArrayList<>(stratagemsList.subList(0, count));
//
//        if (top4.contains(patriot) && top4.contains(emancipator) && stratagemsList.size() > 5) {
//            top4.remove(ThreadLocalRandom.current().nextBoolean()
//                    ? patriot
//                    : emancipator);
//            top4.add(stratagemsList.get(5));
//        }
//        stratgems = String.join("\n", top4);
    }
    public void enemyType(){
        int emenyInt = ThreadLocalRandom.current().nextInt(1, 4); //bound is exclusive

        switch (emenyInt){
            case 1-> enemyType = "Terminids";
            case 2-> enemyType = "Automatons";
            case 3-> enemyType = "Illuminate";
            default -> enemyType = "Automatons";
        }
    }

    public void diff(){

        int diffInt = ThreadLocalRandom.current().nextInt(1, 8); //bound is exclusive

        switch (diffInt){
            case 1-> diff = "Challenging";
            case 2-> diff = "Hard";
            case 3-> diff = "Extreme";
            case 4-> diff = "Sui Mission";
            case 5-> diff = "Impossible";
            case 6-> diff = "Helldive";
            case 7-> diff = "Super Helldive";
            default -> diff= "Your Choice";
        }
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
                .toList();
    }

    public String operation(String enemy){
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

    public void warbondArmor(List<String> selectedBondNames){
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
    }
    public void warbondPrimary(List<String> selectedBondNames){

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
    }
    public void warbondThrowable(List<String> selectedBondNames){

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
    }
    public void warbondSecondary(List<String> selectedBondNames){

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

    }
    public void shuffleStrats(){
        Collections.shuffle(strategemList);
    }
    public String getEnemyType() {
        return enemyType;
    }
    public static String getDiff() {
        return diff;
    }
    public static String getStratgems() {
        return stratgems;
    }
    public static List<String> getBoosterList() {
        return boosterList;
    }
    public static ArrayList<String> getPrimaryList() {
        return primaryList;
    }

    public static ArrayList<String> getSecondaryList() {
        return secondaryList;
    }

    public static ArrayList<String> getThrowableList() {
        return throwableList;
    }
        public static List<String> getStrategemList() {
        return strategemList;
    }
}
