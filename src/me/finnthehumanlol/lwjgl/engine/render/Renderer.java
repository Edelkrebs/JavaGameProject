package me.finnthehumanlol.lwjgl.engine.render;

import org.lwjgl.opengl.GL11;

import me.finnthehumanlol.lwjgl.engine.types.Mesh;

public class Renderer {

	public static void render(Mesh mesh) {
		mesh.prepareRender();
		if(mesh.getIbo() == 0)
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, mesh.getVertexCount());
		else {
			GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		}
		mesh.finishRender();
	}
	
	private Renderer() {}
	
}
