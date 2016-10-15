package Lights;

import Entity.Entity;
import Shaders.LightShader;
import Shaders.Shader;
import Vectors.vec2f;
import Vectors.vec3f;

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
    }

    public float getRadius() {
        return radius;
    }

    public float getIntensity() {
        return intensity;
    }

    private void setCenterCoords(){
        shader.setCenterCoords(new vec2f(super.position.x + (super.size.x/2.0f), super.position.y + (super.size.y/2.0f)));
    }

    public void setRadius(float radius) {
        this.radius = radius;
        shader.setRadius(radius);

        float size = (radius/(float)Math.sqrt(2)) * 2;
        super.setSize(new vec2f(size, size));
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
        shader.setIntensity(intensity);
    }

    public void render(){
        setCenterCoords();
        super.render();
    }

}
