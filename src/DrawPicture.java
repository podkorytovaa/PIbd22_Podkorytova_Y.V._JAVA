import javax.swing.*;
import java.awt.*;

public class DrawPicture extends JPanel {
    private Catamaran catamaran;

    public void paintComponent(Graphics g) {
        if (catamaran != null) {
            catamaran.DrawTransport(g);
        }
    }

    public void SetCatamaran(Catamaran ex) {
        this.catamaran = ex;
    }

    public Catamaran GetCatamaran() {
        return catamaran;
    }
}