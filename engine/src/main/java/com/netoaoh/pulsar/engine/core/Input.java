/**
 * Project: PulsarGameEngine
 * Filename: Input.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core;

import com.netoaoh.pulsar.engine.math.Vector2f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {

	public static final int NUM_KEYCODES = 256;
	public static final int NUM_MOUSEBUTTONS = 5;

	private static boolean[] m_lastKeys;
	private static boolean[] m_lastMouse;

	public static void initialize(){
		m_lastKeys = new boolean[NUM_KEYCODES];
		m_lastMouse = new boolean[NUM_MOUSEBUTTONS];
	}

	public static void update(){
		for(int i = 0; i < NUM_KEYCODES; i++)
			m_lastKeys[i] = getKey(i);

		for(int i = 0; i < NUM_MOUSEBUTTONS; i++)
			m_lastMouse[i] = getMouse(i);
	}

	public static boolean getKey(int keyCode){
		return Keyboard.isKeyDown(keyCode);
	}

	public static boolean getKeyDown(int keyCode){
		return getKey(keyCode) && !m_lastKeys[keyCode];
	}

	public static boolean getKeyUp(int keyCode){
		return !getKey(keyCode) && m_lastKeys[keyCode];
	}

	public static boolean getMouse(int mouseButton){
		return Mouse.isButtonDown(mouseButton);
	}

	public static boolean getMouseDown(int mouseButton){
		return getMouse(mouseButton) && !m_lastMouse[mouseButton];
	}

	public static boolean getMouseUp(int mouseButton){
		return !getMouse(mouseButton) && m_lastMouse[mouseButton];
	}

	public static Vector2f getMousePosition(){
		return new Vector2f(Mouse.getX(), Mouse.getY());
	}

	public static void setMousePosition(Vector2f pos){
		Mouse.setCursorPosition((int)pos.getX(), (int)pos.getY());
	}

	public static void setCursor(boolean enabled){
		Mouse.setGrabbed(!enabled);
	}
}