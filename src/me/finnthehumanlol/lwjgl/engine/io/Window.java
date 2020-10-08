package me.finnthehumanlol.lwjgl.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import me.finnthehumanlol.lwjgl.engine.core.Updateable;

public class Window implements Updateable{

	private int width, height;
	private String title;
	private long window;
	private GLFWVidMode vidMode;
	
	public Window(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		if(!GLFW.glfwInit()) {
			System.err.println("GLFW couldnt be initialised");
			System.exit(-1);
		}
		
		window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
		
		if(window == 0) {
			System.err.println("Window coult be created");
			System.exit(-1);
		}
		
		vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, (vidMode.width() - width)/ 2, (vidMode.height() - height) / 2 );
		
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		GLFW.glfwShowWindow(window);
		
	}

	@Override
	public void onUpdate() {
		
	}
	
	@Override
	public void onRender() {
		GLFW.glfwPollEvents();
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(1.0f, 1.0f, 0.0f, 1.0f);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getTitle() {
		return title;
	}

	public long getWindow() {
		return window;
	}

	public GLFWVidMode getVidMode() {
		return vidMode;
	}

	
}
