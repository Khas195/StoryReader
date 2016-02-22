package com.example.inspiron.kidbook.Application;

import android.app.Activity;
import android.widget.LinearLayout;

import com.example.inspiron.kidbook.MainMenu.Menu;
import com.example.inspiron.kidbook.StateStack.State;
import com.example.inspiron.kidbook.StateStack.StateStack;

/**
 * Created by INSPIRON on 6/24/2015.
 */
public class GameDirector {
    StateStack mainMenu;

    public static GameDirector getInstance() {
        if ( instance == null){
            instance = new GameDirector();
        }
        return instance;
    }

    public  int getSize() {
        return mainMenu.getSize();
    }

    public void init(Activity mainActivity){
        mainMenu = new StateStack();
        Menu.getInstance().initialize(mainActivity);
    }
    static GameDirector instance;

    GameDirector(){
    }

    public void push(State newState){
        if (mainMenu != null)
            this.mainMenu.push(newState);
    }
    public void pop(){
        if (mainMenu != null)
         this.mainMenu.pop();
    }
    public boolean isEmpty(){
        return this.mainMenu.isEmpty();
    }
}
