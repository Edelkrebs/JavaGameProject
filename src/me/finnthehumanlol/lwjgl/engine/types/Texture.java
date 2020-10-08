package me.finnthehumanlol.lwjgl.engine.types;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.security.auth.callback.TextInputCallback;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

public class Texture {

	private int textureID;
	private ByteBuffer image;
	private String texturePath;
	private int texWidth, texHeight;
	
	public Texture(String texturePath) {
		int textureID;
		
		int width, height;
		ByteBuffer image;
		
		try(MemoryStack stack = MemoryStack.stackPush()){
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);
			IntBuffer comp = stack.mallocInt(1);
		
			image = STBImage.stbi_load(texturePath, w, h, comp, 4);
			
			if(image == null) {
				System.err.println("Couldnt load texture!");
			}
			
			width = w.get();
			height = h.get();
			this.texWidth = width;
			this.texHeight = height;
			
		}

		textureID = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		
		this.textureID = textureID;
	}

	public int getTextureID() {
		return textureID;
	}

	public ByteBuffer getImage() {
		return image;
	}

	public String getTexturePath() {
		return texturePath;
	}

	public int getTexWidth() {
		return texWidth;
	}

	public int getTexHeight() {
		return texHeight;
	}
	
}
