package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;
import com.ray3k.template.Resources.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Resources.Values.*;

public class DebugShapeEntity extends Entity {
    private Polygon polygon;
    
    public DebugShapeEntity(Polygon polygon) {
        this.polygon = polygon;
        depth = GameScreen.DEBUG_DEPTH;
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
        if (debugging) {
            JamGame.shapeDrawer.setColor(Color.RED);
            JamGame.shapeDrawer.polygon(polygon.getTransformedVertices());
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
