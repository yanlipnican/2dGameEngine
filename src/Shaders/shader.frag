#version 410 core

out vec4 color_out;

uniform vec3 color;

void main(){

    color_out = vec4(color.xyz , 1.0);

}
