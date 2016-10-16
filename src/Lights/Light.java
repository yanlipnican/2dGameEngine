package Lights;

import Entity.Entity;
import Renderer.Renderer;
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
public class Light extends Entity{

    private float radius;
    private float intensity;
    private LightShader shader;

    public Light(LightShader shader, float radius, float intensity) {
        super(shader);
        this.shader = shader;
        setIntensity(intensity);
        setRadius(radius);
        super.setBlendFunction(GL_ONE);
    }

    public float getRadius() {
        return radius;
    }

    public float getIntensity() {
        return intensity;
    }

    private void setCenterCoords(){
        shader.setCenterCoords(new vec2f((-1.0f + (super.position.x + (Renderer.ratio*super.size.x/2.0f))), 0.9f +  super.position.y - (super.size.y/2.0f)));
    }

    public void setRadius(float radius) {
        this.radius = radius;

        float size = (radius/(float)Math.sqrt(2)) * 2;
        super.setSize(new vec2f(size * 2f, size*2f));
        super.setPosition(new vec2f((super.position.x - size/2f), super.position.y + size/2f));
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public void render(){
        glUseProgram(shader.getID());
        setCenterCoords();
        shader.setIntensity(intensity);
        shader.setRadius(radius);
        shader.setRatio(Renderer.ratio);
        super.render();
    }

    public void setPosition(vec2f position){
        super.setPosition(position);
        setRadius(radius);
    }

}
