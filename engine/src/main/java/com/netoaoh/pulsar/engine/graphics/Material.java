/**
 * Project: PulsarGameEngine
 * Filename: Material.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics;

import com.netoaoh.pulsar.engine.components.Component;
import com.netoaoh.pulsar.engine.components.SpriteRenderer;
import com.netoaoh.pulsar.engine.math.Color;
import com.netoaoh.pulsar.engine.math.Vector2f;
import com.netoaoh.pulsar.engine.math.Vector3f;
import com.netoaoh.pulsar.engine.utils.MappedValues;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Material extends MappedValues {

	private static Material defaultMaterial = null;

	private String name;
	private Color color;

	public Material(String name){
		this.name = name;
		addTexture("diffuse", new Texture("Default.png"));
		addColor("materialColor", new Color(1.0f, 1.0f, 1.0f, 1.0f));
		this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
		addInteger("numberOfRows", getTexture("diffuse").getNumberOfRows());
	}

	public Material(String name, Texture texture){
		this.name = name;
		addTexture("diffuse", texture);
		addColor("materialColor", new Color(1.0f, 1.0f, 1.0f, 1.0f));
		this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
		addInteger("numberOfRows", getTexture("diffuse").getNumberOfRows());
	}

	public Material(String name, Texture texture, Color color){
		this.name = name;
		addTexture("diffuse", texture);
		addColor("materialColor", color);
		this.color = color;
		addInteger("numberOfRows", getTexture("diffuse").getNumberOfRows());
	}

	public Vector2f calculateTextureOffset(int textureIndex){
		int column = textureIndex % getTexture("diffuse").getNumberOfRows();
		int row = (int) Math.floor(textureIndex / getTexture("diffuse").getNumberOfRows());
		float XOffset = (float) column / (float)getTexture("diffuse").getNumberOfRows();
		float YOffset = (float) row / (float)getTexture("diffuse").getNumberOfRows();

		return new Vector2f(XOffset, YOffset);
	}

	public String getName(){
		return name;
	}

	public Color getColor(){
		return color;
	}

	public void setName(String name){
		if(name.isEmpty())
			return;

		this.name = name;
	}

	public void setColor(Color color){
		if(color == null)
			return;

		this.color = color;
	}

	public static Material getDefaultMaterial(){
		if(defaultMaterial == null){
			defaultMaterial = new Material("Default");
		}

		return defaultMaterial;
	}

    public JSONObject serialize(){
        JSONObject obj = new JSONObject();

        try {
            obj.put("name", name);
            obj.put("color", color.serialize());

            int index = 0;
            JSONArray colorMapArr = new JSONArray();
            for (Map.Entry<String, Color> comp : colorMap.entrySet()) {
                colorMapArr.put(index, comp.getValue().serialize());
                index++;
            }
            obj.put("colorMap", colorMapArr);

            index = 0;
            JSONArray vector3MapArr = new JSONArray();
            for (Map.Entry<String, Vector3f> comp : vector3fMap.entrySet()) {
                colorMapArr.put(index, comp.getValue().serialize());
                index++;
            }
            obj.put("vector3Map", vector3MapArr);

            index = 0;
            JSONArray vector2MapArr = new JSONArray();
            for (Map.Entry<String, Vector2f> comp : vector2fMap.entrySet()) {
                colorMapArr.put(index, comp.getValue().serialize());
                index++;
            }
            obj.put("vector2Map", vector2MapArr);

            index = 0;
            JSONArray textureMapArr = new JSONArray();
            for (Map.Entry<String, Texture> comp : textureMap.entrySet()) {
                //colorMapArr.put(index, comp.getValue().serialize());
                index++;
            }
            obj.put("textureMap", vector3MapArr);

            //TODO: Serializar INTEGER e FLOAT
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public Material deserialize(String str){
        try {
            JSONObject obj = new JSONObject(str);
            name = obj.getString("name");
            color = new Color(0,0,0,0).deserialize(obj.getString("color"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }
}
