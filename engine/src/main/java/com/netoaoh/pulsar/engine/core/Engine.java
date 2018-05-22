/**
 * Project: PulsarGameEngine
 * Filename: Engine.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core;

import com.netoaoh.pulsar.engine.graphics.Window;

public class Engine extends IEngine {

	private boolean running;

	public Engine() {
        super();
        running = false;
	}

	public void initialize(String gameName, int width, int height, boolean fullscreen){
        Application.setResourcePath(System.getProperty("user.dir") + "/resources/");
		Log.getInstance().initialize();
        Window.getInstance().create(width, height, gameName, fullscreen);
		Input.initialize();
		physicsEngine.initialize();
        renderingEngine.initialize();
		audioEngine.initialize();
		scriptEngine.initialize();

		running = true;
	}

	public void fixedUpdate(){

        physicsEngine.fixedUpdate();
        scriptEngine.fixedUpdate();
        getGameInstance().fixedUpdate();
	}

	public void update(){
		if(Window.getInstance().isCloseRequested())
			running = false;

        getGameInstance().update();

        scriptEngine.update();

        if(Input.getKeyDown(exitKeycode))
			running = false;

		Input.update();
	}

	public void render(){
        renderingEngine.render();
		Window.getInstance().swapBuffers();
	}

	public void endFrame(){
		GameObject.clearDeadObjects();
        scriptEngine.removeAll();
        getGameInstance().changeScene();
	}

	public void shutdown(){
		audioEngine.shutdown();
        renderingEngine.shutdown();
        physicsEngine.shutdown();
		Window.getInstance().destroy();
		Log.getInstance().shutdown();
	}

	public void run(String gameName, int width, int height, boolean fullscreen){
		double frameCounter = 0;
		double frameCap = 1.0f/60.0f;

		initialize(gameName, width, height, fullscreen);

		double lastTime = Time.getTime();
		double unprocessedTime = 0;

		while(running){

			Time.deltaTime = (float)frameCap * Time.timeScale;

			boolean render = false;
			double startTime = Time.getTime();
			double passedTime = startTime - lastTime;
			lastTime = startTime;
			unprocessedTime += passedTime;
			frameCounter += passedTime;

			while(unprocessedTime > frameCap){
				render = true;
				unprocessedTime -= frameCap;

				fixedUpdate();
				update();

				if(frameCounter >= 1.0){
					frameCounter = 0;
				}
			}

			if(render){
				render();
			} else {
				try{
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			endFrame();
		}

		shutdown();
	}
}