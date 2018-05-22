/**
 * Project: PulsarGameEngine
 * Filename: Vector2f.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.math;

import org.json.JSONException;
import org.json.JSONObject;

public class Vector2f {

	private float x;
	private float y;

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2f add(Vector2f r) {
		return new Vector2f(x + r.x, y + r.y);
	}

	public Vector2f add(float r) {
		return new Vector2f(x + r, y + r);
	}

	public Vector2f sub(Vector2f r) {
		return new Vector2f(x - r.x, y - r.y);
	}

	public Vector2f sub(float r) {
		return new Vector2f(x - r, y - r);
	}

	public Vector2f mul(Vector2f r) {
		return new Vector2f(x * r.x, y * r.y);
	}

	public Vector2f mul(float r) {
		return new Vector2f(x * r, y * r);
	}

	public Vector2f div(Vector2f r) {
		return new Vector2f(x / r.x, y / r.y);
	}

	public Vector2f div(float r) {
		return new Vector2f(x / r, y / r);
	}

	public Vector2f abs() {
		return new Vector2f(Math.abs(x), Math.abs(y));
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public float lengthSq() {
		return x * x + y * y;
	}

	public float max() {
		return Math.max(x, y);
	}

	public float min() {
		return Math.min(x, y);
	}

	public float dot(Vector2f r) {
		return x * r.x + y * r.y;
	}

	public float cross(Vector2f r) {
		return x * r.y - y * r.x;
	}

	public Vector2f lerp(Vector2f dest, float lerpFactor) {
		return dest.sub(this).mul(lerpFactor).add(this);
	}

	public Vector2f normalized() {
		float length = length();

		return new Vector2f(x / length, y / length);
	}

	public void normalize() {
		float length = length();

		x /= length;
		y /= length;
	}

	public Vector2f Rotate(float angle) {
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);

		return new Vector2f((float) (x * cos - y * sin), (float) (x * sin + y * cos));
	}

	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	public boolean equals(Vector2f r) {
		if (r == null)
			return false;

		return x == r.x && y == r.y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void set(Vector2f other) {
		set(other.x, other.y);
	}

    public JSONObject serialize(){
        JSONObject obj = new JSONObject();

        try {
            obj.put("x", x);
            obj.put("y", y);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public Vector2f deserialize(String str){
        try {
            JSONObject obj = new JSONObject(str);
            x = (float)obj.getDouble("x");
            y = (float)obj.getDouble("y");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }
}
