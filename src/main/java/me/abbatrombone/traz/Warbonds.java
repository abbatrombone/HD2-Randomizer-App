package me.abbatrombone.traz;

import java.util.Arrays;

import me.abbatrombone.traz.GameItems.Armor;
public class Warbonds {

    public enum Bonds {
        //med "Extra Padding" armor is always avaible work into logic!
        Cadet_Loadout(
                new String[]{"AR-23 Liberator"},
                new String[]{"P-2 Peacemaker"},
                new String[]{"G-12 High Explosive"},
                new String[]{
                        "LIFT-850 Jump Pack",
                        "B-1 Supply Pack",
                        "AX/LAS-5 \"Guard Dog\" Rover",
                        "SH-20 Ballistic Shield Backpack"
                        "SH-32 Shield Generator Pack",
                        "AX/AR-23 \"Guard Dog\""},
                new String[]{
                        // Blue Strats
                        "MG-43 Machine Gun",
                        "APW-1 Anti-Materiel Rifle",
                        "M-105 Stalwart",
                        "EAT-17 Expendable Anti-Tank",
                        "GR-8 Recoilless Rifle",
                        "FLAM-40 Flamethrower",
                        "AC-8 Autocannon",
                        "MG-206 Heavy Machine Gun",
                        "RL-77 Airburst Rocket Launcher",
                        "MLS-4X Commando",
                        "RS-422 Railgun",
                        "FAF-14 Spear",
                        "StA-X3 W.A.S.P. Launcher",
                        "M-102 Fast Recon Vehicle",
                        "GL-21 Grenade Launcher",
                        "LAS-98 Laser Cannon",
                        "Arc Thrower",
                        "LAS-99 Quasar Cannon",
                        "EXO-45 Patriot Exosuit",
                        "EXO-49 Emancipator Exosuit",

                        // Red Strats
                        "Orbital Gatling Barrage",
                        "Orbital Airburst Strike",
                        "Orbital 120mm HE Barrage",
                        "Orbital 380mm HE Barrage",
                        "Orbital Walking Barrage",
                        "Orbital Laser",
                        "Orbital Napalm Barrage",
                        "Orbital Railcannon Strike",
                        "Eagle Strafing Run",
                        "Eagle Airstrike",
                        "Eagle Cluster Bomb",
                        "Eagle Napalm Airstrike",
                        "Eagle Smoke Strike",
                        "Eagle 110mm Rocket Pods",
                        "Eagle 500kg Bomb",
                        "Orbital Precision Strike",
                        "Orbital Gas Strike",
                        "Orbital EMS Strike",
                        "Orbital Smoke Strike",

                        //Green Strats
                        "E/MG-101 HMG Emplacement",
                        "FX-12 Shield Generator Relay",
                        "A/ARC-3 Tesla Tower",
                        "E/GL-21 Grenadier Battlement",
                        "MD-6 Anti-Personnel Minefield",
                        "MD-I4 Incendiary Mines",
                        "MD-17 Anti-Tank Mines",
                        "MD-8 Gas Mines",
                        "A/MG-43 Machine Gun Sentry",
                        "A/G-16 Gatling Sentry",
                        "A/M-12 Mortar Sentry",
                        "A/AC-8 Autocannon Sentry",
                        "A/MLS-4X Rocket Sentry",
                        "A/M-23 EMS Mortar Sentry",

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
                new String[]{"",""}, //backpack
                new String[]{"",""}, //strat
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
                new String[]{"",""},  //backpack
                new String[]{"",""},  //Strats
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
                new String[]{"",""},
                new String[]{"",""},
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
                new String[]{"",""}, // Backpack
                new String[]{"",""},  // Strats
                new String[]{ // Armor
                        addLightArmor("Scout"),
                        addHeavyArmor("Fortifeid"),
                        addHeavyArmor("Servo-Assisted"),},
                new String[]{"Motivational Shocks"}), //booster),

        Viper_Commandos(
                new String[]{"Ar-23A Liberator Carbine"},
                new String[]{"sg-22 Bushwhacker"},
                new String[]{"K-2 Throwing Knife"},
                new String[]{"",""},
                new String[]{"",""},
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
                new String[]{"",""},
                new String[]{"",""},
                new String[]{
                        addLightArmor("Inflammable"),
                        addMedArmor("Inflammable"),
                        addHeavyArmor("Inflammable")},
                new String[]{"Firebomb Hellpods"}), //Booster),

        Chemical_Agents(
                new String[]{"",""},    //pri
                new String[]{"P-11 Stim Pistol"},    //sec
                new String[]{"G-4 Gas"},    //thrown
                new String[]{"AX/TX-13 “Guard Dog” Dog Breath"},    //back
                new String[]{"TX-41 Sterilizer"}, //strat
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
                new String[]{"",""},
                new String[]{"",""},
                new String[]{
                        addLightArmor("Unflinching"),
                        addMedArmor("Unflinching") },
                new String[]{"Dead Sprint"}), //opt),

        Urban_Legends(
                new String[]{"",""},
                new String[]{"Cqc-19 stun B","CQC-30 Stun Baton"},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{
                        "Flame Sentry",
                        "Anti-Tank Emplacement"
                        "Directional Shield"},
                new String[]{
                        addLightArmor("Siege-Ready"),
                        addHeavyArmor("Siege-Ready"),},
                new String[]{"Armed Resupply Pods"}), //opt),

        Servants_of_Freedom(
                new String[]{"LAS-17 Double-Edge Sickle"},
                new String[]{"GP-31 Ultimatum"},
                new String[]{"G-50 Seeker"},
                new String[]{"Portable Hellbomb",""},
                new String[]{"",""},
                new String[]{
                        addLightArmor("Integrated Explosives"),
                        addMedArmor("Integrated Explosives")},
                new String[]{"",""}), //opt),

        Borderline_Justice(
                new String[]{"R-6 Deadeye"},
                new String[]{"LAS-58 Talon"},
                new String[]{"TED-63 Dynamite"},
                new String[]{"LIFT-860 Hover Pack",""},
                new String[]{"",""},
                new String[]{
                        addLightArmor("Gunslinger"),
                        addMedArmor("Gunslinger"),
                        addHeavyArmor("Gunslinger")},
                new String[]{"Sample Extricator"}), //opt),

        Masters_of_Ceremony(
                new String[]{"R-2 Amendment"},
                new String[]{"CQC-2 Saber"},
                new String[]{"G-142 Pyrotech"},
                new String[]{"",""},
                new String[]{"CQC-1 One True Flag"},
                new String[]{
                        addLightArmor("Reinforced Epaulettes"),
                        addMedArmor("Reinforced Epaulettes"),
                        addHeavyArmor("Reinforced Epaulettes")},
                new String[]{"Sample Scanner"}), //opt),

        Force_of_Law(
                new String[]{"AR-32 Pacifier"},
                new String[]{"",""},
                new String[]{"G-109 Urchin",""},
                new String[]{"AX/ARC-3 “Guard Dog” K-9"},
                new String[]{"GL-52 De-Escalator",""},
                new String[]{
                        addLightArmor("Ballistic Padding"),
                        addMedArmor("Ballistic Padding"),
                        addHeavyArmor("Ballistic Padding")},
                new String[]{"Stun Pods",""}), //opt),

        Control_Group(
                new String[]{"VG-70 Variable",""},
                new String[]{"",""},
                new String[]{"G-31 Arc",""},
                new String[]{"LIFT-182 Warp Pack"},
                new String[]{
                        "PLAS-45 Epoch",
                        "A/LAS-98 Laser Sentry"},
                new String[]{
                        addHeavyArmor("Adreno-Defibrillator"),
                        addMedArmor("Adreno-Defibrillator"),
                        addLightArmor("Adreno-Defibrillator")},
                new String[]{"",""}), //opt),

        Dust_Devils(
                new String[]{"AR-2 Coyote",""},
                new String[]{"",""},
                new String[]{"G-7 Pineapple"},
                new String[]{"",""},
                new String[]{
                        "S-11 Speargun",
                        "EAT-700 Expendable Napalm",
                        "MS-11 Solo Silo"},
                new String[]{
                        addLightArmor("Desert Stormer"),
                        addMedArmor("Desert Stormer"),
                        addHeavyArmor("Desert Stormer")},
                new String[]{"",""}), //opt),

        Python_Commandos(
                new String[]{"AR/GL-21 One-Two",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"AX/FLAM-75 \"Guard Dog\" Hot Dog"},
                new String[]{
                        "CQC-9 Defoliation Tool",
                        "M-1000 Maxigun"},
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
                new String[]{"",""},
                new String[]{"",""},
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
                new String[]{"",""},
                new String[]{"",""},
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
                new String[]{"",""},
                new String[]{"",""},
                new String[]{
                        addMedArmor("Extra Padding"),
                        addMedArmor("Democracy Protects")
                        addHeavyArmor("Servo-Assisted")},
                new String[]{"",""}); //opt),

        Super_Citizen(
                new String[]{"MP-98 Knight"},
                new String[]{""},
                new String[]{""},
                new String[]{""},
                new String[]{""},
                new String[]{addMedArmor("Democracy Protects")},
                new String[]{""})

        

        private final String[] primary;
        private final String[] secondary;
        private final String[] throwable;
        private final String[] backpack;
        private final String[] stratagem;
        private final String[] armor;
        private final String[] opt; // optional string

        Bonds(String[] primary, String[] secondary, String[] throwable, String[] backpack, String[] stratagem, String[] armor, String[] opt) {
            // store copies to keep enum instances immutable
            this.primary = Arrays.copyOf(primary, primary.length);
            this.secondary = Arrays.copyOf(secondary, secondary.length);
            this.throwable = Arrays.copyOf(throwable, throwable.length);
            this.backpack = Arrays.copyOf(backpack, backpack.length);
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

        public String[] getBackpack() {
            return Arrays.copyOf(backpack, backpack.length);
        }

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
        String[] all = concat(bond.getPrimary());
        //, bond.getSecondary(), bond.getThrowable(), bond.getBackpack(), bond.getStratagem()
        return all;
    }
    public static String[] getAllArmor(Bonds bond) {
        String[] all = concat(bond.getArmor());
        //, bond.getSecondary(), bond.getThrowable(), bond.getBackpack(), bond.getStratagem()
        return all;
    }
    public static String addLightArmor(String passive){
        return String.valueOf(new Armor(Armor.Level.Light, passive));
    }
    public static String addMedArmor(String passive){
        return String.valueOf(new Armor(Armor.Level.Med, passive));
    }
    public static String addHeavyArmor(String passive){
        return String.valueOf(new Armor(Armor.Level.Heavy, passive));
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