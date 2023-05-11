package frontControllers;

import interfaces.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import services.ScoreService;
import services.ValidationEngine;
import views.Home;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class HomeController implements Observer {

    private Logger log = LogManager.getLogger("HomeController");
    private final ValidationEngine validationEngine;
    private final ScoreService scoreService;
    private final Home home;

    private Timer timer;

    public HomeController(Home home, ValidationEngine validationEngine, ScoreService scoreService){
        log.info("se crea el componente {}", HomeController.class.getName());
        this.home = home;
        this.validationEngine = validationEngine;
        this.scoreService = scoreService;
        this.timer = new Timer();
    }

    public void startValidationTask(){
        timer = new Timer();
        JTextField user = home.getUserNameTextField();
        JTextField machine = home.getMachineSerialCodeTextField();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                post(user.getText(), machine.getText());
            }
        }, 0, 3000);
    }

    public void post(String userName, String machineCode){
        this.validationEngine.validate(userName,machineCode);
    }

    public void setUpActions() {
        JTextField user = home.getUserNameTextField();
        JTextField machine = home.getMachineSerialCodeTextField();
        JButton validatorBtn = home.getValidatorBtn();
        JLabel resultLabel = home.getResultLabel();
        user.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                log.info("metodo insertUpdate: {} {}",user.getText(),machine.getText() );
                resultLabel.setText("");
                timer.cancel();
                validatorBtn.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                log.info("metodo removeUpdate: {} {}",user.getText(),machine.getText() );
                resultLabel.setText("");
                timer.cancel();
                validatorBtn.setEnabled(true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                log.info("metodo changedUpdate: {} {}",user.getText(),machine.getText() );
                resultLabel.setText("");
                timer.cancel();
                validatorBtn.setEnabled(true);
            }
        });

        machine.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                resultLabel.setText("");
                timer.cancel();
                validatorBtn.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                resultLabel.setText("");
                timer.cancel();
                validatorBtn.setEnabled(true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                resultLabel.setText("");
                timer.cancel();
                validatorBtn.setEnabled(true);
            }
        });

        validatorBtn.addActionListener(e -> {
            startValidationTask();
            validatorBtn.setEnabled(false);
        });
    }

    @Override
    public void update() {
        JLabel resultLabel = home.getResultLabel();
        Boolean result = home.getCore().getValidatorManager().getValidationResult();
        log.info("metodo update - result: {} ", result);
        if(result){
            resultLabel.setText("Puede utilizar la máquina");
            resultLabel.setForeground(Color.GREEN);
            scoreService.addScore(home.getUserNameTextField().getText(), 50);
        }else{
            resultLabel.setText("No puede utilizar la máquina");
            resultLabel.setForeground(Color.RED);
        }
    }

}