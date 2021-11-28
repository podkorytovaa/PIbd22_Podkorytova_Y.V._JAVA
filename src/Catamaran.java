import java.awt.*;

public class Catamaran extends Boat {
    private IFloats addFloat;

    public Color DopColor; // Дополнительный цвет
    public Color getDopColor() { return DopColor; }
    public void setDopColor(Color dopColor) { DopColor = dopColor; }

    private boolean Floater; // Признак наличия поплавков
    public boolean getFloater() { return Floater; }
    public void setFloater(boolean floater) { Floater = floater; }

    public boolean ControlWheel; // Признак наличия перо руля
    public boolean getControlWheel() { return ControlWheel; }
    public void setControlWheel(boolean controlWheel) { ControlWheel = controlWheel; }

    public boolean Seat; // Признак наличия сидения
    public boolean getSeat() { return Seat; }
    public void setSeat(boolean seat) { Seat = seat; }

    // Конструктор
    public Catamaran(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean floater, boolean controlWheel, boolean seat, int floatsAmount, int addState) {
        super(maxSpeed, weight, mainColor, 115, 95);
        DopColor = dopColor;
        Floater = floater;
        ControlWheel = controlWheel;
        Seat = seat;

        switch (addState) {
            case 0:
                addFloat = new AddFloats();
                break;
            case 1:
                addFloat = new AddOvalFloats();
                break;
            case 2:
                addFloat = new AddSemiOvalFloats();
                break;
        }
        addFloat.SetAmount(floatsAmount);
    }

    // Отрисовка катамарана
    public void DrawTransport(Graphics g) {
        super.DrawTransport(g);

        if (Floater) {
            addFloat.DrawFloats(g, MainColor, DopColor, _startPosX, _startPosY);
        }

        if (ControlWheel) {
            g.setColor(DopColor);
            g.fillRect(_startPosX, _startPosY + 37, 10, 5);
            g.setColor(MainColor);
            g.drawRect(_startPosX, _startPosY + 37, 10, 5);
        }

        if (Seat) {
            g.setColor(Color.black);
            g.fillRect(_startPosX + 40, _startPosY + 30, 10, 20);
        }
    }
}
