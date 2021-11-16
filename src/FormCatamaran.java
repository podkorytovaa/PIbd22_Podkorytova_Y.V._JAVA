import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class FormCatamaran {
    Catamaran catamaran;
    JPanel panel;
    Graphics g;

    JButton buttonCreate = new JButton("Создать");
    JButton buttonUp = new JButton(new ImageIcon("Resources/up.png"));
    JButton buttonDown = new JButton(new ImageIcon("Resources/down.png"));
    JButton buttonLeft = new JButton(new ImageIcon("Resources/left.png"));
    JButton buttonRight = new JButton(new ImageIcon("Resources/right.png"));

    private void Draw() {
        catamaran.DrawTransport(g);
    }

    public FormCatamaran() {
        JFrame frame = new JFrame("Катамаран");
        frame.setLayout(null);
        frame.setSize(700, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setSize(690, 350);
        panel.setBackground(Color.WHITE);
        panel.setLocation(0, 0);
        panel.setVisible(true);

        buttonCreate.setLocation(10, 430);
        buttonCreate.setSize(100, 30);
        buttonCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                g = panel.getGraphics();
                panel.update(g);
                Random rnd = new Random();
                catamaran = new Catamaran();
                catamaran.Init(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.BLUE, Color.WHITE, true, true,  rnd.nextInt(3) + 1);
                catamaran.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 10,
                        panel.getWidth(), panel.getHeight());
                Draw();
            }
        });

        buttonUp.setLocation(560, 360);
        buttonUp.setSize(50, 50);
        buttonUp.setVisible(true);
        buttonUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.update(g);
                catamaran.MoveTransport(Direction.Up);
                Draw();
            }
        });

        buttonDown.setLocation(560, 420);
        buttonDown.setSize(50, 50);
        buttonDown.setVisible(true);
        buttonDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.update(g);
                catamaran.MoveTransport(Direction.Down);
                Draw();
            }
        });

        buttonRight.setLocation(620, 420);
        buttonRight.setSize(50, 50);
        buttonRight.setVisible(true);
        buttonRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.update(g);
                catamaran.MoveTransport(Direction.Right);
                Draw();
            }
        });

        buttonLeft.setLocation(500, 420);
        buttonLeft.setSize(50, 50);
        buttonLeft.setVisible(true);
        buttonLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.update(g);
                catamaran.MoveTransport(Direction.Left);
                Draw();
            }
        });

        frame.add(buttonCreate);
        frame.add(buttonUp);
        frame.add(buttonDown);
        frame.add(buttonRight);
        frame.add(buttonLeft);
        frame.add(panel);
        frame.setVisible(true);
    }
}
