package me.finnthehumanlol.lwjgl.project;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import me.finnthehumanlol.lwjgl.engine.Engine;
import me.finnthehumanlol.lwjgl.engine.core.MainGameLoop;
import me.finnthehumanlol.lwjgl.engine.render.Renderer;
import me.finnthehumanlol.lwjgl.engine.render.Shader;
import me.finnthehumanlol.lwjgl.engine.types.Mesh;
import me.finnthehumanlol.lwjgl.engine.types.Texture;
import me.finnthehumanlol.lwjgl.engine.types.builtin.Entity;
import me.finnthehumanlol.lwjgl.engine.util.MathUtil;

public class Main {
	
	public static MainGameLoop mainGameLoop = new MainGameLoop() {
		
		Mesh mesh;
		Matrix4f mat4 = new Matrix4f();
		FloatBuffer testBuffer = BufferUtils.createFloatBuffer(16);
		Entity entity;
		
		@Override
		public void onUpdate() {
			
		}

		@Override
		public void onRender() {
			
			Renderer.render(entity);
			
		}
		
		@Override
		public void stop() {
			
		}
		
		@Override
		public void start() {
			mesh = new Mesh(new float[] {
					-0.5f, 0.5f, 0.0f,
					-0.5f, -0.5f, 0.0f,
					0.5f, -0.5f, 0.0f,
					0.5f, 0.5f, 0.0f
			}, new int[] {
					0, 1, 2, 2, 3, 0
			});
			
			mesh.applyTexture(new Texture("res/test/phantom.png"), new float[] {0, 0, 
																				0, 1,
																				1, 1,
																				1, 0});
			entity = new Entity(mesh.getTexture(), mesh, new Vector3f(0, 0, -.2f), new Vector3f(0, 0, 0), 1);
		}
		
	};
	
	public static void main(String[] args) {
		Engine.createWindow(1280, 720, "Window");
		Engine.start(mainGameLoop);
	}

}
