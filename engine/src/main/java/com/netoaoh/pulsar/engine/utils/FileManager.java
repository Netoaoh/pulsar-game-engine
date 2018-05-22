/**
 * Project: PulsarGameEngine
 * Filename: FileManager.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.utils;

import com.netoaoh.pulsar.engine.core.Application;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileManager {

	public static String loadAsString(String filePath, String includeDirective){
		StringBuilder fileSource = new StringBuilder();
		BufferedReader fileReader = null;
		final String INCLUDE_DIRECTIVE = includeDirective;

		try{
			fileReader = new BufferedReader(new FileReader(filePath));
			String line;

			while((line = fileReader.readLine()) != null){
				if(line.startsWith(INCLUDE_DIRECTIVE))
					fileSource.append(loadAsString(Application.resourcePath(line.substring(INCLUDE_DIRECTIVE.length() + 2, line.length() - 1)), includeDirective));
				else
					fileSource.append(line).append("\n");
			}

			fileReader.close();
		} catch(Exception e) {
			//TODO: Log this
			e.printStackTrace();
			System.exit(1);
		}

		return fileSource.toString();
	}

	public static String loadAsString(String filePath){
		StringBuilder fileSource = new StringBuilder();
		BufferedReader fileReader = null;

		try{
			fileReader = new BufferedReader(new FileReader(filePath));
			String line;

			while((line = fileReader.readLine()) != null){
				fileSource.append(line).append("\n");
			}

			fileReader.close();
		} catch(Exception e) {
			//TODO: Log this
			e.printStackTrace();
			System.exit(1);
		}

		return fileSource.toString();
	}
}
