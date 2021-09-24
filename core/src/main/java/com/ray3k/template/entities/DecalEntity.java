package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;

public class DecalEntity extends Entity {
    private AtlasSprite region;
    
    public DecalEntity(AtlasSprite region, int centerX, int centerY) {
        this.region = region;
        x = centerX - region.getWidth() / 2f;
        y = centerY - region.getHeight() / 2f;
        depth = GameScreen.DECAL_DEPTH;
    }
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
    
    }
    
    @Override
    public void draw(float delta) {
        region.setPosition(x, y);
        region.draw(JamGame.batch);
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
