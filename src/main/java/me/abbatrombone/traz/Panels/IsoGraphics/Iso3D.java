package me.abbatrombone.traz.Panels.IsoGraphics;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Iso3D{
    private static final Logger logger = Logger.getLogger(Iso3D.class.getName());

    public Iso3D(){
    }
    int mode=1;
    public final int LEFT_ISO=0;
    public final int CENTER_ISO=1;
    public final int RIGHT_ISO=2;
    public float ySkew=1.0f;

    public void setTransformMode(int mode){this.mode=mode;}

    public Point2D transform3D(Point3D point3D) throws Exception{
        return switch (mode) {
            case LEFT_ISO ->   new Point2D(point3D.x + point3D.z, (int) (((-point3D.y) + point3D.z) * ySkew));
            case CENTER_ISO -> new Point2D(point3D.x + point3D.z, (int) (((-point3D.y) + point3D.z - point3D.x) * ySkew));
            case RIGHT_ISO ->  new Point2D(point3D.x - point3D.z, (int) (((-point3D.y) + point3D.z) * ySkew));
            default -> {
                logger.log(Level.WARNING,"Invalid Transformation Mode! [" + mode + "]?");
                throw   new Exception("Invalid Transformation Mode! [" + mode + "]?");
            }
        };
    }
}