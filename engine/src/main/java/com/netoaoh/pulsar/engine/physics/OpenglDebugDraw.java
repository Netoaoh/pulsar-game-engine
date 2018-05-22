/**
 * Project: PulsarGameEngine
 * Filename: OpenglDebugDraw.java
 * Author: Paulo Maria Neto
 * Created: 29/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 Design Coding. All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.physics;

import com.netoaoh.pulsar.engine.graphics.Vertex;
import com.netoaoh.pulsar.engine.graphics.buffers.IndexBuffer;
import com.netoaoh.pulsar.engine.graphics.buffers.VertexArray;
import com.netoaoh.pulsar.engine.graphics.buffers.VertexBuffer;
import com.netoaoh.pulsar.engine.math.Vector2f;
import com.netoaoh.pulsar.engine.math.Vector3f;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.OBBViewportTransform;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;

public class OpenglDebugDraw extends DebugDraw {

	private VertexArray vertexArray;
	private IndexBuffer indexBuffer;

	public OpenglDebugDraw(){
		super(new OBBViewportTransform());
	}

	@Override
	public void drawPoint(Vec2 vec2, float v, Color3f color3f) {
		System.out.println("Point");
	}

	@Override
	public void drawSolidPolygon(Vec2[] vec2s, int i, Color3f color3f) {
		//System.out.println(vec2s.length);

		/*ArrayList<Vertex> vertex = new ArrayList<Vertex>();
		ArrayList<Integer> indices = new ArrayList<Integer>();

		for(int j = 0; j < vec2s.length; j++)
		{
			vertex.add(new Vertex(new Vector3f(vec2s[j].x, vec2s[j].y, 0)));
		}

		for(int j = 0; j < vec2s.length; j++)
		{
			indices.add(j);
		}*/
		//TODO:Fazer o debugdraw funcionar
		Vertex[] vertices = new Vertex[]{
				new Vertex(new Vector3f(-vec2s[0].x, vec2s[0].y, 0), new Vector2f(1, 0)),
				new Vertex(new Vector3f(-vec2s[1].x, -vec2s[1].y, 0), new Vector2f(1, 1)),
				new Vertex(new Vector3f(vec2s[2].x, -vec2s[2].y, 0), new Vector2f(0, 1)),
				new Vertex(new Vector3f(vec2s[3].x, vec2s[3].y, 0), new Vector2f(0, 0))
		};

		int[] indices = new int[]{
				0, 1, 2,
				2, 3, 0
		};

		vertexArray = new VertexArray();
		indexBuffer = new IndexBuffer(indices);

		vertexArray.bind();
		indexBuffer.bind();

		glDrawElements(GL_TRIANGLES, indexBuffer.getCount(), GL_UNSIGNED_INT, 0);

		vertexArray.unbind();
		indexBuffer.unbind();
	}

	@Override
	public void drawCircle(Vec2 vec2, float v, Color3f color3f) {
		System.out.println("Circle");
	}

	@Override
	public void drawSolidCircle(Vec2 vec2, float v, Vec2 vec21, Color3f color3f) {
		System.out.println("SolidCircle");
	}

	@Override
	public void drawSegment(Vec2 vec2, Vec2 vec21, Color3f color3f) {
		System.out.println("Segment");
	}

	@Override
	public void drawTransform(Transform transform) {
		System.out.println("Transform");
	}

	@Override
	public void drawString(float v, float v1, String s, Color3f color3f) {
		System.out.println("String");
	}
}
