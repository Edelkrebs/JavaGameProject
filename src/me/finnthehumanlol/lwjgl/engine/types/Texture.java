package me.finnthehumanlol.lwjgl.engine.types;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.security.auth.callback.TextInputCallback;

import org.newdawn.slick.*;
import org.newdawn.slick.opengl.TextureLoader;

public class Texture {

	private int textureID;
	private ByteBuffer image;
	private String texturePath;
	
	public Texture(String texturePath) {
		this.texturePath = texturePath;
		org.newdawn.slick.opengl.Texture texture = null;
		
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(texturePath));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		this.textureID = texture.getTextureID();
		return textureID;
		
	}
	
}
