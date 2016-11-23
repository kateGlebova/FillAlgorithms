import java.awt.*;
import java.util.*;

public class BezierCurve {
    ArrayList<Point> sourcePoints;
    ArrayList<Point> curvePoints;
    Graphics painter;

    public BezierCurve(ArrayList<Point> srcPoints, Graphics g) {
        sourcePoints = srcPoints;
        painter = g;
        generatePoints();
    }

    public void DrawBezierCurve() {
        for (int i = 0; i < curvePoints.size(); i++)
        {
            int x = (int)(curvePoints.get(i).getX());
            int y = (int)(curvePoints.get(i).getY());
            painter.drawRect(x, y, 1, 1);
        }
    }

    public ArrayList<Point> TranslatePoints(int originX, int originY) {
        ArrayList<Point> translated_points = new ArrayList<Point>();
        for (int i = 0; i < curvePoints.size(); i++) {
            int new_x = (int)curvePoints.get(i).getX() + originX;
            int new_y = (int)curvePoints.get(i).getY() + originY;
            translated_points.add(new Point(new_x, new_y));
        }
        return translated_points;
    }

    private void generatePoints() {
        curvePoints = new ArrayList<>();

        for (double t = 0; t <= 1; t += 0.001) {
            curvePoints.add(BezierFunction(t));
        }
    }

    private Point BezierFunction (double t) {
        double x = 0;
        double y = 0;
        int n = sourcePoints.size() - 1;

        for (int i = 0; i <= n; ++i) {
            x += sourcePoints.get(i).getX() * fact(n) / (fact(i) * fact(n - i)) * Math.pow(t, i) * Math.pow(1 - t, n - i);
            y += sourcePoints.get(i).getY() * fact(n) / (fact(i) * fact(n - i)) * Math.pow(t, i) * Math.pow(1 - t, n - i);
        }

        return new Point((int)x, (int)y);
    }

    private int fact(int n) {
        if (n < 0) throw new RuntimeException("negative argument.");
        if (n == 0)
            return 1;
        return n * fact(n - 1);
    }
}
