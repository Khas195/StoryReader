package com.example.inspiron.kidbook.Application;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.inspiron.kidbook.Manager.BookManager;
import com.example.inspiron.kidbook.R;


public class MainActivity extends Activity {
    GameView gameView;
    public static RelativeLayout BUTTONS_LAYOUT;
    FrameLayout game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // set full screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Init Main Menu Layout

        // Init Main Menu Layout
        BUTTONS_LAYOUT = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        BUTTONS_LAYOUT.setLayoutParams(params);
        BUTTONS_LAYOUT.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        game = new FrameLayout(this);
        gameView = new GameView(this);
        game.addView(gameView);
        game.addView(BUTTONS_LAYOUT);
        setContentView(game);

    }

    @Override
    public void onBackPressed() {
        GameDirector.getInstance().pop();
        if ( GameDirector.getInstance().isEmpty() ) {
            this.game = null;
            this.gameView = null;
            this.BUTTONS_LAYOUT = null;
            finish();
            System.exit(0);
        }
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
}
