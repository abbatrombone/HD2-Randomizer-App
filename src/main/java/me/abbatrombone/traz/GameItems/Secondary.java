package me.abbatrombone.traz.GameItems;

public class Secondary {
    private final ArmorPen armorPen;
    private final GunType gunType;

    enum ArmorPen{
        Light,
        Med,
        Heavy
    }

    enum GunType{
        Pistol,
        Melee,
        Special
    }
    public Secondary(ArmorPen armorPen, GunType gunType){
        this.armorPen = armorPen;
        this.gunType = gunType;
    }
    @Override
    public String toString() {
        return "Secondary{" +
                "ArmorPen=" + armorPen +
                ", GunType='" + gunType + '\'' +
                '}';
    }
}
