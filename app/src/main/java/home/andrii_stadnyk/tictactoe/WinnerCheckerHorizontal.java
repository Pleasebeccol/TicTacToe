package home.andrii_stadnyk.tictactoe;

public class WinnerCheckerHorizontal implements WinnerChecker{

    private Game game;

    public WinnerCheckerHorizontal(Game game) {
        this.game = game;
    }

    public Player checkWinner(int x, int y, String mark){
        Square[][] field = game.getField();
        Player player;
        for (int i = 0; i < field.length; i++) {
            player = field[x][i].getPlayer();
            if (player == null || !mark.equals(player.getMark().toString())){
                return null;
            }
        }
        return field[x][y].getPlayer();
    }
}
