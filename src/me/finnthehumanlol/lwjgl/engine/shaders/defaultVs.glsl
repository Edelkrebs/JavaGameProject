#version 400 core

in vec3 position;

out vec3 color;

void main(void){
	gl_Position = vec4(position.xyz, 1.0f);
	color = vec3(1.0f, 0.0f, 1.0f);
}