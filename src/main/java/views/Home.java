package views;

//java
import javax.swing.*;
import java.awt.*;

//custom imports
import core.Core;
import frontControllers.HomeController;
import services.ScoreService;
import services.ValidationEngine;


public class Home extends JFrame {
    private static final int MIN_WIDTH = 600;
    private static final int MIN_HEIGHT = 500;
    private static final Color PRIMARY_COLOR = new Color(230, 230, 230);
    private static final Color SECONDARY_COLOR = new Color(50, 50, 50);
    private static final Color ACCENT_COLOR = new Color(200, 50, 50);
    private static final Font TITLE_FONT = new Font("Poppins", Font.BOLD, 48);
    private static final Font FORM_FONT = new Font("Poppins", Font.PLAIN, 24);
    private static final String TITLE = "Fitech";

    // Componentes de la interfaz gráfica
    private JPanel contentPanel;
    private JTextField userNameTextField;
    private JTextField machineSerialCodeTextField;
    private JLabel resultLabel;

    // Controlador
    private HomeController homeController;
    private Core core;

    public Home(Core core, ScoreService scoreService) {
        super(TITLE);
        this.core = core;
        ValidationEngine validationEngine = core.getValidatorManager();
        homeController = new HomeController(this, validationEngine, scoreService);
        validationEngine.addObserver(homeController);

        createUIComponents();
        homeController.setUpActions();
        homeController.startValidationTask();
    }

    private void createUIComponents() {
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(SECONDARY_COLOR);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(null);
        JLabel title = new JLabel(TITLE);
        title.setForeground(ACCENT_COLOR);
        title.setFont(TITLE_FONT);
        titlePanel.add(title);

        JPanel formPanel = new JPanel();
        GridLayout formLayout = new GridLayout(3, 1);
        formPanel.setLayout(formLayout);
        formPanel.setBackground(null);

        JLabel nameLabel = new JLabel("Nombre de usuario:");
        nameLabel.setForeground(PRIMARY_COLOR);
        nameLabel.setFont(FORM_FONT);

        userNameTextField = new JTextField();
        userNameTextField.setForeground(SECONDARY_COLOR);
        userNameTextField.setBackground(PRIMARY_COLOR);
        userNameTextField.setFont(FORM_FONT);
        userNameTextField.setColumns(20);
        userNameTextField.setMargin(new Insets(2, 5, 2, 5));

        JPanel nameWrapper = new JPanel();
        nameWrapper.setBackground(null);
        nameWrapper.setLayout(new FlowLayout());
        nameWrapper.add(nameLabel);
        nameWrapper.add(userNameTextField);
        formPanel.add(nameWrapper);

        JLabel machineLabel = new JLabel("Código de máquina:");
        machineLabel.setForeground(PRIMARY_COLOR);
        machineLabel.setFont(FORM_FONT);
        machineLabel.setHorizontalAlignment(SwingConstants.LEFT);

        machineSerialCodeTextField = new JTextField();
        machineSerialCodeTextField.setForeground(SECONDARY_COLOR);
        machineSerialCodeTextField.setBackground(PRIMARY_COLOR);
        machineSerialCodeTextField.setFont(FORM_FONT);
        machineSerialCodeTextField.setColumns(20);
        machineSerialCodeTextField.setMargin(new Insets(2, 5, 2, 5));

        JPanel machineWrapper = new JPanel();
        machineWrapper.setBackground(null);
        machineWrapper.setLayout(new FlowLayout());
        machineWrapper.add(machineLabel);
        machineWrapper.add(machineSerialCodeTextField);
        formPanel.add(machineWrapper);

        resultLabel = new JLabel("");
        resultLabel.setForeground(ACCENT_COLOR);
        resultLabel.setFont(FORM_FONT);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.CENTER);

        JPanel resultPanel = new JPanel();
        GridLayout resultLayout = new GridLayout(3, 1);
        resultPanel.setBackground(null);
        resultPanel.setLayout(resultLayout);
        resultPanel.add(resultLabel);

        contentPanel.add(titlePanel, BorderLayout.NORTH);
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(resultPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    public JLabel getResultLabel(){
        return this.resultLabel;
    }

    public JTextField getUserNameTextField(){
        return this.userNameTextField;
    }

    public JTextField getMachineSerialCodeTextField(){
        return this.machineSerialCodeTextField;
    }

    public Core getCore(){
        return this.core;
    }

}