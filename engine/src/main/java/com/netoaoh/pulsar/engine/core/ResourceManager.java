/**
 * Project: PulsarGameEngine
 * Filename: ResourceManager.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.core;

import com.netoaoh.pulsar.engine.audio.WaveData;
import com.netoaoh.pulsar.engine.core.resourcemanagement.*;
import com.netoaoh.pulsar.engine.graphics.Texture.TextureFilter;
import com.netoaoh.pulsar.engine.graphics.Texture.TextureWarp;
import com.netoaoh.pulsar.engine.graphics.Vertex;
import com.netoaoh.pulsar.engine.utils.BufferUtils;
import com.netoaoh.pulsar.engine.utils.FileManager;
import org.lwjgl.openal.AL10;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class ResourceManager {

	//TODO:criar metodo que remove os resources das listas
	public static HashMap<String, ShaderResource> shaderResources = new HashMap<String, ShaderResource>();
	public static HashMap<String, TextureResource> textureResources = new HashMap<String, TextureResource>();
	public static HashMap<String, ScriptResource> scriptResource = new HashMap<String, ScriptResource>();
	public static HashMap<String, AudioResource> audioResource = new HashMap<String, AudioResource>();

	public static AudioResource loadAudio(String filename){
		if(audioResource.containsKey(filename))
			return audioResource.get(filename);

		AudioResource newAudioResource = null;

		int bufferID = AL10.alGenBuffers();
		int sourceID = AL10.alGenSources();
		WaveData waveFile = WaveData.create(Application.resourcePath("Audio/" + filename));
		AL10.alBufferData(bufferID, waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();

		newAudioResource = new AudioResource(bufferID, sourceID);
		audioResource.put(filename, newAudioResource);

		return newAudioResource;
	}

	public static ScriptResource loadScript(String filename){
		if(scriptResource.containsKey(filename))
			return scriptResource.get(filename);

		ScriptResource newScriptResource = null;

		newScriptResource = new ScriptResource(FileManager.loadAsString(Application.resourcePath("Scripts/" + filename), "#import"));
		scriptResource.put(filename, newScriptResource);

		return newScriptResource;
	}

	public static TextureResource loadTexture(String filename){

		if(textureResources.containsKey(filename))
			return textureResources.get(filename);

		TextureResource newTextureResource = null;

		try{
			BufferedImage image = ImageIO.read(new File(Application.resourcePath("Textures/" + filename)));
			int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());

			ByteBuffer buffer = BufferUtils.createByteBuffer(image.getHeight() * image.getWidth() * 4);
			boolean hasAlpha = image.getColorModel().hasAlpha();

			for(int y = 0; y < image.getHeight(); y++){
				for(int x = 0; x < image.getWidth(); x++){
					int pixel = pixels[y * image.getWidth() + x];

					buffer.put((byte)((pixel >> 16) & 0xFF)); //Red
					buffer.put((byte)((pixel >> 8) & 0xFF));  //Green
					buffer.put((byte)((pixel) & 0xFF));		  //Blue
					if(hasAlpha)
						buffer.put((byte)((pixel >> 24) & 0xFF)); //Alpha
					else
						buffer.put((byte)(0xFF)); //Alpha
				}
			}

			buffer.flip();

			newTextureResource = new TextureResource(buffer, image.getWidth(), image.getHeight(), TextureFilter.Linear, TextureWarp.ClampToBorder);
			textureResources.put(filename, newTextureResource);


		} catch(Exception e) {
			//TODO: log this
			e.printStackTrace();
			System.exit(1);
		}

		return newTextureResource;
	}

	public static ShaderResource loadShader(String filename){

		if(shaderResources.containsKey(filename))
			return shaderResources.get(filename);

		int shaderID = glCreateProgram();

		if(shaderID == 0){
			//TODO: Log this
			System.err.println("Shader creation failed: Could not find valid memory location in constructor");
			System.exit(1);
		}

		String vertexShader = "";
		String fragmentShader = "";
		ArrayList<GLSLUniformStruct> uniformStruct = new ArrayList<GLSLUniformStruct>();
		BufferedReader fileReader = null;

		boolean vertex = false;
		boolean fragment = false;

		try{
			fileReader = new BufferedReader(new FileReader(Application.resourcePath("Shaders/" + filename)));
			String line;

			//Le a linha atual
			while((line = fileReader.readLine()) != null){
				//Elimina espaçoes na linha
				String trimLine = line.trim();
				//Verifica se a linah atual é um define
				if(trimLine.startsWith("#define")){
					//Remove a palavra chave define e verifica o tipo do shader
					//A flag do shader atual é atualizada para que seja carregado na variavel certa
					String shaderType = trimLine.substring("#define".length(), trimLine.length());
					if(shaderType.trim().matches("VERTEX_SHADER_PASS")){
						vertex = true;
						continue;
					} else if(shaderType.trim().matches("FRAGMENT_SHADER_PASS")){
						fragment = true;
						continue;
					}
				}

				//Verifica se a linha é o termino de um define e atualiza a flag para falso
				if(trimLine.startsWith("#enddef")){
					vertex = false;
					fragment = false;
					continue;
				}

				//Verifica se o bloco definido é um Vertex Shader
				if(vertex){
					//Adiciona a linha a variavel fonte
					vertexShader += line.trim() + "\n";
					//Verifica se a linha contem a definição de uma uniform
					if(trimLine.startsWith("uniform")){
						//Divide a linha em um array
						String[] splitedLine = trimLine.split(" ");
						//Remove a pontuação no final do nome da variavel
						if(splitedLine[2].endsWith(";"))
							splitedLine[2] = splitedLine[2].substring(0, splitedLine[2].length() - 1);
						//Adiciona o tipo e o name de variavel ao array
						uniformStruct.add(new GLSLUniformStruct(splitedLine[1].trim(), splitedLine[2].trim()));
					}
				} else {
					//Verifica se o bloco definido é um Fragment Shader
					if(fragment){
						//Adiciona a linha a variavel fonte
						fragmentShader += line.trim() + "\n";
						//Verifica se a linha contem a definição de uma uniform
						if(trimLine.startsWith("uniform")){
							//Divide a linha em um array
							String[] splitedLine = trimLine.split(" ");
							//Remove a pontuação no final do nome da variavel
							if(splitedLine[2].endsWith(";"))
								splitedLine[2] = splitedLine[2].substring(0, splitedLine[2].length() - 1);
							//Adiciona o tipo e o name de variavel ao array
							uniformStruct.add(new GLSLUniformStruct(splitedLine[1].trim(), splitedLine[2].trim()));
						}
					}
				}
			}

			fileReader.close();
		} catch(Exception e) {
			//TODO: Log this
			e.printStackTrace();
			System.exit(1);
		}

		//Remove a primeira e a ultima linha do arquivo fonte para retirar chaves indesejadas
		String vertSource = "#version 330 core\n" + vertexShader.substring(1, vertexShader.length() - 2);
		String fragSource = "#version 330 core\n" + fragmentShader.substring(1, fragmentShader.length() - 2);

		glAttachShader(shaderID, compileShader(vertSource, GL_VERTEX_SHADER));
		glAttachShader(shaderID, compileShader(fragSource, GL_FRAGMENT_SHADER));

		glLinkProgram(shaderID);

		if (glGetShaderi(shaderID, GL_LINK_STATUS) == GL_FALSE) {
			Log.getInstance().fatalError(ErrorCode.SHADER_LINK_ERROR, "Falha ao linkar Shader " + filename + " - " + glGetShaderInfoLog(shaderID, 1024));
		}

		glValidateProgram(shaderID);

		if (glGetShaderi(shaderID, GL_VALIDATE_STATUS) == GL_FALSE) {
			Log.getInstance().fatalError(ErrorCode.SHADER_VALIDATE_ERROR, "Falha ao validar Shader " + filename + " - " + glGetShaderInfoLog(shaderID, 1024));
		}

		ShaderResource newShaderResource = new ShaderResource(shaderID, vertexShader, fragmentShader, uniformStruct);
		shaderResources.put(filename, newShaderResource);

		return newShaderResource;
	}

	private static int compileShader(String source, int type){
		int id = glCreateShader(type);

		String shaderType = "";

		if(type == GL_VERTEX_SHADER){
			shaderType = "Vertex Shader";
		} else if(type == GL_FRAGMENT_SHADER){
			shaderType = "Fragment Shader";
		}

		if (id == 0) {
			Log.getInstance().fatalError(ErrorCode.SHADER_CREATION_FAILURE, "Falha ao criar " + shaderType + ".");
		}

		glShaderSource(id, source);
		glCompileShader(id);

		if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) {
			Log.getInstance().fatalError(ErrorCode.SHADER_COMPILE_ERROR, "Falha ao compilar " + shaderType + " - " + glGetShaderInfoLog(id, 1024));
		}

		return id;
	}

	public static ShaderResource getShaderResource(String name){
		if(shaderResources.containsKey(name))
			return shaderResources.get(name);

		return null;
	}

	public static TextureResource getTextureResource(String name){
		if(textureResources.containsKey(name))
			return textureResources.get(name);

		return null;
	}
}