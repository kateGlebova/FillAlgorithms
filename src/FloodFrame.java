import javax.swing.*;
import java.awt.*;
import java.util.*;

/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class FloodFrame extends JFrame {
    // Define constants
    public static final int CANVAS_WIDTH  = 200;
    public static final int CANVAS_HEIGHT = 200;

    // Declare an instance of the drawing canvasFlood,
    // which is an inner class called DrawCanvasFlood extending javax.swing.JPanel.
    private DrawCanvasFlood canvasFlood;

    // Constructor to set up the GUI components and event handlers
    public FloodFrame() {
        canvasFlood = new DrawCanvasFlood();    // Construct the drawing canvasFlood
        canvasFlood.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // Set the Drawing JPanel as the JFrame's content-pane
        Container cp = getContentPane();
        cp.add(canvasFlood);
        // or "setContentPane(canvasFlood);"

        setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
        pack();              // Either pack() the components; or setSize()
        setTitle("......");  // "super" JFrame sets the title
        setVisible(true);    // "super" JFrame show
    }


    private class DrawCanvasFlood extends JPanel {

        int originX = 100;
        int originY = 100;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            g.setColor(Color.BLACK);
            //BezierCurve is drawn considering a canvasFlood center to be the origin
            g.translate(originX, originY);
            ArrayList<Point> CTRLpoints = new ArrayList<Point>() {{
                add(new Point(-95,-95));
                add(new Point(-30,95));
                add(new Point(60,-95));
                add(new Point(80,-95));
                add(new Point(-95,-95));
            }};
            BezierCurve curve = new BezierCurve(CTRLpoints, g);
            curve.DrawBezierCurve();
            //Fill algorithms work considering a canvasFlood top left corner to be the origin
            g.translate(-originX, -originY);
            FillAlgorithms f = new FillAlgorithms();
            f.FloodFill(g, CANVAS_WIDTH, CANVAS_HEIGHT, curve.TranslatePoints(originX, originY), Color.gray, 40, 40);
        }
    }

    private class DrawCanvasPolygon extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            g.setColor(Color.BLACK);
            int xpoints[] = {25, 200, 100};
            int ypoints[] = {50, 100, 110};
            g.drawPolygon(xpoints, ypoints, 3);
        }
    }


    public static void main(String[] args) {
        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FloodFrame(); // Let the constructor do the job
            }
        });
    }
}

