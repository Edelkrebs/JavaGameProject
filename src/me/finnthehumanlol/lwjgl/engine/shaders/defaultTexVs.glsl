#version 400 core

in vec3 position;
in vec2 texCoord;

out vec2 outTexCoord;

uniform mat4 transformMatrix;
uniform mat4 projectionMatrix;

void main(void){
	gl_Position = projectionMatrix * transformMatrix * vec4(position.xyz, 1.0f);
	outTexCoord = texCoord;
}