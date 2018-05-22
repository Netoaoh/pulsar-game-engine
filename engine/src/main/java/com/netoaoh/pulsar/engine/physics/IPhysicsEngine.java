package com.netoaoh.pulsar.engine.physics;


import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;

public abstract class IPhysicsEngine {
    protected static IPhysicsEngine instance;

    protected IPhysicsEngine() {
        instance = this;
    }

    public abstract void initialize();
    public abstract void fixedUpdate();
    public abstract void shutdown();

    public abstract void removeBody(Body body);
    public abstract Body createBody(BodyDef bodyDef);
    public abstract void addToPhysicsEngine(Body body);
}
