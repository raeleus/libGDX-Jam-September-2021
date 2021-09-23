package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;

public class DebugShapeEntity extends Entity {
    private Polygon polygon;
    
    public DebugShapeEntity(Polygon polygon) {
        this.polygon = polygon;
        depth = -100;
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
        JamGame.shapeDrawer.setColor(Color.RED);
        JamGame.shapeDrawer.polygon(polygon.getTransformedVertices());
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
