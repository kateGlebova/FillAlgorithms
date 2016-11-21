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

    public void TranslateBezierCurve(int deltaX, int deltaY) {
        for (int i = 0; i < curvePoints.size(); i++)
        {

            int x = (int)(curvePoints.get(i).getX() + deltaX);
            int y = (int)(curvePoints.get(i).getY() + deltaY);
            painter.drawRect(x, y, 1, 1);
        }
    }

    public void RotateBezierCurve(double angle) {
        double radians = Math.toRadians(angle);
        for (int i = 0; i < curvePoints.size(); i++)
        {
            int originalX = (int)(curvePoints.get(i).getX());
            int originalY =(int)(curvePoints.get(i).getY());
            int x = rotateX(originalX, originalY, radians);
            int y = rotateY(originalX, originalY, radians);
            painter.drawRect(x, y, 1, 1);
        }

    }

    public void ScaleBezierCurve(double coefficient) {
        for (int i = 0; i < curvePoints.size(); i++)
        {
            int x = (int)(curvePoints.get(i).getX() * coefficient);
            int y = (int)(curvePoints.get(i).getY() * coefficient);
            painter.drawRect(x, y, 1, 1);
        }
    }

    private int rotateX(int x, int y, double angle_radians) {
        return (int)(x * Math.cos(angle_radians) - y * Math.sin(angle_radians));
    }

    private int rotateY(int x, int y, double angle_radians) {
        return (int)(x * Math.sin(angle_radians) + y * Math.cos(angle_radians));
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
