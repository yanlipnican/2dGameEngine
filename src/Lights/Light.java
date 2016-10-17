package Lights;

import Entity.Entity;
import Renderer.*;
import Shaders.LightShader;
import Shaders.Shader;
import Vectors.vec2f;
import Vectors.vec3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * Created by lipnican on 15/10/2016.
 */
public class Light{

    private float radius;
    private float intensity;
    private LightShader shader;
    private vec2f position;
    private vec3f color;
    private FrameBuffer fb = new FrameBuffer();

    public Light(LightShader shader, float radius, float intensity) {
        this.shader = shader;
        setIntensity(intensity);
        setRadius(radius);
        fb.setShader(shader);
    }

    public vec3f getColor() {
        return color;
    }

    public float getRadius() {
        return radius;
    }

    public float getIntensity() {
        return intensity;
    }

    public void render(){

        glUseProgram(shader.getID());

        shader.setIntensity(intensity);
        shader.setRadius(radius);
        shader.setRatio(Renderer.ratio);
        shader.setColor(color);
        shader.setPosition(position);

        fb.render();

    }

    public void setPosition(vec2f position){
        position.y *= Renderer.ratio;
        this.position = new vec2f(-1.0f + position.x, 1.0f - position.y);
    }

    public void setColor(vec3f color) {
        this.color = color;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }


}
