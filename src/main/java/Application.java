import core.CoreFitech;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.Home;

import java.io.FileNotFoundException;

public class Application {
    private static final Logger log = LogManager.getLogger("Application");

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 2) {
            log.info("Debe proporcionar el path al directorio y el código de la máquina");
            System.exit(0);
        }
        String path = args[0];
        String machineCode = args[1];
        CoreFitech coreFitech = new CoreFitech(path, machineCode);
        Home home = new Home(coreFitech);
    }
}
