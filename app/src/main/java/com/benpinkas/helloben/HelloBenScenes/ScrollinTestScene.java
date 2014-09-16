package com.benpinkas.helloben.HelloBenScenes;

import com.benpinkas.bEngine.object.Brectangle;
import com.benpinkas.bEngine.scene.Scene;
import com.benpinkas.bEngine.scene.SceneManager;

/**
 * Created by Ben on 3/13/14.
 */
public class ScrollinTestScene extends Scene {

    ScrollinTestScene(){
        super("scrollingScene");

        final Brectangle rect11 = new Brectangle(0 ,0, 0.5f, 1, 0.3f, 0.5f, 1.0f, 0.8f);
        final Brectangle rect22 = new Brectangle(0 ,0, 0.5f, 1, 0.3f, 0.2f, 0.4f, 0.4f);
        add(rect11);
        add(rect22);

      //  final BinfiniteScrolling scrolling = new BinfiniteScrolling( rect11, rect22, 0, 0, 1, 1 );
      //  add(scrolling);
    }

    @Override
    public void start() {
        setVisible(true);
        SceneManager.setInputFocus( this );
    }

    public void stop(){
    }

}