package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;
import space.earlygrey.shapedrawer.JoinType;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.Resources.SpineActor.*;
import static com.ray3k.template.Resources.Values.*;
import static com.ray3k.template.screens.GameScreen.*;

/*
0 = stand, walk
1 = flag
2 = spit
 */
public class EnemyEntity extends Entity {
    private static final FloatArray floatArray = new FloatArray();
    private FloatArray movePath;
    private float targetX;
    private float targetY;
    private boolean targetingHouse;
    public int health;
    public float hurtTimer;
    public float friction;
    public float moveSpeed;
    public int damage;
    public enum EnemyType {
        ZOMBIE, POUNCER, TANK, WITCH, SPITTER, EXPLODER
    }
    public EnemyType enemyType;
    public float shotTimer;
    
    private static final Vector2 temp = new Vector2();
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        
        switch (enemyType) {
            case ZOMBIE:
                health = zombieHealth;
                moveSpeed = zombieMoveSpeed;
                damage = zombieDamage;
                skeleton.setSkin(skinZombie);
                skeleton.setScale(.25f, .25f);
                break;
            case EXPLODER:
                health = exploderHealth;
                moveSpeed = exploderMoveSpeed;
                damage = exploderDamage;
                skeleton.setSkin(skinExploder);
                skeleton.setScale(.3f, .3f);
                break;
            case SPITTER:
                health = spitterHealth;
                moveSpeed = spitterMoveSpeed;
                damage = spitterDamage;
                skeleton.setSkin(skinSpitter);
                skeleton.setScale(.4f, .4f);
                break;
            case POUNCER:
                health = pouncerHealth;
                moveSpeed = pouncerMoveSpeed;
                damage = pouncerDamage;
                skeleton.setSkin(skinPouncer);
                skeleton.setScale(.25f, .25f);
                break;
            case WITCH:
                health = witchHealth;
                moveSpeed = witchMoveSpeed;
                damage = witchDamage;
                skeleton.setSkin(skinWitch);
                skeleton.setScale(.25f, .25f);
                break;
            case TANK:
                health = tankHealth;
                moveSpeed = tankMoveSpeed;
                damage = tankDamage;
                skeleton.setSkin(skinTank);
                skeleton.setScale(.75f, .75f);
                break;
        }
        
        animationState.setAnimation(0, animationStand, false);
        animationState.setAnimation(1, animationFlagNone, false);
        depth = GameScreen.ACTOR_DEPTH;
        
        animationState.apply(skeleton);
        skeleton.updateWorldTransform();
        skeletonBounds.update(skeleton, true);
        setCollisionBox(-5, -5, 10, 10, enemyCollisionFilter);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        Entity target = null;
        
        SoldierEntity closestSolder = null;
        var bestDistance = Float.MAX_VALUE;
        for (var soldier : gameScreen.soldiers) {
            if (soldier.hurtTimer <= 0) {
                var distance = Utils.pointDistance(x, y, soldier.x, soldier.y);
                if (distance < enemyToPlayerMinDistance && distance < bestDistance) {
                    closestSolder = soldier;
                    bestDistance = distance;
                }
            }
        }
        
        if (closestSolder != null) {
            target = closestSolder;
            targetingHouse = false;
        }
        else {
            bestDistance = Float.MAX_VALUE;
            HouseEntity closestHouse = null;
            for (var house : gameScreen.houses) {
                if (house.health > 0) {
                    var distance = Utils.pointDistance(x, y, house.x, house.y);
                    if (distance < bestDistance) {
                        closestHouse = house;
                        bestDistance = distance;
                    }
                }
            }
            
            if (closestHouse != null) {
                target = closestHouse;
                targetingHouse = true;
            } else {
                bestDistance = Float.MAX_VALUE;
                closestHouse = null;
                for (var house : gameScreen.houses) {
                    var distance = Utils.pointDistance(x, y, house.x, house.y);
                    if (distance < bestDistance) {
                        closestHouse = house;
                        bestDistance = distance;
                    }
                }
    
                if (closestHouse != null) {
                    target = closestHouse;
                    targetingHouse = true;
                }
            }
        }
        
        if (target != null) {
            targetX = target.x;
            targetY = target.y;
    
            try {
                gameScreen.pathHelper.findPath(x, y, targetX, targetY, 8, floatArray);
    
                if (floatArray.size > 0) {
                    if (movePath == null) movePath = new FloatArray();
                    movePath.clear();
                    movePath.addAll(floatArray);
                    movePath.removeRange(0, 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                x += 1.1f;
                y += 1.1f;
            }
        }
        
        if (movePath != null && movePath.size > 1) {
            moveTowardsTarget(moveSpeed, movePath.get(0), movePath.get(1));
            if (animationState.getCurrent(0).getAnimation() != animationWalk) animationState.setAnimation(0, animationWalk, true);
            
            if (Utils.pointDistance(x, y, movePath.get(0), movePath.get(1)) < .01f) {
                movePath.removeRange(0, 1);
                if (movePath.size == 0) setSpeed(0);
                animationState.setAnimation(0, animationStand, true);
            }
        }
    
        if (hurtTimer <= 0 && movePath != null && movePath.size > 1) {
            moveTowardsTarget(moveSpeed, movePath.get(0), movePath.get(1));
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
                try {
                    gameScreen.pathHelper.findPath(x, y, targetX, targetY, 8, floatArray);
    
                    if (floatArray.size > 0) {
                        if (movePath == null) movePath = new FloatArray();
                        movePath.clear();
                        movePath.addAll(floatArray);
                        movePath.removeRange(0, 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    x += 1.1f;
                    y += 1.1f;
                }
            }
        }
    
        if (enemyType == EnemyType.SPITTER) {
            
            
            SoldierEntity closestSoldier = null;
            var bestSoldierDistance = Float.MAX_VALUE;
            for (var soldier : gameScreen.soldiers) {
                var distance = Utils.pointDistance(x, y, soldier.x, soldier.y);
                if (distance < spitterRange && distance < bestSoldierDistance) {
                    bestSoldierDistance = distance;
                    closestSoldier = soldier;
                }
            }

            if (closestSolder != null) {
                moveTowardsTarget(false);
                setSpeed(0);
                shotTimer -= delta;
    
                if (shotTimer < 0) {
                    float direction = Utils.pointDirection(x, y, closestSoldier.x, closestSoldier.y);
                    sfx_spit.play(sfx);
                    shotTimer = spitterDelay;
                    animationState.setAnimation(2, animationSpit, false);
                    if (direction > 90 && direction < 270) skeleton.setScale(-1 * Math.abs(skeleton.getScaleX()),skeleton.getScaleY());
                    else skeleton.setScale(Math.abs(skeleton.getScaleX()),skeleton.getScaleY());
                    closestSoldier.hurt(damage, direction);
                }
            }
        }
        
        setSpeed(Utils.approach(getSpeed(), 0, friction * delta));
        
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
            if (rect != null) shapeDrawer.rectangle(rect.x, rect.y, rect.w, rect.h);
        }
    }
    
    @Override
    public void destroy() {
        gameScreen.enemies.removeValue(this, true);
        if (enemyType != EnemyType.EXPLODER) {
            var cloud = new BloodCloudEntity();
            cloud.setPosition(x, y);
            entityController.add(cloud);
        } else {
            var cloud = new PoisonCloudEntity();
            cloud.setPosition(x, y);
            entityController.add(cloud);
            sfx_explosion2.play(sfx);
            
            for (var soldier : gameScreen.soldiers) {
                float distance = Utils.pointDistance(x, y, soldier.x, soldier.y);
                if (distance < exploderRange) {
                    if (soldier.hurtTimer <= 0) {
                        soldier.hurt(damage, Utils.pointDirection(x, y, soldier.x, soldier.y));
                    }
                }
            }
        }
    }
    
    @Override
    public void projectedCollision(Result result) {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
        for (int i = 0; i < collisions.size(); i++) {
            var collision = collisions.get(i);
    
            var other = collision.other.userData;
            if (other instanceof EnemyEntity) {
                var otherEnemy = (EnemyEntity) other;
                temp.set(x, y);
                temp.sub(otherEnemy.x, otherEnemy.y);
                temp.setLength(1);
                x += temp.x;
                y += temp.y;
            } else if (other instanceof SoldierEntity) {
                var otherSoldier = (SoldierEntity) other;
                if (otherSoldier.hurtTimer <= 0) {
                    if (enemyType != EnemyType.EXPLODER) {
                        otherSoldier.hurt(damage, Utils.pointDirection(x, y, otherSoldier.x, otherSoldier.y));
                        sfx_slash.play(sfx);
                    } else {
                        destroy = true;
                    }
                }
            } else if (other instanceof HouseEntity) {
                var otherHouse = (HouseEntity) other;
                if (targetingHouse && otherHouse.hurtTimer <= 0) {
                    otherHouse.hurt(damage);
                    sfx_slash.play(sfx);
                }
            }
        }
    }
    
    public static class EnemyCollisionFilter implements CollisionFilter {
        @Override
        public Response filter(Item item, Item other) {
            if (other.userData instanceof WallEntity) return Response.bounce;
            if (other.userData instanceof EnemyEntity) return Response.cross;
            if (other.userData instanceof  SoldierEntity) return Response.cross;
            if (other.userData instanceof  HouseEntity) return Response.cross;
            return null;
        }
    }
    
    public final static EnemyCollisionFilter enemyCollisionFilter = new EnemyCollisionFilter();
    
    public void hurt(float damage, float direction) {
        health -= damage;
        if (health <= 0) {
            destroy = true;
            sfx_zombieDeath.play(sfx);
        } else {
            hurtTimer = soldierHurtDelay;
            setMotion(soldierHurtSpeed, direction);
            friction = soldierHurtFriction;
            if (direction > 90 && direction < 270) {
                animationState.setAnimation(2, animationHurtLeft, false);
            } else {
                animationState.setAnimation(2, animationHurtRight, false);
            }
        }
    }
}