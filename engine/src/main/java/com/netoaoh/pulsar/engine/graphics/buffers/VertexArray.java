/**
 * Project: PulsarGameEngine
 * Filename: VertexArray.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics.buffers;

import com.netoaoh.pulsar.engine.core.ErrorCode;
import com.netoaoh.pulsar.engine.core.Log;
import com.netoaoh.pulsar.engine.graphics.RenderSettings;
import com.netoaoh.pulsar.engine.graphics.Vertex;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray {

	private int vertexArray = 0;
	private ArrayList<VertexBuffer> buffers = new ArrayList<VertexBuffer>();

	public VertexArray() {
		vertexArray = glGenVertexArrays();

		if (vertexArray == 0) {
			Log.getInstance().fatalError(ErrorCode.VERTEX_ARRAY_OBJECT_CREATION_FAILURE, "Falha ao criar Vertex Array Buffer.");
		}
	}

	public void clear() {
		glDeleteVertexArrays(vertexArray);

		for (VertexBuffer buffer : buffers) {
			buffer.clear();
		}

		buffers.clear();
	}

	public void removeBuffers(){
		for (VertexBuffer buffer : buffers) {
			buffer.clear();
		}

		buffers.clear();
	}

	public void addBuffer(VertexBuffer buffer) {
		bind();
		buffer.bind();

		//glEnableVertexAttribArray(0);
		//glEnableVertexAttribArray(1);

		glVertexAttribPointer(RenderSettings.SHADER_VERTEX_INDEX, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		glVertexAttribPointer(RenderSettings.SHADER_TEXCOORD_INDEX, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
		glVertexAttribPointer(RenderSettings.SHADER_NORMAL_INDEX, 3, GL_FLOAT, false, Vertex.SIZE * 4, 20);

		//glDisableVertexAttribArray(1);
		//glDisableVertexAttribArray(0);

		buffer.unbind();
		unbind();

		buffers.add(buffer);
	}

	public void bind() {
		glBindVertexArray(vertexArray);
		glEnableVertexAttribArray(RenderSettings.SHADER_VERTEX_INDEX);
		glEnableVertexAttribArray(RenderSettings.SHADER_TEXCOORD_INDEX);
		glEnableVertexAttribArray(RenderSettings.SHADER_NORMAL_INDEX);
	}

	public void unbind() {
		glDisableVertexAttribArray(RenderSettings.SHADER_VERTEX_INDEX);
		glDisableVertexAttribArray(RenderSettings.SHADER_TEXCOORD_INDEX);
		glDisableVertexAttribArray(RenderSettings.SHADER_NORMAL_INDEX);
		glBindVertexArray(0);
	}
}
