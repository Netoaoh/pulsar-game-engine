package com.netoaoh.pulsar.engine.script;

import com.netoaoh.pulsar.engine.physics.Collision;

public abstract class IScriptEngine {

    protected static IScriptEngine instance;

    protected IScriptEngine(){
        instance = this;
    }

    public abstract void initialize();
    public abstract void awake();
    public abstract void start();
    public abstract void update();
    public abstract void onCollisionEnter(Collision collision);
    public abstract void removeAll();
    public abstract int addScript(JSScript script);
}
