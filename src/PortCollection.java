import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PortCollection {
    final HashMap<String, Port<Vehicle, IFloats>> portStages; // Хранилище с гаванями
    private final int pictureWidth; // Ширина окна отрисовки
    private final int pictureHeight; // Высота окна отрисовки
    private final String separator = ":";
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
            return portStages.get(name).GetNext(index);
        }
        return null;
    }

    // Сохранение информации по лодкам в гаванях в файл
    public boolean SaveData(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            fileWriter.write("PortCollection\n");
            for (HashMap.Entry<String, Port<Vehicle, IFloats>> level : portStages.entrySet()) {
                fileWriter.write("Port" + separator + level.getKey() + "\n");
                ITransport boat;
                for (int i = 0; (boat = level.getValue().GetNext(i)) != null; i++) {
                    if (boat.getClass().getSimpleName().equals("Boat")) {
                        fileWriter.write("Boat" + separator);
                    }
                    else if (boat.getClass().getSimpleName().equals("Catamaran")) {
                        fileWriter.write("Catamaran" + separator);
                    }
                    fileWriter.write(boat.toString() + "\n");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Загрузка информации по транспорту в гаванях из файла
    public boolean LoadData(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return false;
        }
        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            String line = scanner.nextLine();
            if (line.contains("PortCollection")) {
                portStages.clear();
            }
            else {
                return false;
            }
            Vehicle boat = null;
            String key = "";
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains("Port")) {
                    key = line.split(separator)[1];
                    portStages.put(key, new Port<>(pictureWidth, pictureHeight));
                    continue;
                }
                if (line.contains(separator)) {
                    if (line.contains("Boat")) {
                        boat = new Boat(line.split(separator)[1]);
                    }
                    else if (line.contains("Catamaran")) {
                        boat = new Catamaran(line.split(separator)[1]);
                    }
                    int result = portStages.get(key).Plus(boat);
                    if (result == -1) {
                        return false;
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Сохранение гавани
    public boolean SavePort(String filename, String key) {
        if (!portStages.containsKey(key)) {
            return false;
        }
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            if (portStages.containsKey(key)) {
                fileWriter.write("Port" + separator + key + "\n");
            }
            ITransport boat;
            for (int i = 0; (boat = portStages.get(key).GetNext(i)) != null; i++) {
                if (boat.getClass().getSimpleName().equals("Boat")) {
                    fileWriter.write("Boat" + separator);
                }
                else if (boat.getClass().getSimpleName().equals("Catamaran")) {
                    fileWriter.write("Catamaran" + separator);
                }
                fileWriter.write(boat.toString() + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Загрузка гавани
    public boolean LoadPort(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return false;
        }
        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            String key;
            String line = scanner.nextLine();
            if (line.contains("Port:")) {
                key = line.split(separator)[1];
                if (portStages.containsKey(key)) {
                    portStages.get(key).ClearPlaces();
                }
                else {
                    portStages.put(key, new Port<>(pictureWidth, pictureHeight));
                }
            }
            else {
                return false;
            }
            Vehicle boat = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(separator)) {
                    if (line.contains("Boat")) {
                        boat = new Boat(line.split(separator)[1]);
                    }
                    else if (line.contains("Catamaran")) {
                        boat = new Catamaran(line.split(separator)[1]);
                    }
                    int result = portStages.get(key).Plus(boat);
                    if (result == -1) {
                        return false;
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
