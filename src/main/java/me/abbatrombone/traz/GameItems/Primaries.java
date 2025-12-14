package me.abbatrombone.traz.GameItems;

public class Primaries {
    private final ArmorPen armorPen;
    private final GunType gunType;

    enum ArmorPen{
        Light,
        Med,
        Heavy
    }

    enum GunType{
        AR,
        Marksmen,
        Submachine,
        Shotgun,
        Explosive,
        Energy,
        Special
    }
    public Primaries(ArmorPen armorPen, GunType gunType){
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
