#version 410 core

out vec4 color_out;

in vec2 UV;

uniform sampler2D renderedTexture;

void main(){


    vec4 color = texture(renderedTexture, UV);

    color_out = color;

}
