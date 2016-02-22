package com.example.inspiron.kidbook.MainMenu;

import android.app.Activity;
import android.widget.LinearLayout;

import com.example.inspiron.kidbook.Application.MainActivity;
import com.example.inspiron.kidbook.Manager.BookManager;
import com.example.inspiron.kidbook.StateStack.State;



/**
 * Created by INSPIRON on 6/22/2015.
 */
public class Menu implements State, IMenu {
    static  Menu instance;
    BookManager bookManager;

    public static Menu getInstance() {
        if (instance == null){
            instance = new Menu();
        }
        return instance;
    }


    Menu(){

    }
    @Override
    public void showContent(){
        bookManager.showContent();
    }
    @Override
    public void hideContent(){
        bookManager.hideContent();
    }
    @Override
    public void onPushed() {
        showContent();
    }

    @Override
    public void onPop() {
        hideContent();
    }

    @Override
    public void onReturnTop() {
        showContent();
    }

    @Override
    public void onBeingPressed() {
        hideContent();
    }


    public void initialize(Activity activity) {

        bookManager = new BookManager(activity);
        hideContent();
    }


}
