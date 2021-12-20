import java.awt.*;
import java.util.*;

public class Port<T extends ITransport, F extends IFloats> implements Iterator<T>, Iterable<T> {
    private final ArrayList<T> _places; // Список объектов, которые храним
    private final int _maxCount; // Максимальное количество мест в гавани
    private final int pictureWidth; // Ширина окна отрисовки
    private final int pictureHeight; // Высота окна отрисовки
    private final int _placeSizeWidth = 230; // Ширина парковочного места
    private final int _placeSizeHeight = 120; // Высота парковочного места
    private int _currentIndex;

    // Конструктор
    public Port(int picWidth, int picHeight) {
        int width = picWidth / _placeSizeWidth;
        int height = picHeight / _placeSizeHeight;
        _maxCount = width * height;
        _places = new ArrayList<>();
        pictureWidth = picWidth;
        pictureHeight = picHeight;
        _currentIndex = -1;
    }

    // Перегрузка оператора сложения
    public int Plus(T boat) throws PortOverflowException, PortAlreadyHaveException {
        if (_places.size() >= _maxCount) {
            throw new PortOverflowException();
        }
        if (_places.contains(boat)) {
            throw new PortAlreadyHaveException();
        }
        _places.add(boat);
        return _places.size() - 1;
    }

    // Перегрузка оператора вычитания
    public T Minus(int index) throws PortNotFoundException {
        if (index <= -1 || index >= _places.size()) {
            throw new PortNotFoundException(index);
        }
        T temp = _places.get(index);
        _places.remove(index);
        return temp;
    }

    public boolean More(Port<T, F> p1, Port<T, F> p2) {
        int placesP1 = 0;
        int placesP2 = 0;
        for (int i = 0; i < p1._places.size(); i++) {
            if (p1._places.get(i) != null) placesP1++;
        }
        for (int i = 0; i < p2._places.size(); i++) {
            if (p2._places.get(i) != null) placesP2++;
        }
        return (placesP1 > placesP2);
    }

    public boolean Less(Port<T, F> p1, Port<T, F> p2) {
        int placesP1 = 0;
        int placesP2 = 0;
        for (int i = 0; i < p1._places.size(); i++) {
            if (p1._places.get(i) != null) placesP1++;
        }
        for (int i = 0; i < p2._places.size(); i++) {
            if (p2._places.get(i) != null) placesP2++;
        }
        return (placesP1 < placesP2);
    }

    // Метод отрисовки гавани
    public void Draw(Graphics g) {
        DrawMarking(g);
        for (int i = 0; i < _places.size(); i++) {
            _places.get(i).SetPosition(i % (pictureWidth / _placeSizeWidth) * _placeSizeWidth + 5, i / (pictureWidth / _placeSizeWidth) * _placeSizeHeight + 14, pictureWidth, pictureHeight);
            _places.get(i).DrawTransport(g);
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

    // Функция получения элементы из списка
    public T GetNext(int index) {
        if (index > -1 && index < _places.size()) {
            return _places.get(index);
        }
        return null;
    }

    public void ClearPlaces() {
        _places.clear();
    }

    public void Sort() {
        _places.sort((Comparator<T>)new BoatComparer());
    }

    @Override
    public boolean hasNext() {
        return _currentIndex < _places.size() - 1;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        _currentIndex++;
        return _places.get(_currentIndex);
    }

    @Override
    public Iterator<T> iterator() {
        _currentIndex = -1;
        return this;
    }
}