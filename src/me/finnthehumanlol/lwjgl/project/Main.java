package me.finnthehumanlol.lwjgl.project;

import me.finnthehumanlol.lwjgl.engine.Engine;
import me.finnthehumanlol.lwjgl.engine.core.MainGameLoop;
import me.finnthehumanlol.lwjgl.engine.render.Renderer;
import me.finnthehumanlol.lwjgl.engine.types.Mesh;

public class Main {
	
	public static MainGameLoop mainGameLoop = new MainGameLoop() {
		
		Mesh mesh;
		
		@Override
		public void update() {
			
		}
		
		@Override
		public void stop() {
			
		}
		
		@Override
		public void start() {
			mesh = new Mesh(new float[] {
					-0.5f, -0.5f, 1.0f,
					0.0f, 0.5f, 1.0f, 
					0.5f, -0.5f, 1.0f
			}, new int[] {
					0, 1, 2
			});
			mesh.attachShader();
		}
		
		@Override
		public void render() {
			Renderer.render(mesh);
		}
	};
	
	public static void main(String[] args) {
		Engine.createWindow(1280, 720, "Window");
		Engine.start(mainGameLoop);
	}

}
