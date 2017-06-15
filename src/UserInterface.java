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

        widthInput = new JTextField("640");
        heightInput = new JTextField("480");

        XLabel = new JLabel("X");
        YLabel = new JLabel("Y");

        XMinInput = new JTextField("-2");
        XMaxInput = new JTextField("2");
        YMinInput = new JTextField("-2");
        YMaxInput = new JTextField("2");

        imgNameLabel = new JLabel("Name of the output image");
        imgNameInput = new JTextField("zad19.png");

        generateButton = new JButton("Generate");

        log = new JTextArea(6, 20);
        log.setEditable(false);

        contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        //Color panelColor = new Color(52,73,94);
        //contentPanel.setBackground(panelColor);

        contentPanel.add(numThreadsLabel);
        contentPanel.add(numThreadsInput);

        contentPanel.add(sizeLabel);
        contentPanel.add(widthLabel);
        contentPanel.add(widthInput);
        contentPanel.add(heightLabel);
        contentPanel.add(heightInput);

        contentPanel.add(XLabel);
        contentPanel.add(XMinInput);
        contentPanel.add(XMaxInput);
        contentPanel.add(YLabel);
        contentPanel.add(YMinInput);
        contentPanel.add(YMaxInput);

        contentPanel.add(imgNameLabel);
        contentPanel.add(imgNameInput);

        contentPanel.add(generateButton);

        contentPanel.add(log);

        generateButton.addActionListener(new ListenToGenerateButton());

        this.setContentPane(contentPanel);
    }

    public void run(){
        this.setSize(470,380);
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
