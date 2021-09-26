package com.ray3k.template.entities;

import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;

import static com.ray3k.template.Resources.SpinePoisonCloud.*;

public class PoisonCloudEntity extends Entity {
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        skeleton.setScale(.5f, .5f);
        animationState.setAnimation(0, animationAnimation, false);
        animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void complete(TrackEntry entry) {
                destroy = true;
            }
        });
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
    
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
