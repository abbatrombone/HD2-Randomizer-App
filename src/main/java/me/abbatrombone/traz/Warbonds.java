package me.abbatrombone.traz;

import me.abbatrombone.traz.GameItems.Armor;

import java.util.Arrays;

public class Warbonds {

    public enum Bonds {

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
                new String[]{"G-6 Frag",
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
                },
                new String[]{}), //opt
        Steeled_Veterans( new String[]{"Ar-23C- Liberator Concussive",
                "Jar-5 Dominator",
                "Sg-2251E Breaker Incendiary"},
                new String[]{"P-4 Senator"},
                new String[]{"G-10 Incendiary"},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{}),
        Cutting_Edge( new String[]{"",""},
                new String[]{"Arc-12 Blitzer",
                        "Las-16 Sickle",
                        "SG-8P Punisher Plasma"},
                new String[]{"Las-7 Dagger"},
                new String[]{"G-23 Stun"},
                new String[]{"",""},
                new String[]{addLightArmor("Electrical Conduit")},
                new String[]{}),
        Democratic_Detonation(new String[]{"Br-14 Adjudicator",
                "Cb Exploding Crossbow",
                "R-36 Eruiptor"},
                new String[]{"Gp-31 Grenade Pistol"},
                new String[]{"6-123 Thermite"},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{addLightArmor("Engineering Kit"),addMedArmor("Engineering Kit")},
                new String[]{}),
        Polar_Patriots( new String[]{"Ar-61 Tenderizer",
                "Smg-72 Pummeler",
                "Plas-101 Purifier"},
                new String[]{"P-2 P-113 Verdict"},
                new String[]{"G-13 Incendiary"},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{addLightArmor("Scout"),},
                new String[]{}),
        Viper_Commandos( new String[]{"Ar-23A Liberator Carbine"},
                new String[]{"sg-22 Bushwhacker"},
                new String[]{"K-2 Throwing Knife"},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{addLightArmor("Peak Physique")},
                new String[]{}),
        Freedom_Flame( new String[]{"Sg-451 Cookout",
                "Flam-66 Torcher"},
                new String[]{"P-72 Crisper"},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{addLightArmor("Inflammable"),addMedArmor("Inflammable")},
                new String[]{}),
        Chemical_Agents( new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{addMedArmor("Advanced Filtration")},
                new String[]{}),
        Truth_Enforcers( new String[]{"Smg-32 Reprimand",
            "Sg-20 Halt"},
                new String[]{"Plas-15 Loyalist"},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{addLightArmor("Unflinching")},
                new String[]{}),
        Urban_Legends( new String[]{"",""},
                new String[]{"Cqc-19 stun lance"},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{addLightArmor("Siege-Ready")},
                new String[]{}),
        Servants_of_Freedom( new String[]{"LAS-17 Double-Edge Sickle"},
                new String[]{"GP-31 Ultimatum"},
                new String[]{"G-50 Seeker"},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{}),
        Borderline_Justice( new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{}),
        Masters_of_Ceremony( new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{}),
        Force_of_Law( new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{}),
        Control_Group( new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{}),
        Dust_Devils( new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{}),
        Obedient_Democracy_Support_Troopers( new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{}),
        Super_Store( new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{addLightArmor("Servo-Assisted"),
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
                        addMedArmor("Advanced Filtration"),
                },
                new String[]{}),
        Pre_Order( new String[]{"MP-98 Knight"},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{
                        addMedArmor("Extra Padding"),
                        addMedArmor("Democracy Protects")
                },
                new String[]{}),
        Killzone( new String[]{"StA-52 Assault Rifle",
                "PLAS-39 Accelerator Rifle",
                "Sta-11 SMG"},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{addLightArmor("Acclimated"), addMedArmor("Acclimated")},
                new String[]{}),
        Halo( new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{"",""},
                new String[]{});

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
        return String.valueOf(new Armor(Armor.Level.Med, passive));
    }
    public static String addHevayArmor(String passive){
        return String.valueOf(new Armor(Armor.Level.Hevay, passive));
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
