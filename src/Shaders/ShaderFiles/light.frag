#version 410 core

out vec4 color_out;

in vec2 UV;
in vec2 pass_position;

uniform sampler2D renderedTexture;
uniform vec3 color;
uniform vec2 position;
uniform float radius;
uniform float ratio;

void main(){

    vec2 frag_position = vec2(pass_position.x, pass_position.y / ratio);
    vec2 _position = vec2(position.x, position.y / ratio);

    color_out = vec4(color, 1.0) - (distance(_position, frag_position)/radius*ratio);

}
