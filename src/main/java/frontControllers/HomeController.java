package frontControllers;

import core.CoreFitech;
import interfaces.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import services.ValidationTask;
import views.Home;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class HomeController implements Observer {

    private final Logger log = LogManager.getLogger("HomeController");
    private final ValidationTask validationTask;
    private final Home home;
    private Boolean isButtonEnabled;

    public HomeController(Home home, CoreFitech coreFitech) {
        this.home = home;
        this.validationTask = coreFitech.subscribe(this);
        setUpActions();
        isButtonEnabled = true;
    }

    public void setUpActions() {
        JTextField user = home.getUserNameTextField();
        JButton validatorBtn = home.getValidatorBtn();
        JLabel resultLabel = home.getResultLabel();
        user.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isButtonEnabled = true;
                resultLabel.setText("");
                validatorBtn.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isButtonEnabled = true;
                resultLabel.setText("");
                validatorBtn.setEnabled(true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isButtonEnabled = true;
                resultLabel.setText("");
                validatorBtn.setEnabled(true);
            }
        });

        validatorBtn.addActionListener(e -> {
            isButtonEnabled = false;
            validationTask.processRequest(user.getText());
            validatorBtn.setEnabled(false);
        });
    }

    @Override
    public void update() {
    }

    @Override
    public void update(Boolean result) {
        JLabel resultLabel = home.getResultLabel();
        if (!isButtonEnabled) {
            System.out.println("\u001B[33mHome Controller Update\u001B[0m");
            log.info("metodo update - result: {} ", result);
            if (result) {
                resultLabel.setText("Puede utilizar la m�quina");
                resultLabel.setForeground(Color.GREEN);
            } else {
                resultLabel.setText("No puede utilizar la m�quina");
                resultLabel.setForeground(Color.RED);
            }
        }
    }
}