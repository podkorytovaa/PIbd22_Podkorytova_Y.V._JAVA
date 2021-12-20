import java.util.*;

public class BoatComparer implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle x, Vehicle y) {
        if (x.getClass() == y.getClass()) {
            if(x.getClass() == Boat.class) {
                return ComparerBoat((Boat)x, (Boat)y);
            }
            else {
                return ComparerCatamaran((Catamaran)x, (Catamaran)y);
            }
        }
        else {
            if (x.getClass() == Boat.class) {
                return 1;
            }
            else {
                return -1;
            }
        }
    }

    private int ComparerBoat(Boat x, Boat y) {
        if (x.MaxSpeed != y.MaxSpeed) {
            return Integer.compare(x.MaxSpeed, y.MaxSpeed);
        }
        if (x.Weight != y.Weight) {
            return Integer.compare(x.Weight, y.Weight);
        }
        if (x.MainColor != y.MainColor) {
            return Integer.compare(x.MainColor.getRGB(), y.MainColor.getRGB());
        }
        return 0;
    }

    private int ComparerCatamaran(Catamaran x, Catamaran y) {
        int res = ComparerBoat(x, y);
        if (res != 0) {
            return res;
        }
        if (x.DopColor != y.DopColor) {
            return Integer.compare(x.DopColor.getRGB(), y.DopColor.getRGB());
        }
        if (x.Floater != y.Floater) {
            return Boolean.compare(x.Floater, y.Floater);
        }
        if (x.ControlWheel != y.ControlWheel) {
            return Boolean.compare(x.ControlWheel, y.ControlWheel);
        }
        if (x.Seat != y.Seat) {
            return Boolean.compare(x.Seat, y.Seat);
        }
        return 0;
    }
}
