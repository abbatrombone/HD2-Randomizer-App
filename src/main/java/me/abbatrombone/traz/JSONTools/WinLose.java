package me.abbatrombone.traz.JSONTools;

public class WinLose {

    public int win;
    public int lose;

    public WinLose() {}

    public WinLose(int win, int lose) {
        this.win = win;
        this.lose = lose;
    }
    public int getLose() {
        return lose;
    }

    public int getWin() {
        return win;
    }
    @Override
    public String toString() {
        return " Wins:" + win + ", Loses:" + lose;
    }
}