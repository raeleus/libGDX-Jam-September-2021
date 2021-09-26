package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineHouse.*;
import static com.ray3k.template.Resources.Values.*;
import static com.ray3k.template.screens.GameScreen.*;

public class HouseEntity extends Entity {
    public int health;
    public float hurtTimer;
    
    @Override
    public void create() {
        health = houseHealth;
        setSkeletonData(skeletonData, animationData);
        animationState.setAnimation(0, animationAlive, false);
        skeleton.setScale(.5f, .5f);
        depth = ACTOR_DEPTH  + y * .1f;
        
        animationState.apply(skeleton);
        skeleton.updateWorldTransform();
        skeletonBounds.update(skeleton, true);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds, Core.nullCollisionFilter);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (health > 0 && hurtTimer > 0) {
            hurtTimer -= delta;
        }
    }
    
    @Override
    public void draw(float delta) {
        if (debugWalkable) {
            JamGame.shapeDrawer.setColor(Color.RED);
            var rect = world.getRect(item);
            shapeDrawer.rectangle(rect.x, rect.y, rect.w, rect.h);
        }
        if (debugJbump) {
            JamGame.shapeDrawer.setColor(Color.BLUE);
            var rect = world.getRect(item);
            shapeDrawer.rectangle(rect.x, rect.y, rect.w, rect.h);
        }
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
    
    public void hurt(int damage) {
        health -= damage;
        hurtTimer = houseHurtDelay;
        if (health <= 0) {
            animationState.setAnimation(0, animationDestroyed, false);
            Resources.sfx_buildingDestroyed.play(sfx);
        } else {
        
        }
    }
}
