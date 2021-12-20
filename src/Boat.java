import java.awt.*;
import java.util.*;

public class Boat extends Vehicle implements Iterator<String>, Iterable<String>, Comparable<Boat> {
    protected int catamaranWidth = 105; // Ширина отрисовки лодки
    protected int catamaranHeight = 55; // Высота отрисовки лодки
    protected String separator = ";";

    // Конструктор
    public Boat(int maxSpeed, int weight, Color mainColor) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }

    // Конструктор для загрузки с файла
    public Boat(String info) {
        String[] strs = info.split(separator);
        if (strs.length == 3) {
            MaxSpeed = Integer.parseInt(strs[0]);
            Weight = Integer.parseInt(strs[1]);
            MainColor = Color.decode(strs[2]);
        }
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

    @Override
    public String toString() {
        return MaxSpeed + separator + Weight + separator + MainColor.getRGB();
    }

    public void SetMainColor(Color mainColor) {
        MainColor = mainColor;
    }

    public boolean equals(Boat other) {
        if (other == null) {
            return false;
        }
        if(other.getClass() != Boat.class) {
            return false;
        }
        if (MaxSpeed != other.MaxSpeed) {
            return false;
        }
        if (Weight != other.Weight) {
            return false;
        }
        if (MainColor != other.MainColor) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != Boat.class) {
            return false;
        }
        else {
            return equals((Boat) obj);
        }
    }

    private int currentIndex;
    @Override
    public int compareTo(Boat boat) {
        if (MaxSpeed != boat.MaxSpeed) {
            return Integer.compare(MaxSpeed, boat.MaxSpeed);
        }
        if (Weight != boat.Weight) {
            return Integer.compare(Weight, boat.Weight);
        }
        if (MainColor != boat.MainColor) {
            return Integer.compare(MainColor.getRGB(), boat.MainColor.getRGB());
        }
        return 0;
    }

    @Override
    public String next() {
        currentIndex++;
        switch (currentIndex) {
            case 0:
                return String.valueOf(MaxSpeed);
            case 1:
                return String.valueOf(Weight);
            case 2:
                return String.valueOf(MainColor.getRGB());
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (currentIndex > 2) {
            currentIndex = -1;
            return false;
        }
        return true;
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }
}