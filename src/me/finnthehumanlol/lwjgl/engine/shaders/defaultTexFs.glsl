#version 400 core

in vec2 outTexCoord;

out vec4 color;

uniform sampler2D textureSampler;

void main(void){
	color = texture(textureSampler, outTexCoord);
}