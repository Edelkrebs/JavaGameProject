package me.finnthehumanlol.lwjgl.engine.types.builtin;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import me.finnthehumanlol.lwjgl.engine.Engine;
import me.finnthehumanlol.lwjgl.engine.core.Updateable;
import me.finnthehumanlol.lwjgl.engine.render.Renderer;
import me.finnthehumanlol.lwjgl.engine.types.Mesh;
import me.finnthehumanlol.lwjgl.engine.types.Texture;
import me.finnthehumanlol.lwjgl.engine.util.MathUtil;

public class Entity implements Updateable{

	private Texture texture;
	private Mesh mesh;

	private Vector3f position;
	private Vector3f rotation;
	private float scale;

	public Entity(Texture texture, Mesh mesh, Vector3f position, Vector3f rotation, float scale) {
		Engine.addEntity(this);
		this.texture = texture;
		this.mesh = mesh;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public void increasePosition(Vector3f value) {
		this.position.add(value);
	}
	
	public void rotate(Vector3f rotation) {
		this.rotation.add(rotation);
	}

	@Override
	public void onUpdate() {
		
	}
	
	@Override
	public void onRender() {
		
	}

	public Texture getTexture() {
		return texture;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	
}
