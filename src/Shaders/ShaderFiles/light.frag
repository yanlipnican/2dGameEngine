#version 410 core

out vec4 color_out;

in vec2 UV;
in vec2 pass_position;

uniform sampler2D renderedTexture;
uniform vec3 color;
uniform vec2 centerCoords;
uniform float radius;
uniform float ratio;

void main(){

    vec2 position = vec2(pass_position.x/ratio, pass_position.y);
    vec2 center = vec2(centerCoords.x, centerCoords.y);

    vec2 _UV = vec2(UV.x, UV.y);

    vec4 _color = vec4(color, 1.0);

    color_out = _color - (distance(centerCoords, position)/radius);


}
