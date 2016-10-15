#version 410 core

out vec4 color_out;

in vec2 UV;

uniform sampler2D renderedTexture;

void main(){

    vec2 _UV = vec2(UV.x, UV.y);

    vec4 color = texture(renderedTexture, _UV);

    color_out = color;

}
