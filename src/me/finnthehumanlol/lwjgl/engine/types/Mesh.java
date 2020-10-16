package me.finnthehumanlol.lwjgl.engine.types;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import me.finnthehumanlol.lwjgl.engine.render.Shader;

public class Mesh {

	private int vao, vertexCount, attributeCount;
	private int ibo = 0;
	private Shader shader;
	private float[] positions, texCoords;
	private int[] indices;
	private Texture texture;
	
	private List<Integer> vbos = new ArrayList<>();

	public Mesh(float[] positions) {
		vao = GL30.glGenVertexArrays();
		this.positions = positions;
		vertexCount = positions.length / 3;
		GL30.glBindVertexArray(vao);
		addAttribute(0, positions, 3);
		attachShader();
	}
	
	public Mesh(float[] positions, int[] indices) {
		vao = GL30.glGenVertexArrays();
		this.positions = positions;
		this.indices = indices;
		vertexCount = indices.length;
		GL30.glBindVertexArray(vao);
		addAttribute(0, positions, 3);
		addIndexBuffer(indices);
		attachShader();
	}

	public void applyTexture(Texture texture, float[] texCoords) {
		this.texture = texture;
		this.shader = new Shader("src/me/finnthehumanlol/lwjgl/engine/shaders/defaultTexVs.glsl", "src/me/finnthehumanlol/lwjgl/engine/shaders/defaultTexFs.glsl");
		this.texCoords = texCoords;
		addAttribute(1, texCoords, 2);
	}
	
	public void applyTexture(Texture texture, float[] texCoords, Shader texShader) {
		this.texture = texture;
		this.shader = texShader;
		this.texCoords = texCoords;
	}
	
	public void attachShader(Shader shader) {
		this.shader = shader;
	}
	
	public void attachShader() {
		this.shader = new Shader("src/me/finnthehumanlol/lwjgl/engine/shaders/defaultVs.glsl", "src/me/finnthehumanlol/lwjgl/engine/shaders/defaultFs.glsl");
	}
	
	public void detachShader() {
		this.shader = new Shader("src/me/finnthehumanlol/lwjgl/engine/shaders/defaultVs.glsl", "src/me/finnthehumanlol/lwjgl/engine/shaders/defaultFs.glsl");
	}
	
	public void prepareRender() {
		GL30.glBindVertexArray(vao);
		
		GL20.glEnableVertexAttribArray(0);

		GL20.glEnableVertexAttribArray(1);
		
		GL20.glUseProgram(shader.getProgram());
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
		
		if(texture != null) {			
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
		}
	}

	public void finishRender() {
		GL30.glBindVertexArray(0);
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		
		GL20.glUseProgram(0);

		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		if(texture != null) {
			GL13.glActiveTexture(0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		}
	}

	private void addIndexBuffer(int[] data) {
		this.vertexCount = data.length;
		ibo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	private void addAttribute(int index, float[] data, int size) {
		int vbo = GL15.glGenBuffers();
		vbos.add(vbo);
		attributeCount++;
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, size, GL15.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	public void resetIbo() {
		ibo = 0;
		this.vertexCount = positions.length / 3;
	}

	public int getVao() {
		return vao;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public int getAttributeCount() {
		return attributeCount;
	}

	public List<Integer> getVbos() {
		return vbos;
	}

	public int getIbo() {
		return ibo;
	}

	public Shader getShader() {
		return shader;
	}

	public float[] getPositions() {
		return positions;
	}

	public int[] getIndices() {
		return indices;
	}

	public Texture getTexture() {
		return texture;
	}

}
