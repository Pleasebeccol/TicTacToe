package home.andrii_stadnyk.tictactoe;

public class Player {

    private String mark;

    public Player(String mark) {
        this.mark = mark;
    }

    public CharSequence getMark() {
        return mark;
    }
}
