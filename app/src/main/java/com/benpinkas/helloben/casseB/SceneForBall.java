package com.benpinkas.helloben.casseB;

import com.benpinkas.R;
import com.benpinkas.bEngine.object.BglSprite;
import com.benpinkas.bEngine.scene.Scene;
import com.benpinkas.bEngine.scene.SceneManager;

/**
 * Created by Ben on 13-Jul-14.
 */
public class SceneForBall extends Scene {

    private Ball myBall = new Ball(0.5f,0.8f, 0.045f, 0.025f, 0.1f, 0.7f, 1);
    private Brick[] destroyMe = new Brick[300];
    private Bat bat = new Bat();
    private static int MAX_LIFE = 3;
    private int remainingLife;
    private static enum GameState {ON, PAUSE, OFF};
    private GameState gameState = GameState.OFF;

    //Layout constants
    private final float PADDX = 0.04f;
    private final float PADDY = 0.01f;
    private final float SIZEW = 0.075f;
    private final float SIZEH = 0.03f;
    private final int ROW = 10;
    private final int COL = 11;

    public SceneForBall(){
        super("myBalls");

        int index = 0;
        for (int i=0; i < ROW; i++){
            for (int j=0; j < COL; j++){
                destroyMe[index] = new Brick();
                destroyMe[index].setVisible(false);
                add(destroyMe[index]);
                //add(destroyMe[i].getParticles(0));
                //add(destroyMe[i].getParticles(1));
                //add(destroyMe[i].getParticles(2));
                //add(destroyMe[i].getParticles(3));
                index++;
            }
        }
        bat.setVisible(false);
        add(bat);
        myBall.setVisible(false);
        add(myBall);
        //SceneManager.initAllArea();
    }

    public void initBricks(){
        int index = 0;
        for (int i=0; i < ROW; i++){
            for (int j=0; j < COL; j++){
                destroyMe[index].setPos(PADDX + j*(SIZEW+0.01f), PADDY + i*(SIZEH+0.01f) );
                destroyMe[index].setSize(SIZEW, SIZEH);
                destroyMe[index].setColor(j * 0.05f, j * 0.1f, 1.0f, 1.0f);
                destroyMe[index].initParticles();
                destroyMe[index].setCollide(true);
                destroyMe[index].setVisible(true);
                index++;
            }
        }
    }

    public void initGame(){
        remainingLife = MAX_LIFE;
        initBricks();
    }

    public void startGame(){
        myBall.fire(0.1f, -0.25f);
        myBall.setVisible(true);
        bat.setVisible(true);
        gameState = GameState.ON;
    }

    public void resetBallposition(){
        float posx = bat.getPosX();
        float posy = bat.getPosY();
        myBall.setPos(posx, posy-0.1f);
    }

    public void update(float dt){

        if (gameState != GameState.ON){
            return;
        }

        if (myBall.getPosY() > bat.getPosY() ){
            remainingLife --;

            if ( remainingLife == 0 ) {
                stop();
                SceneManager.startScene("startScene");
            }
            else {
                resetBallposition();
                myBall.fire(0.1f, -0.25f);
            }

        }

    }

    @Override
    public void start() {
        initGame();
        startGame();
        SceneManager.setInputFocus(this);
    }

    @Override
    public void stop() {
        myBall.fire(0,0);
        myBall.setVisible(false);
        bat.setVisible(false);
        gameState = GameState.OFF;
    }
}
