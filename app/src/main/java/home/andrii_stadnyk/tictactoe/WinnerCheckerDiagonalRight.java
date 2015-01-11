package home.andrii_stadnyk.tictactoe;

public class WinnerCheckerDiagonalRight implements WinnerChecker{

    private Game game;

    public WinnerCheckerDiagonalRight(Game game)
    {
        this.game = game;
    }

    public Player checkWinner(int x, int y, String mark){
        Square[][] field = game.getField();
        int length = field.length;
        if(x == (length - 1) - y) {
            Player player;
            for (int i = 0; i < length; i++) {
                player = field[i][(length - 1) - i].getPlayer();
                if(player == null || !mark.equals(player.getMark().toString())){
                    return null;
                }
            }
            return field[x][y].getPlayer();
        }
        return null;
    }
}
