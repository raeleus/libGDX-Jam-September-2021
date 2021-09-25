package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.FloatArray;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.Animation;
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
    public int team;
    
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
    
        if (team == 1) {
            animationState.setAnimation(1, animationFlagBlue, true);
        } else if (team == 2) {
            animationState.setAnimation(1, animationFlagGreen, true);
        } else if (team == 3) {
            animationState.setAnimation(1, animationFlagOrange, true);
        } else if (team == 4) {
            animationState.setAnimation(1, animationFlagPurple, true);
        }
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (team == 1 && gameScreen.isBindingJustPressed(Binding.TEAM_1)) {
            gameScreen.selectedSoldier = this;
        } else if (team == 2 && gameScreen.isBindingJustPressed(Binding.TEAM_2)) {
            gameScreen.selectedSoldier = this;
        } else if (team == 3 && gameScreen.isBindingJustPressed(Binding.TEAM_3)) {
            gameScreen.selectedSoldier = this;
        } else if (team == 4 && gameScreen.isBindingJustPressed(Binding.TEAM_4)) {
            gameScreen.selectedSoldier = this;
        } else if (gameScreen.isBindingJustPressed(Binding.DESELECT)) {
            gameScreen.selectedSoldier = null;
        }
        
        var selected = gameScreen.selectedSoldier == this;
        
        if (!selected && gameScreen.justTapped && Utils.pointDistance(x, y, gameScreen.mouseX, gameScreen.mouseY) < 50) {
            gameScreen.selectedSoldier = this;
            selected = true;
        }
    
        Animation animation = null;
        if (selected) {
            if (team == 1) {
                animation = animationSelectedBlue;
            } else if (team == 2) {
                animation = animationSelectedGreen;
            } else if (team == 3) {
                animation = animationSelectedOrange;
            } else if (team == 4) {
                animation = animationSelectedPurple;
            }
            if (animationState.getCurrent(2) == null || animationState.getCurrent(2).getAnimation() != animation) animationState.setAnimation(2, animation, true);
        } else {
            animationState.setEmptyAnimation(2, 0);
        }
        
        if (selected && gameScreen.isBindingJustPressed(Binding.MOVE)) {
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
