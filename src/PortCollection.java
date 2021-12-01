import java.util.*;

public class PortCollection {
    final HashMap<String, Port<Vehicle, IFloats>> portStages; // Хранилище с гаванями
    private final int pictureWidth; // Ширина окна отрисовки
    private final int pictureHeight; // Высота окна отрисовки

    public Set<String> Keys() {
        return portStages.keySet();
    }

    // Конструктор
    public PortCollection(int pictureWidth, int pictureHeight) {
        portStages = new HashMap<>();
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }

    // Добавление гавани
    public void AddPort(String name) {
        if (portStages.containsKey(name)) {
            return;
        }
        portStages.put(name, new Port<>(pictureWidth, pictureHeight));
    }

    // Удаление гавани
    public void DelPort(String name) {
        if (portStages.containsKey(name)) {
            portStages.remove(name);
        }
    }

    // Доступ к гавани
    public Port<Vehicle, IFloats> Get(String name) {
        if (portStages.containsKey(name)) {
            return portStages.get(name);
        }
        return null;
    }

    public Vehicle Get(String name, int index) {
        if (portStages.containsKey(name)) {
            return portStages.get(name).Get(index);
        }
        return null;
    }
}
