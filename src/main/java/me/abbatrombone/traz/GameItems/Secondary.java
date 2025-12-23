package me.abbatrombone.traz.GameItems;

public class Secondary {
    private final ArmorPen armorPen;
    private final GunType gunType;
    private final String gunName;

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
    public Secondary(String gunName, ArmorPen armorPen, GunType gunType){
        this.gunName = gunName;
        this.armorPen = armorPen;
        this.gunType = gunType;
    }
    @Override
    public String toString() {
        return "Secondary{" +
                "ArmorPen='" + armorPen + '\'' +
                ", GunType='" + gunType + '\'' +
                '}';
    }
}
