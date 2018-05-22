/**
 * Project: PulsarGameEngine
 * Filename: Vector3f.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.math;

import org.json.JSONException;
import org.json.JSONObject;

public class Vector3f {

	public static Vector3f up = new Vector3f(0.0f, 1.0f, 0.0f);
	public static Vector3f down = new Vector3f(0.0f, -1.0f, 0.0f);
	public static Vector3f left = new Vector3f(-1.0f, 0.0f, 0.0f);
	public static Vector3f right = new Vector3f(1.0f, 0.0f, 0.0f);
	public static Vector3f forward = new Vector3f(0.0f, 0.0f, 1.0f);
	public static Vector3f backward = new Vector3f(0.0f, 0.0f, -1.0f);
	public static Vector3f zero = new Vector3f(0.0f, 0.0f, 0.0f);
	public static Vector3f unit = new Vector3f(1.0f, 1.0f, 1.0f);
	public static Vector3f inverse = new Vector3f(-1.0f, -1.0f, -1.0f);

	private float x;
	private float y;
	private float z;

	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3f add(Vector3f r) {
		return new Vector3f(x + r.x, y + r.y, z + r.z);
	}

	public Vector3f add(float r) {
		return new Vector3f(x + r, y + r, z + r);
	}

	public Vector3f sub(Vector3f r) {
		return new Vector3f(x - r.x, y - r.y, z - r.z);
	}

	public Vector3f sub(float r) {
		return new Vector3f(x - r, y - r, z - r);
	}

	public Vector3f mul(Vector3f r) {
		return new Vector3f(x * r.x, y * r.y, z * r.z);
	}

	public Vector3f mul(float r) {
		return new Vector3f(x * r, y * r, z * r);
	}

	public Vector3f div(Vector3f r) {
		return new Vector3f(x / r.x, y / r.y, z / r.z);
	}

	public Vector3f div(float r) {
		return new Vector3f(x / r, y / r, z / r);
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public float lengthSq() {
		return x * x + y * y + z * z;
	}

	public float max() {
		return Math.max(x, Math.max(y, z));
	}

	public float min() {
		return Math.min(x, Math.min(y, z));
	}

	public float dot(Vector3f r) {
		return x * r.x + y * r.y + z * r.z;
	}

	public Vector3f abs() {
		return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
	}

	public Vector3f lerp(Vector3f dest, float lerpFactor) {
		return dest.sub(this).mul(lerpFactor).add(this);
	}

	public Vector3f cross(Vector3f r) {
		float x_ = y * r.z - z * r.y;
		float y_ = z * r.x - x * r.z;
		float z_ = x * r.y - y * r.x;

		return new Vector3f(x_, y_, z_);
	}

	public Vector3f normalized() {
		float length = length();

		return new Vector3f(x / length, y / length, z / length);
	}

	public void normalize() {
		float length = length();

		x /= length;
		y /= length;
		z /= length;
	}

	public Vector3f rotate(Vector3f axis, float angle) {
		float sinAngle = (float) Math.sin(-angle);
		float cosAngle = (float) Math.cos(-angle);

		return this.cross(axis.mul(sinAngle)).add(           //Rotation on local X
				(this.mul(cosAngle)).add(                     //Rotation on local Z
						axis.mul(this.dot(axis.mul(1 - cosAngle))))); //Rotation on local Y
	}

	public Vector3f rotate(Quaternion rotation) {
		Quaternion conjugate = rotation.conjugate();

		Quaternion w = rotation.mul(this).mul(conjugate);

		return new Vector3f(w.getX(), w.getY(), w.getZ());
	}

	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}

	public boolean equals(Vector3f r) {
		if (r == null)
			return false;

		return x == r.x && y == r.y && z == r.z;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public Vector2f getXY() {
		return new Vector2f(x, y);
	}

	public Vector2f getYZ() {
		return new Vector2f(y, z);
	}

	public Vector2f getZX() {
		return new Vector2f(z, x);
	}

	public Vector2f getYX() {
		return new Vector2f(y, x);
	}

	public Vector2f getZY() {
		return new Vector2f(z, y);
	}

	public Vector2f getXZ() {
		return new Vector2f(x, z);
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public Vector3f set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public Vector3f set(Vector3f other) {
		set(other.x, other.y, other.z);
		return this;
	}

    public JSONObject serialize(){
        JSONObject obj = new JSONObject();

        try {
            obj.put("x", x);
            obj.put("y", y);
            obj.put("z", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public Vector3f deserialize(String str){
        try {
            JSONObject obj = new JSONObject(str);
            x = (float)obj.getDouble("x");
            y = (float)obj.getDouble("y");
            z = (float)obj.getDouble("z");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }
}
