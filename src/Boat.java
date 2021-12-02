import java.awt.*;

public class Boat extends Vehicle {
    protected int catamaranWidth = 105; // Ширина отрисовки лодки
    protected int catamaranHeight = 55; // Высота отрисовки лодки

    public void SetMainColor(Color mainColor) {
        MainColor = mainColor;
    }

    // Конструктор
    public Boat(int maxSpeed, int weight, Color mainColor) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }

    // Конструктор с изменением размеров лодки
    protected Boat(int maxSpeed, int weight, Color mainColor, int catamaranWidth, int catamaranHeight) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        this.catamaranWidth = catamaranWidth;
        this.catamaranHeight = catamaranHeight;
    }

    // Изменение направления пермещения
    @Override
    public void MoveTransport(Direction direction) {
        int step = MaxSpeed * 100 / Weight;
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

    @Override
    public void DrawTransport(Graphics g) {
        g.setColor(MainColor);
        g.fillPolygon(new int[] {_startPosX + 10, _startPosX + 85, _startPosX + 115, _startPosX + 85, _startPosX + 10},
                new int[] {_startPosY + 25, _startPosY + 25, _startPosY + 40, _startPosY + 55, _startPosY + 55}, 5);

        g.setColor(Color.WHITE);
        g.fillOval(_startPosX + 15, _startPosY + 30, 75, 20);
    }
}