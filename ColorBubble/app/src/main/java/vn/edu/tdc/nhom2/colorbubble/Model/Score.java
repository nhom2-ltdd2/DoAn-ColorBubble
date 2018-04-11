package vn.edu.tdc.nhom2.colorbubble.Model;

public class Score {
    private String name;
    private int score;
    private int time;

    public Score() {
    }

    public Score(String name, int score, int time) {
        this.name = name;
        this.score = score;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
