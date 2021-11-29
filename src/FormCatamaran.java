import javax.swing.*;
import java.awt.*;
import java.util.*;

public class FormCatamaran {
    private JFrame frame;
    private ITransport boat;
    private DrawPicture draw;

    private JButton buttonCreateBoat;
    private JButton buttonCreateCatamaran;
    private JButton buttonUp;
    private JButton buttonDown;
    private JButton buttonLeft;
    private JButton buttonRight;

    public FormCatamaran() {
        draw = new DrawPicture();

        frame = new JFrame("Катамаран");
        frame.setSize(700, 520);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        InitializeComponent();

        frame.getContentPane().add(buttonCreateBoat);
        frame.getContentPane().add(buttonCreateCatamaran);
        frame.getContentPane().add(buttonUp);
        frame.getContentPane().add(buttonDown);
        frame.getContentPane().add(buttonLeft);
        frame.getContentPane().add(buttonRight);
        frame.getContentPane().add(draw);
        draw.setBounds(0, 0, 695, 490);
        frame.repaint();
    }

    // Передача лодки на форму
    public void SetBoat(ITransport boat) {
        this.boat = boat;
        draw.SetTransport(boat);
        frame.repaint();
    }

    public void InitializeComponent() {
        buttonCreateBoat = new JButton("Создать лодку");
        buttonCreateBoat.setBounds(10, 440, 150, 30);
        buttonCreateBoat.addActionListener(e -> {
            Random rnd = new Random();
            boat = new Boat(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.BLUE);
            boat.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 10, draw.getWidth(), draw.getHeight());
            draw.SetTransport(boat);
            frame.repaint();
        });

        buttonCreateCatamaran = new JButton("Создать катамаран");
        buttonCreateCatamaran.setBounds(170, 440, 170, 30);
        buttonCreateCatamaran.addActionListener(e -> {
            Random rnd = new Random();
            boat = new Catamaran(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.BLUE, Color.WHITE, true, true, true, rnd.nextInt(3) + 1, rnd.nextInt(3));
            boat.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 10, draw.getWidth(), draw.getHeight());
            draw.SetTransport(boat);
            frame.repaint();
        });

        buttonUp = new JButton(new ImageIcon("Resources/up.png"));
        buttonUp.setName("Up");
        buttonUp.setBounds(560, 360, 50, 50);
        buttonUp.addActionListener(e -> ButtonClick(buttonUp));

        buttonDown = new JButton(new ImageIcon("Resources/down.png"));
        buttonDown.setName("Down");
        buttonDown.setBounds(560, 420, 50, 50);
        buttonDown.addActionListener(e -> ButtonClick(buttonDown));

        buttonLeft = new JButton(new ImageIcon("Resources/left.png"));
        buttonLeft.setName("Left");
        buttonLeft.setBounds(500, 420, 50, 50);
        buttonLeft.addActionListener(e -> ButtonClick(buttonLeft));

        buttonRight = new JButton(new ImageIcon("Resources/right.png"));
        buttonRight.setName("Right");
        buttonRight.setBounds(620, 420, 50, 50);
        buttonRight.addActionListener(e -> ButtonClick(buttonRight));
    }

    public void ButtonClick(JButton button) {
        String name = button.getName();
        switch (name) {
            case "Up":
                boat.MoveTransport(Direction.Up);
                break;
            case "Down":
                boat.MoveTransport(Direction.Down);
                break;
            case "Left":
                boat.MoveTransport(Direction.Left);
                break;
            case "Right":
                boat.MoveTransport(Direction.Right);
                break;
        }
        frame.repaint();
    }
}
