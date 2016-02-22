package com.example.inspiron.kidbook.Application;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;



/**
 * Created by INSPIRON on 6/21/2015.
 */
public class Background{
    Bitmap image;
    int x,y;
    private Bitmap savedImage;

    public Background(Bitmap image){
        this.image = image;
        savedImage = image;
    }

    public void update(){
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);

    }
    public void setImage(Bitmap image) {
        this.image = null;
        this.image = image;
    }

    public void restoreImage() {
        image = savedImage;
    }

    public Bitmap getImage() {
        return image;
    }
}
