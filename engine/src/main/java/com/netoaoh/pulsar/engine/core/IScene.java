package com.netoaoh.pulsar.engine.core;

public abstract class IScene {

    protected String name;
    protected GameObject root = new GameObject();

    protected IScene(String sceneName){
        name = sceneName;
        GameObject.root = root;
    }

    public abstract void start();

    public void fixedUpdate(){
        root.fixedUpdate();
    }

    public void update(){
        root.update();
    }

    public void addToScene(GameObject obj){
        if(obj != null)
            root.addChild(obj);
    }

    public String getName() {
        return name;
    }

    public GameObject getRootObject(){
        return root;
    }
}
