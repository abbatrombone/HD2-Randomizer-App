package me.abbatrombone.traz.GameItems;

public class Armor {
    private final Level level;
    private final String passive;

    enum Level{
        Light,
        Med,
        Hevay
    }
    //Enum for passives???

    public Armor(Level level, String passive){
        this.level = level;
        this.passive = passive;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "level=" + level +
                ", passive='" + passive + '\'' +
                '}';
    }
}
