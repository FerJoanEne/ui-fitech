import core.Core;
import services.ScoreService;
import views.Home;

import java.io.File;

public class Application {

    public static void main(String[] args) {
       Core core = new Core(new File("").getAbsolutePath());
       ScoreService scoreService = new ScoreService(new File("").getAbsolutePath()+File.separator+"files"+File.separator+"Puntajes.json");
       Home home = new Home(core, scoreService);
    }
}
