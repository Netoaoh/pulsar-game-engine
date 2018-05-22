/**
 * Project: PulsarGameEngine
 * File: GameObject.java
 * Author: Paulo Maria Neto
 * Created: 04/07/16
 * --------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.launcher;

import com.netoaoh.pulsar.engine.audio.AudioEngine;
import com.netoaoh.pulsar.engine.core.Engine;
import com.netoaoh.pulsar.engine.core.KeyCode;
import com.netoaoh.pulsar.engine.graphics.RenderingEngine;
import com.netoaoh.pulsar.engine.physics.PhysicsEngine;
import com.netoaoh.pulsar.engine.script.JSEngine;
import com.netoaoh.pulsar.launcher.scenes.DemoScene;

import java.io.File;

public class Main {
    public static final int EXIT_WITHOUT_ERROR = 0;
    public static final int EXIT_WITH_ERROR = 0;

    /**
     * Main method of application
     * @param args
     */
	public static void main(String[] args){

		// Initializing LWJGL library
        setupLwjgl();

        // Create a new instance of engine
        Engine engine = new Engine();

        // Setup sub engines
        engine.setRenderingEngine(new RenderingEngine());
        engine.setAudioEngine(new AudioEngine());
        engine.setPhysicsEngine(new PhysicsEngine());
        engine.setScriptEngine(new JSEngine());
        engine.setExitKeycode(KeyCode.KEY_ESCAPE);

        // Adding scenes to game
        engine.getGameInstance().addScene(new DemoScene());
        engine.getGameInstance().loadScene("Demo Scene");

        // Starting the engine
		engine.run("Demo Game", 800, 600, false);

		// Finishing application
        System.exit(EXIT_WITHOUT_ERROR);
	}

    /**
     * Setup method for LWJGL
     */
	private static void setupLwjgl(){
        // Getting current OS name
        String currentOSName = System.getProperty("os.name");

        // Check if current os is Mac OSX
        if(currentOSName.contains("Mac")){
            // Open Mac OSX native binaries and bind to framework
            File file = new File(System.getProperty("user.dir") + "/lib/natives/macosx");
            System.setProperty("org.lwjgl.librarypath", file.getAbsolutePath());

            // Check if current os is Windows
        } else if(currentOSName.contains("Windows")) {
            // Open Windows native binaries and bind to framework
            File file = new File(System.getProperty("user.dir") + "/lib/natives/windows");
            System.setProperty("org.lwjgl.librarypath", file.getAbsolutePath());

            // Check if current os is Linux
        } else if(currentOSName.contains("Linux")) {

            // Open Linux native binaries and bind to framework
            File file = new File(System.getProperty("user.dir") + "/lib/natives/linux");
            System.setProperty("org.lwjgl.librarypath", file.getAbsolutePath());
        } else {
            // Print error and finish application
            System.out.println("Unsuported platform...");
            System.exit(EXIT_WITH_ERROR);
        }
    }
}
