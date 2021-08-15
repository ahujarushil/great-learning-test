import java.util.*;
import java.util.stream.Stream;

public class Logger {

    private static Logger logger;

    private ApplicationContext context;

    private Logger() {
        context = ApplicationContext.getInstance();
    }

    private Player getActivePlayer() {
        int id = context.getActivePlayerId();
        return context.getActivePlayers()
                .stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private String[][] prepareTableData() {
        final List<Player> completedPlayers = new ArrayList<>(context.getCompletedPlayers());
        final List<Player> activePlayers = new ArrayList<>(context.getActivePlayers());
        final List<Player> allPlayers = new ArrayList<>();

        Collections.sort(activePlayers, (a, b) -> {
            Integer a1 = a.getScore();
            Integer a2 = b.getScore();

            return a2.compareTo(a1);
        });

        allPlayers.addAll(completedPlayers);
        allPlayers.addAll(activePlayers);

        int n = allPlayers.size();

        final String [][] tableData = new String [n + 1][];

        String [] str = new String[4];
        str[0] = "Rank";
        str[1] = "Name";
        str[2] = "Score";
        str[3] = "Status";
        tableData[0] = str;


        for(int i = 0; i < n; ++i) {
            final Player p = allPlayers.get(i);
            str = new String [4];
            str[0] = String.valueOf(i + 1);
            str[1] = "Player - " + p.getName();
            str[2] = String.valueOf(p.getScore());
            str[3] = p.isFinished() ? "Completed" : "In Progress";
            tableData[i + 1] = str;

        }

        return tableData;
    }


    public void printTally() {

        /*
         * leftJustifiedRows - If true, it will add "-" as a flag to format string to
         * make it left justified. Otherwise right justified.
         */
        boolean leftJustifiedRows = false;

        /*
         * Table to print in console in 2-dimensional array. Each sub-array is a row.
         */
        String[][] table = prepareTableData();
        /*
         * Calculate appropriate Length of each column by looking at width of data in
         * each column.
         *
         * Map columnLengths is <column_number, column_length>
         */
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));

        /*
         * Prepare format String
         */
        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");

        /*
         * Print table
         */
        Stream.iterate(0, (i -> i < table.length), (i -> ++i))
                .forEach(a -> System.out.printf(formatString.toString(), table[a]));
    }

    public void printPlayerTurn() {
        final Player activePlayer = getActivePlayer();
        System.out.println(
                "******************************************************************\n" +
                        "Player-" + activePlayer.getName() + " it's your turn (press 'r' to roll the dice)\n" +
                        "******************************************************************\n"
        );
    }

    public void printRolledValue(int roll) {
        final Player activePlayer = getActivePlayer();
        System.out.println("Player - " + activePlayer.getName() + " rolled " + roll);
    }

    public static Logger getInstance() {
        if(null == logger)
            logger = new Logger();
        return logger;
    }

}
