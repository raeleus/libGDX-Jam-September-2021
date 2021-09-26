package com.ray3k.template.entities;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.screens.GameScreen.*;

public class EnemyShipSpawner extends Entity {
    public int count;
    public float delay;
    public Class type;
    public float targetX;
    public float targetY;
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        delay -= delta;
        if (delay <= 0) {
            if (!gameScreen.playedSiren) {
                gameScreen.stage.addAction(Actions.delay(3f, Actions.run(() -> {
                    bgm_game.setLooping(true);
                    bgm_game.setVolume(bgm);
                    bgm_game.play();
                })));
                sfx_zombieRally.play(sfx);
                gameScreen.playedSiren = true;
            }
    
            var ship = new EnemyShipEntity();
            ship.setPosition(x, y);
            ship.targetX = targetX;
            ship.targetY = targetY;
            ship.count = count;
            entityController.add(ship);
            
            destroy = true;
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
