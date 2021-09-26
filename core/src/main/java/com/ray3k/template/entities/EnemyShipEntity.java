package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineShip.*;
import static com.ray3k.template.Resources.Values.*;
import static com.ray3k.template.screens.GameScreen.*;

public class EnemyShipEntity extends Entity {
    public float targetX;
    public float targetY;
    public boolean triggered;
    public int count;
    private float timer;
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        skeleton.setScale(.25f, .25f);
        animationState.setAnimation(0, animationAnimation, false);
        animationState.setAnimation(1, animationThruster, true);
        depth = SHIP_DEPTH;
        
        moveTowardsTarget(shipSpeed, targetX, targetY);
        skeleton.getRootBone().setRotation(Utils.pointDirection(x, y, targetX, targetY));
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (!triggered && MathUtils.isEqual(x, targetX) && MathUtils.isEqual(y, targetY)) {
            triggered = true;
            animationState.setAnimation(1, animationNoThruster, false);
            setSpeed(0);
            
            timer = shipSpawnDelay;
        }
        
        if (triggered) {
            timer -= delta;
            if (timer <= 0 && count > 0) {
                Resources.sfx_troopDeploy.play(sfx);
                var enemy = new EnemyEntity();
                gameScreen.enemies.add(enemy);
                enemy.setPosition(x, y);
                entityController.add(enemy);
                timer = shipSpawnDelay;
                count--;
                if (count == 0) gameScreen.ships--;
            }
            
        }
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void projectedCollision(Result result) {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
