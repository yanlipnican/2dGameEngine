package Shaders;

import Vectors.vec2f;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by lipnican on 15/10/2016.
 */
public class LightShader extends Shader{

    private int intensity_location;
    private int radius_location;
    private int centerCoords_location;
    private int ratio_location;

    public LightShader() {
        super("src/Shaders/ShaderFiles/light.vertex", "src/Shaders/ShaderFiles/light.frag");

        intensity_location = glGetUniformLocation(super.getID(), "intensity");
        radius_location = glGetUniformLocation(super.getID(), "radius");
        centerCoords_location = glGetUniformLocation(super.getID(), "centerCoords");
        ratio_location = glGetUniformLocation(super.getID(), "ratio");
    }

    public void setIntensity(float intensity){
        glUniform1f(intensity_location, intensity);
    }

    public void setRadius(float radius){
        glUniform1f(radius_location, radius);
    }

    public void setCenterCoords(vec2f coords){
        glUniform2f(centerCoords_location, coords.x, coords.y);
    }

    public void setRatio(float ratio){
        glUniform1f(ratio_location, ratio);
    }

}
