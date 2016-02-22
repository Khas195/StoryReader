package com.example.inspiron.kidbook.StateStack;

/**
 * Created by INSPIRON on 6/22/2015.
 */
public interface State {
     void onPushed();
     void onPop();
     void onReturnTop();
     void onBeingPressed();
}
