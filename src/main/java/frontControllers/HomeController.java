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

    private static final Logger log = LogManager.getLogger("HomeController");
    private final ValidationTask validationTask;
    private final Home home;
    private boolean isButtonEnabled;

    public HomeController(Home home, CoreFitech coreFitech) {
        this.home = home;
        this.validationTask = coreFitech.getValidationTask();
        this.validationTask.addObserver(this);
        this.setUpActions();
        this.isButtonEnabled = true;
    }

    public void setUpActions() {
        JTextField user = home.getUserNameTextField();
        JButton validatorBtn = home.getValidatorBtn();
        JLabel resultLabel = home.getResultLabel();
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                enableButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                enableButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                enableButton();
            }

            private void enableButton() {
                isButtonEnabled = true;
                resultLabel.setText("");
                validatorBtn.setEnabled(true);
            }
        };

        user.getDocument().addDocumentListener(documentListener);

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
            if (result != null) {
                resultLabel.setText(result ? "Puede utilizar la m�quina" : "No puede utilizar la m�quina");
                resultLabel.setForeground(result ? Color.GREEN : Color.RED);
            }
        }
    }
}