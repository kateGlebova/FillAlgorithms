import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PolygonFrame extends JFrame {
    // Define constants
    public static final int CANVAS_WIDTH  = 200;
    public static final int CANVAS_HEIGHT = 200;

    // Declare an instance of the drawing canvasFlood,
    // which is an inner class called DrawCanvasFlood extending javax.swing.JPanel.
    private DrawCanvasPolygon canvasPolygon;

    // Constructor to set up the GUI components and event handlers
    public PolygonFrame() {
        canvasPolygon = new DrawCanvasPolygon();
        canvasPolygon.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // Set the Drawing JPanel as the JFrame's content-pane
        Container cp = getContentPane();
        cp.add(canvasPolygon);
        // or "setContentPane(canvasFlood);"

        setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
        pack();              // Either pack() the components; or setSize()
        setTitle("......");  // "super" JFrame sets the title
        setVisible(true);    // "super" JFrame show
    }

    private class DrawCanvasPolygon extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            g.setColor(Color.BLACK);
            int xpoints[] = {30, 25, 90, 100};
            int ypoints[] = {25, 50, 110, 30};
            g.drawPolygon(xpoints, ypoints, 4);
            ArrayList<Point> polygon = new ArrayList<>();
            for (int i = 0; i < xpoints.length; i++) {
                polygon.add(new Point(xpoints[i], ypoints[i]));
            }
            FillAlgorithms f = new FillAlgorithms();
            f.PolygonFill(g, polygon);
        }
    }

    public static void main(String[] args) {
        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PolygonFrame(); // Let the constructor do the job
            }
        });
    }
}