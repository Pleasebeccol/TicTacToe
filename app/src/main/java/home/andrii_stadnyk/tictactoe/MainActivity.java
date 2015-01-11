package home.andrii_stadnyk.tictactoe;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private TableLayout tableLayout;
    private Game game;
    private Square[][] field;
    private boolean pvp;

    private final int AMOUNT_PLAYERS = 2;
    private final int FIELD_SIZE = 3;
    private final int BUTTON_SIZE = 160;
    private final String EMPTY = "";

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = (TableLayout)findViewById(R.id.main_l);
        pvp = getIntent().getBooleanExtra("pvp", false);
        startGame();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Button[][] buttons = new Button[FIELD_SIZE][FIELD_SIZE];

    private void buildGameField(){
        field = game.getField();
        for(int i = 0; i < field.length; i++){
            TableRow row = new TableRow(this);
            for(int j = 0; j < field[i].length; j++){
                Button button = new Button(this);
                buttons[i][j] = button;
                button.setOnClickListener(new Listener(i,j));
                row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                button.setWidth(BUTTON_SIZE);
                button.setWidth(BUTTON_SIZE);
            }
            tableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    public void onBackToMenu(View view) {
        Intent intent = new Intent(MainActivity.this, home.andrii_stadnyk.tictactoe.Menu.class);
        startActivity(intent);
    }


    public void onClickReset(View view) {
        game.reset();
        refresh();
    }

    public class Listener implements View.OnClickListener{

        private int x = 0;
        private int y = 0;

        public Listener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            Player player = game.getCurrentActivePlayer();
            if(makeTurn(x,y)){
                button.setText(player.getMark());
            } else {
                return;
            }
            checkWinner(x, y, player.getMark().toString());
            if (!pvp){
                botTurn();
                checkWinner(x, y, player.getMark().toString());
            }
        }
    }


    public void startGame(){
        game = new Game(AMOUNT_PLAYERS, FIELD_SIZE, pvp);
        game.start();
        if (field == null) {
            buildGameField();
        } else {
            game.reset();
            refresh();
        }
    }

    private void checkWinner(int x, int y, String mark){
        Player winner = game.checkWinner(x, y, mark);
        if(winner != null){
            gameOver("Player \"" + winner.getMark() + "\" won!");
        }
        if (game.isFieldFilled()){
            gameOver("Draw");
        }
    }

    private void gameOver(CharSequence text)
    {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        game.reset();
        refresh();
    }


    private boolean makeTurn(int x, int y)
    {
        return game.makeTurn(x, y);
    }

    private void botTurn(){
        game.botTurn();
        buttons[game.getiBot()][game.getjBot()].setText(game.getCurrentActivePlayer().getMark());
        game.switchPlayers();
    }

    private void refresh() {
        Square[][] field = game.getField();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                    buttons[i][j].setText(EMPTY);
            }
        }
    }
}
