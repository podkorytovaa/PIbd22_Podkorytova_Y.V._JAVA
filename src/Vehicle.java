import java.awt.*;

public abstract  class Vehicle implements ITransport {
    protected int _startPosX; // Левая координата отрисовки лодки
    protected int _startPosY; // Правая кооридната отрисовки лодки
    protected int _pictureWidth; // Ширина окна отрисовки
    protected int _pictureHeight; // Высота окна отрисовки

    public int MaxSpeed; // Максимальная скорость
    public int getMaxSpeed() { return MaxSpeed; }
    public void setMaxSpeed(int maxSpeed) { MaxSpeed = maxSpeed; }

    public int Weight; // Вес лодки
    public int getWeight() { return Weight; }
    public void setWeight(int weight) { Weight = weight; }

    public Color MainColor; // Основной цвет
    public Color getMainColor() { return MainColor; }
    public void setMainColor(Color mainColor) { MainColor = mainColor; }

    public void SetPosition(int x, int y, int width, int height) {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    public abstract void DrawTransport(Graphics g);

    public abstract void MoveTransport(Direction direction);
}