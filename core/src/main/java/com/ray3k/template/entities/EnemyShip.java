package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;

import static com.ray3k.template.Resources.SpineShip.*;
import static com.ray3k.template.Resources.Values.*;
import static com.ray3k.template.screens.GameScreen.*;

public class EnemyShip extends Entity {
    public float targetX;
    public float targetY;
    public boolean triggered;
    
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
