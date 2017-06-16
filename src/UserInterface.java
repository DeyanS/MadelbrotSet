import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame{

    public static UserInterface instance;

    private JSpinner numThreadsInput;
    private JLabel numThreadsLabel;

    private JTextField widthInput;
    private JTextField heightInput;
    private JLabel sizeLabel;
    private JLabel widthLabel;
    private JLabel heightLabel;

    private JTextField XMinInput;
    private JTextField XMaxInput;
    private JTextField YMinInput;
    private JTextField YMaxInput;
    private JLabel XLabel;
    private JLabel YLabel;

    private JTextField imgNameInput;
    private JLabel imgNameLabel;

    private JButton generateButton;
    public JTextArea log;

    public JPanel[] contentPanelRows = new JPanel[6];
    public JPanel contentPanel;

    public static UserInterface getInstance(){
        if(instance != null){
            return instance;
        }

        return new UserInterface();
    }

    private UserInterface(){

        numThreadsLabel = new JLabel("Number of threads: ");
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, //initial value
                1, //min
                100, //max
                1);//step

        numThreadsInput = new JSpinner(spinnerModel);

        sizeLabel = new JLabel("size of the image");
        widthLabel = new JLabel("width");
        heightLabel = new JLabel("height");

        widthInput = new JTextField("640",4);
        heightInput = new JTextField("480",4);

        XLabel = new JLabel("X");
        YLabel = new JLabel("Y");

        XMinInput = new JTextField("-2", 4);
        XMaxInput = new JTextField("2",4);
        YMinInput = new JTextField("-2",4);
        YMaxInput = new JTextField("2",4);

        imgNameLabel = new JLabel("Name of the output image");
        imgNameInput = new JTextField("zad19.png", 12);

        generateButton = new JButton("Generate");

        log = new JTextArea(25, 35);
        log.setEditable(false);

        log.getPreferredScrollableViewportSize();

        for(int i = 0; i < contentPanelRows.length; i++){
            contentPanelRows[i] = new JPanel();
            contentPanelRows[i].setLayout(new FlowLayout());
            contentPanelRows[i].setSize(450, 75);
        }

        contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        //Color panelColor = new Color(52,73,94);
        //contentPanel.setBackground(panelColor);

        contentPanelRows[0].add(numThreadsLabel);
        contentPanelRows[0].add(numThreadsInput);

        contentPanelRows[1].add(sizeLabel);
        contentPanelRows[1].add(widthLabel);
        contentPanelRows[1].add(widthInput);
        contentPanelRows[1].add(heightLabel);
        contentPanelRows[1].add(heightInput);

        contentPanelRows[2].add(XLabel);
        contentPanelRows[2].add(XMinInput);
        contentPanelRows[2].add(XMaxInput);
        contentPanelRows[2].add(YLabel);
        contentPanelRows[2].add(YMinInput);
        contentPanelRows[2].add(YMaxInput);

        contentPanelRows[3].add(imgNameLabel);
        contentPanelRows[3].add(imgNameInput);

        contentPanelRows[4].add(generateButton);

        JScrollPane scroll = new JScrollPane (log,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        contentPanelRows[5].add(scroll);

        generateButton.addActionListener(new ListenToGenerateButton());

        for(int i = 0; i < contentPanelRows.length; i++) {
            contentPanel.add(contentPanelRows[i]);
        }

        this.setContentPane(contentPanel);
    }

    public void run(){
        this.setSize(470,600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    class ListenToGenerateButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            log.setText("");
            Parameters params = new Parameters();

            params.width = Integer.parseInt(widthInput.getText());
            params.height = Integer.parseInt(heightInput.getText());

            params.max_x = Double.parseDouble(XMaxInput.getText());
            params.min_x = Double.parseDouble(XMinInput.getText());

            params.max_y = Double.parseDouble(YMaxInput.getText());
            params.min_y = Double.parseDouble(YMinInput.getText());

            params.imageName = imgNameInput.getText();

            params.numThreads = (Integer)numThreadsInput.getValue();

            params.area = log;

            Main.runMain(params);
        }
    }
}
