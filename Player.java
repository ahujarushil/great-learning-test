public class Player {

    private int id;

    private String name;

    private int score;

    private boolean shouldSkip;

    private int prevRoll;

    private boolean isFinished;

    public boolean isFinished() {
        return isFinished;
    }

    public Player setFinished(boolean finished) {
        isFinished = finished;
        return this;
    }

    public int getId() {
        return id;
    }

    public Player setId(int id) {
        this.id = id;
        return this;
    }

    public int getPrevRoll() {
        return prevRoll;
    }

    public void setPrevRoll(int prevRoll) {
        this.prevRoll = prevRoll;
    }

    public boolean isShouldSkip() {
        return shouldSkip;
    }

    public void setShouldSkip(boolean shouldSkip) {
        this.shouldSkip = shouldSkip;
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Player setScore(int score) {
        this.score = score;
        return this;
    }
}
