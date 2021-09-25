package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.FloatArray;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;
import space.earlygrey.shapedrawer.JoinType;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineActor.*;
import static com.ray3k.template.Resources.Values.*;
import static com.ray3k.template.screens.GameScreen.*;

public class SoldierEntity extends Entity {
    private static final FloatArray floatArray = new FloatArray();
    private FloatArray movePath;
    private float targetX;
    private float targetY;
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        animationState.setAnimation(0, animationStand, false);
        skeleton.setSkin(skinAssault);
        skeleton.setScale(.25f, .25f);
        depth = GameScreen.PLAYER_DEPTH;
        
        animationState.apply(skeleton);skeleton.updateWorldTransform();
        skeletonBounds.update(skeleton, true);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds, nullCollisionFilter);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (gameScreen.isBindingJustPressed(Binding.MOVE)) {
            targetX = gameScreen.mouseX;
            targetY = gameScreen.mouseY;
            gameScreen.pathHelper.findPath(x, y, targetX, targetY, 20, floatArray);
            
            if (floatArray.size > 0) {
                if (movePath == null) movePath = new FloatArray();
                movePath.clear();
                movePath.addAll(floatArray);
                
                var arrow = new MoveArrowEntity(gameScreen.mouseX, gameScreen.mouseY);
                entityController.add(arrow);
            } else {
                var error = new MoveErrorEntity(gameScreen.mouseX, gameScreen.mouseY);
                entityController.add(error);
            }
        }
        
        if (movePath != null && movePath.size > 1) {
            moveTowardsTarget(soldierMoveSpeed, movePath.get(0), movePath.get(1));
            if (!moveTargetActivated) movePath.removeRange(0, 1);
        }
    }
    
    @Override
    public void draw(float delta) {
        if (debugWalkable) {
            shapeDrawer.setColor(Color.RED);
            if (movePath != null) shapeDrawer.path(movePath, 1f, JoinType.SMOOTH, true);
        }
        if (debugJbump) {
            shapeDrawer.setColor(Color.BLUE);
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
}
