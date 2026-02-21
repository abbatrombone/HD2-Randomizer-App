package me.abbatrombone.traz.Panels.IsoGraphics;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import java.util.logging.Logger;

public class GenerateWaterFeatures {
    private static final Logger logger = Logger.getLogger(GenerateWaterFeatures.class.getName());
    int minH = -300;
    int maxH = 300;
    Surface3D surface= new Surface3D(50, 50, 150, minH, maxH);
    public int originX;
    public int originY;
    public float ySkew=1.0f;
    public Iso3D iso3D= new Iso3D();
    int xs = surface.xSquareSize;
    int ys = surface.ySquareSize;

    private double[][] elev;
    private List<Point> rivers;
    Random rand = new Random();

    public GenerateWaterFeatures(int originX,int originY){
        this.originX = originX;
        this.originY = originY;
    }

    public void drawLines(Graphics g, int[] px, int[] py){
        g.setColor(Color.GREEN);
        g.drawPolygon(px, py, 4);
    }

    static List<Point> generateRiverPaths(double[][] e) {
        int w = e.length, h = e[0].length;
        List<Point> river = new ArrayList<>();

        Point start = findHighestPoint(e);
        Point p = start;

        for (int i = 0; i < 200; i++) {
            river.add(p);
            p = findLowestNeighbor(p.x, p.y, e);
            if (p == null || e[p.x][p.y] < 0) break;
        }

        return river;
    }

    static void generateLakes(double[][] e, List<Point> river) {
        for (Point p : river) {
            if (Math.random() < 0.04) {
                fillCircle(e, p.x, p.y, 4 + (int)(Math.random()*6), -3);
            }
        }
    }

    static Point findHighestPoint(double[][] e) {
        Point best = new Point(0,0);
        for (int y = 0; y < e[0].length; y++)
            for (int x = 0; x < e.length; x++)
                if (e[x][y] > e[best.x][best.y]) best = new Point(x,y);
        return best;
    }

    static Point findLowestNeighbor(int x, int y, double[][] e) {
        int[][] n = {{1,0},{-1,0},{0,1},{0,-1}};
        Point best = null;
        for (int[] d : n) {
            int nx = x + d[0], ny = y + d[1];
            if (nx < 0 || ny < 0 || nx >= e.length || ny >= e[0].length) continue;
            if (best == null || e[nx][ny] < e[best.x][best.y])
                best = new Point(nx, ny);
        }
        return best;
    }

    static void fillCircle(double[][] e, int cx, int cy, int r, double depth) {
        for (int y = -r; y <= r; y++)
            for (int x = -r; x <= r; x++) {
                int tx = cx+x, ty = cy+y;
                if (tx < 0 || ty < 0 || tx >= e.length || ty >= e[0].length) continue;
                if (x*x + y*y <= r*r) e[tx][ty] = depth;
            }
    }

    static void fillCircle(double[][] e, int cx, int cy, int r, int level, double depth) {
        fillCircle(e, cx, cy, r, depth);
    }

    static void fillCircle(double[][] e, int cx, int cy, int r, double depth, double blend) {
        fillCircle(e,cx,cy,r,depth);
    }

    static void fillCircle(double[][] e, int cx, int cy, int r, double depth, int dummy) {
        fillCircle(e,cx,cy,r,depth);
    }

    static void fillCircle(double[][] e, int cx, int cy, int r, double depth, boolean dummy) {
        fillCircle(e,cx,cy,r,depth);
    }

    static double distanceToCenter(int x, int y, int w, int h) {
        double dx = x - w/2.0, dy = y - h/2.0;
        return Math.sqrt(dx*dx + dy*dy) / (Math.min(w,h)/2.0);
    }

    static double noise(double x, double y) {
        int xi = (int)x, yi = (int)y;
        double xf = x - xi, yf = y - yi;
        double n1 = hash(xi, yi);
        double n2 = hash(xi+1, yi);
        double n3 = hash(xi, yi+1);
        double n4 = hash(xi+1, yi+1);
        double i1 = lerp(n1,n2,xf);
        double i2 = lerp(n3,n4,xf);
        return lerp(i1,i2,yf);
    }

    static double hash(int x, int y) {
        return ((x*374761393L + y*668265263L) ^ 0x5bf03635L) / (double)Integer.MAX_VALUE;
    }

    static double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }
    static boolean isInList(int x, int y, List<Point> list) {
        for (Point p : list) if (p.x==x && p.y==y) return true;
        return false;
    }

    void drawTile(Graphics g, int x, int y) {
        int[][] coords = computeIsoQuad(x,y);
        int[] px = coords[0], py = coords[1];
        g.fillPolygon(px,py,4);
        g.setColor(Color.BLACK);
        g.drawPolygon(px,py,4);
    }

    int[][] computeIsoQuad(int x, int y) {
        try {
            Point2D c1 = iso3D.transform3D(new Point3D(x * xs, (int) (elev[x][y] * ySkew), y * ys));
            Point2D c2 = iso3D.transform3D(new Point3D((x + 1) * xs, (int) (elev[x + 1][y] * ySkew), y * ys));
            Point2D c3 = iso3D.transform3D(new Point3D((x + 1) * xs, (int) (elev[x + 1][y + 1] * ySkew), (y + 1) * ys));
            Point2D c4 = iso3D.transform3D(new Point3D(x * xs, (int) (elev[x][y + 1] * ySkew), (y + 1) * ys));

            int[] px = {c1.x + originX, c2.x + originX, c3.x + originX, c4.x + originX};
            int[] py = {c1.y + originY, c2.y + originY, c3.y + originY, c4.y + originY};
            return new int[][]{px, py};
        } catch (Exception e) {
            return new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}};
        }
    }
    private boolean isRiver(int x, int y, List<Point> river) {
        for (Point p : river) {
            if (p.x == x && p.y == y) return true;
        }
        return false;
    }
}
