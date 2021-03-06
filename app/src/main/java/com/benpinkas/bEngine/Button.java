package com.benpinkas.bEngine;

import com.benpinkas.bEngine.object.BglSprite;
import com.benpinkas.bEngine.object.Brectangle;

import java.util.concurrent.Callable;

/**
 * Created by Ben on 2/4/14.
 */
public class Button extends BglSprite {

    private Callable<Void> cb;

    public Button(float x, float y, float w, float h, int[] res, Callable<Void> cb){
        super(x, y, w, h, res);
        this.cb = cb;
    }

    @Override
    public void touchUp() {
        super.touchUp();
        try {
            cb.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
