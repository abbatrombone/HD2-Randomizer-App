package me.abbatrombone.traz.CustomComponents;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class CustomCursor{
    private final Logger logger = Logger.getLogger(CustomCursor.class.getName());

    public enum Type {
        DEFAULT,
        CROSSHAIR,
        HAND,
        TEXT,
        WAIT,
        MOVE,
        N_RESIZE, S_RESIZE, E_RESIZE, W_RESIZE,
        NE_RESIZE, NW_RESIZE, SE_RESIZE, SW_RESIZE,
        CUSTOM_ARROW,CUSTOM_HAND_ARROW
    }

    private final Map<Type, Supplier<Cursor>> cursorMap =
            new EnumMap<>(Type.class);

    public CustomCursor() {
        registerDefaults();
        /*
DEFAULT_CURSOR: The standard arrow cursor.
CROSSHAIR_CURSOR: The crosshair cursor type, useful for precise positioning.
HAND_CURSOR: The pointing hand cursor, often used for clickable elements like buttons or links.
TEXT_CURSOR: The I-beam text cursor, used in text input fields.
WAIT_CURSOR: The wait or busy cursor, typically a spinning hourglass or animated icon, indicating that the application is busy.
MOVE_CURSOR: The move cursor, indicating that an object can be moved.
RESIZE_CURSOR: Various resize cursors such as N_RESIZE_CURSOR, S_RESIZE_CURSOR, E_RESIZE_CURSOR, W_RESIZE_CURSOR, NE_RESIZE_CURSOR, NW_RESIZE_CURSOR, SE_RESIZE_CURSOR, and SW_RESIZE_CURSOR, used when resizing components.
         */

        /*
The hotspot on the creation of an object of even size (like 32) ... if it's in the center, is actually 15.5, 15.5
as 32 is technically 0..31, as everything is shifted down 1, but Points for cursors don't take a double, so we're forced to use 15 or 16
so if you leave it by default, the clickable region will be Lower, Right as 16 is bigger than the technical center of what is shown
if you want the offset of the hotspot to be biased, you can just subtract or add 1 from one of the Point coordinates.
This keeps us from using a weird cursor size like 31x31 or 33x33.
Even though you can't do hotspots on half pixels, you can technically move the drawing by half pixels,
tho - so that's a semi workaround g.translate(-0.5, -0.5); or similar
         */

    }
    public Cursor create(Type type) {
        Supplier<Cursor> supplier = cursorMap.get(type);
        if (supplier == null) {
            logger.warning("Cursor type not implemented: " + type);
            return Cursor.getDefaultCursor();
        }
        return supplier.get();
    }

    private void registerDefaults() {
        cursorMap.put(Type.DEFAULT,
                () -> Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        cursorMap.put(Type.CROSSHAIR,
                () -> Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        cursorMap.put(Type.HAND,
                () -> Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cursorMap.put(Type.TEXT,
                () -> Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        cursorMap.put(Type.WAIT,
                () -> Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        cursorMap.put(Type.MOVE,
                () -> Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));

        cursorMap.put(Type.N_RESIZE,
                () -> Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        cursorMap.put(Type.S_RESIZE,
                () -> Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        cursorMap.put(Type.E_RESIZE,
                () -> Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        cursorMap.put(Type.W_RESIZE,
                () -> Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        cursorMap.put(Type.NE_RESIZE,
                () -> Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
        cursorMap.put(Type.NW_RESIZE,
                () -> Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        cursorMap.put(Type.SE_RESIZE,
                () -> Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        cursorMap.put(Type.SW_RESIZE,
                () -> Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));

        cursorMap.put(Type.CUSTOM_ARROW, this::drawArrowCursor);
        cursorMap.put(Type.CUSTOM_HAND_ARROW, this::drawHandArrowCursor);
    }
    private Cursor drawArrowCursor() {
        int arrowSize = 32;
        int backingSize = 128;
        float scale = backingSize / (float) arrowSize;

        Dimension best = Toolkit.getDefaultToolkit()
                .getBestCursorSize(arrowSize, arrowSize);

        Polygon arrow = new Polygon(
                new int[]{0, 16, 7, 6 },
                new int[]{0, 6 , 7, 16},
                4
        );

        BufferedImage hiRes = new BufferedImage(
                backingSize, backingSize, BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g = hiRes.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);

        g.scale(scale, scale);

        g.setColor(Color.BLACK);
        g.fill(arrow);

        g.setStroke(new BasicStroke(1f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER));
        g.setColor(Color.WHITE);
        g.draw(arrow);

        g.dispose();

        // Downscale
        BufferedImage cursorImg = new BufferedImage(
                best.width, best.height, BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2 = cursorImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(hiRes, 0, 0, best.width, best.height, null);
        g2.dispose();

        // Bias hotspot slightly toward tip
        return Toolkit.getDefaultToolkit()
                .createCustomCursor(cursorImg, new Point(1, 1), "ArrowCursor");
    }
    private Cursor drawHandArrowCursor() {
        //need to add upsidedown V under arrow
        int arrowSize = 32;
        int backingSize = 128;
        float scale = backingSize / (float) arrowSize;

        Dimension best = Toolkit.getDefaultToolkit()
                .getBestCursorSize(arrowSize, arrowSize);

        Polygon arrow = new Polygon(
                new int[]{0, 16, 7, 6 },
                new int[]{0, 6 , 7, 16},
                4
        );
            Path2D chevron = new Path2D.Float();
        chevron.moveTo(-6, 10);
        chevron.lineTo(0, 5);
        chevron.lineTo(6, 10);

        AffineTransform at = new AffineTransform();
        at.translate(7f, 7f); //makes it concave
        at.rotate(Math.toRadians(-45));

        Shape rotatedChevron = at.createTransformedShape(chevron);

        BufferedImage hiRes = new BufferedImage(
                backingSize, backingSize, BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g = hiRes.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);

        g.scale(scale, scale);

        g.setColor(Color.DARK_GRAY);
        g.fill(arrow);

        g.setStroke(new BasicStroke(1f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER));
        g.setColor(Color.WHITE);
        g.draw(arrow);

        g.setStroke(new BasicStroke(1.2f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        g.setColor(Color.WHITE);
        g.draw(rotatedChevron);

        g.dispose();

        // Downscale
        BufferedImage cursorImg = new BufferedImage(
                best.width, best.height, BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2 = cursorImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(hiRes, 0, 0, best.width, best.height, null);
        g2.dispose();

        // Bias hotspot slightly toward tip
        return Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(1, 1), "ArrowCursor");
    }
}
