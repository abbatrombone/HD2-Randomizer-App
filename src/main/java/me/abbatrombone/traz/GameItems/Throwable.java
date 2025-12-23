package me.abbatrombone.traz.GameItems;

public class Throwable {
    private final ThrowableType throwableType;
    private final Penitration penitration;
    private final String throwableName;

    public enum ThrowableType{
        Standard,
        Special
    }
    public enum Penitration{
        Light,
        Medium,
        Heavy,
        Anti_Tank_I,
        Anti_Tank_II,
        Anti_Tank_III,
        No_Hitbox
    }
    public Throwable(String throwableName,ThrowableType throwableType, Penitration penitration){
        this.throwableName = throwableName;
        this.throwableType = throwableType;
        this.penitration = penitration;
    }

    @Override
    public String toString() {
        return "Throwable{" +
                "throwableName='" + throwableName + '\'' +
                ", throwableType='" + throwableType + '\'' +
                ", penitration='" + penitration + '\'' +
                '}';
    }

}
