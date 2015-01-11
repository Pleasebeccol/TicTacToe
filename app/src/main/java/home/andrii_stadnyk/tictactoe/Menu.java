package home.andrii_stadnyk.tictactoe;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.os.Bundle;

public class Menu extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void onClickNewGameWithPlayer(View view) {
        Intent intent = new Intent(Menu.this, MainActivity.class);
        intent.putExtra("pvp", true);
        startActivity(intent);
    }

    public void onClickNewGameWithBot(View view) {
        Intent intent = new Intent(Menu.this, MainActivity.class);
        intent.putExtra("pvp", false);
        startActivity(intent);
    }

}
