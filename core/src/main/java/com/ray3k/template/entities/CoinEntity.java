package com.ray3k.template.entities;

import com.crashinvaders.vfx.effects.ShaderVfxEffect;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.esotericsoftware.spine.Event;
import com.ray3k.template.*;

import static com.ray3k.template.Resources.SpineCoin.*;

public class CoinEntity extends Entity {
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        animationState.setAnimation(0, animationAnimation, false);
        animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void complete(TrackEntry entry) {
                destroy = true;
            }
    
            @Override
            public void event(TrackEntry entry, Event event) {
                Resources.sfx_coin.play(Core.sfx);
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
