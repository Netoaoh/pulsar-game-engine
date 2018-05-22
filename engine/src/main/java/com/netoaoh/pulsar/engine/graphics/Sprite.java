/**
 * Project: PulsarGameEngine
 * Filename: Sprite.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics;

import com.netoaoh.pulsar.engine.components.SpriteRenderer;
import com.netoaoh.pulsar.engine.math.Vector2f;
import com.netoaoh.pulsar.engine.math.Vector3f;
import org.json.JSONException;
import org.json.JSONObject;

public class Sprite {

	public Vertex[] vertices;
	public int[] indices;
	private int textureIndex = 0;
	private float width = 0;
	private float height = 0;

	public Sprite(float width, float height) {
		this.width = width;
		this.height = height;
        createSprite();
	}

	private void createSprite(){
        float w = this.width / 2.0f;
        float h = this.height / 2.0f;

        vertices = new Vertex[]{
                new Vertex(new Vector3f(-w, h, 0), new Vector2f(1, 0)),
                new Vertex(new Vector3f(-w, -h, 0), new Vector2f(1, 1)),
                new Vertex(new Vector3f(w, -h, 0), new Vector2f(0, 1)),
                new Vertex(new Vector3f(w, h, 0), new Vector2f(0, 0))
        };

        indices = new int[]{
                0, 1, 2,
                2, 3, 0
        };
    }

	public float getWidth(){
		return this.width;
	}

	public float getHeight(){
		return this.height;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public int[] getIndices() {
		return indices;
	}

	public int getTextureIndex() {
		return textureIndex;
	}

	public void setWidth(float width){
		this.width = width;

		float w = this.width / 2.0f;
		float h = this.height / 2.0f;

		vertices = new Vertex[]{
				new Vertex(new Vector3f(-w, h, 0), new Vector2f(1, 0)),
				new Vertex(new Vector3f(-w, -h, 0), new Vector2f(1, 1)),
				new Vertex(new Vector3f(w, -h, 0), new Vector2f(0, 1)),
				new Vertex(new Vector3f(w, h, 0), new Vector2f(0, 0))
		};
	}

	public void setHeight(float height){
		this.height = height;

		float w = this.width / 2.0f;
		float h = this.height / 2.0f;

		vertices = new Vertex[]{
				new Vertex(new Vector3f(-w, h, 0), new Vector2f(1, 0)),
				new Vertex(new Vector3f(-w, -h, 0), new Vector2f(1, 1)),
				new Vertex(new Vector3f(w, -h, 0), new Vector2f(0, 1)),
				new Vertex(new Vector3f(w, h, 0), new Vector2f(0, 0))
		};
	}

	public void setDimensions(float width, float height){
		this.width = width;
		this.height = height;

		float w = this.width / 2.0f;
		float h = this.height / 2.0f;

		vertices = new Vertex[]{
				new Vertex(new Vector3f(-w, h, 0), new Vector2f(1, 0)),
				new Vertex(new Vector3f(-w, -h, 0), new Vector2f(1, 1)),
				new Vertex(new Vector3f(w, -h, 0), new Vector2f(0, 1)),
				new Vertex(new Vector3f(w, h, 0), new Vector2f(0, 0))
		};
	}

	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}

	public void setIndices(int[] indices) {
		this.indices = indices;
	}

	public void setTextureIndex(int textureIndex) {
		this.textureIndex = textureIndex;
	}

    public JSONObject serialize(){
        JSONObject obj = new JSONObject();

        try {
            obj.put("width", width);
            obj.put("height", height);
            obj.put("textureIndex", textureIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public Sprite deserialize(String str){
        try {
            JSONObject obj = new JSONObject(str);
            width = obj.getInt("width");
            height = obj.getInt("height");
            textureIndex = obj.getInt("textureIndex");
            createSprite();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }
}
