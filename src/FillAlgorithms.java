import java.awt.*;
import java.util.*;

public class FillAlgorithms {

    public void FloodFill(Graphics g, int canvas_width, int canvas_height, int originX, int originY,
                          ArrayList<Point> boundary, Color new_color, int start_x, int start_y) {
        boolean[][] colored = new boolean[canvas_width][canvas_height];
        for (int i = 0; i < boundary.size(); i++)
        {
            colored[(int)(boundary.get(i).getX()) + originX][(int)(boundary.get(i).getY()) + originY] = true;
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
}
