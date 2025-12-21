package me.abbatrombone.traz;

import java.util.Arrays;

import me.abbatrombone.traz.GameItems.Armor;
import me.abbatrombone.traz.GameItems.Stratagems;
//remove backpack values and move into stratagems. for all!
public class Warbonds {

    public enum Bonds {
        //med "Extra Padding" armor is always avaible work into logic!
        Cadet_Loadout(
                new String[]{"AR-23 Liberator"},
                new String[]{"P-2 Peacemaker"},
                new String[]{"G-12 High Explosive"},
                new String[]{
                        // Blue Strats
                        addBackpack("LIFT-850 Jump Pack"),
                        addBackpack("B-1 Supply Pack"),
                        addBackpack("AX/LAS-5 \"Guard Dog\" Rover"),
                        addBackpack("SH-20 Ballistic Shield Backpack"),
                        addBackpack("SH-32 Shield Generator Pack"),
                        addBackpack("AX/AR-23 \"Guard Dog\""),
                        addWeaponStratagem("MG-43 Machine Gun"),
                        addWeaponStratagem("APW-1 Anti-Materiel Rifle"),
                        addWeaponStratagem("M-105 Stalwart"),
                        addWeaponStratagem("EAT-17 Expendable Anti-Tank"),
                        addBackpackWeapon("GR-8 Recoilless Rifle"),
                        addWeaponStratagem("FLAM-40 Flamethrower"),
                        addBackpackWeapon("AC-8 Autocannon"),
                        addWeaponStratagem("MG-206 Heavy Machine Gun"),
                        addBackpackWeapon("RL-77 Airburst Rocket Launcher"),
                        addWeaponStratagem("MLS-4X Commando"),
                        addWeaponStratagem("RS-422 Railgun"),
                        addBackpackWeapon("FAF-14 Spear"),
                        addBackpackWeapon("StA-X3 W.A.S.P. Launcher"),
                        addVehicle("M-102 Fast Recon Vehicle"),
                        addWeaponStratagem("GL-21 Grenade Launcher"),
                        addWeaponStratagem("LAS-98 Laser Cannon"),
                        addWeaponStratagem("Arc Thrower"),
                        addWeaponStratagem("LAS-99 Quasar Cannon"),
                        addMech("EXO-45 Patriot Exosuit"),
                        addMech("EXO-49 Emancipator Exosuit"),

                        // Red Strats
                        addOrbital("Orbital Gatling Barrage"),
                        addOrbital("Orbital Airburst Strike"),
                        addOrbital("Orbital 120mm HE Barrage"),
                        addOrbital("Orbital 380mm HE Barrage"),
                        addOrbital("Orbital Walking Barrage"),
                        addOrbital("Orbital Laser"),
                        addOrbital("Orbital Napalm Barrage"),
                        addOrbital("Orbital Railcannon Strike"),
                        addOrbital("Orbital Precision Strike"),
                        addOrbital("Orbital Gas Strike"),
                        addOrbital("Orbital EMS Strike"),
                        addOrbital("Orbital Smoke Strike"),
                        addEagleStrike("Eagle Strafing Run"),
                        addEagleStrike("Eagle Airstrike"),
                        addEagleStrike("Eagle Cluster Bomb"),
                        addEagleStrike("Eagle Napalm Airstrike"),
                        addEagleStrike("Eagle Smoke Strike"),
                        addEagleStrike("Eagle 110mm Rocket Pods"),
                        addEagleStrike("Eagle 500kg Bomb"),

                        //Green Strats
                        addEncampment("E/MG-101 HMG Emplacement"),
                        addEncampment("FX-12 Shield Generator Relay"),
                        addEncampment("E/GL-21 Grenadier Battlement"),
                        addTurret("A/ARC-3 Tesla Tower"),
                        addTurret("A/MG-43 Machine Gun Sentry"),
                        addTurret("A/G-16 Gatling Sentry"),
                        addTurret("A/M-12 Mortar Sentry"),
                        addTurret( "A/AC-8 Autocannon Sentry"),
                        addTurret("A/MLS-4X Rocket Sentry"),
                        addTurret("A/M-23 EMS Mortar Sentry"),
                        addMines("MD-6 Anti-Personnel Minefield"),
                        addMines("MD-I4 Incendiary Mines"),
                        addMines("MD-17 Anti-Tank Mines"),
                        addMines("MD-8 Gas Mines")

                },
                new String[]{addMedArmor("Extra Padding"),},
                new String[]{"",""}
        ),

        Helldivers_Mobilize(
                new String[]{
                        "Ar-23P Liberator Penetrator", //prim
                        "R-63 Dilligence",
                        "R-63CS Dilligence Counter Sniper",
                        "Smg-37 Defender",
                        "Sg-8 Punisher",
                        "Sg-8S Slugger",
                        "Sg-255 Breaker",
                        "Sg-225SP Breaker Spray & Pray",
                        "Las-5 Scythe",
                        "Plas-1 Scorcher"},
                new String[]{"P-19 Redeemer"}, //sec
                new String[]{
                        "G-6 Frag",
                        "G-16 Impact",
                        "G-3 Smoke"}, //throw
                new String[]{}, //strat
                new String[]{ //armor
                        addLightArmor("Scout"),
                        addLightArmor("Extra Padding"),
                        addMedArmor("Scout"),
                        addMedArmor("Engineering Kit"),
                        addMedArmor("Med-Kit"),
                        addMedArmor("Democracy Protects"),
                        addHeavyArmor("Fortifeid"),
                        addHeavyArmor("Democracy Protects")},
                //opt
                new String[]{
                        "Hellpod Space Optimization",
                        "Vitality Enhancement",
                        "UAV Recon Booster",
                        "Stamina Enhancement",
                        "Muscle Enhancement",
                        "Increased Reinforcement Budget",
                }),

        Steeled_Veterans(
                new String[]{
                        "Ar-23C- Liberator Concussive",
                        "Jar-5 Dominator",
                        "Sg-2251E Breaker Incendiary"},
                new String[]{"P-4 Senator"},
                new String[]{"G-10 Incendiary"},
                new String[]{"",""},
                new String[]{
                        addLightArmor("Servo-Assisted"),
                        addMedArmor("Servo-Assisted"),
                        addHeavyArmor("Servo-Assisted"),},
                new String[]{"Flexible Reinforcement Budget"}), //opt),

        Cutting_Edge(
                new String[]{
                        "Arc-12 Blitzer",
                        "Las-16 Sickle",
                        "SG-8P Punisher Plasma"},
                new String[]{"Las-7 Dagger"},
                new String[]{"G-23 Stun"},
                new String[]{},  //Strats
                new String[]{
                        addLightArmor("Electrical Conduit"),
                        addMedArmor("Electrical Conduit"),
                },
                new String[]{"Localization Confusion",}), //opt),

        Democratic_Detonation(
                new String[]{
                        "Br-14 Adjudicator",
                        "Cb Exploding Crossbow",
                        "R-36 Eruiptor"},
                new String[]{"Gp-31 Grenade Pistol"},
                new String[]{"6-123 Thermite"},
                new String[]{},
                new String[]{
                        addLightArmor("Engineering Kit"),
                        addMedArmor("Engineering Kit"),
                        addHeavyArmor("Fortified")},
                new String[]{"Expert Extraction Pilot"}), //opt),


        Polar_Patriots( new String[]{
                "Ar-61 Tenderizer",
                "Smg-72 Pummeler",
                "Plas-101 Purifier"},
                new String[]{"P-2 P-113 Verdict"},
                new String[]{"G-13 Incendiary"},
                new String[]{},  // Strats
                new String[]{ // Armor
                        addLightArmor("Scout"),
                        addHeavyArmor("Fortifeid"),
                        addHeavyArmor("Servo-Assisted"),},
                new String[]{"Motivational Shocks"}), //booster),

        Viper_Commandos(
                new String[]{"Ar-23A Liberator Carbine"},
                new String[]{"sg-22 Bushwhacker"},
                new String[]{"K-2 Throwing Knife"},
                new String[]{},
                new String[]{
                        addLightArmor("Peak Physique"),
                        addMedArmor("Peak Physique"),
                        addHeavyArmor("Peak Physique")},
                new String[]{"Experimental Infusion"}), //Booster),

        Freedom_Flame(
                new String[]{
                        "Sg-451 Cookout",
                        "Flam-66 Torcher"},
                new String[]{"P-72 Crisper"},
                new String[]{"",""},
                new String[]{},
                new String[]{
                        addLightArmor("Inflammable"),
                        addMedArmor("Inflammable"),
                        addHeavyArmor("Inflammable")},
                new String[]{"Firebomb Hellpods"}), //Booster),

        Chemical_Agents(
                new String[]{"",""},    //pri
                new String[]{"P-11 Stim Pistol"},    //sec
                new String[]{"G-4 Gas"},    //thrown
                new String[]{addBackpack("AX/TX-13 \"Guard Dog\" Dog Breath"),
                        addWeaponStratagem("TX-41 Sterilizer")}, //strat
                new String[]{           //armor
                        addLightArmor("Advanced Filtration"),
                        addMedArmor("Advanced Filtration")},
                new String[]{"",""}), //booster),

        Truth_Enforcers(
                new String[]{
                        "Smg-32 Reprimand",
                        "Sg-20 Halt"},
                new String[]{"Plas-15 Loyalist"},
                new String[]{"",""},
                new String[]{},
                new String[]{
                        addLightArmor("Unflinching"),
                        addMedArmor("Unflinching") },
                new String[]{"Dead Sprint"}), //opt),

        Urban_Legends(
                new String[]{"",""},
                new String[]{"Cqc-19 stun Lance","CQC-30 Stun Baton"},
                new String[]{"",""},
                new String[]{
                        addTurret("Flame Sentry"),
                        addEncampment("Anti-Tank Emplacement"),
                        addBackpack("Directional Shield")},
                new String[]{
                        addLightArmor("Siege-Ready"),
                        addHeavyArmor("Siege-Ready"),},
                new String[]{"Armed Resupply Pods"}), //opt),

        Servants_of_Freedom(
                new String[]{"LAS-17 Double-Edge Sickle"},
                new String[]{"GP-31 Ultimatum"},
                new String[]{"G-50 Seeker"},
                new String[]{addBackpack("Portable Hellbomb")},
                new String[]{
                        addLightArmor("Integrated Explosives"),
                        addMedArmor("Integrated Explosives")},
                new String[]{"",""}), //opt),

        Borderline_Justice(
                new String[]{"R-6 Deadeye"},
                new String[]{"LAS-58 Talon"},
                new String[]{"TED-63 Dynamite"},
                new String[]{addBackpack("LIFT-860 Hover Pack")},
                new String[]{
                        addLightArmor("Gunslinger"),
                        addMedArmor("Gunslinger"),
                        addHeavyArmor("Gunslinger")},
                new String[]{"Sample Extricator"}), //opt),

        Masters_of_Ceremony(
                new String[]{"R-2 Amendment"},
                new String[]{"CQC-2 Saber"},
                new String[]{"G-142 Pyrotech"},
                new String[]{addWeaponStratagem("CQC-1 One True Flag")},
                new String[]{
                        addLightArmor("Reinforced Epaulettes"),
                        addMedArmor("Reinforced Epaulettes"),
                        addHeavyArmor("Reinforced Epaulettes")},
                new String[]{"Sample Scanner"}), //opt),

        Force_of_Law(
                new String[]{"AR-32 Pacifier"},
                new String[]{"",""},
                new String[]{"G-109 Urchin",""},
                new String[]{addBackpack("AX/ARC-3 \"Guard Dog\" K-9"),addWeaponStratagem("GL-52 De-Escalator")},
                new String[]{
                        addLightArmor("Ballistic Padding"),
                        addMedArmor("Ballistic Padding"),
                        addHeavyArmor("Ballistic Padding")},
                new String[]{"Stun Pods",""}), //opt),

        Control_Group(
                new String[]{"VG-70 Variable",""},
                new String[]{"",""},
                new String[]{"G-31 Arc",""},
                new String[]{
                        addBackpack("LIFT-182 Warp Pack"),
                        addWeaponStratagem("PLAS-45 Epoch"),
                        addTurret("A/LAS-98 Laser Sentry")},
                new String[]{
                        addHeavyArmor("Adreno-Defibrillator"),
                        addMedArmor("Adreno-Defibrillator"),
                        addLightArmor("Adreno-Defibrillator")},
                new String[]{"",""}), //opt),

        Dust_Devils(
                new String[]{"AR-2 Coyote",""},
                new String[]{"",""},
                new String[]{"G-7 Pineapple"},
                new String[]{
                        addWeaponStratagem("S-11 Speargun"),
                        addWeaponStratagem("EAT-700 Expendable Napalm"),
                        addWeaponStratagem("MS-11 Solo Silo")},
                new String[]{
                        addLightArmor("Desert Stormer"),
                        addMedArmor("Desert Stormer"),
                        addHeavyArmor("Desert Stormer")},
                new String[]{"",""}), //opt),

        Python_Commandos(
                new String[]{"AR/GL-21 One-Two",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{
                        addBackpack("AX/FLAM-75 \"Guard Dog\" Hot Dog"),
                        addWeaponStratagem("CQC-9 Defoliation Tool"),
                        addBackpackWeapon("M-1000 Maxigun")},
                new String[]{
                        addLightArmor("Rock Solid"),
                        addMedArmor("Rock Solid"),
                        addHeavyArmor("Rock Solid"),},
                new String[]{"",""}),

        Obedient_Democracy_Support_Troopers(
                new String[]{
                        "MA5C Assault Rifle",
                        "M7S SMG",
                        "M90A Shotgun"},
                new String[]{"M6C/SOCOM Pistol"},
                new String[]{"",""},
                new String[]{},
                new String[]{addMedArmor("Feet First")},
                new String[]{"",""}), //opt),

        Helldivers_X_Killzone(
                new String[]{
                        "PLAS-39 Accelerator Rifle",
                        "StA-52 Assault Rifle",
                        "StA-11 SMG"},
                new String[]{},
                new String[]{},
                new String[]{},
                new String[]{
                        addLightArmor("Acclimated"),
                        addMedArmor("Acclimated"),
                },
                new String[]{}),

        Super_Store(
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{},
                new String[]{
                        addLightArmor("Servo-Assisted"),
                        addLightArmor("Engineering Kit"),
                        addLightArmor("Fortified"),
                        addLightArmor("Extra Padding"),
                        addLightArmor("Med-Kit"),
                        addLightArmor("Integrated Explosives"),
                        addMedArmor("Acclimated"),
                        addMedArmor("Engineering Kit"),
                        addMedArmor("Fortified"),
                        addMedArmor("Med-Kit"),
                        addMedArmor("Extra Padding"),
                        addMedArmor("Peak Physique"),
                        addMedArmor("Advanced Filtration")},
                new String[]{"",""}), //opt),

        Pre_Order(
                new String[]{""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{},
                new String[]{
                        addMedArmor("Extra Padding"),
                        addMedArmor("Democracy Protects"),
                        addHeavyArmor("Servo-Assisted")},
                new String[]{"",""}), //opt),

        Super_Citizen(
                new String[]{"MP-98 Knight"},
                new String[]{""},
                new String[]{""},
                new String[]{},
                new String[]{addMedArmor("Democracy Protects")},
                new String[]{""});



        private final String[] primary;
        private final String[] secondary;
        private final String[] throwable;
        //private final String[] backpack;
        private final String[] stratagem;
        private final String[] armor;
        private final String[] opt; // optional string

        Bonds(String[] primary, String[] secondary, String[] throwable,
              //String[] backpack,
              String[] stratagem, String[] armor, String[] opt) {
            // store copies to keep enum instances immutable
            this.primary = Arrays.copyOf(primary, primary.length);
            this.secondary = Arrays.copyOf(secondary, secondary.length);
            this.throwable = Arrays.copyOf(throwable, throwable.length);
            //this.backpack = Arrays.copyOf(backpack, backpack.length);
            this.stratagem = Arrays.copyOf(stratagem, stratagem.length);
            this.armor = armor;
            this.opt = opt;
        }

        // getters return copies to avoid exposing internal arrays
        public String[] getPrimary() {
            return Arrays.copyOf(primary, primary.length);
        }

        public String[] getSecondary() {
            return Arrays.copyOf(secondary, secondary.length);
        }

        public String[] getThrowable() {
            return Arrays.copyOf(throwable, throwable.length);
        }

//        public String[] getBackpack() {
//            return Arrays.copyOf(backpack, backpack.length);
//        }

        public String[] getArmor() {
            return Arrays.copyOf(armor, armor.length);
        }


        public String[] getStratagem() {
            return Arrays.copyOf(stratagem, stratagem.length);
        }

        public String[] getOpt() {
            return opt;
        }
    }

    // private constructor to prevent instantiation
    private Warbonds() {}

    // returns the primary guns for a bond
    public static String[] getGuns(Bonds bond){
        return bond.getPrimary();
    }

    // example: return all guns across categories for a bond as a single array
    public static String[] getAllGuns(Bonds bond) {
        //, bond.getSecondary(), bond.getThrowable(), bond.getBackpack(), bond.getStratagem()
        return concat(bond.getPrimary());
    }
    public static String[] getAllArmor(Bonds bond) {
        //, bond.getSecondary(), bond.getThrowable(), bond.getBackpack(), bond.getStratagem()
        return concat(bond.getArmor());
    }
    public static String addLightArmor(String passive){
        return String.valueOf(new Armor(Armor.Level.Light, passive));
    }
    public static String addMedArmor(String passive){
        return String.valueOf(new Armor(Armor.Level.Medium, passive));
    }
    public static String addHeavyArmor(String passive){
        return String.valueOf(new Armor(Armor.Level.Heavy, passive));
    }
    public static String addEagleStrike(String name){
        return String.valueOf(new Stratagems(name, Stratagems.StratagemColor.RED,Stratagems.Subtype.EAGLE));
    }
    public static String addOrbital(String name){
        return String.valueOf(new Stratagems(name, Stratagems.StratagemColor.RED,Stratagems.Subtype.ORBITAL));
    }
    public static String addBackpack(String name){
        return String.valueOf(new Stratagems(name, Stratagems.StratagemColor.BLUE,Stratagems.Subtype.BACKPACK));
    }
    public static String addBackpackWeapon(String name){
        return String.valueOf(new Stratagems(name, Stratagems.StratagemColor.BLUE,Stratagems.Subtype.WEAPON_WITH_BACKPACK));
    }
    public static String addWeaponStratagem(String name){
        return String.valueOf(new Stratagems(name, Stratagems.StratagemColor.BLUE,Stratagems.Subtype.WEAPON));
    }
    public static String addMech(String name){
        return String.valueOf(new Stratagems(name, Stratagems.StratagemColor.BLUE,Stratagems.Subtype.MECH));
    }
    public static String addVehicle(String name){
        return String.valueOf(new Stratagems(name, Stratagems.StratagemColor.BLUE,Stratagems.Subtype.VEHICLE));
    }
    public static String addMines(String name){
        return String.valueOf(new Stratagems(name, Stratagems.StratagemColor.GREEN,Stratagems.Subtype.MINES));
    }
    public static String addTurret(String name){
        return String.valueOf(new Stratagems(name, Stratagems.StratagemColor.GREEN,Stratagems.Subtype.TURRET));
    }
    public static String addEncampment(String name){
        return String.valueOf(new Stratagems(name, Stratagems.StratagemColor.GREEN,Stratagems.Subtype.ENCAMPMENT));
    }


    private static String[] concat(String[]... arrays) {
        int total = 0;
        for (String[] a : arrays) total += a.length;
        String[] result = new String[total];
        int pos = 0;
        for (String[] a : arrays) {
            System.arraycopy(a, 0, result, pos, a.length);
            pos += a.length;
        }
        return result;
    }
}