package me.finnthehumanlol.lwjgl.engine;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import me.finnthehumanlol.lwjgl.engine.core.MainGameLoop;
import me.finnthehumanlol.lwjgl.engine.core.Updateable;
import me.finnthehumanlol.lwjgl.engine.io.Window;
import me.finnthehumanlol.lwjgl.engine.render.Renderer;
import me.finnthehumanlol.lwjgl.engine.render.Shader;
import me.finnthehumanlol.lwjgl.engine.types.builtin.Entity;
import me.finnthehumanlol.lwjgl.engine.util.MathUtil;

public final class Engine {

	public static MainGameLoop mainGameLoop;
	private static Window window;
	private static List<Entity> entities = new ArrayList<Entity>();
	
	public static Thread mainThread = new Thread(new Runnable() {
		
		@Override
		public void run() {
			Renderer.projectionMatrix = MathUtil.createProjectionMatrix(Renderer.FOV, Renderer.MIN_DIST, Renderer.MAX_DIST);
			Shader.projectionMatrix = Renderer.projectionMatrix;
			mainGameLoop.start();
			while(!GLFW.glfwWindowShouldClose(window.getWindow())) {				
				mainGameLoop.onUpdate();
				for(Entity e : entities) {
					e.onUpdate();
				}
				window.onRender();
				mainGameLoop.onRender();
				for(Entity e : entities) {
					e.onRender();
				}
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
	
	public static void addEntity(Entity e) {
		Engine.entities.add(e);
	}
	
}
