package me.abbatrombone.traz.GameItems;

public class Secondary {
    private final ArmorPen armorPen;
    private final GunType gunType;
    private final String gunName;

    public enum ArmorPen{
        NA,
        Light,
        Med,
        Heavy,
        Anti_Tank_I,
        Anti_Tank_II
    }

    public enum GunType{
        Pistol,
        Melee,
        Special
    }
    public Secondary(String gunName, GunType gunType,ArmorPen armorPen){
        this.gunName = gunName;
        this.armorPen = armorPen;
        this.gunType = gunType;
    }
    @Override
    public String toString() {
        return "Secondary{" +
                "gunName='" + gunName + '\'' +
                "ArmorPen='" + armorPen + '\'' +
                ", GunType='" + gunType + '\'' +
                '}';
    }
}
