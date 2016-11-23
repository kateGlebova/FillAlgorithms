import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.Math.abs;

public class FillAlgorithms {

    public void FloodFill(Graphics g, int canvas_width, int canvas_height, ArrayList<Point> boundary, Color new_color,
                          int start_x, int start_y) {
        boolean[][] colored = new boolean[canvas_width][canvas_height];
        for (int i = 0; i < boundary.size(); i++)
        {
            colored[(int)(boundary.get(i).getX())][(int)(boundary.get(i).getY())] = true;
        }
        g.setColor(new_color);
        RecursiveFill(g, colored, start_x, start_y, canvas_width, canvas_height);
    }

    private void RecursiveFill(Graphics g, boolean[][] colored, int x, int y, int width, int height) {
        if ((x > 0) && (y > 0) && (x < width) && (y < height) && (!colored[x][y])) {
            g.drawRect(x, y, 1, 1);
            colored[x][y] = true;
            RecursiveFill(g, colored, x - 1, y, width, height);
            RecursiveFill(g, colored, x + 1, y, width, height);
            RecursiveFill(g, colored, x, y - 1, width, height);
            RecursiveFill(g, colored, x, y + 1, width, height);
        }
    }

    public void PolygonFill(Graphics g, ArrayList<Point> polygon) {
        ArrayList<Point> sorted_by_Y = new ArrayList<Point>(polygon);
        Collections.sort( sorted_by_Y, compareY);
        for (int y = (int)sorted_by_Y.get(0).getY(); y <= (int)sorted_by_Y.get(sorted_by_Y.size() - 1).getY(); y++) {
            ArrayList<Point> intersectionPoints = new ArrayList<>();
            for (int i = 0; i < polygon.size() - 1; i++) {
                Point intersection = getIntersection(y, polygon.get(i), polygon.get(i + 1));
                if (intersection != null)
                    intersectionPoints.add(intersection);
            }
            Point intersection = getIntersection(y, polygon.get(polygon.size() - 1), polygon.get(0));
            if (intersection != null)
                intersectionPoints.add(intersection);
            Collections.sort( intersectionPoints, compareX);
            for (int i = 0; i < intersectionPoints.size(); i += 2) {
                int x1 = (int) intersectionPoints.get(i).getX();
                int x2 = (int)intersectionPoints.get(i + 1).getX();
                g.drawLine(x1, y, x2, y);
            }
        }
    }

    private Point getIntersection (int y, Point edge1, Point edge2) {
        int x1 = (int) edge1.getX();
        int y1 = (int) edge1.getY();
        int x2 = (int) edge2.getX();
        int y2 = (int) edge2.getY();

        if ((y1 <= y && y2 > y) || (y2 <= y && y1 > y)) {
            double deltax = x2 - x1;
            double deltay = y2 - y1;

            int x = (int) Math.round(x1 + deltax / deltay * (y - y1));

            return new Point(x, y);
        }
        return null;
    }

    private static Comparator<Point> compareX = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            int result = Double.compare(p1.getX(), p2.getX());
            return result;
        }
    };

    private static Comparator<Point> compareY = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            int result = Double.compare(p1.getY(), p2.getY());
            return result;
        }
    };
}
