/**
 * Project: PulsarGameEngine
 * Filename: ShaderResource.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core.resourcemanagement;

import com.netoaoh.pulsar.engine.core.ErrorCode;
import com.netoaoh.pulsar.engine.core.Log;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class ShaderResource {

	private int shaderID;
	private String vertexSource;
	private String fragmentSource;
	private HashMap<String, Integer> uniformsLocation;
	private ArrayList<GLSLUniformStruct> uniformStruct;

	public ShaderResource(int shaderID, String vertexSource, String fragmentSource, ArrayList<GLSLUniformStruct> uniformStruct){
		this.vertexSource = vertexSource;
		this.fragmentSource = fragmentSource;
		this.uniformStruct = uniformStruct;

		this.shaderID = shaderID;
		uniformsLocation = new HashMap<String, Integer>();
	}

	public void clear(){
		glDeleteBuffers(shaderID);
	}

	public int getProgram()	{ return shaderID; }

	public int getUniformLocation(String uniformName) {
		if (uniformsLocation.containsKey(uniformName)) {
			return uniformsLocation.get(uniformName);
		}

		int result = glGetUniformLocation(shaderID, uniformName);

		if (result == -1) {
			Log.getInstance().fatalError(ErrorCode.INVALID_UNIFORM_LOCATION, "Não foi possivel localizar a uniform '" + uniformName + "'.");
		}

		uniformsLocation.put(uniformName, result);
		return result;
	}

	public ArrayList<GLSLUniformStruct> getUniformStruct()	{	return uniformStruct;	}

	public int getShaderID() {
		return shaderID;
	}

	public String getVertexSource() {
		return vertexSource;
	}

	public String getFragmentSource() {
		return fragmentSource;
	}
}