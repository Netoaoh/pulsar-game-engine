/**
 * Project: PulsarGameEngine
 * Filename: IndexBuffer.java
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

public class IndexBuffer {

	private int buffer;
	private int count;

	public IndexBuffer(int[] data) {
		count = data.length;

		buffer = glGenBuffers();

		if (buffer == 0) {
			Log.getInstance().fatalError(ErrorCode.INDEX_BUFFER_OBJECT_CREATION_FAILURE, "Falha ao criar Index Buffer.");
		}

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buffer);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createFlippedBuffer(data), GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public IndexBuffer(ArrayList<Integer> indices) {
		int[] temp = new int[indices.size()];

		for(int i = 0; i < indices.size(); i++)
		{
			temp[i] = indices.get(i);
		}

		count = temp.length;

		buffer = glGenBuffers();

		if (buffer == 0) {
			Log.getInstance().fatalError(ErrorCode.INDEX_BUFFER_OBJECT_CREATION_FAILURE, "Falha ao criar Index Buffer.");
		}

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buffer);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createFlippedBuffer(temp), GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public void bind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buffer);
	}

	public void unbind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public int getCount() {
		return count;
	}
}
