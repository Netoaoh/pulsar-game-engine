/**
 * Project: PulsarGameEngine
 * Filename: SpriteRenderer.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.components;

import com.netoaoh.pulsar.engine.core.IEngine;
import com.netoaoh.pulsar.engine.graphics.Material;
import com.netoaoh.pulsar.engine.graphics.RenderingEngine;
import com.netoaoh.pulsar.engine.graphics.Sprite;
import com.netoaoh.pulsar.engine.graphics.buffers.IndexBuffer;
import com.netoaoh.pulsar.engine.graphics.buffers.VertexArray;
import com.netoaoh.pulsar.engine.graphics.buffers.VertexBuffer;
import com.netoaoh.pulsar.engine.math.Quaternion;
import org.json.JSONException;
import org.json.JSONObject;

import static org.lwjgl.opengl.GL11.*;

public class SpriteRenderer extends Renderer {

	private Material material = null;
	private Sprite sprite = null;
	private VertexArray vertexArray;
	private IndexBuffer indexBuffer;

	public SpriteRenderer(Sprite sprite) {
		super();
		this.name = getClass().getSimpleName().toString();
		this.sprite = sprite;
	}

	public SpriteRenderer(Sprite sprite, Material material) {
		super();
		this.name = getClass().getSimpleName().toString();
		this.material = material;
		this.sprite = sprite;
	}

	public void awake() {
		super.awake();

		vertexArray = new VertexArray();
		vertexArray.addBuffer(new VertexBuffer(sprite.vertices));
		indexBuffer = new IndexBuffer(sprite.indices);
	}

	public void start() {
		super.start();
	}

	public void update() {
		super.update();

		AnimationComponent anim = gameObject.getComponent(AnimationComponent.class);

		if (anim != null) {
			anim.update();

			sprite.setTextureIndex(anim.getCurrentAnimation());
		}

		IEngine.getRenderingEngine().addToRenderingEngine(material, this);
	}

	public void render() {
		super.render();

		/*material.getShader().enable();
        material.getShader().setUniform("pr_matrix", Camera.mainCamera.getViewProjection());
        material.getShader().setUniform("ml_matrix", gameObject.transform.getTransformation());
        material.getShader().setUniform("numberOfRows", material.getTexture("diffuse").getNumberOfRows());
        material.getShader().setUniform("texCoordOffset", material.calculateTextureOffset(sprite.getTextureIndex()));
        material.getShader().setUniform("materialColor", material.getColor());
        material.getTexture("diffuse").bind();*/
		vertexArray.bind();
		indexBuffer.bind();

		glDrawElements(GL_TRIANGLES, indexBuffer.getCount(), GL_UNSIGNED_INT, 0);

		vertexArray.unbind();
		indexBuffer.unbind();
        /*material.getTexture("diffuse").unbind();
        material.getShader().disable();*/
	}

	public Material getMaterial() {
		return material;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

    public JSONObject serialize(){
        JSONObject obj = new JSONObject();

        try {
            obj.put("material", material.serialize());
            obj.put("sprite", sprite.serialize());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public SpriteRenderer deserialize(String str){
        try {
            JSONObject obj = new JSONObject(str);
            material = new Material("").deserialize(obj.getString("material"));
            sprite = new Sprite(0,0).deserialize(obj.getString("sprite"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }
}
