/**
 * Project: PulsarGameEngine
 * Filename: GLSLUniformStruct.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core.resourcemanagement;

public class GLSLUniformStruct {

	public String type;
	public String name;

	public GLSLUniformStruct(String type, String name){
		this.type = type;
		this.name = name;
	}

	public String toString(){
		return "Uniform Type: " + type + " Uniform Name: " + name;
	}
}