package me.abbatrombone.traz.Panels.IsoGraphics;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class IsoPanel extends JPanel{
    private static final Logger logger = Logger.getLogger(IsoPanel.class.getName());
    int minH = -300;
    int maxH = 300;
    Surface3D surface= new Surface3D(50, 50, 150, minH, maxH);
    public int originX=getPreferredSize().width;
    public int originY=250;
    public float ySkew=1.0f;
    public Iso3D iso3D= new Iso3D();
    int xs = surface.xSquareSize;
    int ys = surface.ySquareSize;

    private double[][] elev;
    private List<Point> rivers;
    Random rand = new Random();

    JLabel label = new JLabel("Democratic Territory to be Seized");

    public IsoPanel(){
        setOpaque(true);
        setLayout(null);

        label.setForeground(Color.WHITE);
        label.setFont(new Font("FS Sinclair",Font.BOLD,16));
        label.setSize(label.getPreferredSize());
        add(label);
    }


//@Override
//    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//
//        // background
//        g.setColor(new Color(51,51,51));
//        g.fillRect(0,0,getWidth(),getHeight());
//
//        // generate new world EVERY frame/repaint
//        double[][] elev = generateElevation(surface.xGridSize, surface.yGridSize);
//        List<Point> rivers = generateRiverPaths(elev);
//        generateLakes(elev, rivers);
//
//        // draw terrain
//        for (int y = 0; y < surface.yGridSize - 1; y++) {
//            for (int x = 0; x < surface.xGridSize - 1; x++) {
//
//                int h = (int)elev[x][y];
//                Color tile;
//
//                if (h > 60) tile = Color.WHITE;
//                else if (h > 25) tile = Color.GRAY;
//                else if (h > 3)  tile = new Color(34,139,34);
//                else tile = new Color(0,60,180);
//
//                // draw river on top if present
//                if (isRiver(x, y, rivers))
//                    tile = new Color(30,144,255);
//
//                g.setColor(tile);
//                drawTile(g, x, y);
//            }
//        }
//    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(new Color(51,51,51));
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.green);

        for (int y = 0; y < surface.yGridSize - 1; y++) {
            for (int x = 0; x < surface.xGridSize - 1; x++) {
                double v = surface.getElevationAvg(x, y) * ySkew;
                try {
                    Point2D c1 = iso3D.transform3D(
                            new Point3D(x * xs,     (int)(surface.getElevationAvg(x,y) * ySkew),y * ys)
                    );
                    Point2D c2 = iso3D.transform3D(
                            new Point3D((x+1) * xs, (int)(surface.getElevationAvg(x+1,y) * ySkew),y * ys)
                    );
                    Point2D c3 = iso3D.transform3D(
                            new Point3D((x+1) * xs, (int)(surface.getElevationAvg(x+1,y+1) * ySkew),(y+1) * ys)
                    );
                    Point2D c4 = iso3D.transform3D(
                            new Point3D(x * xs,     (int)(surface.getElevationAvg(x,y+1) * ySkew),(y+1) * ys)
                    );

                    int[] px = { c1.x + originX, c2.x + originX, c3.x + originX, c4.x + originX };
                    int[] py = { c1.y + originY, c2.y + originY, c3.y + originY, c4.y + originY };


                    if (v < -2) {
                        g.setColor(new Color(0, 60, 180));
                        g.fillPolygon(px, py, 4);
                    } else if (v < 2) {
                        g.setColor(new Color(42, 145, 78));
                        g.fillPolygon(px, py, 4);
                        drawLines(g,px,py);
                    }else if (v < 20){
                        g.setColor(Color.GRAY);
                        g.fillPolygon(px, py, 4);
                        drawLines(g,px,py);
                    } else {
                        g.setColor(Color.WHITE);
                        g.fillPolygon(px, py, 4);
                        drawLines(g,px,py);
                    }
                } catch (Exception e) {
                    logger.log(Level.WARNING, e.toString());
                }
            }
        }
        label.setLocation(125,440);
    }
    public void drawLines(Graphics g, int[] px, int[] py){
        g.setColor(Color.GREEN);
        g.drawPolygon(px, py, 4);
    }
}