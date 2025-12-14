package me.abbatrombone.traz.GameItems;

public class Throwable {
    private final ThrowableType throwableType;
    private final Penitration penitration;

    enum ThrowableType{
        Standard,
        Special
    }
    enum Penitration{
        Light,
        Med,
        Heavy,
        Anti_Tank
    }
    public Throwable(ThrowableType throwableType, Penitration penitration){
        this.throwableType = throwableType;
        this.penitration = penitration;
    }
}
