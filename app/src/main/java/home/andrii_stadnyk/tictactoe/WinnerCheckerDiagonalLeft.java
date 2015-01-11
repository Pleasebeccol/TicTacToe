package home.andrii_stadnyk.tictactoe;

public class WinnerCheckerDiagonalLeft implements WinnerChecker{

    private Game game;

    public WinnerCheckerDiagonalLeft(Game game)
    {
        this.game = game;
    }

    public Player checkWinner(int x, int y, String mark){
        Square[][] field = game.getField();
        if(x == y) {
            Player player;
            for (int i = 0; i < field.length; i++) {
                player = field[i][i].getPlayer();
                if(player == null || !mark.equals(player.getMark().toString())){
                    return null;
                }
            }
        return field[x][y].getPlayer();
        }
    return null;
    }

}
