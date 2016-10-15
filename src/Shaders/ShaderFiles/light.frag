#version 410 core

out vec4 color_out;

in vec2 UV;
in vec2 pass_position;

uniform sampler2D renderedTexture;
uniform vec3 color;
uniform vec2 centerCoords;

void main(){

    vec2 _UV = vec2(UV.x, UV.y);

    vec4 _color = vec4(color, 1.0);
    color_out = _color - distance(centerCoords, pass_position);

}
