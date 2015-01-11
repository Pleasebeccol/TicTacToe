package home.andrii_stadnyk.tictactoe;

public class WinnerCheckerVertical implements WinnerChecker{

    private Game game;

    public WinnerCheckerVertical (Game game)
    {
        this.game = game;
    }

    public Player checkWinner(int x, int y, String mark){
        Square[][] field = game.getField();
        Player player;
        for (int i = 0; i < field[y].length; i++) {
            player = field[i][y].getPlayer();
            if (player == null || !mark.equals(player.getMark().toString())){
                return null;
            }
        }
        return field[x][y].getPlayer();
    }

}
