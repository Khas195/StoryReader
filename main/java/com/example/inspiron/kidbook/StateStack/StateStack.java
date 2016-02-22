package com.example.inspiron.kidbook.StateStack;

import java.util.Stack;

/**
 * Created by INSPIRON on 6/22/2015.
 */
public class StateStack {
    Stack<State> mStack;

    public StateStack(){
        mStack = new Stack<>();
    }
    public boolean isEmpty(){
        return mStack.isEmpty();

    }
    public void push(State newState){
        if (newState == null)
            return;
        if (!mStack.empty()){
            mStack.peek().onBeingPressed();
        }
        mStack.push(newState);
        newState.onPushed();
    }
    public void pop(){
        if (!mStack.empty()){
            mStack.peek().onPop();
            mStack.pop();

            if (!mStack.empty()){
                mStack.peek().onReturnTop();
            }
        }
    }

    public int getSize() {
        return this.mStack.size();
    }
}
