import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PlayerController {

    private static PlayerController playerController;

    private ApplicationContext context;

    private Logger logger;

    private PlayerController() {
        context = ApplicationContext.getInstance();
        logger = Logger.getInstance();
    }

    private Player getActivePlayer() {
        return context
                .getActivePlayers()
                .stream().filter(p -> p.getId() == context.getActivePlayerId())
                .findFirst().orElse(null);
    }

    private int generateRandomNumber() {
        return new Random().nextInt(6) + 1;
    }

    private boolean hasRolled6(int roll) {
        return roll == 6;
    }

    private boolean hasCompleted(int score) {
        return score >= context.getWinningPoints();
    }

    private boolean shouldBreak(int roll, int score) {
        return !hasRolled6(roll) || hasCompleted(score);
    }

    private boolean shouldSkipNext(final Player player, final int roll) {
        return player.getPrevRoll() == 1 && roll == 1;
    }

    private List<Player> getActivePlayers() {
        return context.getActivePlayers();
    }

    private void addPlayerToCompleted(final Player player) {
        List<Player> completedPlayers = context.getCompletedPlayers();
        player.setFinished(true);
        completedPlayers.add(player);
    }

    private void removePlayerFromActiveList(final Player activePlayer) {
        List<Player> activePlayers = getActivePlayers();
        context.setActivePlayers(
                activePlayers.stream().filter(p -> p.getId() != activePlayer.getId()).collect(Collectors.toList()));
    }

    private void moveToNextPlayer() {
        int n = context.getActivePlayers().size();
        int index = -1;
        for(int i = 0; i < n; ++i) {
            final Player p = context.getActivePlayers().get(i);
            if(p.getId() == context.getActivePlayerId()) {
                index = i;
                break;
            }
        }
        int nextIndex = (index + 1) % n;
        final Player nextActivePlayer = context.getActivePlayers().get(nextIndex);
        context.setActivePlayerId(nextActivePlayer.getId());
    }


    public void rollDice() {
        Player activePlayer = getActivePlayer();


        if(activePlayer.isShouldSkip()) {
            activePlayer.setShouldSkip(false);
            moveToNextPlayer();
            return;
        }

        int roll = generateRandomNumber();

        activePlayer
                .setScore(roll + activePlayer.getScore());

        if(shouldSkipNext(activePlayer, roll))
            activePlayer.setShouldSkip(true);

        activePlayer.setPrevRoll(roll);

        logger.printRolledValue(roll);

        if(hasCompleted(activePlayer.getScore()))
            activePlayer.setFinished(true);

        logger.printTally();

        if(hasCompleted(activePlayer.getScore())) {
            moveToNextPlayer();
            removePlayerFromActiveList(activePlayer);
            addPlayerToCompleted(activePlayer);
            return;
        }

        if(!hasRolled6(roll)) {
            moveToNextPlayer();
            return;
        }

    }


    public static PlayerController getInstance() {
        if(null == playerController)
            playerController = new PlayerController();
        return playerController;
    }
}
