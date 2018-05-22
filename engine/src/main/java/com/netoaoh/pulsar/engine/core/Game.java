/**
 * Project: PulsarGameEngine
 * Filename: Game.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core;

import java.util.HashMap;

public class Game {

	//private static Game instance = new Game();

	private HashMap<String, IScene> scenes;
	private IScene currentScene = null;
	private IScene nextScene = null;

	public Game(){
		scenes = new HashMap<String, IScene>();
		currentScene = null;
		nextScene = null;
	}

	public void fixedUpdate(){
		if(currentScene == null)
			return;

		currentScene.fixedUpdate();
	}

	public void update(){
		if(currentScene == null)
			return;

		currentScene.update();
	}

	public void addScene(IScene scene){
		if(scene == null)
			return;

		scenes.put(scene.getName(), scene);
	}

	public void removeScene(String name){
		if(name.isEmpty() || name == null)
			return;

		scenes.remove(name);
	}

	public void changeScene(){
		if(nextScene == null)
			return;

		currentScene = nextScene;
		nextScene = null;
		currentScene.start();
	}

	public void loadScene(String name){
		if(scenes.containsKey(name))
			nextScene = scenes.get(name);
		else
			Log.getInstance().error("Não foi possivel localizar a cena '" + name + "'.");
	}

	public IScene getCurrentScene(){
	    return currentScene;
    }

	//public static Game getInstance()	{	return instance;	}
}