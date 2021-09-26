package com.ray3k.template.entities;

import com.badlogic.gdx.math.MathUtils;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class HeavyMissileEntity extends Entity {
    public float splashRange;
    public float damage;
    public EnemyEntity target;
    public float timer;
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        timer -= delta;
        if (timer <= 0) {
            destroy = true;
            
            for (var enemy : GameScreen.gameScreen.enemies) {
                var distance = Utils.pointDistance(target.x, target.y, enemy.x, enemy.y);
                if (distance < splashRange) {
                    enemy.hurt(damage, Utils.pointDirection(target.x, target.y, enemy.x, enemy.y));
                }
            }
            
            var explosion = new ExplosionEntity();
            explosion.setPosition(target.x, target.y);
            entityController.add(explosion);
            explosion.skeleton.setScale(explosion.skeleton.getScaleX() * splashRange / Values.heavySplashRange, explosion.skeleton.getScaleY() * splashRange / Values.heavySplashRange);
            switch (MathUtils.random(2)) {
                case 0:
                    sfx_explosion.play(sfx);
                    break;
                case 1:
                    sfx_explosion2.play(sfx);
                    break;
                case 2:
                    sfx_explosion3.play(sfx);
                    break;
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
