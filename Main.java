import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;

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
                .setActivePlayerId(new Random().nextInt(numberOfPlayers));
    }


    public static void main(String[] args) {

        try {
            if(args.length < 2 || args.length > 2)
                throw new IllegalArgumentException();

            int N = Integer.parseInt(args[0]);
            int M = Integer.parseInt(args[1]);

            if(N < 0 || M < 0)
                throw new IllegalArgumentException();

            configureApplicationContext(N, M);

            ApplicationController
                    .getInstance()
                    .startApplication();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
