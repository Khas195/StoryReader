package com.example.inspiron.kidbook.Application;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.inspiron.kidbook.Manager.PageManager;
import com.example.inspiron.kidbook.R;
import com.example.inspiron.kidbook.StateStack.State;
import com.example.inspiron.kidbook.StateStack.StateStack;
/**
 * Created by INSPIRON on 6/28/2015.
 */
public class Book implements  State{
    Activity mainActivity;
    int bookIconID;

    String pageFileName;
    String authorName;
    String publishedDate;
    String bookTitle;

    String iconName;
    String backgroundName;


    public static Bitmap backgroundImage;
    static PageManager pageManager;
    public Book( String iconName,String backgroundName,  String authorName, String publishedDate, String bookTitle,String fileName,Activity activity) {
        this.authorName = authorName;
        this.publishedDate = publishedDate;
        this.bookTitle = bookTitle;
        this.mainActivity = activity;
        this.iconName = iconName;
        this.backgroundName = backgroundName;
        this.pageFileName = fileName;
        this.bookIconID = mainActivity.getResources().getIdentifier(iconName, "drawable", mainActivity.getPackageName());


    }
    void createList(){
        processInfo(backgroundName);
        pageManager = new PageManager(mainActivity, this, this.pageFileName);
        pageManager.hideList();
    }
    void reset(){
        this.backgroundImage = null;
        pageManager.destruct();
        pageManager = null;
    }
    private void processInfo(String backgroundName) {
        int backgroundID = mainActivity.getResources().getIdentifier(backgroundName, "drawable", mainActivity.getPackageName());

        this.backgroundImage = BitmapFactory.decodeResource(mainActivity.getResources(), backgroundID);
    }

    @Override
    public void onPushed() {
        createList();
        this.pageManager.showList();
        GameView.bg.setImage(backgroundImage);
    }

    @Override
    public void onPop() {
        this.pageManager.hideList();
        reset();
        GameView.bg.restoreImage();
    }

    @Override
    public void onReturnTop() {

        this.pageManager.showList();

        GameView.bg.setImage(backgroundImage);
    }

    @Override
    public void onBeingPressed() {
        this.pageManager.hideList();
    }
    public int getBookIconID() {
        return bookIconID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
