import javax.swing.*;
import java.awt.*;

public class DrawPort extends JPanel {
    private Port<Boat, IFloats> port;

    public DrawPort(Port<Boat, IFloats> port) {
        this.port = port;
    }

    protected void paintComponent(Graphics g) {
        if (port != null) {
            port.Draw(g);
        }
    }

    public Port<Boat, IFloats> getPort() {
        return port;
    }
}