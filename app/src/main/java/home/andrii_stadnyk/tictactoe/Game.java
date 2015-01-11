package home.andrii_stadnyk.tictactoe;

import java.util.Random;

public class Game {

    private Square[][] field;
    private Player[] players;
    private Player activePlayer;
    private WinnerChecker[] winnerCheckers;
    boolean pvp;
    private int filled;
    private int squareCount;
    private int iBot;
    private int jBot;

    private final String PLAYER_X ="X";
    private final String PLAYER_O ="O";

    public Game(int amountPlayers, int field_size, boolean pvp){
        this.pvp = pvp;
        field = new Square[field_size][field_size];
        squareCount = 0;
        for(int i = 0 ; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++){
                field[i][j] = new Square();
                squareCount++;
            }
        }
        players = new Player[amountPlayers];
        activePlayer = null;
        resetCounter();
        winnerCheckers = new WinnerChecker[4];
        winnerCheckers[0] = new WinnerCheckerHorizontal(this);
        winnerCheckers[1] = new WinnerCheckerVertical(this);
        winnerCheckers[2] = new WinnerCheckerDiagonalLeft(this);
        winnerCheckers[3] = new WinnerCheckerDiagonalRight(this);
    }

    public void start(){
        resetPlayers();
    }

    private void resetPlayers(){
        players[0] = new Player(PLAYER_X);
        players[1] = new Player(PLAYER_O);

        setCurrentActivePlayer(players[0]);
    }

    private void setCurrentActivePlayer(Player player){
        activePlayer = player;
    }

    public Square[][] getField(){
        return field;
    }

    public boolean makeTurn(int x, int y){
        if (field[x][y].isFilled()){
            return false;
        }
        field[x][y].fill(getCurrentActivePlayer());
        filled++;
        switchPlayers();
        return true;
    }

    public void switchPlayers(){
        activePlayer = (activePlayer == players[0]) ? players[1] : players[0];
    }

    public boolean isFieldFilled(){
        return squareCount == filled;
    }

    public void reset(){
        resetField();
        resetPlayers();
        resetCounter();
    }

    private void resetField(){
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field[i].length; j++){
                field[i][j].fill(null);
            }
        }
    }

    private void resetCounter(){
        filled = 0;
    }

    public Player checkWinner(int x, int y, String mark){
        for(WinnerChecker winnerChecker: winnerCheckers){
            Player winner = winnerChecker.checkWinner(x, y, mark);
            if(winner != null){
                 return winner;
            }
        }
        return null;
    }


    public void botTurn(){
        Random random = new Random(47);
        setMark(random);
        filled++;
    }

    private void setMark(Random random){
        for (int i = 0; i < field.length; i++){
            for(int j = 0; j < field[i].length; j++){
                if(!field[i][j].isFilled() && random.nextInt(10) == 0){
                    field[i][j].fill(getCurrentActivePlayer());
                    iBot = i;
                    jBot = j;
                    return;
                }
            }
        }
        setMark(random);
    }

    public int getiBot() {
        return iBot;
    }

    public int getjBot() {
        return jBot;
    }

    public Player getCurrentActivePlayer() {
        return activePlayer;
    }
}
