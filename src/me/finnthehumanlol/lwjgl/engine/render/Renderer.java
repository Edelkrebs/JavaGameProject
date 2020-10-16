package me.finnthehumanlol.lwjgl.engine.render;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import me.finnthehumanlol.lwjgl.engine.types.Mesh;
import me.finnthehumanlol.lwjgl.engine.types.builtin.Entity;
import me.finnthehumanlol.lwjgl.engine.util.MathUtil;

public class Renderer {

	public static float FOV = 70;
	public static float MIN_DIST = 0.1f;
	public static float MAX_DIST = 1000;
	
	public static Matrix4f projectionMatrix;
	
	public static void updateProjectionMatrix() {
		Renderer.projectionMatrix = MathUtil.createProjectionMatrix(FOV, MIN_DIST, MAX_DIST);
	}
	
	public static void render(Mesh mesh) {
		mesh.prepareRender();
		if(mesh.getIbo() == 0)
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, mesh.getVertexCount());
		else {
			GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		}
		mesh.finishRender();
	}
	
	public static void render(Entity entity) {
		entity.getMesh().prepareRender();
		Matrix4f mat4 = MathUtil.createTransformationMatrix(entity.getPosition(), entity.getRotation(), new Vector3f(entity.getScale(), entity.getScale(), entity.getScale()));
		entity.getMesh().getShader().setUniformMat4("transformMatrix", mat4);
		entity.getMesh().getShader().setUniformMat4("projectionMatrix", Renderer.projectionMatrix);
		if(entity.getMesh().getIbo() == 0)
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, entity.getMesh().getVertexCount());
		else {
			GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getMesh().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		}
		entity.getMesh().finishRender();
	}
	
	private Renderer() {}
	
}
