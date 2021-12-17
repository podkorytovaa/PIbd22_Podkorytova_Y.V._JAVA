import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class FormCatamaranConfig extends JDialog {
    private Boat boat;
    private Color color;
    private IFloats floats;
    private DrawPicture draw;

    private Border typeBorder;
    private JPanel panelTypeOfTransport;
    private JLabel lableBoat;
    private JLabel lableCatamaran;
    private JPanel panelOptions;
    private JLabel labelMaxSpeed;
    private JLabel labelWight;
    private JSpinner spinnerMaxSpeed;
    private JSpinner spinnerWeight;
    private JCheckBox checkBoxControlWheel;
    private JCheckBox checkBoxSeat;
    private JPanel panelColors;
    private JLabel labelMainColor;
    private JLabel labelDopColor;
    private JPanel panelRed;
    private JPanel panelBlue;
    private JPanel panelYellow;
    private JPanel panelFuchsia;
    private JPanel panelWhite;
    private JPanel panelBlack;
    private JPanel panelGreen;
    private JPanel panelPink;
    private JButton buttonAdd;
    private JButton buttonCancel;
    private JPanel panelFloats;
    private JLabel labelNormal;
    private JLabel labelOval;
    private JLabel labelSemiOval;
    private JLabel labelFloatsCount;
    private JSpinner spinnerFloatsCount;
    private int state;

    public FormCatamaranConfig(Frame frame) {
        super(frame, true);
        setSize(640, 350);
        setLayout(null);
        InitializeComponent();
        add(panelTypeOfTransport);
        getContentPane().add(draw);
        add(panelOptions);
        add(panelColors);
        add(buttonAdd);
        add(buttonCancel);
        add(panelFloats);
        setVisible(true);
    }

    public void InitializeComponent() {
        // Панель параметров
        panelOptions = new JPanel();
        panelOptions.setBounds(10, 150, 210, 110);
        panelOptions.setLayout(null);
        typeBorder = BorderFactory.createTitledBorder("Параметры");
        panelOptions.setBorder(typeBorder);
        labelMaxSpeed = new JLabel("Макс. скорость:");
        labelMaxSpeed.setBounds(10, 20, 100, 15);
        labelWight = new JLabel("Вес:");
        labelWight.setBounds(10, 65, 100, 15);
        checkBoxControlWheel = new JCheckBox("Перо руля");
        checkBoxControlWheel.setBounds(115, 30, 90, 20);
        checkBoxSeat = new JCheckBox("Сидение");
        checkBoxSeat.setBounds(115, 60, 90, 20);
        spinnerMaxSpeed = new JSpinner(new SpinnerNumberModel(100, 1, 1000, 1));
        spinnerMaxSpeed.setBounds(55, 40, 50, 20);
        spinnerWeight = new JSpinner(new SpinnerNumberModel(100, 1, 1000, 1));
        spinnerWeight.setBounds(55, 80, 50, 20);
        panelOptions.add(labelMaxSpeed);
        panelOptions.add(labelWight);
        panelOptions.add(checkBoxControlWheel);
        panelOptions.add(checkBoxSeat);
        panelOptions.add(spinnerMaxSpeed);
        panelOptions.add(spinnerWeight);

        // Панель выбора поплавков
        panelFloats = new JPanel();
        panelFloats.setBounds(240, 150, 370, 110);
        panelFloats.setLayout(null);
        typeBorder = BorderFactory.createTitledBorder("Поплавки");
        panelFloats.setBorder(typeBorder);
        labelNormal = new JLabel("Обычные");
        labelNormal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelNormal.setBounds(20, 30, 100, 40);
        labelNormal.setHorizontalAlignment(SwingConstants.CENTER);
        labelOval = new JLabel("Овальные");
        labelOval.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelOval.setBounds(135, 30, 100, 40);
        labelOval.setHorizontalAlignment(SwingConstants.CENTER);
        labelSemiOval = new JLabel("Полуовальные");
        labelSemiOval.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelSemiOval.setBounds(250, 30, 100, 40);
        labelSemiOval.setHorizontalAlignment(SwingConstants.CENTER);
        labelFloatsCount = new JLabel("Количество поплавков:");
        labelFloatsCount.setBounds(80, 78, 140, 20);
        spinnerFloatsCount = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
        spinnerFloatsCount.setBounds(240, 80, 50, 20);

        MouseAdapter listenerFloats = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JLabel labelFloater = (JLabel) e.getSource();
                int number = (int) spinnerFloatsCount.getValue();
                switch (labelFloater.getText()) {
                    case "Обычные":
                        floats = new AddFloats();
                        state = 0;
                        break;
                    case "Овальные":
                        floats = new AddOvalFloats();
                        state = 1;
                        break;
                    case "Полуовальные":
                        floats = new AddSemiOvalFloats();
                        state = 2;
                        break;
                }
                floats.SetAmount(number);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (draw.GetCatamaran() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() + panelFloats.getX() >= draw.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getAlignmentX() + panelFloats.getX() <= draw.getX() + draw.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() + panelFloats.getY() >= draw.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() + panelFloats.getY() <= draw.getY() + draw.getHeight() &&
                            draw.GetCatamaran()instanceof Catamaran) {
                        Catamaran catamaran = (Catamaran) draw.GetCatamaran();
                        catamaran.setFloats(floats);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                floats = null;
            }
        };

        labelNormal.addMouseListener(listenerFloats);
        labelOval.addMouseListener(listenerFloats);
        labelSemiOval.addMouseListener(listenerFloats);
        panelFloats.add(labelNormal);
        panelFloats.add(labelOval);
        panelFloats.add(labelSemiOval);
        panelFloats.add(labelFloatsCount);
        panelFloats.add(spinnerFloatsCount);

        // Панель выбора транспорта
        panelTypeOfTransport = new JPanel();
        panelTypeOfTransport.setBounds(10, 10, 180, 135);
        panelTypeOfTransport.setLayout(null);
        Border typeBorder = BorderFactory.createTitledBorder("Тип транспорта");
        panelTypeOfTransport.setBorder(typeBorder);
        lableBoat = new JLabel("Лодка");
        lableBoat.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        lableBoat.setBounds(20, 30, 140, 40);
        lableBoat.setHorizontalAlignment(SwingConstants.CENTER);
        lableCatamaran = new JLabel("Катамаран");
        lableCatamaran.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        lableCatamaran.setBounds(20, 80, 140, 40);
        lableCatamaran.setHorizontalAlignment(SwingConstants.CENTER);

        MouseAdapter listenerTransportType = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JLabel label = (JLabel) e.getSource();
                int maxSpeed = (int) spinnerMaxSpeed.getValue();
                int weight = (int) spinnerWeight.getValue();
                switch (label.getText()) {
                    case "Лодка":
                        boat = new Boat(maxSpeed, weight, Color.WHITE);
                        break;
                    case "Катамаран":
                        boat = new Catamaran(maxSpeed, weight, Color.WHITE, Color.WHITE,true, checkBoxControlWheel.isSelected(), checkBoxSeat.isSelected(), (int)spinnerFloatsCount.getValue(), state);
                        break;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getX() + ((JComponent) e.getSource()).getX() + panelTypeOfTransport.getX() >= draw.getX() &&
                        e.getX() + ((JComponent) e.getSource()).getX() + panelTypeOfTransport.getX() <= draw.getX() + draw.getWidth() &&
                        e.getY() + ((JComponent) e.getSource()).getY() + panelTypeOfTransport.getY() >= draw.getY() &&
                        e.getY() + ((JComponent) e.getSource()).getY() + panelTypeOfTransport.getY() <= draw.getY() + draw.getHeight()) {
                    boat.SetPosition(15, 15, draw.getWidth(), draw.getHeight());
                    draw.SetTransport(boat);
                    repaint();
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                boat = null;
            }
        };

        lableBoat.addMouseListener(listenerTransportType);
        lableCatamaran.addMouseListener(listenerTransportType);
        panelTypeOfTransport.add(lableBoat);
        panelTypeOfTransport.add(lableCatamaran);

        // Панель отрисовки
        draw = new DrawPicture();
        draw.setBounds(210, 18, 150, 125);
        draw.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        MouseListener listenerPanelDrawTransport = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (boat != null || (draw.GetCatamaran() instanceof Catamaran && floats != null)) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (boat != null || floats != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
            }
        };
        draw.addMouseListener(listenerPanelDrawTransport);

        // Панель выбора цвета
        panelColors = new JPanel();
        panelColors.setBounds(380, 10, 230, 135);
        panelColors.setLayout(null);
        typeBorder = BorderFactory.createTitledBorder("Цвета");
        panelColors.setBorder(typeBorder);
        labelMainColor = new JLabel("Основной цвет");
        labelMainColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelMainColor.setBounds(10, 20, 100, 30);
        labelMainColor.setHorizontalAlignment(SwingConstants.CENTER);
        labelDopColor = new JLabel("Доп. цвет");
        labelDopColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelDopColor.setBounds(120, 20, 100, 30);
        labelDopColor.setHorizontalAlignment(SwingConstants.CENTER);

        MouseAdapter listenerColor = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JPanel panelColor = (JPanel) e.getSource();
                color = panelColor.getBackground();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (draw.GetCatamaran() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() >= labelMainColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelMainColor.getX() + labelMainColor.getWidth() &&
                              e.getY() + ((JComponent) e.getSource()).getY() >= labelMainColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelMainColor.getY() + labelMainColor.getHeight()) {
                        draw.GetCatamaran().SetMainColor(color);
                        repaint();
                    } else if (e.getX() + ((JComponent) e.getSource()).getX() >= labelDopColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelDopColor.getX() + labelDopColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelDopColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelDopColor.getY() + labelDopColor.getHeight() &&
                            draw.GetCatamaran() instanceof Catamaran) {
                        Catamaran catamaran = (Catamaran) draw.GetCatamaran();
                        catamaran.setDopColor(color);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                color = null;
            }
        };

        MouseListener listenerColorLabel = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel labelColor = (JLabel) e.getSource();
                switch (labelColor.getText()) {
                    case "Основной цвет":
                        if (draw.GetCatamaran() != null) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                        break;
                    case "Доп. цвет":
                        if (draw.GetCatamaran() instanceof Catamaran) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                        break;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (color != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
            }
        };

        labelMainColor.addMouseListener(listenerColorLabel);
        labelDopColor.addMouseListener(listenerColorLabel);

        panelBlue = new JPanel();
        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBlue.setBounds(20, 60, 30, 30);
        panelBlue.addMouseListener(listenerColor);

        panelYellow = new JPanel();
        panelYellow.setBackground(Color.YELLOW);
        panelYellow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelYellow.setBounds(70, 60, 30, 30);
        panelYellow.addMouseListener(listenerColor);

        panelBlack = new JPanel();
        panelBlack.setBackground(Color.BLACK);
        panelBlack.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBlack.setBounds(20, 95, 30, 30);
        panelBlack.addMouseListener(listenerColor);

        panelPink = new JPanel();
        panelPink.setBackground(Color.PINK);
        panelPink.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelPink.setBounds(70, 95, 30, 30);
        panelPink.addMouseListener(listenerColor);

        panelWhite = new JPanel();
        panelWhite.setBackground(Color.WHITE);
        panelWhite.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelWhite.setBounds(130, 60, 30, 30);
        panelWhite.addMouseListener(listenerColor);

        panelGreen = new JPanel();
        panelGreen.setBackground(Color.GREEN);
        panelGreen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelGreen.setBounds(180, 60, 30, 30);
        panelGreen.addMouseListener(listenerColor);

        panelRed = new JPanel();
        panelRed.setBackground(Color.RED);
        panelRed.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelRed.setBounds(130, 95, 30, 30);
        panelRed.addMouseListener(listenerColor);

        panelFuchsia = new JPanel();
        panelFuchsia.setBackground(new Color(149, 58, 136));
        panelFuchsia.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelFuchsia.setBounds(180, 95, 30, 30);
        panelFuchsia.addMouseListener(listenerColor);

        panelColors.add(labelMainColor);
        panelColors.add(labelDopColor);
        panelColors.add(panelRed);
        panelColors.add(panelBlue);
        panelColors.add(panelYellow);
        panelColors.add(panelFuchsia);
        panelColors.add(panelWhite);
        panelColors.add(panelBlack);
        panelColors.add(panelGreen);
        panelColors.add(panelPink);

        buttonAdd = new JButton("Добавить");
        buttonAdd.setBounds(395, 270, 100, 30);
        buttonAdd.addActionListener(e -> dispose());
        buttonCancel = new JButton("Отмена");
        buttonCancel.setBounds(508, 270, 100, 30);
        buttonCancel.addActionListener(e -> {
            draw.SetTransport(null);
            dispose();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                draw.SetTransport(null);
            }
        });
    }

    public ITransport GetTransport() {
        return draw.GetCatamaran();
    }
}

