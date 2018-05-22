/**
 * Project: PulsarGameEngine
 * Filename: RenderingEngine.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics;

import com.netoaoh.pulsar.engine.components.BaseLight;
import com.netoaoh.pulsar.engine.components.Renderer;
import com.netoaoh.pulsar.engine.core.Log;
import com.netoaoh.pulsar.engine.math.Color;
import com.netoaoh.pulsar.engine.utils.MappedValues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

public class RenderingEngine extends MappedValues implements IRenderingEngine {

	//private static RenderingEngine instance = new RenderingEngine();

	private HashMap<String, Batch> batches;
	private ArrayList<BaseLight> lights;
	private BaseLight activeLight;
	Shader shader;

	public RenderingEngine() {
		batches = new HashMap<String, Batch>();
		lights = new ArrayList<BaseLight>();
	}

	public void initialize() {
		Log.getInstance().info("Opengl version: " + getOpenGLVersion());

		//glClearColor(0.2f, 0.5f, 0.8f, 1.0f);

        /*glEnable(GL_BLEND);
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);*/
		glEnable(GL_DEPTH_TEST);

		//
		// glEnable(GL_DEPTH_CLAMP);

		glEnable(GL_TEXTURE_2D);
		//PostProcessManager.getInstance().initialize();
		//PostProcessManager.getInstance().addEffect(new PostProcessEffect("Grayscale", new Shader("Grayscale")));
		shader = new Shader("ForwardAmbient");
		//addColor("ambientColor", new Color(0.2f, 0.5f, 0.8f, 1.0f));
		addColor("ambientColor", new Color(1.0f, 1.0f, 1.0f, 1.0f));
		addFloat("ambientIntensity", 1.0f);
	}

	public void render() {
        glClearColor(0.2f, 0.5f, 0.8f, 1.0f);
		if (batches.size() == 0)
			return;

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		//PostProcessManager.getInstance().prepare();


		for (Map.Entry<String, Batch> entry : batches.entrySet()) {
			entry.getValue().render(shader);
		}

        /*glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);
        glDepthMask(false);
        glDepthFunc(GL_EQUAL);

        for (BaseLight light : lights) {
            activeLight = light;

            for (Map.Entry<String, Batch> entry : batches.entrySet()) {
                entry.getValue().render(activeLight.getShader());
            }
        }

        glDepthFunc(GL_LESS);
        glDepthMask(true);
        glDisable(GL_BLEND);*/

		//PostProcessManager.getInstance().apply();

		batches.clear();
	}

	public void shutdown() {
		PostProcessManager.getInstance().clear();
		batches.clear();
		lights.clear();
	}

	public void addToRenderingEngine(Material material, Renderer renderer) {
		if (batches.containsKey(material.getName())) {
			batches.get(material.getName()).addToBatch(renderer);
		} else {
			Batch newBatch = new Batch(material);
			newBatch.addToBatch(renderer);
			batches.put(material.getName(), newBatch);
		}
	}

	public void AddLight(BaseLight light) {
		if (light != null)
			lights.add(light);
	}

	public void RemoveLight(BaseLight light) {
		if (light != null)
			lights.remove(light);
	}

	public String getOpenGLVersion() {
		return glGetString(GL_VERSION);
	}

	/*public static RenderingEngine getInstance() {
		return instance;
	}*/
}
