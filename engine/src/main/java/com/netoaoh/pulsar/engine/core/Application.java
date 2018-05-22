/**
 * Project: PulsarGameEngine
 * Filename: Application.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core;

public class Application {

	private static String rootPath /*= "/Users/Neto/Desenvolvimento/Projetos/Java/pulsar-game-engine/resources/"*/;

	public static String resourcePath(String filename){
		return rootPath + filename;
	}
	public static void setResourcePath(String path){
        rootPath = path;
    }
}