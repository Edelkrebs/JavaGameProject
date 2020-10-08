package me.finnthehumanlol.lwjgl.engine;

import org.lwjgl.glfw.GLFW;

import me.finnthehumanlol.lwjgl.engine.core.MainGameLoop;
import me.finnthehumanlol.lwjgl.engine.io.Window;

public final class Engine {

	public static MainGameLoop mainGameLoop;
	private static Window window;
	
	public static Thread mainThread = new Thread(new Runnable() {
		
		@Override
		public void run() {
			mainGameLoop.start();
			while(!GLFW.glfwWindowShouldClose(window.getWindow())) {				
				mainGameLoop.update();
				window.onRender();
				mainGameLoop.render();
				GLFW.glfwSwapBuffers(window.getWindow());
			}
			mainGameLoop.stop();
		}
	}, "main");
	
	public static void start(MainGameLoop mainGameLoop) {
		Engine.mainGameLoop = mainGameLoop;
		mainThread.run();
	}
	
	public static void createWindow(int width, int height, String title) {
		window = new Window(width, height, title);
	}

	public static MainGameLoop getMainGameLoop() {
		return mainGameLoop;
	}

	public static Window getWindow() {
		return window;
	}

	public static Thread getMainThread() {
		return mainThread;
	}
	
}
