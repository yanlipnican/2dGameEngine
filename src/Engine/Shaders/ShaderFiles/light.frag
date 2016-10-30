#version 410 core

out vec4 color_out;

in vec2 UV;
in vec2 pass_position;

uniform sampler2D renderedTexture;
uniform vec3 color;
uniform vec2 position;
uniform float radius;
uniform float ratio;

uniform vec2 cameraPosition;
uniform float cameraZoom;

void main(){

    vec2 frag_position = vec2(pass_position.x * cameraZoom, pass_position.y / ratio * cameraZoom);
    vec2 _position = vec2(position.x - cameraPosition.x, (position.y - cameraPosition.y)/ ratio);

    //_position = vec2((_position.x - _position.y) * 0.2 / 2, (_position.x + _position.y) * 0.2 / 4);
    //frag_position = vec2((frag_position.x - frag_position.y) * 0.2 / 2, (frag_position.x + frag_position.y) * 0.2 / 4);

    //x = (x - y) * 0.2 / 2
    //y = (x + y) * 0.2 / 4

    color_out = vec4(color, 1.0) - (distance(_position, frag_position)/radius*ratio);

}
