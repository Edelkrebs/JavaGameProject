package me.finnthehumanlol.lwjgl.engine.util;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import me.finnthehumanlol.lwjgl.engine.Engine;

public abstract class MathUtil {
	
	public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, Vector3f scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.translate(translation);
		matrix.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0));
		matrix.rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
		matrix.rotate((float) Math.toRadians(rotation.z), new Vector3f(0, 0, 1));
		matrix.scale(scale);
		return matrix;
	}
	
	public static Matrix4f createProjectionMatrix(float fov, float min_dist, float max_dist) {
		float aspectRatio = (float) Engine.getWindow().getVidMode().width() / (float) Engine.getWindow().getVidMode().width();
		float y_scale = (float)(1f / Math.tan(Math.toRadians(fov / 2f))) * aspectRatio;
		float x_scale = y_scale / aspectRatio;
		float frustum_length = max_dist - min_dist;
		
		Matrix4f projectionMatrix = new Matrix4f();
		projectionMatrix.m00(x_scale);
		projectionMatrix.m11(y_scale);
		projectionMatrix.m22(-((max_dist + min_dist) / frustum_length));
		projectionMatrix.m23(-1);
		projectionMatrix.m32(-((2 * min_dist * max_dist) / frustum_length));
		projectionMatrix.m33(0);
		
		return projectionMatrix;
	}
	
}
