import java.util.ArrayList;
import java.util.List;

public final class ApplicationContext {

    private static ApplicationContext context;

    private int numberOfPlayers;

    private List<Player> playerList;

    private int activePlayerId;

    private List<Player> activePlayers;

    private List<Player> completedPlayers;

    private int winningPoints;

    private ApplicationContext() {
        completedPlayers = new ArrayList<>();
    }

    public List<Player> getCompletedPlayers() {
        return completedPlayers;
    }

    public ApplicationContext setCompletedPlayers(List<Player> completedPlayers) {
        this.completedPlayers = completedPlayers;
        return this;
    }

    public int getWinningPoints() {
        return winningPoints;
    }

    public ApplicationContext setWinningPoints(int winningPoints) {
        this.winningPoints = winningPoints;
        return this;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public int getActivePlayerId() {
        return activePlayerId;
    }

    public List<Player> getActivePlayers() {
        return activePlayers;
    }


    public ApplicationContext setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        return this;
    }

    public ApplicationContext setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
        return this;
    }


    public ApplicationContext setActivePlayerId(int activePlayerId) {
        this.activePlayerId = activePlayerId;
        return this;
    }

    public ApplicationContext setActivePlayers(List<Player> activePlayers) {
        this.activePlayers = activePlayers;
        return this;
    }

    public static ApplicationContext getInstance() {
        if(null == context) {
            context = new ApplicationContext();
        }
        return context;
    }
}
