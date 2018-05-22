/**
 * Project: PulsarGameEngine
 * Filename: Log.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core;

public class Log {

	private static Log instance = new Log();

	private Log() {

	}

	public void initialize() {

	}

	public void shutdown() {

	}

	public void info(String msg) {
		System.out.println("Info: " + msg);
	}

	public void warning(String msg) {
		System.out.println("Warning: " + msg);
	}

	public void error(String msg) {
		System.out.println("Error: " + msg);
	}

	public void fatalError(ErrorCode errorCode, String msg) {
		System.out.println("Fatal Error - Tipo: " + errorCode.toString() + ". " + msg);
		System.exit(errorCode.getErrorCode());
	}

	public void fatalError(ErrorCode errorCode, String msg, String exception) {
		fatalError(errorCode, msg + " Exceção: " + exception);
	}

	public static Log getInstance() {
		return instance;
	}
}