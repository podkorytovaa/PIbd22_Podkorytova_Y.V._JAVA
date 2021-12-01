import javax.swing.*;
import java.awt.*;

public class DrawPort extends JPanel {
    private PortCollection portCollection;
    private String port = null;

    public DrawPort(PortCollection portCollection) {
        this.portCollection = portCollection;
    }

    protected void paintComponent(Graphics g) {
        if (port != null) {
            if (portCollection != null) {
                portCollection.Get(port).Draw(g);
            }
        }
    }

    public void SetPort(String port) {
        this.port = port;
    }
}