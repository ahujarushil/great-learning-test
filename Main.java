import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static void configureApplicationContext(int numberOfPlayers, int winningPoints) {
        List<Player> players = IntStream
                .range(1, numberOfPlayers + 1)
                .boxed()
                .map(i -> new Player()
                        .setName(String.valueOf(i))
                        .setId(i)
                        .setScore(0))
                .collect(Collectors.toList());

        ApplicationContext
                .getInstance()
                .setNumberOfPlayers(numberOfPlayers)
                .setWinningPoints(winningPoints)
                .setPlayerList(players)
                .setActivePlayers(players)
                .setActivePlayerId(1);
    }


    public static void main(String[] args) {
        int N = 1;
        int M = 10;

        configureApplicationContext(N, M);

        ApplicationController
                .getInstance()
                .startApplication();
    }
}
