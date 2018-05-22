/**
 * Project: PulsarGameEngine
 * Filename: ErrorCode.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core;

public enum ErrorCode {

	UNKNOWN(-1, "Unknown"),
	NO_ERROR(0, "No Error"),
	RUNTIME_ERROR(1, "Runtime Error"),
	SHADER_CREATION_FAILURE(2, "Shader Creation Failed"),
	SHADER_COMPILE_ERROR(3, "Shader Compile Error"),
	SHADER_LINK_ERROR(4, "Shader Link Error"),
	SHADER_VALIDATE_ERROR(5, "Shader Validation Error"),
	INVALID_UNIFORM_LOCATION(6, "Invalid Uniform Location"),
	VERTEX_BUFFER_OBJECT_CREATION_FAILURE(7, "Vertex Buffer Object Creation Failure"),
	INDEX_BUFFER_OBJECT_CREATION_FAILURE(8, "Vertex Buffer Object Creation Failure"),
	VERTEX_ARRAY_OBJECT_CREATION_FAILURE(9, "Vertex Array Object Creation Failure"),
	SCRIPT_INVALID_METHOD(10, "Script Invalid Method"),
	SCRIPT_CALL_FAILURE(11, "Script Call Failure"),
	FRAMEBUFFER_CREATION_FAILURE(12, "FrameBuffer Creation Failure"),
	MAX_ERRORS(15, "Max Errors");

	private int error;
	private String name;

	private ErrorCode(int error, String name) {
		this.error = error;
		this.name = name;
	}

	public int getErrorCode()	{	return error;	}

	public String toString()	{	return name;	}
}