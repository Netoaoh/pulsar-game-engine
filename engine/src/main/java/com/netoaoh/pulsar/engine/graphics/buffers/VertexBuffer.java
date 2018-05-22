/**
 * Project: PulsarGameEngine
 * Filename: VertexBuffer.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics.buffers;

import com.netoaoh.pulsar.engine.core.ErrorCode;
import com.netoaoh.pulsar.engine.core.Log;
import com.netoaoh.pulsar.engine.graphics.Vertex;
import com.netoaoh.pulsar.engine.utils.BufferUtils;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL15.*;

public class VertexBuffer {

	private int buffer;

	public VertexBuffer(Vertex[] vertices) {
		buffer = glGenBuffers();

		if (buffer == 0) {
			Log.getInstance().fatalError(ErrorCode.VERTEX_BUFFER_OBJECT_CREATION_FAILURE, "Falha ao criar Vertex Buffer.");
		}

		glBindBuffer(GL_ARRAY_BUFFER, buffer);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public VertexBuffer(ArrayList<Vertex> vertices) {
		buffer = glGenBuffers();

		if (buffer == 0) {
			Log.getInstance().fatalError(ErrorCode.VERTEX_BUFFER_OBJECT_CREATION_FAILURE, "Falha ao criar Vertex Buffer.");
		}

		Vertex[] temp = new Vertex[vertices.size()];
		temp = vertices.toArray(temp);

		glBindBuffer(GL_ARRAY_BUFFER, buffer);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFlippedBuffer(temp), GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void clear() {
		glDeleteBuffers(buffer);
	}

	public void bind() {
		glBindBuffer(GL_ARRAY_BUFFER, buffer);
	}

	public void unbind() {
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
}
