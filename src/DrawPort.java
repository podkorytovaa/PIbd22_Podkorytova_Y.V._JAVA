import javax.swing.*;
import java.awt.*;

public class DrawPort extends JPanel {
    private PortCollection portCollector;
    private String port = null;

    public DrawPort(PortCollection portCollection) {
        portCollector = portCollection;
    }

    protected void paintComponent(Graphics g) {
        if (port != null) {
            if (portCollector != null) {
                portCollector.Get(port).Draw(g);
            }
        }
    }

    public void SetPort(String port) {
        this.port = port;
    }
}