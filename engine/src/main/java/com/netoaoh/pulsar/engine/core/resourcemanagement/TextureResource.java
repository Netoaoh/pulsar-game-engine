/**
 * Project: PulsarGameEngine
 * Filename: TextureResource.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core.resourcemanagement;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class TextureResource {

	private int textureID;
	private int numberOfRows = 1;

	public TextureResource(ByteBuffer buffer, int width, int height, int textureFilter, int textureWarp) {
		textureID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, getTextureID());

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, textureWarp);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, textureWarp);

		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, textureFilter);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, textureFilter);

		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
	}

	public int getTextureID() {
		return textureID;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows){
		this.numberOfRows = numberOfRows;
	}
}