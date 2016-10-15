package Lights;

import Entity.Entity;
import Shaders.LightShader;
import Shaders.Shader;
import Vectors.vec2f;
import Vectors.vec3f;

/**
 * Created by lipnican on 15/10/2016.
 */
public class Light extends Entity{

    private float radius;
    private float intensity;
    private LightShader shader;

    public Light(LightShader shader) {
        super(shader);
        this.shader = shader;
    }

    public float getRadius() {
        return radius;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        shader.setRadius(radius);
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
        shader.setIntensity(intensity);
    }

}
