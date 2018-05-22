/**
 * Project: PulsarGameEngine
 * Filename: Window.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.graphics;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import java.awt.*;

public class Window {

	private static Window instance = new Window();

	private Window(){

	}

	public void create(int width, int height, String title, boolean fullscreen){
		if(width <= 1 || height <= 1 || title.isEmpty())
			return;

		Display.setTitle(title);

		try {
			PixelFormat pixelFormat = new PixelFormat();
			//TODO: verificar versão opengl para criar contexto
			ContextAttribs contextAtrributes = new ContextAttribs(3, 2)
					.withForwardCompatible(true)
					.withProfileCore(true);

			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setFullscreen(fullscreen);
			Display.setVSyncEnabled(true);
			Display.create(pixelFormat, contextAtrributes);
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void create(Canvas canvas){
        //if(canvas.getWidth() <= 1 || canvas.getHeight() <= 1)
          //  return;

        try {
            PixelFormat pixelFormat = new PixelFormat();
            //TODO: verificar versão opengl para criar contexto
            ContextAttribs contextAtrributes = new ContextAttribs(3, 2)
                    .withForwardCompatible(true)
                    .withProfileCore(true);
            Display.setParent(canvas);
            Display.setResizable(true);
            Display.setDisplayMode(new DisplayMode(300, 300));
            Display.setVSyncEnabled(true);
            Display.create(pixelFormat, contextAtrributes);
            Keyboard.create();
            Mouse.create();
        } catch (LWJGLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setParent(Canvas canvas){
        try {
            Display.setParent(canvas);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

	public void swapBuffers(){
		Display.update();
	}

	public void destroy(){
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}

	/*
	 * Getters
	 */
	public int getWidth() {
		return Display.getWidth();
	}

	public int getHeight() {
		return Display.getHeight();
	}

	public String getTitle() {
		return Display.getTitle();
	}

	public boolean isFullscreen() {
		return Display.isFullscreen();
	}

	public boolean isCloseRequested() {
		return Display.isCloseRequested();
	}

	public void setWidth(int width){
		if(width <= 1)
			return;

		try {
			Display.setDisplayMode(new DisplayMode(width, getHeight()));
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setHeight(int height){
		if(height <= 1)
			return;

		try {
			Display.setDisplayMode(new DisplayMode(getWidth(), height));
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setTitle(String title){
		if(title.isEmpty())
			return;

		Display.setTitle(title);
	}

	public void setFullscreen(boolean fullscreen){
		try {
			Display.setFullscreen(fullscreen);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setPosition(int x, int y){
		Display.setLocation(x, y);
	}

	public static Window getInstance() {
		return instance;
	}
}
