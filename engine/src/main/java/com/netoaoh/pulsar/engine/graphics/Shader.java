/**
 * Project: PulsarGameEngine
 * Filename: Shader.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics;

import com.netoaoh.pulsar.engine.components.Camera;
import com.netoaoh.pulsar.engine.core.IEngine;
import com.netoaoh.pulsar.engine.core.ResourceManager;
import com.netoaoh.pulsar.engine.core.resourcemanagement.GLSLUniformStruct;
import com.netoaoh.pulsar.engine.core.resourcemanagement.ShaderResource;
import com.netoaoh.pulsar.engine.math.Color;
import com.netoaoh.pulsar.engine.math.Matrix4f;
import com.netoaoh.pulsar.engine.math.Vector2f;
import com.netoaoh.pulsar.engine.math.Vector3f;
import com.netoaoh.pulsar.engine.utils.BufferUtils;
import com.netoaoh.pulsar.engine.utils.MappedValues;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

	private ShaderResource shaderResource;
	private ArrayList<GLSLUniformStruct> uniformStruct;

	public Shader(String filename) {
		shaderResource = ResourceManager.loadShader(filename + ".glsl");
		uniformStruct = shaderResource.getUniformStruct();
	}

	public void enable() {
		glUseProgram(shaderResource.getProgram());
	}

	public void disable() {
		glUseProgram(0);
	}

	public void updateUniforms(Material material){

		for(int i = 0; i < uniformStruct.size(); i++){
			String name = uniformStruct.get(i).name;
			String type = uniformStruct.get(i).type;

			if(name.startsWith("R_")){
				switch(type){
					case "vec2":
						setUniform(name, ((MappedValues)IEngine.getRenderingEngine()).getVector2f(name.substring(2, name.length())));
						break;
					case "vec3":
						setUniform(name, ((MappedValues)IEngine.getRenderingEngine()).getVector3f(name.substring(2, name.length())));
						break;
					case "vec4":
						setUniform(name, ((MappedValues)IEngine.getRenderingEngine()).getColor(name.substring(2, name.length())));
						break;
					case "float":
						setUniform(name, ((MappedValues)IEngine.getRenderingEngine()).getFloat(name.substring(2, name.length())));
						break;
					case "int":
						setUniform(name, ((MappedValues)IEngine.getRenderingEngine()).getInteger(name.substring(2, name.length())));
						break;
					default:
						//TODO: Log this
						System.out.println("Uniform '" + name.substring(2, name.length()) + "' não encontrada na Rendering Engine");
						break;
				}
			} else if(name.startsWith("C_")){

			} else if(name.startsWith("ml_")){

			} else {
				if(name.matches("texCoordOffset")){
					setUniform(name, material.calculateTextureOffset(0));
				} else if(name.matches("pr_matrix")){
					setUniform(name, Camera.mainCamera.getViewProjection());
				} else {
					switch(type){
						case "vec2":
							setUniform(name, material.getVector2f(name));
							break;
						case "vec3":
							setUniform(name, material.getVector3f(name));
							break;
						case "vec4":
							setUniform(name, material.getColor(name));
							break;
						case "float":
							setUniform(name, material.getFloat(name));
							break;
						case "int":
							setUniform(name, material.getInteger(name));
							break;
						default:
							//TODO: Log this
							//System.out.println("Uniform '" + name + "' não encontrada em Material");
							break;
					}
				}
			}

    		/*switch(uniformStruct.get(i).name){
	    		case "pr_matrix":
	    			setUniform("pr_matrix", Camera.mainCamera.getViewProjection());
	    			break;
	    		case "ml_matrix":
	    			break;
	    		case "numberOfRows":
	    			setUniform("numberOfRows", material.getTexture("diffuse").getNumberOfRows());
	    			break;
	    		case "texCoordOffset":
	    			setUniform("texCoordOffset", material.calculateTextureOffset(0));
	    			break;
	    		case "materialColor":
	    			setUniform("materialColor", material.getColor());
	    			break;
	    		case "ambientColor":
	    			setUniform("ambientColor", RenderingEngine.getInstance().getColor("ambientColor"));
	    			break;
	    		case "ambientIntensity":
	    			setUniform("ambientIntensity", RenderingEngine.getInstance().getFloat("ambientIntensity"));
	    			break;
    			default:
    				break;
    		}*/
		}
	}

	public void setUniform(String uniformName, int value) {
		glUniform1i(shaderResource.getUniformLocation(uniformName), value);
	}

	public void setUniform(String uniformName, float value) {
		glUniform1f(shaderResource.getUniformLocation(uniformName), value);
	}

	public void setUniform(String uniformName, Vector2f vector) {
		glUniform2f(shaderResource.getUniformLocation(uniformName), vector.getX(), vector.getY());
	}

	public void setUniform(String uniformName, Vector3f vector) {
		glUniform3f(shaderResource.getUniformLocation(uniformName), vector.getX(), vector.getY(), vector.getZ());
	}

	public void setUniform(String uniformName, Color color) {
		glUniform4f(shaderResource.getUniformLocation(uniformName), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}

	public void setUniform(String uniformName, Matrix4f matrix) {
		glUniformMatrix4(shaderResource.getUniformLocation(uniformName), true, BufferUtils.createFlippedBuffer(matrix));
	}
}