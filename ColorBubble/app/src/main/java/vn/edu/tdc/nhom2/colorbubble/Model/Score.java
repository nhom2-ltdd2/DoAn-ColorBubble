package vn.edu.tdc.nhom2.colorbubble.Model;

public class Score {
    public String name,hinh;
    public int score;
    public int time;

    public Score() {
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public Score(String name, int score, int time, String hinh) {
        this.name = name;
        this.score = score;
        this.time = time;
        this.hinh = hinh;

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
