/**
 * Project: PulsarGameEngine
 * Filename: FrameBuffer.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics;

import com.netoaoh.pulsar.engine.core.ErrorCode;
import com.netoaoh.pulsar.engine.core.Log;
import com.netoaoh.pulsar.engine.graphics.buffers.IndexBuffer;
import com.netoaoh.pulsar.engine.graphics.buffers.VertexArray;
import com.netoaoh.pulsar.engine.graphics.buffers.VertexBuffer;
import com.netoaoh.pulsar.engine.math.Vector2f;
import com.netoaoh.pulsar.engine.math.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class FrameBuffer {

	private int framebuffer;
	private int renderBuffer;
	private int textureID;

	private int[] indices;
	private Vertex[] vertices;

	private VertexArray vertexArray;
	private IndexBuffer indexBuffer;

	public FrameBuffer(float width, float height){

		vertices = new Vertex[]{
				new Vertex(new Vector3f(-width, height, 0), new Vector2f(0, 1)),
				new Vertex(new Vector3f(-width, -height, 0), new Vector2f(0, 0)),
				new Vertex(new Vector3f(width, -height, 0), new Vector2f(1, 0)),
				new Vertex(new Vector3f(width, height, 0), new Vector2f(1, 1))
		};

		indices = new int[]{
				0, 1, 2,
				2, 3, 0
		};

		vertexArray = new VertexArray();
		vertexArray.addBuffer(new VertexBuffer(vertices));
		indexBuffer = new IndexBuffer(indices);
	}

	public void create(){
		framebuffer = glGenFramebuffers();
		glBindFramebuffer(GL_FRAMEBUFFER, framebuffer);

		textureID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureID);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, Window.getInstance().getWidth(), Window.getInstance().getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, (java.nio.ByteBuffer)null);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR );
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glBindTexture(GL_TEXTURE_2D, 0);

		glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, textureID, 0);
		// Create a renderbuffer object for depth and stencil attachment (we won't be sampling these)

		renderBuffer = glGenRenderbuffers();
		glBindRenderbuffer(GL_RENDERBUFFER, renderBuffer);
		glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH24_STENCIL8, Window.getInstance().getWidth(), Window.getInstance().getHeight()); // Use a single renderbuffer object for both a depth AND stencil buffer.
		glBindRenderbuffer(GL_RENDERBUFFER, 0);
		glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_STENCIL_ATTACHMENT, GL_RENDERBUFFER, renderBuffer); // Now actually attach it

		// Now that we actually created the framebuffer and added all attachments we want to check if it is actually complete now
		if(glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE)
			Log.getInstance().fatalError(ErrorCode.FRAMEBUFFER_CREATION_FAILURE, "Falha ao criar framebuffer.");

		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}

	public void bind(){
		glBindFramebuffer(GL_FRAMEBUFFER, framebuffer);
	}

	public void unbind(){
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}

	public void render(){
		vertexArray.bind();
		indexBuffer.bind();
		glBindTexture(GL_TEXTURE_2D, textureID);

		glDrawElements(GL_TRIANGLES, indexBuffer.getCount(), GL_UNSIGNED_INT, 0);

		glBindTexture(GL_TEXTURE_2D, 0);
		vertexArray.unbind();
		indexBuffer.unbind();
	}

	public void clear(){
		glDeleteFramebuffers(framebuffer);
	}
}
