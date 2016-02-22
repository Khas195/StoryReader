package com.example.inspiron.kidbook.Application;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by INSPIRON on 6/21/2015.
 */
public class GameThread extends  Thread {
    private int FPS = 30;
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean run;
    public static Canvas canvas;


    public GameThread(SurfaceHolder surfaceHolder, GameView gameView){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }
    public  void setRun(boolean run){
        this.run = run;
    }
    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        int frameCount = 0;
        long targetTime = 1000/FPS;

        while (run){
            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e){
            }
            finally {
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            timeMillis = (System.nanoTime() - startTime)/ 1000000;
            waitTime = targetTime - timeMillis;

            try{
                this.sleep(waitTime);
            } catch (Exception e) {
            }
            frameCount++;
            if (frameCount == FPS){
                frameCount = 0;
            }
        }

    }
}
