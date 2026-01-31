package me.abbatrombone.traz.GameItems;

import java.util.Arrays;

public class Warbonds {

    public enum Bonds {
        Cadet_Loadout(
                new String[]{addPrimary("AR-23 Liberator", Primaries.GunType.AR, Primaries.ArmorPen.Light)},
                new String[]{addSecondary("P-2 Peacemaker",Secondary.GunType.Pistol,Secondary.ArmorPen.Light)},
                new String[]{addThrowable("G-12 High Explosive", Throwable.ThrowableType.Standard,Throwable.Penitration.Heavy)},
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
                new String[]{addMedArmor("Extra Padding")},
                new String[]{}
        ),

        Helldivers_Mobilize(
                new String[]{
                        addPrimary("AR-23P Liberator Penetrator", Primaries.GunType.AR, Primaries.ArmorPen.Med),
                        addPrimary("R-63 Dilligence", Primaries.GunType.Marksmen, Primaries.ArmorPen.Light),
                        addPrimary("R-63CS Dilligence Counter Sniper",Primaries.GunType.Marksmen, Primaries.ArmorPen.Med),
                        addPrimary("SMG-37 Defender", Primaries.GunType.Submachine, Primaries.ArmorPen.Light),
                        addPrimary("SG-8 Punisher", Primaries.GunType.Shotgun, Primaries.ArmorPen.Light),
                        addPrimary("SG-8S Slugger", Primaries.GunType.Shotgun, Primaries.ArmorPen.Med),
                        addPrimary("SG-255 Breaker", Primaries.GunType.Shotgun, Primaries.ArmorPen.Light),
                        addPrimary("SG-225SP Breaker Spray & Pray", Primaries.GunType.Shotgun, Primaries.ArmorPen.Light),
                        addPrimary("Las-5 Scythe", Primaries.GunType.Energy, Primaries.ArmorPen.Light),
                        addPrimary("Plas-1 Scorcher", Primaries.GunType.Special, Primaries.ArmorPen.Med)},
                new String[]{addSecondary("P-19 Redeemer",Secondary.GunType.Pistol, Secondary.ArmorPen.Light)},
                new String[]{
                       addThrowable("G-6 Frag", Throwable.ThrowableType.Standard,Throwable.Penitration.Medium),
                       addThrowable("G-16 Impact", Throwable.ThrowableType.Special, Throwable.Penitration.Heavy),
                       addThrowable("G-3 Smoke", Throwable.ThrowableType.Special, Throwable.Penitration.No_Hitbox)},
                new String[]{}, //strat
                new String[]{
                        addLightArmor("Scout"),
                        addLightArmor("Extra Padding"),
                        addMedArmor("Scout"),
                        addMedArmor("Engineering Kit"),
                        addMedArmor("Med-Kit"),
                        addMedArmor("Democracy Protects"),
                        addHeavyArmor("Fortifeid"),
                        addHeavyArmor("Democracy Protects")},
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
                        addPrimary("AR-23C- Liberator Concussive", Primaries.GunType.AR, Primaries.ArmorPen.Light),
                        addPrimary("Jar-5 Dominator", Primaries.GunType.Special, Primaries.ArmorPen.Med),
                        addPrimary("SG-225IE Breaker Incendiary", Primaries.GunType.Shotgun, Primaries.ArmorPen.Light)},
                new String[]{addSecondary("P-4 Senator", Secondary.GunType.Pistol, Secondary.ArmorPen.Heavy)},
                new String[]{addThrowable("G-10 Incendiary", Throwable.ThrowableType.Standard,Throwable.Penitration.Medium)},
                new String[]{},
                new String[]{
                        addLightArmor("Servo-Assisted"),
                        addMedArmor("Servo-Assisted"),
                        addHeavyArmor("Servo-Assisted")},
                new String[]{"Flexible Reinforcement Budget"}),

        Cutting_Edge(
                new String[]{
                        addPrimary("Arc-12 Blitzer", Primaries.GunType.Energy, Primaries.ArmorPen.Med),
                        addPrimary("Las-16 Sickle", Primaries.GunType.Energy, Primaries.ArmorPen.Light),
                        addPrimary("SG-8P Punisher Plasma", Primaries.GunType.Energy, Primaries.ArmorPen.Med)},
                new String[]{addSecondary("Las-7 Dagger", Secondary.GunType.Special, Secondary.ArmorPen.Light)},
                new String[]{addThrowable("G-23 Stun", Throwable.ThrowableType.Special, Throwable.Penitration.Anti_Tank_II)},
                new String[]{},
                new String[]{
                        addLightArmor("Electrical Conduit"),
                        addMedArmor("Electrical Conduit"),
                },
                new String[]{"Localization Confusion"}),

        Democratic_Detonation(
                new String[]{
                        addPrimary("Br-14 Adjudicator", Primaries.GunType.AR, Primaries.ArmorPen.Med),
                        addPrimary("Cb Exploding Crossbow", Primaries.GunType.Explosive, Primaries.ArmorPen.Med),
                        addPrimary("R-36 Eruiptor", Primaries.GunType.Explosive, Primaries.ArmorPen.Heavy)},
                new String[]{addSecondary("Gp-31 Grenade Pistol", Secondary.GunType.Special, Secondary.ArmorPen.Med)},
                new String[]{addThrowable("6-123 Thermite", Throwable.ThrowableType.Special, Throwable.Penitration.Anti_Tank_III)},
                new String[]{},
                new String[]{
                        addLightArmor("Engineering Kit"),
                        addMedArmor("Engineering Kit"),
                        addHeavyArmor("Fortified")},
                new String[]{"Expert Extraction Pilot"}),


        Polar_Patriots( new String[]{
                addPrimary("AR-61 Tenderizer", Primaries.GunType.AR, Primaries.ArmorPen.Light),
                addPrimary("SMG-72 Pummeler", Primaries.GunType.Submachine, Primaries.ArmorPen.Light),
                addPrimary("Plas-101 Purifier", Primaries.GunType.Energy, Primaries.ArmorPen.Med)},
                new String[]{addSecondary("P-2 P-113 Verdict", Secondary.GunType.Pistol, Secondary.ArmorPen.Med)},
                new String[]{addThrowable("G-13 Incendiary Impact", Throwable.ThrowableType.Special, Throwable.Penitration.Medium)},
                new String[]{},
                new String[]{
                        addLightArmor("Scout"),
                        addHeavyArmor("Fortifeid"),
                        addHeavyArmor("Servo-Assisted")},
                new String[]{"Motivational Shocks"}),

        Viper_Commandos(
                new String[]{addPrimary("AR-23A Liberator Carbine", Primaries.GunType.AR, Primaries.ArmorPen.Light)},
                new String[]{addSecondary("SG-22 Bushwhacker", Secondary.GunType.Special, Secondary.ArmorPen.Light)},
                new String[]{addThrowable("K-2 Throwing Knife", Throwable.ThrowableType.Special, Throwable.Penitration.Medium)},
                new String[]{},
                new String[]{
                        addLightArmor("Peak Physique"),
                        addMedArmor("Peak Physique"),
                        addHeavyArmor("Peak Physique")},
                new String[]{"Experimental Infusion"}),

        Freedom_Flame(
                new String[]{
                       addPrimary("SG-451 Cookout", Primaries.GunType.Shotgun, Primaries.ArmorPen.Light),
                       addPrimary("Flam-66 Torcher", Primaries.GunType.Special, Primaries.ArmorPen.Heavy)},
                new String[]{addSecondary("P-72 Crisper", Secondary.GunType.Special, Secondary.ArmorPen.Heavy)},
                new String[]{},
                new String[]{},
                new String[]{
                        addLightArmor("Inflammable"),
                        addMedArmor("Inflammable"),
                        addHeavyArmor("Inflammable")},
                new String[]{"Firebomb Hellpods"}),

        Chemical_Agents(
                new String[]{},
                new String[]{addSecondary("P-11 Stim Pistol", Secondary.GunType.Special, Secondary.ArmorPen.NA)},
                new String[]{addThrowable("G-4 Gas", Throwable.ThrowableType.Special, Throwable.Penitration.Anti_Tank_II)},
                new String[]{addBackpack("AX/TX-13 \"Guard Dog\" Dog Breath"),
                        addWeaponStratagem("TX-41 Sterilizer")},
                new String[]{
                        addLightArmor("Advanced Filtration"),
                        addMedArmor("Advanced Filtration")},
                new String[]{}),

        Truth_Enforcers(
                new String[]{
                       addPrimary("SMG-32 Reprimand", Primaries.GunType.Submachine, Primaries.ArmorPen.Med),
                       addPrimary( "SG-20 Halt", Primaries.GunType.Shotgun, Primaries.ArmorPen.Med)}, //techincally lighh & Med
                new String[]{addSecondary("Plas-15 Loyalist", Secondary.GunType.Special, Secondary.ArmorPen.Med)},
                new String[]{},
                new String[]{},
                new String[]{
                        addLightArmor("Unflinching"),
                        addMedArmor("Unflinching") },
                new String[]{"Dead Sprint"}),

        Urban_Legends(
                new String[]{},
                new String[]{addSecondary("CQC-19 stun Lance", Secondary.GunType.Melee, Secondary.ArmorPen.Med),addSecondary("CQC-30 Stun Baton", Secondary.GunType.Melee, Secondary.ArmorPen.Med)},
                new String[]{},
                new String[]{
                        addTurret("Flame Sentry"),
                        addEncampment("Anti-Tank Emplacement"),
                        addBackpack("Directional Shield")},
                new String[]{
                        addLightArmor("Siege-Ready"),
                        addHeavyArmor("Siege-Ready")},
                new String[]{"Armed Resupply Pods"}),

        Servants_of_Freedom(
                new String[]{addPrimary("LAS-17 Double-Edge Sickle", Primaries.GunType.Energy, Primaries.ArmorPen.Heavy)}, //techinaclly light & Med & Heavy
                new String[]{addSecondary("GP-31 Ultimatum", Secondary.GunType.Special, Secondary.ArmorPen.Anti_Tank_II)},
                new String[]{addThrowable("G-50 Seeker", Throwable.ThrowableType.Special, Throwable.Penitration.Heavy)},
                new String[]{addBackpack("Portable Hellbomb")},
                new String[]{
                        addLightArmor("Integrated Explosives"),
                        addMedArmor("Integrated Explosives")},
                new String[]{}),

        Borderline_Justice(
                new String[]{addPrimary("R-6 Deadeye", Primaries.GunType.Marksmen, Primaries.ArmorPen.Med)},
                new String[]{addSecondary("LAS-58 Talon", Secondary.GunType.Special, Secondary.ArmorPen.Med)},
                new String[]{addThrowable("TED-63 Dynamite", Throwable.ThrowableType.Standard, Throwable.Penitration.Heavy)},
                new String[]{addBackpack("LIFT-860 Hover Pack")},
                new String[]{
                        addLightArmor("Gunslinger"),
                        addMedArmor("Gunslinger"),
                        addHeavyArmor("Gunslinger")},
                new String[]{"Sample Extricator"}),

        Masters_of_Ceremony(
                new String[]{addPrimary("R-2 Amendment", Primaries.GunType.Marksmen, Primaries.ArmorPen.Light)},
                new String[]{addSecondary("CQC-2 Saber", Secondary.GunType.Melee, Secondary.ArmorPen.Med)},
                new String[]{addThrowable("G-142 Pyrotech", Throwable.ThrowableType.Special, Throwable.Penitration.Medium)},
                new String[]{addWeaponStratagem("CQC-1 One True Flag")},
                new String[]{
                        addLightArmor("Reinforced Epaulettes"),
                        addMedArmor("Reinforced Epaulettes"),
                        addHeavyArmor("Reinforced Epaulettes")},
                new String[]{"Sample Scanner"}),

        Force_of_Law(
                new String[]{addPrimary("AR-32 Pacifier", Primaries.GunType.AR, Primaries.ArmorPen.Med)},
                new String[]{},
                new String[]{addThrowable("G-109 Urchin", Throwable.ThrowableType.Special, Throwable.Penitration.Anti_Tank_II)},
                new String[]{addBackpack("AX/ARC-3 \"Guard Dog\" K-9"),addWeaponStratagem("GL-52 De-Escalator")},
                new String[]{
                        addLightArmor("Ballistic Padding"),
                        addMedArmor("Ballistic Padding"),
                        addHeavyArmor("Ballistic Padding")},
                new String[]{"Stun Pods"}),

        Control_Group(
                new String[]{addPrimary("VG-70 Variable", Primaries.GunType.Special, Primaries.ArmorPen.Light)},
                new String[]{},
                new String[]{addThrowable("G-31 Arc", Throwable.ThrowableType.Special, Throwable.Penitration.Heavy)},
                new String[]{
                        addBackpack("LIFT-182 Warp Pack"),
                        addWeaponStratagem("PLAS-45 Epoch"),
                        addTurret("A/LAS-98 Laser Sentry")},
                new String[]{
                        addHeavyArmor("Adreno-Defibrillator"),
                        addMedArmor("Adreno-Defibrillator"),
                        addLightArmor("Adreno-Defibrillator")},
                new String[]{}),

        Dust_Devils(
                new String[]{addPrimary("AR-2 Coyote", Primaries.GunType.AR, Primaries.ArmorPen.Med)},
                new String[]{},
                new String[]{addThrowable("G-7 Pineapple", Throwable.ThrowableType.Standard,Throwable.Penitration.Medium)},
                new String[]{
                        addWeaponStratagem("S-11 Speargun"),
                        addWeaponStratagem("EAT-700 Expendable Napalm"),
                        addWeaponStratagem("MS-11 Solo Silo")},
                new String[]{
                        addLightArmor("Desert Stormer"),
                        addMedArmor("Desert Stormer"),
                        addHeavyArmor("Desert Stormer")},
                new String[]{}),

        Python_Commandos(
                new String[]{addPrimary("AR/GL-21 One-Two", Primaries.GunType.AR, Primaries.ArmorPen.Light)}, //Secondary is med
                new String[]{},
                new String[]{},
                new String[]{
                        addBackpack("AX/FLAM-75 \"Guard Dog\" Hot Dog"),
                        addWeaponStratagem("CQC-9 Defoliation Tool"),
                        addBackpackWeapon("M-1000 Maxigun")},
                new String[]{
                        addLightArmor("Rock Solid"),
                        addMedArmor("Rock Solid"),
                        addHeavyArmor("Rock Solid")},
                new String[]{}),

        Obedient_Democracy_Support_Troopers(
                new String[]{
                        addPrimary("MA5C Assault Rifle", Primaries.GunType.AR, Primaries.ArmorPen.Med),
                        addPrimary("M7S SMG", Primaries.GunType.Submachine, Primaries.ArmorPen.Light),
                        addPrimary("M90A Shotgun", Primaries.GunType.Shotgun, Primaries.ArmorPen.Light)},
                new String[]{addSecondary("M6C/SOCOM Pistol", Secondary.GunType.Pistol, Secondary.ArmorPen.Light)},
                new String[]{},
                new String[]{},
                new String[]{addMedArmor("Feet First")},
                new String[]{}),

        Helldivers_X_Killzone(
                new String[]{
                        addPrimary("PLAS-39 Accelerator Rifle", Primaries.GunType.Energy, Primaries.ArmorPen.Med),
                        addPrimary("StA-52 Assault Rifle", Primaries.GunType.AR, Primaries.ArmorPen.Light),
                        addPrimary("StA-11 SMG", Primaries.GunType.Submachine, Primaries.ArmorPen.Light)},
                new String[]{},
                new String[]{},
                new String[]{},
                new String[]{
                        addLightArmor("Acclimated"),
                        addMedArmor("Acclimated"),
                },
                new String[]{}),

        Redacted(  new String[]{
                addPrimary("R-72 Censor", Primaries.GunType.Marksmen, Primaries.ArmorPen.Light),
                addPrimary("AR-59 Suppressor", Primaries.GunType.AR, Primaries.ArmorPen.Light)},
                new String[]{ addSecondary("P-35 Re-Educator", Secondary.GunType.Special, Secondary.ArmorPen.Heavy),},
                new String[]{},
                new String[]{
                        addMines("TM-01 Lure Mine"),
                        addBackpackWeapon("C4 Pack")
                },
                new String[]{
                        addLightArmor("Reduced Signature"),
                        addMedArmor("Acclimated"),
                },
                new String[]{"Concealed Insertion"}),

        Super_Store(
                new String[]{},
                new String[]{
                        addSecondary("P-92 Warrant", Secondary.GunType.Pistol, Secondary.ArmorPen.Med),
                        addSecondary("CQC-5 Combat Hatchet", Secondary.GunType.Melee, Secondary.ArmorPen.Med),
                        addSecondary("CQC-42 Machte", Secondary.GunType.Melee, Secondary.ArmorPen.Med)
                },
                new String[]{},
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
                new String[]{}),

        Pre_Order(
                new String[]{},
                new String[]{},
                new String[]{},
                new String[]{},
                new String[]{
                        addMedArmor("Extra Padding"),
                        addMedArmor("Democracy Protects"),
                        addHeavyArmor("Servo-Assisted")},
                new String[]{}),

        Super_Citizen(
                new String[]{addPrimary("MP-98 Knight", Primaries.GunType.Submachine, Primaries.ArmorPen.Light)},
                new String[]{},
                new String[]{},
                new String[]{},
                new String[]{addMedArmor("Democracy Protects")},
                new String[]{});



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
    public static String addThrowable(String name,Throwable.ThrowableType type, Throwable.Penitration pen){
        return String.valueOf(new Throwable(name, type,pen));
    }
    public static String addPrimary(String name, Primaries.GunType guntype,Primaries.ArmorPen armorPen){
        return String.valueOf(new Primaries(name, guntype,armorPen));
    }
    public static String addSecondary(String name, Secondary.GunType guntype,Secondary.ArmorPen armorPen){
        return String.valueOf(new Secondary(name, guntype,armorPen));
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
