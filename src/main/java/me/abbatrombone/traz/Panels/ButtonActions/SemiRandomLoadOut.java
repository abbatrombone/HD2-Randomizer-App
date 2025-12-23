package me.abbatrombone.traz.Panels.ButtonActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SemiRandomLoadOut {

    public static String result() {

        String enemyType = enemyType();
        String difficulty = diff();
        String primaryWeapon = primaryWeapon();
        String secondaryWeapon = secWeapon();
        String throwable = throwable();
        String armorLevel = armorLevel();

        return "Hello Helldiver, General Brash has demanded that you use the following on your next " + operation(enemyType) + " operation against the " + enemyType() + "\n" +
                "Difficulty: " + diff() + "\n" +
                "Your weapon category is: " + primaryWeapon() + "\n" +
                "Your secondary is: " + secWeapon() + "\n" +
                "Your throwable is: " + throwable() + "\n" +
                "Armor: " + armorLevel() + " armor with any passive \n" +
                "Your stratagems will be the following:\n" + stratagems() + "\n" +
                "Your Booster is: \n" + hellpodOpt() +"\n" +
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

    public static String primaryWeapon(){
        String primary = "";
        Random randomprimary = new Random();
        int primaryInt = randomprimary.nextInt(6) +1;
        switch (primaryInt){
            case 1 -> primary = "AR";
            case 2 -> primary = "Marksmen Rifle";
            case 3-> primary = "Sniper Rifle";
            case 4-> primary = "SMG";
            case 5-> primary = "Shotgun";
            case 6-> primary = "Special";

        }
        return primary;
    }
    public static String secWeapon(){
        String secwep = "";
        Random randomsecwep = new Random();
        int secInt = randomsecwep.nextInt(12 -1 + 1) +1;

        switch (secInt){
            case 1  -> secwep = "P-2 Peacemaker";
            case 2  -> secwep = "P-19 Redeemer";
            case 3  -> secwep = "P-2 P-113 Verdict";
            case 4  -> secwep = "P-4 Senator";
            case 5  -> secwep = "Cqc-19 stun lance";
            case 6  -> secwep = "cqc-30 Stun Baton";
            case 7  -> secwep = "P-11 Stim Pistol";
            case 8  -> secwep = "sg-22 Bushwhacker";
            case 9  -> secwep = "P-72 Crisper";
            case 10 -> secwep = "Gp-31 Grenade Pistol";
            case 11 -> secwep = "Las-7 Dagger";
            case 12 -> secwep = "Plas-15 Loyalist";

        }
        return secwep;
    }

    public static String throwable(){
        String thrown = "";
        Random randomthrown = new Random();
        int thrownInt = randomthrown.nextInt(10 -1 + 1) +1;

        switch (thrownInt){
            case 1 -> thrown = "G-6 Frag";
            case 2 -> thrown = "G-12 High Explosive";
            case 3 -> thrown = "G-10 Incendiary";
            case 4 -> thrown = "G-16 Impact";
            case 5 -> thrown = "G-13 Incendiary";
            case 6 -> thrown = "G-23 Stun";
            case 7 -> thrown = "G-4 Gas";
            case 8 -> thrown = "G-3 Smoke";
            case 9 -> thrown = "6-123 Thermite";
            case 10-> thrown = "k-2 Throwing Knife";

        }
        return thrown;
    }
    public static String stratagems(){
        List<String> stratagems = new ArrayList<>();
        Random stratagemran = new Random();
        int backbackint = stratagemran.nextInt(2);
        int stratagemInt_1 = stratagemran.nextInt(12);
        int stratagemInt_2 = stratagemran.nextInt(7);
        int stratagemInt_3a = stratagemran.nextInt(5);
        int stratagemInt_3b = stratagemran.nextInt(8);
        int stratagemInt_4 = stratagemran.nextInt(19) +1; //case statement starts at 1, arrays start at 0.
        int stratagemInt_5 = stratagemran.nextInt(12);

        String[] orbitals = { "Orbital Gatling Barrage\n", "Orbital Airburst Strike\n", "Orbital 120mm HE Barrage\n","Orbital 380mm HE Barrage\n","Orbital Walking Barrage\n", "Orbital Laser\n", "Orbital Napalm Barrage\n", "Orbital Railcannon Strike\n", "Orbital Precision Strike\n", "Orbital Gas Strike\n", "Orbital EMS Strike\n", "Orbital Smoke Strike\n"};
        String[] eagle = {"Eagle Strafing Run\n", "Eagle Airstrike\n", "Eagle Cluster Bomb\n", "Eagle Napalm Airstrike\n", "Eagle Smoke Strike\n", "Eagle 110mm Rocket Pods\n", "Eagle 500kg Bomb\n"};
        String[] supportBackPack = {"AC-8 Autocannon\n", "RL-77 Airburst Rocket Launcher\n", "FAF-14 Spear\n", "StA-X3 W.A.S.P. Launcher\n","GR-8 Recoilless Rifle\n"};
        String[] backpack = {"LIFT-850 Jump Pack\n", "B-1 Supply Pack\n", "AX/LAS-5 Guard Dog Rover\n", "SH-20 Ballistic Shield Backpack\n", "SH-32 Shield Generator Pack\n", "AX/AR-23 Guard Dog\n", "AX/TX-13 Guard Dog Dog Breath\n", "SH-51 Directional Shield\n","B-100 Portable Hellbomb\n"};
        String[] supportweapon = {"MG-43 Machine Gun\n", "APW-1 Anti-Materiel Rifle\n", "M-105 Stalwart\n", "EAT-17 Expendable Anti-Tank\n", "FLAM-40 Flamethrower\n", "MG-206 Heavy Machine Gun\n", "MLS-4X Commando\n", "RS-422 Railgun\n", "GL-21 Grenade Launcher\n", "LAS-98 Laser Cannon\n", "LAS-99 Quasar Cannon\n", "TX-41 Sterilizer\n",};

        stratagems.add(orbitals[stratagemInt_1]);
        stratagems.add(eagle[stratagemInt_2]);
        if(backbackint == 1){
            stratagems.add(supportBackPack[stratagemInt_3a]);
            switch (stratagemInt_4){
                case 1 ->stratagems.add("M-102 Fast Recon Vehicle\n");
                case 2 ->stratagems.add("E/MG-101 HMG Emplacement\n");
                case 3 ->stratagems.add("FX-12 Shield Generator Relay\n");
                case 4 ->stratagems.add("A/ARC-3 Tesla Tower\n");
                case 5 ->stratagems.add("MD-6 Anti-Personnel Minefield\n");
                case 6 ->stratagems.add("MD-I4 Incendiary Mines\n");
                case 7 ->stratagems.add("ARC-3 Arc Thrower\n");
                case 8 ->stratagems.add("MD-17 Anti-Tank Mines\n");
                case 9 ->stratagems.add("MD-8 Gas Mines\n");
                case 10 ->stratagems.add("A/MG-43 Machine Gun Sentry\n");
                case 11 ->stratagems.add("A/G-16 Gatling Sentry\n");
                case 12 ->stratagems.add("A/M-12 Mortar Sentry\n");
                case 13 ->stratagems.add("A/AC-8 Autocannon Sentry\n");
                case 14 ->stratagems.add("A/MLS-4X Rocket Sentry\n");
                case 15 ->stratagems.add("A/M-23 EMS Mortar Sentry\n");
                case 16 ->stratagems.add("EXO-45 Patriot Exosuit\n");
                case 17 ->stratagems.add("EXO-49 Emancipator Exosuit\n");
                case 18 ->stratagems.add("E/AT-12 Anti-Tank Emplacement\n");
                case 19 ->stratagems.add("A/FLAM-40 Flame Sentry\n");
            }
        } else {
            stratagems.add(backpack[stratagemInt_3b]);
            stratagems.add(supportweapon[stratagemInt_5]);
        }

        return stratagems.toString().replace("[","").replace("]","").replace(",","");
    }
    public static String enemyType(){
        String enemytype = "";
        Random enemyran = new Random();
        int emenyInt = enemyran.nextInt(3 -1 +1) +1;

        switch (emenyInt){
            case 1-> enemytype = "Terminids";
            case 2-> enemytype = "Automatons";
            case 3-> enemytype = "Illuminate";
        }

        return  enemytype;
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
    public static String hellpodOpt(){
        String hellpodOpt = "";
        List<Integer> hellpodoptnumbers = new ArrayList<>();
        List<String> hellpodOpts = new ArrayList<>();

        Random hellpodOptran = new Random();
        for (int i = 0; i < 4; i++) {
            int hellpodoptagemInt = hellpodOptran.nextInt(14 -1 + 1) +1;
            if(!hellpodoptnumbers.contains(hellpodoptagemInt)){hellpodoptnumbers.add(hellpodoptagemInt);}
            else i=i-1;
        }
        for (int number : hellpodoptnumbers){
            switch (number) {
                case 1 ->  hellpodOpts.add("Hellpod Space Optimization");
                case 2 ->  hellpodOpts.add("Vitality Enhancement");
                case 3 ->  hellpodOpts.add("UAV Recon Booster");
                case 4 ->  hellpodOpts.add("Stamina Enhancement");
                case 5 ->  hellpodOpts.add("Muscle Enhancement");
                case 6 ->  hellpodOpts.add("Increased Reinforcement Budget");
                case 7 ->  hellpodOpts.add("Flexible Reinforcement Budget");
                case 8 ->  hellpodOpts.add("Localization Confusion Booster");
                case 9 ->  hellpodOpts.add("Expert Extraction Pilot Booster");
                case 10 -> hellpodOpts.add("Motivational Shocks");
                case 11 -> hellpodOpts.add("Experimental Infusion");
                case 12 -> hellpodOpts.add("Firebomb Hellpods");
                case 13 -> hellpodOpts.add("Dead Sprint");
                case 14 -> hellpodOpts.add("Armed Resupply Pods");
            }
        }

        int phrasefranInt = hellpodOptran.nextInt(14 -1 +1) +1;

        return "1. " + hellpodOpts.get(0) + "\n" +  "2. " + hellpodOpts.get(1) + "\n" + "3. " + hellpodOpts.get(2) + "\n" + "4. " + hellpodOpts.get(3);
        //return hellpodOpt;
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
}
