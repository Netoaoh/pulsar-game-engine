package com.netoaoh.pulsar.engine.core;

import com.netoaoh.pulsar.engine.audio.IAudioEngine;
import com.netoaoh.pulsar.engine.graphics.IRenderingEngine;
import com.netoaoh.pulsar.engine.physics.IPhysicsEngine;
import com.netoaoh.pulsar.engine.script.IScriptEngine;

public abstract class IEngine {

    private static IEngine instance;
    private static Game game;
    protected static int exitKeycode = KeyCode.KEY_ESCAPE;

    protected static IAudioEngine audioEngine;
    protected static IPhysicsEngine physicsEngine;
    protected static IRenderingEngine renderingEngine;
    protected static IScriptEngine scriptEngine;

    protected IEngine(){
        instance = this;
        game = new Game();
    }

    public abstract void initialize(String gameName, int width, int height, boolean fullscreen);
    public abstract void fixedUpdate();
    public abstract void update();
    public abstract void render();
    public abstract void endFrame();
    public abstract void shutdown();

    public static Game getGameInstance(){
        return game;
    }

    public static IEngine getEngine() {
        return instance;
    }

    public static IPhysicsEngine getPhysicsEngine(){
        return physicsEngine;
    }

    public static IRenderingEngine getRenderingEngine(){
        return renderingEngine;
    }

    public static IAudioEngine getAudioEngine(){
        return audioEngine;
    }

    public static IScriptEngine getScriptEngine(){
        return scriptEngine;
    }

    public void setPhysicsEngine(IPhysicsEngine iPhysicsEngine){
        physicsEngine = iPhysicsEngine;
    }

    public void setRenderingEngine(IRenderingEngine iRenderingEngine){
        renderingEngine = iRenderingEngine;
    }

    public void setAudioEngine(IAudioEngine iAudioEngine){
        audioEngine = iAudioEngine;
    }

    public void setScriptEngine(IScriptEngine iScriptEngine){
        scriptEngine = iScriptEngine;
    }

    public void setExitKeycode(int keyCode){
        exitKeycode = keyCode;
    }
}
