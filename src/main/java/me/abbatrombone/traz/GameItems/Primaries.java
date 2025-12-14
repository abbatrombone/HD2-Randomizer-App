package me.abbatrombone.traz.GameItems;

public class Primaries {
    private final ArmorPen armorPen;
    private final GunType gunType;
    private final String gunName;

    public enum ArmorPen{
        Light,
        Med,
        Heavy
    }

    public enum GunType{
        AR,
        Marksmen,
        Submachine,
        Shotgun,
        Explosive,
        Energy,
        Special
    }
    public Primaries(String gunName, ArmorPen armorPen, GunType gunType){
        this.gunName = gunName;
        this.armorPen = armorPen;
        this.gunType = gunType;
    }
    @Override
    public String toString() {
        return "Primary{" +
                "ArmorPen=" + armorPen +
                ", GunType='" + gunType + '\'' +
                '}';
    }

}
