package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.Values.*;

public class DebugShapeEntity extends Entity {
    private Polygon polygon;
    
    public DebugShapeEntity(Polygon polygon) {
        this.polygon = polygon;
        depth = GameScreen.DEBUG_DEPTH;
    }
    
    @Override
    public void create() {
        var rectangle = polygon.getBoundingRectangle();
        setPosition(rectangle.x, rectangle.y);
        setCollisionBox(0, 0, rectangle.width, rectangle.height, Core.nullCollisionFilter);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
    
    }
    
    @Override
    public void draw(float delta) {
        if (debugWalkable) {
            JamGame.shapeDrawer.setColor(Color.RED);
            JamGame.shapeDrawer.polygon(polygon.getTransformedVertices());
        }
        if (debugJbump) {
            JamGame.shapeDrawer.setColor(Color.BLUE);
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
