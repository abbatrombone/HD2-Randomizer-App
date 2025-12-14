package me.abbatrombone.traz.GameItems;

public class Throwable {
    private final ThrowableType throwableType;
    private final Penitration penitration;
    private final String throwableName;

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
    public Throwable(String throwableName,ThrowableType throwableType, Penitration penitration){
        this.throwableName = throwableName;
        this.throwableType = throwableType;
        this.penitration = penitration;
    }
}
