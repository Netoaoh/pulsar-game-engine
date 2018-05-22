/**
 * Project: PulsarGameEngine
 * Filename: MappedValues.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.utils;

import com.netoaoh.pulsar.engine.graphics.Texture;
import com.netoaoh.pulsar.engine.math.Color;
import com.netoaoh.pulsar.engine.math.Vector2f;
import com.netoaoh.pulsar.engine.math.Vector3f;

import java.util.HashMap;

public class MappedValues {

	protected HashMap<String, Color> colorMap = new HashMap<String, Color>();
    protected HashMap<String, Vector3f> vector3fMap = new HashMap<String, Vector3f>();
    protected HashMap<String, Vector2f> vector2fMap = new HashMap<String, Vector2f>();
    protected HashMap<String, Texture> textureMap = new HashMap<String, Texture>();
    protected HashMap<String, Integer> integerMap = new HashMap<String, Integer>();
    protected HashMap<String, Float> floatMap = new HashMap<String, Float>();

	public void addColor(String name, Color color){
		if(color != null)
			colorMap.put(name, color);
	}

	public void addVector3f(String name, Vector3f vector){
		if(vector != null)
			vector3fMap.put(name, vector);
	}

	public void addVector2f(String name, Vector2f vector){
		if(vector != null)
			vector2fMap.put(name, vector);
	}

	public void addTexture(String name, Texture texture){
		if(texture != null)
			textureMap.put(name, texture);
	}

	public void addInteger(String name, int value){
		integerMap.put(name, value);
	}

	public void addFloat(String name, float value){
		floatMap.put(name, value);
	}

	public Color getColor(String name){
		if(colorMap.containsKey(name))
			return colorMap.get(name);

		return null;
	}

	public Vector3f getVector3f(String name){
		if(vector3fMap.containsKey(name))
			return vector3fMap.get(name);

		return null;
	}

	public Vector2f getVector2f(String name){
		if(vector2fMap.containsKey(name))
			return vector2fMap.get(name);

		return null;
	}

	public Texture getTexture(String name){
		if(textureMap.containsKey(name)) {
			return textureMap.get(name);
		}

		return null;
	}

	public int getInteger(String name){
		if(integerMap.containsKey(name))
			return integerMap.get(name);

		return 0;
	}

	public float getFloat(String name){
		if(floatMap.containsKey(name))
			return floatMap.get(name);

		return 0.0f;
	}
}
