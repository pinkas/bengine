package com.benpinkas.helloben;

import com.benpinkas.bEngine.object.Brectangle;

/**
 * Created by Ben on 10/28/13.
 */
public class CubeScroll extends Brectangle {
    @Override
    public void touchDown() {
        super.touchDown();
    }

    @Override
    public void touchUp() {
        super.touchUp();
    }

    @Override
    public void touchMove(float x, float y) {
        super.touchMove(x, y);
        System.out.println( x + "    plplplpl   " + y  );
    }

    @Override
    public void touchUpMove(float x, float y) {
        super.touchUpMove(x, y);
    }

    public CubeScroll( float x, float y, float w, float h, float r, float g, float b, float a ){
        super(  x,  y,  w,  h,  r, g,  b, a );


    }
}
