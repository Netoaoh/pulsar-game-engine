/**
 * Project: PulsarGameEngine
 * Filename: Transform.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.components;

import com.netoaoh.pulsar.engine.math.Matrix4f;
import com.netoaoh.pulsar.engine.math.Quaternion;
import com.netoaoh.pulsar.engine.math.Vector3f;
import org.json.JSONException;
import org.json.JSONObject;

public class Transform {

	private Transform parent;
	private Matrix4f parentMatrix;

	private Vector3f position;
	private Quaternion rotation;
	private Vector3f scale;

	private Vector3f oldPos;
	private Quaternion oldRot;
	private Vector3f oldScale;

	public Transform(){
		position = new Vector3f(0,0,0);
		rotation = new Quaternion(0,0,0,1);
		scale = new Vector3f(1,1,1);

		parentMatrix = new Matrix4f().initIdentity();
	}

	public void update(){
		if(oldPos != null) {
			oldPos.set(position);
			oldRot.set(rotation);
			oldScale.set(scale);
		} else {
			oldPos = new Vector3f(0,0,0).set(position).add(1.0f);
			oldRot = new Quaternion(0,0,0,0).set(rotation).mul(0.5f);
			oldScale = new Vector3f(0,0,0).set(scale).add(1.0f);
		}
	}

	public void translate(Vector3f vector){
		position = position.add(vector);
	}

	public void translate(float x, float y, float z){
		position = position.add(new Vector3f(x, y, z));
	}

	public void rotate(Vector3f axis, float angle){

		rotation = new Quaternion(axis, angle).mul(rotation).normalized();
	}

	public void lookAt(Vector3f point, Vector3f up){
		rotation = getLookAtRotation(point, up);
	}

	public Quaternion getLookAtRotation(Vector3f point, Vector3f up){
		return new Quaternion(new Matrix4f().initRotation(point.sub(position).normalized(), up));
	}

	public boolean hasChanged(){
		if(parent != null && parent.hasChanged())
			return true;

		if(!position.equals(oldPos))
			return true;

		if(!rotation.equals(oldRot))
			return true;

		if(!scale.equals(oldScale))
			return true;

		return false;
	}

	public Matrix4f getTransformation(){
		Matrix4f translationMatrix = new Matrix4f().initTranslation(position.getX(), position.getY(), position.getZ());
		Matrix4f rotationMatrix = rotation.toRotationMatrix();
		Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());

		return getParentMatrix().mul(translationMatrix.mul(rotationMatrix.mul(scaleMatrix)));
	}

	private Matrix4f getParentMatrix(){
		if(parent != null && parent.hasChanged())
			parentMatrix = parent.getTransformation();

		return parentMatrix;
	}

	public void setParent(Transform parent){
		this.parent = parent;
	}

	public Vector3f getTransformedPos(){
		return getParentMatrix().transform(position);
	}

	public Quaternion getTransformedRot(){
		Quaternion parentRotation = new Quaternion(0,0,0,1);

		if(parent != null)
			parentRotation = parent.getTransformedRot();

		return parentRotation.mul(rotation);
	}

	public Vector3f getPos(){
		return position;
	}

	public void setPos(Vector3f pos){
		if(pos != null)
			this.position = pos;
	}

	public Quaternion getRot(){
		return rotation;
	}

	public void setRot(Quaternion rotation){
		if(rotation != null)
			this.rotation = rotation;
	}

	public Vector3f getScale(){
		return scale;
	}

	public void setScale(Vector3f scale){
		if(scale != null)
			this.scale = scale;
	}

	public JSONObject serialize(){
        JSONObject obj = new JSONObject();

        try{
            obj.put("position", position.serialize());
            obj.put("rotation", rotation.serialize());
            obj.put("scale", scale.serialize());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public Transform deserialize(String str){
        try {
            JSONObject obj = new JSONObject(str);
            position = new Vector3f(0,0,0).deserialize(obj.getString("position"));
            rotation = new Quaternion(0,0,0,1).deserialize(obj.getString("rotation"));
            scale = new Vector3f(0,0,0).deserialize(obj.getString("scale"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }
}
