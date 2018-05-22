/**
 * Project: PulsarGameEngine
 * Filename: Color.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.math;

import org.json.JSONException;
import org.json.JSONObject;

public class Color {

	public static Color white = new Color(1.0f, 1.0f, 1.0f, 1.0f);
	public static Color black = new Color(0.0f, 0.0f, 0.0f, 1.0f);
	public static Color gray = new Color(0.5f, 0.5f, 0.5f, 1.0f);
	public static Color red = new Color(1.0f, 0.0f, 0.0f, 1.0f);
	public static Color green = new Color(0.0f, 1.0f, 0.0f, 1.0f);
	public static Color blue = new Color(0.0f, 0.0f, 1.0f, 1.0f);
	public static Color yellow = new Color(0.0f, 1.0f, 1.0f, 1.0f);
	public static Color magent = new Color(1.0f, 1.0f, 0.0f, 1.0f);
	public static Color cyan = new Color(1.0f, 0.0f, 1.0f, 1.0f);

	private float r;
	private float g;
	private float b;
	private float a;

	public Color(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public static int floatToInt(float value) {
		return (int) (value * 255);
	}

	public static float intToFloat(int value) {
		return (float) (value / 255);
	}

	public float getRed() {
		return r;
	}

	public float getGreen() {
		return g;
	}

	public float getBlue() {
		return b;
	}

	public float getAlpha() {
		return a;
	}

	public void setRed(float r) {
		this.r = r;
	}

	public void setGreen(float g) {
		this.g = g;
	}

	public void setBlue(float b) {
		this.b = b;
	}

	public void setAlpha(float a) {
		this.a = a;
	}

	public Color set(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		return this;
	}

	public Color set(Color other) {
		set(other.getRed(), other.getGreen(), other.getBlue(), other.getAlpha());
		return this;
	}

    public JSONObject serialize(){
        JSONObject obj = new JSONObject();

        try {
            obj.put("r", r);
            obj.put("g", g);
            obj.put("b", b);
            obj.put("a", a);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public Color deserialize(String str){
        try {
            JSONObject obj = new JSONObject(str);
            r = (float)obj.getDouble("r");
            g = (float)obj.getDouble("g");
            b = (float)obj.getDouble("b");
            a = (float)obj.getDouble("a");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }
}