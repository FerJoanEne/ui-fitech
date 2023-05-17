package views;

//java

import javax.swing.*;
import java.awt.*;

//custom imports
import core.CoreFitech;
import frontControllers.HomeController;


public class Home extends JFrame {
    private static final int MIN_WIDTH = 600;
    private static final int MIN_HEIGHT = 500;
    private static final Color PRIMARY_COLOR = new Color(230, 230, 230);
    private static final Color SECONDARY_COLOR = new Color(50, 50, 50);
    private static final Color ACCENT_COLOR = new Color(200, 50, 50);
    private static final Font TITLE_FONT = new Font("Poppins", Font.BOLD, 48);
    private static final Font FORM_FONT = new Font("Poppins", Font.PLAIN, 24);
    private static final String TITLE = "Fitech";

    // Componentes de la interfaz gr?fica
    private JPanel contentPanel;
    private JTextField userNameTextField;
    private JLabel resultLabel;

    private JButton validatorBtn;

    public Home(CoreFitech coreFitech) {
        super(TITLE);
        createUIComponents();
        new HomeController(this, coreFitech);
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
        GridLayout formLayout = new GridLayout(4, 1);
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

        resultLabel = new JLabel("");
        resultLabel.setForeground(ACCENT_COLOR);
        resultLabel.setFont(FORM_FONT);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.CENTER);
        formPanel.add(resultLabel);

        JPanel resultPanel = new JPanel();
        GridLayout resultLayout = new GridLayout(3, 1);
        resultPanel.setBackground(null);
        resultPanel.setLayout(resultLayout);

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

        setContentPane(contentPanel);
    }

    public JLabel getResultLabel() {
        return this.resultLabel;
    }

    public JTextField getUserNameTextField() {
        return this.userNameTextField;
    }

    public JButton getValidatorBtn() {
        return this.validatorBtn;
    }
}