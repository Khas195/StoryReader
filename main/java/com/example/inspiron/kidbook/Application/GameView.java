package com.example.inspiron.kidbook.Application;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.inspiron.kidbook.MainMenu.Menu;
import com.example.inspiron.kidbook.R;


/**
 * Created by INSPIRON on 6/21/2015.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    GameThread gameThread = null;
    public static Background bg;
    public static MediaPlayer backgroundMusic;


    public GameView(Context context) {
        super(context);
        Activity temp = (Activity)context;
        bg = new Background(BitmapFactory.decodeResource(temp.getResources(), R.drawable.booklistbackground));
        backgroundMusic = MediaPlayer.create(context, R.raw.backgroundmusic);
        backgroundMusic.start();
        backgroundMusic.setLooping(true);
        GameDirector.getInstance().init(temp);


        getHolder().addCallback(this);
        gameThread = new GameThread(getHolder(), this);
        setFocusable(true);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Thread myThread = new Thread(){
            public void run(){
                GameDirector.getInstance().push(Menu.getInstance());
            }
        };
        myThread.start();
        gameThread.setRun(                          true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        while(true) {
            try {
                gameThread.setRun(false);
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        return super.onTouchEvent(motionEvent);
    }
    public void update(){
        bg.update();
    }
    @Override
    public void draw(Canvas canvas) {
        final float scaleX = getWidth()/(bg.getImage().getWidth() * 1.f);
        final float scaleY = getHeight()/(bg.getImage().getHeight() * 1.f);

        if (canvas!= null){
            final int savedScaleState = canvas.save();
            canvas.scale(scaleX, scaleY);
            bg.draw(canvas);
            canvas.restoreToCount(savedScaleState);
        }
    }
}
