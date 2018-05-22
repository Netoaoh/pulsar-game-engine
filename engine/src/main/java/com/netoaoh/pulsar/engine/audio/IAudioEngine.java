package com.netoaoh.pulsar.engine.audio;

import com.netoaoh.pulsar.engine.math.Vector3f;

public abstract class IAudioEngine {
    protected static IAudioEngine instance;

    protected IAudioEngine() {
        instance = this;
    }

    public abstract void initialize();
    public abstract void shutdown();

    public abstract Vector3f getListenerPos();
    public abstract void setListenerPos(Vector3f listenerPos);
}
