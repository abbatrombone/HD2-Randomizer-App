package me.abbatrombone.traz.GameItems;

public class Armor {
    private final Level level;
    private final String passive;

    public enum Level{
        Light,
        Med,
        Heavy
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
