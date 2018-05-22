/**
 * Project: PulsarGameEngine
 * Filename: Batch.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics;

import com.netoaoh.pulsar.engine.components.Renderer;
import com.netoaoh.pulsar.engine.components.SpriteRenderer;

import java.util.ArrayList;

public class Batch {

	private Material material;
	private String name;
	private ArrayList<Renderer> renderables;

	public Batch(Material material){
		this.name = material.getName();
		this.material = material;
		renderables = new ArrayList<Renderer>();
	}

	public void addToBatch(Renderer component){
		this.renderables.add(component);
	}

	public void render(Shader shader){

		if(renderables.size() == 0)
			return;

		shader.enable();
        /*shader.setUniform("pr_matrix", Camera.mainCamera.getViewProjection());
        shader.setUniform("numberOfRows", material.getTexture("diffuse").getNumberOfRows());
        shader.setUniform("texCoordOffset", material.calculateTextureOffset(0));
        shader.setUniform("materialColor", material.getColor());
        shader.setUniform("ambientColor", RenderingEngine.getInstance().getColor("ambientColor"));
		shader.setUniform("ambientIntensity", RenderingEngine.getInstance().getFloat("ambientIntensity"));*/
		shader.updateUniforms(material);
		material.getTexture("diffuse").bind();
		shader.setUniform("materialColor", material.getColor());
		shader.setUniform("numberOfRows", material.getTexture("diffuse").getNumberOfRows());


		for(int i = 0; i < renderables.size(); i++){
			shader.setUniform("ml_matrix", renderables.get(i).getGameObject().transform.getTransformation());
			shader.setUniform("texCoordOffset", material.calculateTextureOffset(renderables.get(i).getGameObject().getComponent(SpriteRenderer.class).getSprite().getTextureIndex()));
			renderables.get(i).render();
		}

		material.getTexture("diffuse").unbind();
		shader.disable();
	}

	public void clear(){
		renderables.clear();
	}

	public String getName(){
		return name;
	}
}

