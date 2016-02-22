package com.example.inspiron.kidbook.Application;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.inspiron.kidbook.R;
import com.example.inspiron.kidbook.StateStack.StateStack;

public class BookActivity extends ActionBarActivity {
    GameView gameView;
    public static RelativeLayout BUTTONS_LAYOUT;
    public static StateStack bookStates;
    FrameLayout game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        bookStates = new StateStack();
        // set full screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        Intent mIntent = this.getIntent();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book, menu);
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
