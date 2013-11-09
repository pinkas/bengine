package com.example.bEngine;

import com.example.bEngine.object.Brectangle;

import java.util.concurrent.Callable;

/**
 * Created by Ben on 11/5/13.
 */
public class Joypad extends Brectangle  {

    Callable actionDown;

    public Joypad() {
        super( 0, 0, 1, 1, 0, 0, 0, 0 );

    }

    @Override
    public void touchDown()  {
        super.touchDown();
        try {
            actionDown.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void defineActionDown ( Callable<Float> func ){
        actionDown = func;
    }

    public void rightAction(){

    }



}
