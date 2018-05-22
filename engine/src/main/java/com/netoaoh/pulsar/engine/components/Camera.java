/**
 * Project: PulsarGameEngine
 * Filename: Camera.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.components;

import com.netoaoh.pulsar.engine.core.ResourceManager;
import com.netoaoh.pulsar.engine.math.Matrix4f;
import com.netoaoh.pulsar.engine.math.Vector3f;
import com.netoaoh.pulsar.engine.script.JSScript;
import org.json.JSONException;
import org.json.JSONObject;

public class Camera extends Component {

	private Matrix4f projection;
	public static Camera mainCamera = null;

	public Camera(Matrix4f projection) {
		super();
		this.name = getClass().getSimpleName().toString();
		this.projection = projection;
		Camera.mainCamera = this;
	}

	public Matrix4f getViewProjection() {
		Matrix4f cameraRotation = gameObject.transform.getTransformedRot().conjugate().toRotationMatrix();
		Vector3f cameraPos = gameObject.transform.getTransformedPos().mul(-1);

		Matrix4f cameraTranslation = new Matrix4f().initTranslation(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ());

		return projection.mul(cameraRotation.mul(cameraTranslation));
	}

	public void setProjection(Matrix4f projection){
		if(projection == null)
			return;

		this.projection = projection;
	}

    public JSONObject serialize(){
        JSONObject obj = new JSONObject();

        try{
            obj.put("projection", projection.serialize());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public Camera deserialize(String str){
        try {
            JSONObject obj = new JSONObject(str);

            name = obj.getString("name");
            projection = new Matrix4f().deserialize(obj.getString("projection"));
            enabled = obj.getBoolean("enabled");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }
}
