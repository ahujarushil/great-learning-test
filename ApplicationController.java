import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public final class ApplicationController {

    private static ApplicationController applicationController;

    private ApplicationContext context;

    private PlayerController playerController;

    private Logger logger;

    private ApplicationController() {
        context = ApplicationContext.getInstance();
        playerController = PlayerController.getInstance();
        logger = Logger.getInstance();
    }

    private List<Player> getActivePlayers() {
        return context.getActivePlayers();
    }

    private boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public void startApplication() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        try {
            while(!isEmpty(getActivePlayers())) {
                logger.printPlayerTurn();
                line = br.readLine();
                playerController.rollDice();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static ApplicationController getInstance() {
        if(applicationController == null) {
            applicationController = new ApplicationController();
        }
        return applicationController;
    }
}
