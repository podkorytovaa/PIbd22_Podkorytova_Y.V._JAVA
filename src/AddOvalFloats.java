import java.awt.*;

public class AddOvalFloats implements IFloats {
    private Floats floats;

    @Override
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

    @Override
    public void DrawFloats(Graphics g, Color mainColor, Color dopColor, int x, int y) {
        g.setColor(mainColor);
        g.fillOval(x + 50, y + 25, 65, 30);
        g.setColor(dopColor);
        g.fillOval(x + 15, y + 30, 85, 20);
        switch (floats) {
            case One:
                g.setColor(Color.BLACK);
                g.fillRect(x + 40, y + 30, 10, 20);
                break;

            case Two:
                g.setColor(mainColor);
                g.fillOval(x + 50, y + 65, 65, 30);
                g.fillPolygon(new int[] {x + 10, x + 85, x + 115, x + 85, x + 10},
                        new int[] {y + 65, y + 65, y + 80, y + 95, y + 95}, 5);

                g.setColor(dopColor);
                g.fillOval(x + 15, y + 70, 85, 20);
                g.fillRect(x, y + 77, 10, 5);

                g.setColor(mainColor);
                g.drawRect(x, y + 77, 10, 5);

                g.setColor(Color.BLACK);
                g.fillRect(x + 40, y + 71, 10, 19);
                g.fillRect(x + 30, y + 51, 5, 19);
                g.fillRect(x + 80, y + 51, 5, 19);
                break;

            case Three:
                g.setColor(dopColor);
                g.fillOval(x + 10, y, 105, 14);
                g.fillOval(x + 10, y + 66, 105, 14);

                g.setColor(mainColor);
                g.drawOval(x + 10, y, 105, 14);
                g.drawOval(x + 10, y + 66, 105, 14);

                g.setColor(Color.BLACK);
                g.fillRect(x + 30, y + 10, 5, 20);
                g.fillRect(x + 30, y + 51, 5, 20);
                g.fillRect(x + 80, y + 10, 5, 20);
                g.fillRect(x + 80, y + 51, 5, 20);
                break;
        }
    }
}