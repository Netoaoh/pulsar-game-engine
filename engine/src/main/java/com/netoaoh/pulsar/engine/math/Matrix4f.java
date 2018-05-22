/**
 * Project: PulsarGameEngine
 * Filename: Matrix4f.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.math;

import org.json.JSONException;
import org.json.JSONObject;

public class Matrix4f {

	private float[][] elements;

	public Matrix4f() {
		elements = new float[4][4];
	}

	public static Matrix4f identity() {
		Matrix4f res = new Matrix4f();
		return res.initIdentity();
	}

	public static Matrix4f translate(float x, float y, float z) {
		Matrix4f res = new Matrix4f();
		return res.initTranslation(x, y, z);
	}

	public static Matrix4f translate(Vector3f vector) {
		return translate(vector.getX(), vector.getY(), vector.getZ());
	}

	public static Matrix4f rotate(float x, float y, float z) {
		Matrix4f res = new Matrix4f();
		return res.initRotation(x, y, z);
	}

	public static Matrix4f rotate(Vector3f vector) {
		return rotate(vector.getX(), vector.getY(), vector.getZ());
	}

	public static Matrix4f rotate(Vector3f forward, Vector3f up) {
		Matrix4f res = new Matrix4f();
		return res.initRotation(forward, up);
	}

	public static Matrix4f rotate(Vector3f forward, Vector3f up, Vector3f right) {
		Matrix4f res = new Matrix4f();
		return res.initRotation(forward, up, right);
	}

	public static Matrix4f scale(Vector3f vector) {
		return scale(vector.getX(), vector.getY(), vector.getZ());
	}

	public static Matrix4f scale(float x, float y, float z) {
		Matrix4f res = new Matrix4f();
		return res.initScale(x, y, z);
	}

	public static Matrix4f perspective(float fov, float aspectRatio, float zNear, float zFar) {
		Matrix4f res = new Matrix4f();
		return res.initPerspective(fov, aspectRatio, zNear, zFar);
	}

	public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {
		Matrix4f res = new Matrix4f();
		return res.initOrthographic(left, right, bottom, top, near, far);
	}

	public Matrix4f initIdentity() {
		elements[0][0] = 1;	elements[0][1] = 0;	elements[0][2] = 0;	elements[0][3] = 0;
		elements[1][0] = 0;	elements[1][1] = 1;	elements[1][2] = 0;	elements[1][3] = 0;
		elements[2][0] = 0;	elements[2][1] = 0;	elements[2][2] = 1;	elements[2][3] = 0;
		elements[3][0] = 0;	elements[3][1] = 0;	elements[3][2] = 0;	elements[3][3] = 1;

		return this;
	}

	public Matrix4f initTranslation(float x, float y, float z) {
		elements[0][0] = 1;	elements[0][1] = 0;	elements[0][2] = 0;	elements[0][3] = x;
		elements[1][0] = 0;	elements[1][1] = 1;	elements[1][2] = 0;	elements[1][3] = y;
		elements[2][0] = 0;	elements[2][1] = 0;	elements[2][2] = 1;	elements[2][3] = z;
		elements[3][0] = 0;	elements[3][1] = 0;	elements[3][2] = 0;	elements[3][3] = 1;

		return this;
	}

	public Matrix4f initTranslation(Vector3f vector) {
		return initTranslation(vector.getX(), vector.getY(), vector.getZ());
	}

	public Matrix4f initRotation(float x, float y, float z) {
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();

		x = (float)Math.toRadians(x);
		y = (float)Math.toRadians(y);
		z = (float)Math.toRadians(z);

		rz.elements[0][0] = (float)Math.cos(z);rz.elements[0][1] = -(float)Math.sin(z);rz.elements[0][2] = 0;				rz.elements[0][3] = 0;
		rz.elements[1][0] = (float)Math.sin(z);rz.elements[1][1] = (float)Math.cos(z);rz.elements[1][2] = 0;					rz.elements[1][3] = 0;
		rz.elements[2][0] = 0;					rz.elements[2][1] = 0;					rz.elements[2][2] = 1;					rz.elements[2][3] = 0;
		rz.elements[3][0] = 0;					rz.elements[3][1] = 0;					rz.elements[3][2] = 0;					rz.elements[3][3] = 1;

		rx.elements[0][0] = 1;					rx.elements[0][1] = 0;					rx.elements[0][2] = 0;					rx.elements[0][3] = 0;
		rx.elements[1][0] = 0;					rx.elements[1][1] = (float)Math.cos(x);rx.elements[1][2] = -(float)Math.sin(x);rx.elements[1][3] = 0;
		rx.elements[2][0] = 0;					rx.elements[2][1] = (float)Math.sin(x);rx.elements[2][2] = (float)Math.cos(x);rx.elements[2][3] = 0;
		rx.elements[3][0] = 0;					rx.elements[3][1] = 0;					rx.elements[3][2] = 0;					rx.elements[3][3] = 1;

		ry.elements[0][0] = (float)Math.cos(y);ry.elements[0][1] = 0;					ry.elements[0][2] = -(float)Math.sin(y);ry.elements[0][3] = 0;
		ry.elements[1][0] = 0;					ry.elements[1][1] = 1;					ry.elements[1][2] = 0;					ry.elements[1][3] = 0;
		ry.elements[2][0] = (float)Math.sin(y);ry.elements[2][1] = 0;					ry.elements[2][2] = (float)Math.cos(y);ry.elements[2][3] = 0;
		ry.elements[3][0] = 0;					ry.elements[3][1] = 0;					ry.elements[3][2] = 0;					ry.elements[3][3] = 1;

		elements = rz.mul(ry.mul(rx)).getM();

		return this;
	}

	public Matrix4f initRotation(Vector3f vector) {
		return initRotation(vector.getX(), vector.getY(), vector.getZ());
	}

	public Matrix4f initRotation(Vector3f forward, Vector3f up) {
		Vector3f f = forward.normalized();

		Vector3f r = up.normalized();
		r = r.cross(f);

		Vector3f u = f.cross(r);

		return initRotation(f, u, r);
	}

	public Matrix4f initRotation(Vector3f forward, Vector3f up, Vector3f right) {
		Vector3f f = forward;
		Vector3f r = right;
		Vector3f u = up;

		elements[0][0] = r.getX();	elements[0][1] = r.getY();	elements[0][2] = r.getZ();	elements[0][3] = 0;
		elements[1][0] = u.getX();	elements[1][1] = u.getY();	elements[1][2] = u.getZ();	elements[1][3] = 0;
		elements[2][0] = f.getX();	elements[2][1] = f.getY();	elements[2][2] = f.getZ();	elements[2][3] = 0;
		elements[3][0] = 0;			elements[3][1] = 0;			elements[3][2] = 0;			elements[3][3] = 1;

		return this;
	}

	public Matrix4f initScale(float x, float y, float z) {
		elements[0][0] = x;	elements[0][1] = 0;	elements[0][2] = 0;	elements[0][3] = 0;
		elements[1][0] = 0;	elements[1][1] = y;	elements[1][2] = 0;	elements[1][3] = 0;
		elements[2][0] = 0;	elements[2][1] = 0;	elements[2][2] = z;	elements[2][3] = 0;
		elements[3][0] = 0;	elements[3][1] = 0;	elements[3][2] = 0;	elements[3][3] = 1;

		return this;
	}

	public Matrix4f initScale(Vector3f vector) {
		return initScale(vector.getX(), vector.getY(), vector.getZ());
	}

	public Matrix4f initPerspective(float fov, float aspectRatio, float zNear, float zFar) {
		float tanHalfFOV = (float)Math.tan(fov / 2);
		float zRange = zNear - zFar;

		elements[0][0] = 1.0f / (tanHalfFOV * aspectRatio);	elements[0][1] = 0;					elements[0][2] = 0;						elements[0][3] = 0;
		elements[1][0] = 0;									elements[1][1] = 1.0f / tanHalfFOV;	elements[1][2] = 0;						elements[1][3] = 0;
		elements[2][0] = 0;									elements[2][1] = 0;					elements[2][2] = (-zNear -zFar)/zRange;	elements[2][3] = 2 * zFar * zNear / zRange;
		elements[3][0] = 0;									elements[3][1] = 0;					elements[3][2] = 1;						elements[3][3] = 0;


		return this;
	}

	public Matrix4f initOrthographic(float left, float right, float bottom, float top, float near, float far) {
		float width = right - left;
		float height = top - bottom;
		float depth = far - near;

		elements[0][0] = 2/width;elements[0][1] = 0;	elements[0][2] = 0;	elements[0][3] = -(right + left)/width;
		elements[1][0] = 0;	elements[1][1] = 2/height;elements[1][2] = 0;	elements[1][3] = -(top + bottom)/height;
		elements[2][0] = 0;	elements[2][1] = 0;	elements[2][2] = -2/depth;elements[2][3] = -(far + near)/depth;
		elements[3][0] = 0;	elements[3][1] = 0;	elements[3][2] = 0;	elements[3][3] = 1;

		return this;
	}

	public Vector3f transform(Vector3f r) {
		return new Vector3f(elements[0][0] * r.getX() + elements[0][1] * r.getY() + elements[0][2] * r.getZ() + elements[0][3],
				elements[1][0] * r.getX() + elements[1][1] * r.getY() + elements[1][2] * r.getZ() + elements[1][3],
				elements[2][0] * r.getX() + elements[2][1] * r.getY() + elements[2][2] * r.getZ() + elements[2][3]);
	}

	public Matrix4f mul(Matrix4f r) {
		Matrix4f res = new Matrix4f();

		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				res.set(i, j, elements[i][0] * r.get(0, j) +
						elements[i][1] * r.get(1, j) +
						elements[i][2] * r.get(2, j) +
						elements[i][3] * r.get(3, j));
			}
		}

		return res;
	}

	public String toString() {

		String res;

		res = 	"|" + Float.toString(Math.round(elements[0][0])) + " " + Float.toString(Math.round(elements[0][1])) + " " + Float.toString(Math.round(elements[0][2])) + " " + Float.toString(Math.round(elements[0][3])) + "|\n" +
				"|" + Float.toString(Math.round(elements[1][0])) + " " + Float.toString(Math.round(elements[1][1])) + " " + Float.toString(Math.round(elements[1][2])) + " " + Float.toString(Math.round(elements[1][3])) + "|\n" +
				"|" + Float.toString(Math.round(elements[2][0])) + " " + Float.toString(Math.round(elements[2][1])) + " " + Float.toString(Math.round(elements[2][2])) + " " + Float.toString(Math.round(elements[2][3])) + "|\n" +
				"|" + Float.toString(Math.round(elements[3][0])) + " " + Float.toString(Math.round(elements[3][1])) + " " + Float.toString(Math.round(elements[3][2])) + " " + Float.toString(Math.round(elements[3][3])) + "|\n";

		return res;
	}

	public float get(int x, int y) {
		return elements[x][y];
	}

	public float[][] getM() {
		float[][] res = new float[4][4];

		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				res[i][j] = elements[i][j];

		return res;
	}

	public void set(int x, int y, float value) {
		elements[x][y] = value;
	}

	public void setM(float[][] m) {
		this.elements = m;
	}

    public JSONObject serialize(){
        JSONObject obj = new JSONObject();

        try {
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++) {
                    obj.put("element" + Integer.toString(i) + Integer.toString(j), elements[i][j]);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public Matrix4f deserialize(String str){
        try {
            JSONObject obj = new JSONObject(str);

            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++) {
                    elements[i][j] = (float)obj.getDouble("element" + Integer.toString(i) + Integer.toString(j));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }
}
