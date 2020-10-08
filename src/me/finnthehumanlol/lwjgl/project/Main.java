package me.finnthehumanlol.lwjgl.project;

import me.finnthehumanlol.lwjgl.engine.Engine;
import me.finnthehumanlol.lwjgl.engine.core.MainGameLoop;

public class Main {

	public Mesh mesh 
	
	public static MainGameLoop mainGameLoop = new MainGameLoop() {

		@Override
		public void start() {
			
		}
		
		@Override
		public void update() {
			
		}
		
		@Override
		public void render() {
			
		}

		@Override
		public void stop() {
			
		}
	};
	
	public static void main(String[] args) {
		Engine.createWindow(1280, 720, "window");
		Engine.start(mainGameLoop);
	}

}
