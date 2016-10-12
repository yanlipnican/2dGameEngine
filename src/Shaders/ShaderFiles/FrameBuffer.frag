#version 410 core

out vec4 color_out;

in vec2 UV;

uniform sampler2D renderedTexture;

void main(){

    vec2 _UV = vec2(UV.x, UV.y);

    if(UV.x > 0.0){
        _UV.x += 0.5;
    }

    if(UV.y > 0.0){
        _UV.y += 0.5;
    }

    vec4 color = texture(renderedTexture, _UV);

    color_out = color;

}
