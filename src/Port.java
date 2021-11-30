import java.awt.*;

public class Port<T extends ITransport, F extends IFloats> {
    private final T[] _places; // Массив объектов, которые храним
    private final int pictureWidth; // Ширина окна отрисовки
    private final int pictureHeight; // Высота окна отрисовки
    private final int _placeSizeWidth = 230; // Ширина парковочного места
    private final int _placeSizeHeight = 120; // Высота парковочного места

    // Конструктор
    public Port(int picWidth, int picHeight) {
        int width = picWidth / _placeSizeWidth;
        int height = picHeight / _placeSizeHeight;
        _places = (T[]) new ITransport[width * height];
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    // Перегрузка оператора сложения
    public int Plus(T boat) {
        int i = 0;
        while (i < pictureHeight / _placeSizeHeight) {
            int j = 0;
            while (j < pictureWidth / _placeSizeWidth) {
                if (_places[i * (pictureWidth / _placeSizeWidth) + j] == null) {
                    _places[i * (pictureWidth / _placeSizeWidth) + j] = boat;
                    boat.SetPosition(_placeSizeWidth * j + 5, _placeSizeHeight * i + 12, pictureWidth, pictureHeight);
                    return (i * (pictureWidth / _placeSizeWidth) + j);
                }
                j++;
            }
            i++;
        }
        return -1;
    }

    // Перегрузка оператора вычитания
    public T Minus(int index) {
        if (index > -1 && index < _places.length && _places[index] != null) {
            T temp = (T) _places[index];
            _places[index] = null;
            return temp;
        }
        return null;
    }

    public boolean More(Port<T, F> p1, Port<T, F> p2) {
        int placesP1 = 0;
        int placesP2 = 0;
        for (int i = 0; i < p1._places.length; i++) {
            if (p1._places[i] != null) placesP1++;
        }
        for (int i = 0; i < p2._places.length; i++) {
            if (p2._places[i] != null) placesP2++;
        }
        return (placesP1 > placesP2);
    }

    public boolean Less(Port<T, F> p1, Port<T, F> p2) {
        int placesP1 = 0;
        int placesP2 = 0;
        for (int i = 0; i < p1._places.length; i++) {
            if (p1._places[i] != null) placesP1++;
        }
        for (int i = 0; i < p2._places.length; i++) {
            if (p2._places[i] != null) placesP2++;
        }
        return (placesP1 < placesP2);
    }

    // Метод отрисовки гавани
    public void Draw(Graphics g) {
        DrawMarking(g);
        for (int i = 0; i < _places.length; i++) {
            if (_places[i] != null) {
                _places[i].DrawTransport(g);
            }
        }
    }

    // Метод отрисовки разметки парковочных мест
    private void DrawMarking(Graphics gg) {
        Graphics2D g = (Graphics2D) gg;
        g.setColor(new Color(0, 149, 182));
        g.fillRect(0, 0, pictureWidth, pictureHeight);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3));
        for (int i = 0; i < pictureWidth / _placeSizeWidth; i++) {
            for (int j = 0; j < pictureHeight / _placeSizeHeight + 1; j++) {
                g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i * _placeSizeWidth + _placeSizeWidth / 2, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, (pictureHeight / _placeSizeHeight) * _placeSizeHeight);
        }
    }
}