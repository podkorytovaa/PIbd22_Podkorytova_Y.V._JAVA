import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.util.*;

public class FormPort {
    private JFrame framePort;
    private PortCollection portCollection;
    private Stack<Boat> stackTransport;
    private DrawPort drawPort;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem saveFile;
    private JMenuItem loadFile;
    private JMenu portFileMenu;
    private JPanel groupBoxPorts;
    private JTextField textFieldPort;
    private JButton buttonAddPort;
    private JList<String> listBoxPorts;
    private DefaultListModel<String> listPorts;
    private JButton buttonDelPort;
    private Border borderPorts;
    private JButton buttonPortTransport;
    private JPanel groupBoxTake;
    private JLabel labelPlace;
    private JTextField textFieldPlace;
    private JButton buttonPutOnStack;
    private JButton buttonTakeBoat;
    private Border borderTake;

    public FormPort() {
        framePort = new JFrame("Гавань");
        framePort.setSize(920, 535);
        framePort.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        framePort.setLayout(null);
        framePort.setResizable(false);
        framePort.setVisible(true);
        InitializeComponent();
        framePort.getContentPane().add(groupBoxPorts);
        framePort.getContentPane().add(buttonPortTransport);
        framePort.getContentPane().add(groupBoxTake);
        framePort.getContentPane().add(drawPort);
        framePort.setJMenuBar(menuBar);
        framePort.repaint();
    }

    public void InitializeComponent() {
        stackTransport = new Stack<>();
        portCollection = new PortCollection(695, 490);
        drawPort = new DrawPort(portCollection);

        borderPorts = BorderFactory.createTitledBorder("Гавани");
        textFieldPort = new JTextField();
        textFieldPort.setBounds(10, 20, 170, 20);

        buttonAddPort = new JButton("Добавить гавань");
        buttonAddPort.setBounds(10, 50, 170, 30);
        buttonAddPort.addActionListener(e -> {
            if (!textFieldPort.getText().equals("")) {
                portCollection.AddPort(textFieldPort.getText());
                ReloadLevels();
                framePort.repaint();
            }
            else {
                JOptionPane.showMessageDialog(framePort, "Введите название гавани", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        listPorts = new DefaultListModel<>();
        listBoxPorts = new JList<>(listPorts);
        listBoxPorts.setBounds(10, 90, 170, 100);
        listBoxPorts.addListSelectionListener(e -> {
            drawPort.SetPort(listBoxPorts.getSelectedValue());
            framePort.repaint();
        });

        buttonDelPort = new JButton("Удалить гавань");
        buttonDelPort.setBounds(10, 200, 170, 30);
        buttonDelPort.addActionListener(e -> {
            if (listBoxPorts.getSelectedIndex() > -1) {
                int result = JOptionPane.showConfirmDialog(framePort, "Удалить гавань " + listBoxPorts.getSelectedValue() + "?", "Удаление", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    portCollection.DelPort(listBoxPorts.getSelectedValue());
                    ReloadLevels();
                    framePort.repaint();
                }
            }
            else {
                JOptionPane.showMessageDialog(framePort, "Гавань не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        groupBoxPorts = new JPanel();
        groupBoxPorts.setLayout(null);
        groupBoxPorts.setBounds(710, 10, 190, 240);
        groupBoxPorts.setBorder(borderPorts);
        groupBoxPorts.add(textFieldPort);
        groupBoxPorts.add(listBoxPorts);
        groupBoxPorts.add(buttonAddPort);
        groupBoxPorts.add(buttonDelPort);

        buttonPortTransport = new JButton("Пришвартовать транспорт");
        buttonPortTransport.setBounds(710, 275, 190, 30);
        buttonPortTransport.addActionListener(e -> {
            if (listBoxPorts.getSelectedIndex() >= 0) {
                FormCatamaranConfig formConfig = new FormCatamaranConfig(framePort);
                Vehicle transport = (Vehicle) formConfig.GetTransport();
                if (transport  != null) {
                    int index = portCollection.Get(listBoxPorts.getSelectedValue()).Plus((Boat)transport);
                    if (index > -1) {
                        framePort.repaint();
                    } else {
                        JOptionPane.showMessageDialog(framePort, "Гавань переполнена");
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(framePort, "Гавань не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        borderTake = BorderFactory.createTitledBorder("Забрать транспорт");
        labelPlace = new JLabel("Место: ");
        labelPlace.setBounds(20, 25, 50, 30);
        textFieldPlace = new JTextField();
        textFieldPlace.setBounds(70, 30, 90, 20);

        buttonPutOnStack = new JButton("Поместить в стек");
        buttonPutOnStack.setBounds(10, 65, 160, 25);
        buttonPutOnStack.addActionListener(e -> {
            if (listBoxPorts.getSelectedIndex() >= 0) {
                if (!textFieldPlace.getText().equals("")) {
                    int index = Integer.parseInt(textFieldPlace.getText());
                    Boat boat = (Boat) portCollection.Get(listBoxPorts.getSelectedValue()).Minus(index);
                    if (boat != null) {
                        stackTransport.add(boat);
                        framePort.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(framePort, "Транспорта не существует");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(framePort, "Выберите место");
                }
            }
            else {
                JOptionPane.showMessageDialog(framePort, "Гавань не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonTakeBoat = new JButton("Забрать");
        buttonTakeBoat.setBounds(10, 100, 160, 25);
        buttonTakeBoat.addActionListener(e -> {
            if (!stackTransport.isEmpty()) {
                FormCatamaran formCatamaran = new FormCatamaran();
                formCatamaran.SetBoat(stackTransport.pop());
                framePort.repaint();
            }
            else {
                JOptionPane.showMessageDialog(framePort, "Стек пустой", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        groupBoxTake = new JPanel();
        groupBoxTake.setLayout(null);
        groupBoxTake.add(labelPlace);
        groupBoxTake.add(textFieldPlace);
        groupBoxTake.add(buttonPutOnStack);
        groupBoxTake.add(buttonTakeBoat);
        groupBoxTake.setBounds(715, 330, 180, 135);
        groupBoxTake.setBorder(borderTake);

        drawPort.setBounds(0, 0, 695, 490);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("Файл");
        saveFile = new JMenuItem("Сохранить");
        saveFile.addActionListener(e -> {
            JFileChooser fileSaveDialog = new JFileChooser();
            fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
            if (fileSaveDialog.showSaveDialog(framePort) == JFileChooser.APPROVE_OPTION) {
                if (portCollection.SaveData(fileSaveDialog.getSelectedFile().getPath())) {
                    JOptionPane.showMessageDialog(framePort, "Сохранение прошло успешно", "Результат", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(framePort, "Не сохранилось", "Результат", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loadFile = new JMenuItem("Загрузить");
        loadFile.addActionListener(e -> {
            JFileChooser fileOpenDialog = new JFileChooser();
            fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
            if (fileOpenDialog.showOpenDialog(framePort) == JFileChooser.APPROVE_OPTION) {
                if (portCollection.LoadData(fileOpenDialog.getSelectedFile().getPath())) {
                    JOptionPane.showMessageDialog(framePort, "Загрузили", "Результат", JOptionPane.INFORMATION_MESSAGE);
                    ReloadLevels();
                    framePort.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(framePort, "Не загрузили", "Результат", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fileMenu.add(saveFile);
        fileMenu.add(loadFile);
        menuBar.add(fileMenu);
    }

    private void ReloadLevels() {
        int index = listBoxPorts.getSelectedIndex();
        listPorts.removeAllElements();
        int i = 0;
        for (String name : portCollection.Keys()) {
            listPorts.add(i, name);
            i++;
        }
        if (listPorts.size() > 0 && (index == -1 || index >= listPorts.size())) {
            listBoxPorts.setSelectedIndex(0);
        }
        else if (listPorts.size() > 0 && index > -1 && index < listPorts.size()) {
            listBoxPorts.setSelectedIndex(index);
        }
    }
}