package com.benpinkas.helloben.leCasseBrique;

import com.benpinkas.R;
import com.benpinkas.bEngine.Brenderer;
import com.benpinkas.bEngine.InputStatus;
import com.benpinkas.bEngine.effect.Explosion;
import com.benpinkas.bEngine.object.BglSprite;
import com.benpinkas.bEngine.object.Bobject;
import com.benpinkas.bEngine.service.Bcall;
import com.benpinkas.bEngine.service.CollisionService;
import com.benpinkas.bEngine.service.MessageManager;
import com.benpinkas.helloben.leCasseBrique.wizard.Projectile;

/**
 * Created by Ben on 14-Jul-14.
 */
public class Bat extends BglSprite {

    private final static float NORMAL_SIZE_W = 0.25f;
    private final static float NORMAL_SIZE_H = 0.04f;
    private final static float ENLARGE_FACTOR = 2f;
    private float toGoX = 0;
    private Explosion explosion;

    public Bat(){
        super(0.4f, 0.85f, NORMAL_SIZE_W, NORMAL_SIZE_H, new int[] {R.drawable.bat});
        setSize(NORMAL_SIZE_W);
        name = "bat";
        explosion = new Explosion(this, 20, 5);
        addUpdatable(explosion);
        explosion.endCallback();

        MessageManager.addListener("bat_down", new Bcall<Void>() {
            public Void call(Object o) {

                return null;
            }
        }) ;

        MessageManager.addListener("bat_up", new Bcall<Void>() {
            public Void call(Object o) {
                return null;
            }
        }) ;

    }

    public void setBig(boolean big){
        if (big){
            setSize( NORMAL_SIZE_W * ENLARGE_FACTOR, NORMAL_SIZE_H );
        }
        else{
            setSize( NORMAL_SIZE_W, NORMAL_SIZE_H);
        }

    }

    public void setToGoPos() {
        this.toGoX = InputStatus.touchXscr / (float) Brenderer.getScreenW() - 0.5f * getSizeW();
    }

    public void update(float dt){
        if (toGoX != 0 ) {
            // Set x pos
            float x = getPosX();
            float y = getPosY();
            float dx = (toGoX - x) / 3;
            setPos(x + dx, y);
            // Set Y angle (just for fun)
            float angle = -dx*400;
            if (angle < - 20) angle = -25;
            if (angle > 20) angle = 25;
            setAngleY(angle);
        }
    }

    @Override
    public void collision(Bobject collider, CollisionService.collisionSide cs) {
        if ( collider instanceof Projectile) {
            MessageManager.sendMessage("bat_hit_by_projectile");
            explosion.start();
        }
    }

    public void init(){
        explosion.endCallback();
        setVisible(true);
    }


}
