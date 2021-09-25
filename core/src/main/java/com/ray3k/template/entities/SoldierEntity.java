package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.Animation;
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
    public float targetOffsetX;
    public float targetOffsetY;
    
    public int team;
    public SoldierEntity parent;
    public Array<SoldierEntity> children = new Array<>();
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        animationState.setAnimation(0, animationStand, false);
        skeleton.setSkin(skinAssault);
        skeleton.setScale(.25f, .25f);
        depth = GameScreen.ACTOR_DEPTH;
        
        animationState.apply(skeleton);skeleton.updateWorldTransform();
        skeletonBounds.update(skeleton, true);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds, soldierCollisionFilter);
    
        if (parent == null) {
            if (team == 1) {
                animationState.setAnimation(1, animationFlagBlue, true);
            } else if (team == 2) {
                animationState.setAnimation(1, animationFlagGreen, true);
            } else if (team == 3) {
                animationState.setAnimation(1, animationFlagOrange, true);
            } else if (team == 4) {
                animationState.setAnimation(1, animationFlagPurple, true);
            }
        } else {
            animationState.setAnimation(1, animationFlagNone, false);
        }
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (parent == null) {
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
        }
        
        var selected = gameScreen.selectedSoldier == this || gameScreen.selectedSoldier == parent && parent != null;
    
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
        
        if (selected && gameScreen.isBindingJustPressed(Binding.MOVE) && parent == null) {
            targetX = gameScreen.mouseX + targetOffsetX;
            targetY = gameScreen.mouseY + targetOffsetY;

            try {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if (parent != null) {
            targetX = parent.x + targetOffsetX;
            targetY = parent.y + targetOffsetY;
    
            try {
                gameScreen.pathHelper.findPath(x, y, targetX, targetY, 5, floatArray);
                if (floatArray.size == 0) {
                    targetX = parent.x;
                    targetY = parent.y;
                    gameScreen.pathHelper.findPath(x, y, targetX, targetY, 5, floatArray);
                }
    
                if (floatArray.size > 0) {
                    if (movePath == null) movePath = new FloatArray();
                    movePath.clear();
                    movePath.addAll(floatArray);
                    movePath.removeRange(0, 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if (movePath != null && movePath.size > 1) {
            moveTowardsTarget(soldierMoveSpeed, movePath.get(0), movePath.get(1));
            if (!moveTargetActivated) movePath.removeRange(0, 1);
        }
        
        depth = ACTOR_DEPTH + y * .1f;
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
    
    public static class SoldierCollisionFilter implements CollisionFilter {
        @Override
        public Response filter(Item item, Item other) {
            if (other.userData instanceof DebugShapeEntity) return Response.slide;
            if (other.userData instanceof SoldierEntity) {
                var soldier = (SoldierEntity) item.userData;
                var otherSoldier = (SoldierEntity) other.userData;
                if (soldier.parent != null && soldier.parent == otherSoldier) {
                    return Response.slide;
                } else return null;
            }
            return null;
        }
    }
    
    public final static SoldierCollisionFilter soldierCollisionFilter = new SoldierCollisionFilter();
}