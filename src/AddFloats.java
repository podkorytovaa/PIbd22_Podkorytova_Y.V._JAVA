import java.awt.*;

public class AddFloats {
    private Floats floats;

    public void SetAmount(int count) {
        if (count == 1) {
            floats = Floats.One;
            return;
        }
        if (count == 2) {
            floats = Floats.Two;
            return;
        }
        if (count == 3) {
            floats = Floats.Three;
            return;
        }
    }

    public void DrawFloats(Graphics g, Color dopColor, int x, int y) {
        g.setColor(Color.BLUE);
        g.fillPolygon(new int[] {x + 10, x + 90, x + 115, x + 90, x + 10},
                new int[] {y + 25, y + 25, y + 40, y + 55, y + 55}, 5);
        g.setColor(dopColor);
        g.fillOval(x + 15, y + 30, 75, 20);
        switch (floats) {
            case One:
                g.setColor(Color.BLACK);
                g.fillRect(x + 40, y + 30, 10, 20);
                break;

            case Two:
                g.setColor(Color.BLUE);
                g.fillPolygon(new int[] {x + 10, x + 85, x + 115, x + 85, x + 10},
                        new int[] {y + 65, y + 65, y + 80, y + 95, y + 95}, 5);

                g.setColor(dopColor);
                g.fillOval(x + 15, y + 70, 75, 20);

                g.setColor(dopColor);
                g.fillRect(x, y + 77, 10, 5);
                g.setColor(Color.BLUE);
                g.drawRect(x, y + 77, 10, 5);

                g.setColor(Color.BLACK);
                g.fillRect(x + 40, y + 70, 10, 20);
                g.fillRect(x + 20, y + 51, 5, 19);
                g.fillRect(x + 70, y + 51, 5, 19);
                break;

            case Three:
                g.setColor(dopColor);
                g.fillPolygon(new int[] {x + 10, x + 90, x + 115, x + 90, x + 10},
                        new int[] {y, y, y + 7, y + 14, y + 14}, 5);
                g.fillPolygon(new int[] {x + 10, x + 90, x + 115, x + 90, x + 10},
                        new int[] {y + 66, y + 66, y + 73, y + 80, y + 80}, 5);

                g.setColor(Color.BLUE);
                g.drawPolygon(new int[] {x + 10, x + 90, x + 115, x + 90, x + 10},
                        new int[] {y, y, y + 7, y + 14, y + 14}, 5);
                g.drawPolygon(new int[] {x + 10, x + 90, x + 115, x + 90, x + 10},
                        new int[] {y + 66, y + 66, y + 73, y + 80, y + 80}, 5);

                g.setColor(Color.BLACK);
                g.fillRect(x + 20, y + 10, 5, 20);
                g.fillRect(x + 20, y + 51, 5, 20);
                g.fillRect(x + 70, y + 10, 5, 20);
                g.fillRect(x + 70, y + 51, 5, 20);
                break;
        }
    }
}
