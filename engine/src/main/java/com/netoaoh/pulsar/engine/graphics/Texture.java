/**
 * Project: PulsarGameEngine
 * Filename: Texture.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics;

import com.netoaoh.pulsar.engine.core.ResourceManager;
import com.netoaoh.pulsar.engine.core.resourcemanagement.TextureResource;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

public class Texture {

	public class TextureWarp{
		public static final int ClampToBorder = GL_CLAMP_TO_BORDER;
	}

	public class TextureFilter{
		public static final int Nearest = GL_NEAREST;
		public static final int Linear = GL_LINEAR;
	}

	//private int textureID;
	//private int numberOfRows = 1;
	private TextureResource textureResource;

	public Texture(String filename) {
		textureResource = ResourceManager.loadTexture(filename);
	}

	public Texture(String filename, int numberOfRows) {
		textureResource = ResourceManager.loadTexture(filename);
		textureResource.setNumberOfRows(numberOfRows);
	}

	public void bind() {
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, textureResource.getTextureID());
	}

	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public int getTextureID(){
		return textureResource.getTextureID();
	}

	public int getNumberOfRows() {
		return textureResource.getNumberOfRows();
	}

	public void setNumberOfRows(int numberOfRows) {
		textureResource.setNumberOfRows(numberOfRows);
	}
}
