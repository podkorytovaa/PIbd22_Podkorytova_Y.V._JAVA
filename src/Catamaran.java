import java.awt.*;
import java.util.*;

public class Catamaran extends Boat implements Iterable<String>, Iterator<String>, Comparable<Boat> {
    private IFloats addFloat;
    public IFloats getFloats() { return addFloat; }
    public void setFloats(IFloats addFloat) { this.addFloat = addFloat; }

    public Color DopColor; // Дополнительный цвет
    public Color getDopColor() { return DopColor; }
    public void setDopColor(Color dopColor) { DopColor = dopColor; }

    public boolean Floater; // Признак наличия поплавков
    public boolean getFloater() { return Floater; }
    public void setFloater(boolean floater) { Floater = floater; }

    public boolean ControlWheel; // Признак наличия перо руля
    public boolean getControlWheel() { return ControlWheel; }
    public void setControlWheel(boolean controlWheel) { ControlWheel = controlWheel; }

    public boolean Seat; // Признак наличия сидения
    public boolean getSeat() { return Seat; }
    public void setSeat(boolean seat) { Seat = seat; }

    public int FloatsAmount;
    public int AddState;

    // Конструктор
    public Catamaran(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean floater, boolean controlWheel, boolean seat, int floatsAmount, int addState) {
        super(maxSpeed, weight, mainColor, 115, 95);
        DopColor = dopColor;
        Floater = floater;
        ControlWheel = controlWheel;
        Seat = seat;
        FloatsAmount = floatsAmount;
        AddState = addState;
        SetIFloats(AddState);
        addFloat.SetAmount(FloatsAmount);
    }

    // Конструктор для загрузки с файла
    public Catamaran(String info) {
        super(info);
        String[] strs = info.split(separator);
        if (strs.length == 9) {
            MaxSpeed = Integer.parseInt(strs[0]);
            Weight = Integer.parseInt(strs[1]);
            MainColor = Color.decode(strs[2]);
            DopColor = Color.decode(strs[3]);
            Floater = Boolean.parseBoolean(strs[4]);
            ControlWheel = Boolean.parseBoolean(strs[5]);
            Seat = Boolean.parseBoolean(strs[6]);
            FloatsAmount = Integer.parseInt(strs[7]);
            AddState = Integer.parseInt(strs[8]);
            SetIFloats(AddState);
            addFloat.SetAmount(FloatsAmount);
        }
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

    public void SetIFloats(int number) {
        switch (number) {
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
    }

    @Override
    public String toString() {
        return super.toString() + separator + DopColor.getRGB() + separator + Floater + separator + ControlWheel + separator + Seat + separator + FloatsAmount + separator + AddState;
    }

    public boolean equals(Catamaran other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() != Catamaran.class) {
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
        if (DopColor != other.DopColor) {
            return false;
        }
        if (Floater != other.Floater) {
            return false;
        }
        if (ControlWheel != other.ControlWheel) {
            return false;
        }
        if (Seat != other.Seat) {
            return false;
        }
        if (addFloat.getClass() != other.addFloat.getClass()){
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != Catamaran.class) {
            return false;
        }
        else {
            return equals((Catamaran)obj);
        }
    }

    private int currentIndex;
    @Override
    public int compareTo(Boat boat) {
        Catamaran catamaran = (Catamaran) boat;
        if (DopColor != catamaran.DopColor) {
            return Integer.compare(DopColor.getRGB(), catamaran.DopColor.getRGB());
        }
        if (Floater != catamaran.Floater) {
            return Boolean.compare(Floater, catamaran.Floater);
        }
        if (ControlWheel != catamaran.ControlWheel) {
            return Boolean.compare(ControlWheel, catamaran.ControlWheel);
        }
        if (Seat != catamaran.Seat) {
            return Boolean.compare(Seat, catamaran.Seat);
        }
        if (addFloat == null && catamaran.addFloat != null) {
            return 1;
        }
        if (addFloat != null && catamaran.addFloat == null) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean hasNext() {
        if (currentIndex > 8) {
            currentIndex = -1;
            return false;
        }
        return true;
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
            case 3:
                return String.valueOf(DopColor.getRGB());
            case 4:
                return String.valueOf(Floater);
            case 5:
                return String.valueOf(ControlWheel);
            case 6:
                return String.valueOf(Seat);
            case 7:
                return String.valueOf(addFloat);
            case 8:
                return String.valueOf(addFloat);
        }
        return null;
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }
}
