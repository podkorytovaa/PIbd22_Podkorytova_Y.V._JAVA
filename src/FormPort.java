import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;

public class FormPort {
    private JFrame framePort;
    private Port<Boat, IFloats> port;
    private DrawPort drawPort;

    private JButton buttonSetBoat;
    private JButton buttonSetCatamaran;
    private JPanel groupBox;
    private JLabel labelPlace;
    private JTextField textFieldPlace;
    private JButton buttonTakeBoat;
    private Border borderTake;

    public FormPort() {
        framePort = new JFrame("Гавань");
        framePort.setSize(920, 520);
        framePort.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        framePort.setLayout(null);
        framePort.setResizable(false);
        framePort.setVisible(true);
        InitializeComponent();
        framePort.getContentPane().add(buttonSetBoat);
        framePort.getContentPane().add(buttonSetCatamaran);
        framePort.getContentPane().add(groupBox);
        framePort.getContentPane().add(drawPort);
        framePort.repaint();
    }

    public void InitializeComponent() {
        port = new Port<>(695, 490);
        drawPort = new DrawPort(port);

        buttonSetBoat = new JButton("Пришвартовать лодку");
        buttonSetBoat.setBounds(710, 10, 190, 30);
        buttonSetBoat.addActionListener(e -> {
            Color mainColor = JColorChooser.showDialog(framePort, "Color", Color.BLUE);
            if (mainColor != null) {
                Boat boat = new Boat(100,1000, mainColor);
                int index = port.Plus(boat);
                if (index > -1) {
                    framePort.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(framePort, "Гавань переполнена");
                }
            }
        });

        buttonSetCatamaran = new JButton("Пришвартовать катамаран");
        buttonSetCatamaran.setBounds(710, 50, 190, 30);
        buttonSetCatamaran.addActionListener(e -> {
            Color mainColor = JColorChooser.showDialog(framePort, "Color", Color.BLUE);
            if (mainColor != null) {
                Color dopColor = JColorChooser.showDialog(framePort, "Color", Color.WHITE);
                if (dopColor != null) {
                    Random rnd = new Random();
                    Catamaran catamaran = new Catamaran(100, 1000, mainColor, dopColor,true, true, true, rnd.nextInt(3) + 1, rnd.nextInt(3));
                    int index = port.Plus(catamaran);
                    if (index > -1) {
                        framePort.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(framePort, "Гавань переполнена");
                    }
                }
            }
        });

        borderTake = BorderFactory.createTitledBorder("Забрать транспорт");
        labelPlace = new JLabel("Место: ");
        labelPlace.setBounds(20, 25, 50, 30);
        textFieldPlace = new JTextField();
        textFieldPlace.setBounds(70, 30, 90, 20);

        buttonTakeBoat = new JButton("Забрать");
        buttonTakeBoat.setBounds(40, 65, 100, 20);
        buttonTakeBoat.addActionListener(e -> {
            if (textFieldPlace.getText() != "") {
                int index = Integer.parseInt(textFieldPlace.getText());
                ITransport boat = port.Minus(index);
                if (boat != null) {
                    FormCatamaran formCatamaran = new FormCatamaran();
                    formCatamaran.SetBoat(boat);
                    framePort.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(framePort, "Транспорта не существует");
                }
            }
        });

        groupBox = new JPanel();
        groupBox.setLayout(null);
        groupBox.add(labelPlace);
        groupBox.add(textFieldPlace);
        groupBox.add(buttonTakeBoat);
        groupBox.setBounds(720, 110, 180, 100);
        groupBox.setBorder(borderTake);

        drawPort.setBounds(0, 0, 695, 490);
    }
}