package me.finnthehumanlol.lwjgl.engine.render;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL20;

public class Shader {

	private String vertexPath, fragmentPath;

	private int program, vertexShader, fragmentShader;

	public Shader(String vertexPath, String fragmentPath) {
		program = GL20.glCreateProgram();
		this.vertexPath = vertexPath;
		this.fragmentPath = fragmentPath;

		vertexShader = addShader(GL20.GL_VERTEX_SHADER, vertexPath);
		fragmentShader = addShader(GL20.GL_FRAGMENT_SHADER, fragmentPath);

		GL20.glAttachShader(program, vertexShader);
		GL20.glAttachShader(program, fragmentShader);
		GL20.glLinkProgram(program);
		GL20.glValidateProgram(program);

	}

	public void bindAttribute(int index, String varName) {
		GL20.glBindAttribLocation(program, index, varName);
	}
	
	private int addShader(int type, String filePath) {

		int shader = GL20.glCreateShader(type);
		StringBuilder shaderSource = new StringBuilder();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));

			String line;

			while ((line = reader.readLine()) != null) {
				shaderSource.append(line).append("\n");
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		GL20.glShaderSource(shader, shaderSource);
		GL20.glCompileShader(shader);
		if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) != GL20.GL_TRUE) {
			System.err.println("Error! Couldnt compile shader " + filePath);
			System.err.println(GL20.glGetShaderInfoLog(shader));
		}

		return shader;

	}
	
	public void activate() {
		GL20.glUseProgram(program);
	}
	
	public void deactivate() {
		GL20.glUseProgram(0);
	}

	public String getVertexPath() {
		return vertexPath;
	}

	public String getFragmentPath() {
		return fragmentPath;
	}

	public int getProgram() {
		return program;
	}

	public int getVertexShader() {
		return vertexShader;
	}

	public int getFragmentShader() {
		return fragmentShader;
	}

}
