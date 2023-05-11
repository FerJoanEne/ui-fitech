package views;

//java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


//custom imports
import core.Core;
import frontControllers.HomeController;
import services.ScoreService;
import services.ValidationEngine;


public class Home extends JFrame {
    private static final int MIN_WIDTH = 600;
    private static final int MIN_HEIGHT = 800;
    private static final Color PRIMARY_COLOR = new Color(230, 230, 230);
    private static final Color SECONDARY_COLOR = new Color(50, 50, 50);
    private static final Color ACCENT_COLOR = new Color(200, 50, 50);
    private static final Font TITLE_FONT = new Font("Poppins", Font.BOLD, 48);
    private static final Font FORM_FONT = new Font("Poppins", Font.PLAIN, 24);

    private static final Font SCORE_FONT = new Font("Poppins", Font.PLAIN, 18);
    private static final String TITLE = "Fitech";

    // Componentes de la interfaz gráfica
    private JPanel contentPanel;
    private JTextField userNameTextField;
    private JTextField machineSerialCodeTextField;
    private JTextArea resultLabel;
    private JButton validatorBtn;

    private DefaultTableModel scoreModel;

    // Controlador
    private HomeController homeController;
    private Core core;

    public Home(Core core, ScoreService scoreService) {
        super(TITLE);
        this.core = core;
        ValidationEngine validationEngine = core.getValidatorManager();
        createUIComponents();
        homeController = new HomeController(this, validationEngine, scoreService);
        validationEngine.addObserver(homeController);

        homeController.setUpActions();
        homeController.updateScoreTable();
        setVisible(true);
    }

    private void createUIComponents() {
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        userNameTextField.setColumns(10);
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
        machineSerialCodeTextField.setColumns(10);
        machineSerialCodeTextField.setMargin(new Insets(2, 5, 2, 5));

        JPanel machineWrapper = new JPanel();
        machineWrapper.setBackground(null);
        machineWrapper.setLayout(new FlowLayout());
        machineWrapper.add(machineLabel);
        machineWrapper.add(machineSerialCodeTextField);
        formPanel.add(machineWrapper);

        JScrollPane scorePanel = new JScrollPane();

        scoreModel = new DefaultTableModel(null, new String[] {"User", "Score"});
        JTable scoreTable = new JTable(scoreModel);
        scoreTable.setFont(SCORE_FONT);
        scoreTable.setRowHeight(SCORE_FONT.getSize() + 4);
        scoreTable.getTableHeader().setFont(SCORE_FONT);

        scorePanel.setViewportView(scoreTable);


        resultLabel = new JTextArea();
        resultLabel.setBackground(SECONDARY_COLOR);
        resultLabel.setForeground(ACCENT_COLOR);
        resultLabel.setFont(SCORE_FONT);
        resultLabel.setBorder(null);
        resultLabel.setColumns(15);
        resultLabel.setEditable(false);

        JScrollPane scrollResultPanel = new JScrollPane(resultLabel);
        scrollResultPanel.setBorder(null);
        JPanel resultPanel = new JPanel();
        GridLayout resultLayout = new GridLayout(1, 1);
        resultPanel.setBackground(null);
        resultPanel.setLayout(resultLayout);
        resultPanel.add(scrollResultPanel);

        validatorBtn = new JButton("Ingresar");
        validatorBtn.setForeground(ACCENT_COLOR);
        validatorBtn.setBackground(PRIMARY_COLOR);
        validatorBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                BorderFactory.createEmptyBorder(5, 20, 5, 20)
        ));
        validatorBtn.setFont(FORM_FONT);

        JPanel buttonWrapper = new JPanel();
        buttonWrapper.setBackground(null);
        buttonWrapper.setLayout(new FlowLayout());
        buttonWrapper.add(validatorBtn);
        formPanel.add(buttonWrapper);

        contentPanel.add(titlePanel, BorderLayout.NORTH);
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(resultPanel, BorderLayout.EAST);
        contentPanel.add(scorePanel,BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }

    public JTextArea getResultLabel(){
        return this.resultLabel;
    }

    public JButton getValidatorBtn(){
        return this.validatorBtn;
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

    public DefaultTableModel getScoreTableModel(){
        return this.scoreModel;
    }

}