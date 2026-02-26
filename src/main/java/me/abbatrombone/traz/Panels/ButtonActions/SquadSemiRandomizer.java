package me.abbatrombone.traz.Panels.ButtonActions;

import me.abbatrombone.traz.GameItems.Warbonds;
import me.abbatrombone.traz.Panels.SelectBondsPanel;
import me.abbatrombone.traz.Panels.SquadRandomPanel;
import me.abbatrombone.traz.Utilities.StringParser;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SquadSemiRandomizer {

    private static List<String> PrimaryWeaponList = new ArrayList<>();
    private static List<String> secondaryWeaponList = new ArrayList<>();
    private static List<String> throwableList = new ArrayList<>();
    private static List<String> armorLevel = new ArrayList<>();
    private static List<String> boosterList = new ArrayList<>();
    private static String enemyType;
    private static String diff;

    private static List<String> strategemList = new ArrayList<>();
    private static List<String> supportWeaponList = new ArrayList<>();
    private static List<String> secondStrat = new ArrayList<>();
    private static List<String> thirdStrat = new ArrayList<>();
    private static List<String> forthStrat = new ArrayList<>();

    private final static StringParser p = new StringParser();

    public static void createRunData() {
            List<String> selectedBondNames = getSelectedBondNames();

            Collections.addAll(armorLevel,"Light", "Medium", "Heavy");

            enemyType();
            operation(enemyType);
            diff();
            warbondBooster(selectedBondNames);
            primaryCategory(selectedBondNames);
            warbondSecondary(selectedBondNames);
            warbondThrowable(selectedBondNames);
            warbondBooster(selectedBondNames);
            supportWeapon(selectedBondNames);

            for (int i = 0; i < 4; i++) {
                otherStratagems(selectedBondNames,i);
            }
        }
    public String makeSqaudtext(int i){
        StringBuilder sb = new StringBuilder();

        int primarySafeGuard   = PrimaryWeaponList.size() <= i ?   0 : i;
        int secondarySafeGuard = secondaryWeaponList.size() <= i ? 0 : i;
        int throwableSafeGuard = throwableList.size() <= i ? 0 : i;
        int boosterSafeGuard   = boosterList.size() <= i ?   0 : i;

        String primary =   PrimaryWeaponList.get(primarySafeGuard);
        String secondary = p.parseSecondaryName(secondaryWeaponList.get(secondarySafeGuard));
        String throwable = p.parseThrowable(throwableList.get(throwableSafeGuard));

        sb.append("Primary: ").append(primary).append("\n");
        sb.append("Secondary: ").append(secondary).append("\n");
        sb.append("Throwable: ").append(throwable).append("\n\n");
        sb.append("Armor: ").append(armorLevel.get(ThreadLocalRandom.current().nextInt(armorLevel.size()))).append(", ANY passive").append("\n\n");
        sb.append(p.parseStrategem(supportWeaponList.get(i-1))).append("\n");
        sb.append(secondStrat.get(i-1)).append("\n");
        sb.append(thirdStrat.get(i-1)).append("\n");
        sb.append(forthStrat.get(i-1)).append("\n\n");

        if(boosterList.isEmpty()){
            sb.append("Booster: ").append(" ");
        }else{
            sb.append("Booster: ").append(boosterList.get(boosterSafeGuard));
        }

        return sb.toString();
    }

    public static void primaryCategory(List<String> selectedBondNames){

        Set<String> seen = new HashSet<>();

        for (String name : selectedBondNames) {
            Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);
            for (String primary : bond.getPrimary()) {
                if (seen.add(primary) && !primary.isBlank()) {
                    PrimaryWeaponList.add(p.parsePrimaryType(primary));
                }
            }
        }

        Collections.addAll(PrimaryWeaponList,p.parsePrimaryType(Arrays.toString(Warbonds.Bonds.Cadet_Loadout.getPrimary())));
    }
        public static String enemyType(){

            Random enemyran = new Random();
            int emenyInt = enemyran.nextInt(3 -1 +1) +1;

            switch (emenyInt){
                case 1-> enemyType = "Terminids";
                case 2-> enemyType = "Automatons";
                case 3-> enemyType = "Illuminate";
            }
            return  enemyType;
        }

        public static void diff(){

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
                default -> diff = "Your Choice";
            }
        }
        public static void warbondBooster(List<String> selectedBondNames){

            Set<String> seen = new HashSet<>();

            for(String name : selectedBondNames){
                    Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);
                    for(String booster : bond.getOpt()){
                        if (seen.add(booster) && !booster.isBlank()) {
                            boosterList.add(booster);
                        }
                    }
            }
            Collections.shuffle(boosterList, ThreadLocalRandom.current());
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

        public static void warbondThrowable(List<String> selectedBondNames){

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
        public static void warbondSecondary(List<String> selectedBondNames){

            Set<String> seen = new HashSet<>();

            for (String name : selectedBondNames) {
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);
                for (String secondary : bond.getSecondary()) {
                    if (seen.add(secondary) && !secondary.isBlank()) {
                        secondaryWeaponList.add(secondary);
                    }
                }
            }
            secondaryWeaponList.add(Arrays.toString(Warbonds.Bonds.Cadet_Loadout.getSecondary()));
        }
        public static void supportWeapon(List<String> selectedBondNames) {

            List<String> sList = new ArrayList<>();
            Set<String> seen = new HashSet<>();

            for (String name : selectedBondNames) {
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);
                for (String st : bond.getStratagem()) {
                    if (seen.add(st) && (p.parseStrategemSubtype(st).equals("WEAPON") || p.parseStrategemSubtype(st).equals("WEAPON_WITH_BACKPACK"))) {
                        sList.add(st);
                    }
                }
            }

            String[] basic = Warbonds.Bonds.Cadet_Loadout.getStratagem();
            for(String s : basic){
                if(seen.add(s) && (p.parseStrategemSubtype(s).equals("WEAPON") || p.parseStrategemSubtype(s).equals("WEAPON_WITH_BACKPACK"))){
                    sList.add(s);
                }
            }
            supportWeaponList =sList.subList(0,4);
            //supportWeapon = sList.get(ThreadLocalRandom.current().nextInt(sList.size()));
        }
        public static void otherStratagems(List<String> selectedBondNames,int i) {

            List<String> sList = new ArrayList<>();
            Set<String> seen = new HashSet<>();

            String stratOneSub = p.parseStrategemSubtype(supportWeaponList.get(i));

            if(stratOneSub.equals("WEAPON")){
                for (String name : selectedBondNames) {
                    Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);
                    for (String st : bond.getStratagem()) {
                        if (seen.add(st) && p.parseStrategemSubtype(st).equals("BACKPACK")) {
                            sList.add(p.parseStrategem(st));
                        }
                    }
                }

                secondStrat.add(sList.get(ThreadLocalRandom.current().nextInt(sList.size())));
                thirdStrat.add(p.parseStrategem(generateGreenStrat(selectedBondNames)));
                forthStrat.add(p.parseStrategem(generateRedStrat(selectedBondNames)));
            }
            else{
                secondStrat.add(p.parseStrategem(generateGreenStrat(selectedBondNames)));
                thirdStrat.add(p.parseStrategem(generateRedStrat(selectedBondNames)));
                forthStrat.add(p.parseStrategem(ThreadLocalRandom.current().nextInt() % 2 == 0 ? generateGreenStrat(selectedBondNames) : generateRedStrat(selectedBondNames)));
            }

        }
        public static String generateGreenStrat(List<String> selectedBondNames){

            List<String> sList = new ArrayList<>();
            Set<String> seen = new HashSet<>();

            for (String name : selectedBondNames) {
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);
                for(String st: bond.getStratagem()){
                    if(seen.add(st) && p.parseStrategemColor(st).equals("GREEN")){
                        sList.add(st);
                    }
                }
            }

            String[] basic = Warbonds.Bonds.Cadet_Loadout.getStratagem();
            for(String s : basic){
                if(seen.add(s) && p.parseStrategemColor(s).equals("GREEN")){
                    sList.add(s);
                }
            }
            return sList.get(ThreadLocalRandom.current().nextInt(sList.size()));
        }
        public static String generateRedStrat(List<String> selectedBondNames){
            List<String> sList = new ArrayList<>();
            Set<String> seen = new HashSet<>();

            for (String name : selectedBondNames) {
                Warbonds.Bonds bond = Warbonds.Bonds.valueOf(name);
                    for(String st: bond.getStratagem()){
                        if(seen.add(st) && p.parseStrategemColor(st).equals("RED")){
                            sList.add(st);
                        }
                    }
                }
            String[] basic = Warbonds.Bonds.Cadet_Loadout.getStratagem();
            for(String s : basic){
                if(seen.add(s) && p.parseStrategemColor(s).equals("RED")){
                    sList.add(s);
                }
            }
            return sList.get(ThreadLocalRandom.current().nextInt(sList.size()));
        }
    public static List<String> getSelectedBondNames() {
        SelectBondsPanel panel = SquadRandomPanel.getWarbondsPanel();
        List<String> selected = new ArrayList<>();

        for (JCheckBox box : panel.getCheckBoxes()) {
            if (box.isSelected()) {
                selected.add(box.getName());
            }
        }
        return selected;
    }
        public static List<String> getThrowable() {
            return throwableList;
        }

        public static List<String> getSecondaryWeapon() {
            return secondaryWeaponList;
        }
        public static List<String> getBoosterList() {
            return boosterList;
        }

        public static String getEnemyType() {
            return enemyType;
        }

        public static List<String> getSupportWeapon() {
            return supportWeaponList;
        }

        public static List<String> getSecondStrat() {
            return secondStrat;
        }

        public static List<String> getThirdStrat() {
            return thirdStrat;
        }

        public static List<String> getForthStrat() {
            return forthStrat;
        }
        public static List<String> getArmorLevel() {
            return armorLevel;
        }
    public static List<String> getSupportWeaponList() {
        return supportWeaponList;
    }

    public static String getDiff() {
        return diff;
    }

    public static List<String> getThrowableList() {
        return throwableList;
    }

    public static List<String> getSecondaryWeaponList() {
        return secondaryWeaponList;
    }

    public static List<String> getPrimaryWeaponList() {
        return PrimaryWeaponList;
    }
}

