import java.awt.*;

public class Catamaran {
    private AddFloats addFloat;
    private int _startPosX; // Левая координата отрисовки катамарана
    private int _startPosY; // Правая кооридната отрисовки катамарана
    private int _pictureWidth; // Ширина окна отрисовки
    private int _pictureHeight; // Высота окна отрисовки
    private final int catamaranWidth = 115; // Ширина отрисовки катамарана
    private final int catamaranHeight = 95; // Высота отрисовки катамарана

    public int MaxSpeed; // Максимальная скорость
    public int getMaxSpeed() { return MaxSpeed; }
    public void setMaxSpeed(int maxSpeed) { MaxSpeed = maxSpeed; }

    public float Weight; // Вес катамарана
    public float getWeight() { return Weight; }
    public void setWeight(float weight) { Weight = weight; }

    public Color MainColor; // Основной цвет
    public Color getMainColor() { return MainColor; }
    public void setMainColor(Color mainColor) { MainColor = mainColor; }

    public Color DopColor; // Дополнительный цвет
    public Color getDopColor() { return DopColor; }
    public void setDopColor(Color dopColor) { DopColor = dopColor; }

    public boolean ControlWheel; // Признак наличия перо руля
    public boolean getControlWheel() { return ControlWheel; }
    public void setControlWheel(boolean controlWheel) { ControlWheel = controlWheel; }

    public boolean Seat; // Признак наличия сидения
    public boolean getSeat() { return Seat; }
    public void setSeat(boolean seat) { Seat = seat; }

    // Инициализация свойств
    public void Init(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean controlWheel, boolean seat, int floats_amount) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        DopColor = dopColor;
        ControlWheel = controlWheel;
        Seat = seat;

        addFloat = new AddFloats();
        addFloat.SetAmount(floats_amount);
    }

    // Установка позиции катамарана
    public void SetPosition(int x, int y, int width, int height) {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    // Изменение направления пермещения
    public void MoveTransport(Direction direction) {
        float step = MaxSpeed * 100 / Weight;
        switch (direction) {
            // вправо
            case Right:
                if (_startPosX + step < _pictureWidth - catamaranWidth) {
                    _startPosX += step;
                }
                break;
            //влево
            case Left:
                if (_startPosX - step > 0) {
                    _startPosX -= step;
                }
                break;
            //вверх
            case Up:
                if (_startPosY - step > 0) {
                    _startPosY -= step;
                }
                break;
            //вниз
            case Down:
                if (_startPosY + step < _pictureHeight - catamaranHeight) {
                    _startPosY += step;
                }
                break;
        }
    }

    // Отрисовка катамарана
    public void DrawTransport(Graphics g) {
        addFloat.DrawFloats(g, DopColor, _startPosX, _startPosY);

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
