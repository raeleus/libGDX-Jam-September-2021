package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.Animation;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;
import space.earlygrey.shapedrawer.JoinType;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
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
    public float hurtTimer;
    public int team;
    public SoldierEntity parent;
    public Array<SoldierEntity> children = new Array<>();
    private static final Vector2 temp = new Vector2();
    private int easterEggCount;
    private int health;
    private float friction;
    
    @Override
    public void create() {
        health = soldierHealth;
        setSkeletonData(skeletonData, animationData);
        animationState.setAnimation(0, animationStand, false);
        skeleton.setSkin(skinAssault);
        skeleton.setScale(.25f, .25f);
        depth = GameScreen.ACTOR_DEPTH;
        
        animationState.apply(skeleton);
        skeleton.updateWorldTransform();
        skeletonBounds.update(skeleton, true);
        setCollisionBox(-5, -5, 10, 10, soldierCollisionFilter);
    
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
                playSelectedSound();
            } else if (team == 2 && gameScreen.isBindingJustPressed(Binding.TEAM_2)) {
                gameScreen.selectedSoldier = this;
                playSelectedSound();
            } else if (team == 3 && gameScreen.isBindingJustPressed(Binding.TEAM_3)) {
                gameScreen.selectedSoldier = this;
                playSelectedSound();
            } else if (team == 4 && gameScreen.isBindingJustPressed(Binding.TEAM_4)) {
                gameScreen.selectedSoldier = this;
                playSelectedSound();
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
            easterEggCount = 0;
        }
        
        if (selected && gameScreen.isBindingJustPressed(Binding.MOVE) && parent == null) {
            targetX = gameScreen.mouseX + targetOffsetX;
            targetY = gameScreen.mouseY + targetOffsetY;

            try {
                gameScreen.pathHelper.findPath(x, y, targetX, targetY, 8, floatArray);
                if (floatArray.size > 0) {
                    if (movePath == null) movePath = new FloatArray();
                    movePath.clear();
                    movePath.addAll(floatArray);
                    movePath.removeRange(0, 1);
        
                    var arrow = new MoveArrowEntity(gameScreen.mouseX, gameScreen.mouseY);
                    entityController.add(arrow);
                    sfx_click.play(sfx);
                } else {
                    var error = new MoveErrorEntity(gameScreen.mouseX, gameScreen.mouseY);
                    entityController.add(error);
                    sfx_click.play(sfx);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if (parent != null) {
            targetX = parent.x + targetOffsetX;
            targetY = parent.y + targetOffsetY;
    
            try {
                gameScreen.pathHelper.findPath(x, y, targetX, targetY, 8, floatArray);
                if (floatArray.size == 0) {
                    targetX = parent.x;
                    targetY = parent.y;
                    gameScreen.pathHelper.findPath(x, y, targetX, targetY, 8, floatArray);
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
        
        if (hurtTimer <= 0 && movePath != null && movePath.size > 1) {
            moveTowardsTarget(assaultMoveSpeed, movePath.get(0), movePath.get(1));
            if (animationState.getCurrent(0).getAnimation() != animationWalk) {
                animationState.setAnimation(0, animationWalk, true);
                animationState.getCurrent(0).setDelay(MathUtils.random(.5f));
            }
            
            if (Utils.pointDistance(x, y, movePath.get(0), movePath.get(1)) < .01f) {
                movePath.removeRange(0, 1);
                if (movePath.size == 0) setSpeed(0);
                animationState.setAnimation(0, animationStand, false);
            }
        }
        
        if (hurtTimer > 0) {
            hurtTimer -= delta;
            if (hurtTimer <= 0) {
                hurtTimer = 0;
                friction = 0;
                gameScreen.pathHelper.findPath(x, y, targetX, targetY, 8, floatArray);
    
                if (floatArray.size > 0) {
                    if (movePath == null) movePath = new FloatArray();
                    movePath.clear();
                    movePath.addAll(floatArray);
                    movePath.removeRange(0, 1);
                }
            }
        }
        
        setSpeed(Utils.approach(getSpeed(), 0, friction * delta));
        
        depth = ACTOR_DEPTH + y * .1f;
    }
    
    public void hurt(int damage, float direction) {
        health -= damage;
        if (health <= 0) {
            destroy = true;
            sfx_assaultDie.play(sfx);
        } else {
            hurtTimer = soldierHurtDelay;
            setMotion(soldierHurtSpeed, direction);
            friction = soldierHurtFriction;
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
        gameScreen.soldiers.removeValue(this, true);
    }
    
    @Override
    public void projectedCollision(Result result) {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
        for (int i = 0; i < collisions.size(); i++) {
            var collision = collisions.get(i);
    
            if (collision.other.userData instanceof SoldierEntity) {
                var otherSoldier = (SoldierEntity) collision.other.userData;
                if (parent != null) {
                    temp.set(x, y);
                    temp.sub(otherSoldier.x, otherSoldier.y);
                    temp.setLength(1);
                    x += temp.x;
                    y += temp.y;
                }
            }
        }
    }
    
    public static class SoldierCollisionFilter implements CollisionFilter {
        @Override
        public Response filter(Item item, Item other) {
            if (other.userData instanceof WallEntity) return Response.slide;
            if (other.userData instanceof SoldierEntity) {
                var soldier = (SoldierEntity) item.userData;
                var otherSoldier = (SoldierEntity) other.userData;
                if (soldier.parent != null && soldier.parent == otherSoldier) {
                    return Response.slide;
                } else return Response.cross;
            }
            return null;
        }
    }
    
    public final static SoldierCollisionFilter soldierCollisionFilter = new SoldierCollisionFilter();
    
    public void playSelectedSound() {
        easterEggCount++;
        
        if (easterEggCount < 5) {
            var selection = MathUtils.random(2);
            switch (selection) {
                case 0:
                    sfx_assaultSelected1.play(sfx);
                    break;
                case 1:
                    sfx_assaultSelected2.play(sfx);
                    break;
                case 2:
                    sfx_assaultSelected3.play(sfx);
                    break;
            }
        } else {
            sfx_assaultEasterEgg.play(sfx);
            easterEggCount = 0;
        }
    }
}