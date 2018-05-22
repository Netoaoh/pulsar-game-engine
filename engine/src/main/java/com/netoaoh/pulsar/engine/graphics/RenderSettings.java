/**
 * Project: PulsarGameEngine
 * Filename: RenderSettings.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics;

public class RenderSettings {

	public static final int RENDERER_MAX_SPRITES = 10000;
	public static final int RENDERER_VERTEX_SIZE = Vertex.SIZE;
	public static final int RENDERER_SPRITE_SIZE = RENDERER_VERTEX_SIZE * 4;
	public static final int RENDERER_BUFFER_SIZE = RENDERER_SPRITE_SIZE * RENDERER_MAX_SPRITES;
	public static final int RENDERER_INDICES_SIZE = RENDERER_MAX_SPRITES * 6;

	public static final int SHADER_VERTEX_INDEX = 0;
	public static final int SHADER_TEXCOORD_INDEX = 1;
	public static final int SHADER_NORMAL_INDEX = 2;
}