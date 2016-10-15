package Shaders;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by lipnican on 15/10/2016.
 */
public class LightShader extends Shader{

    private int intensity_location;
    private int radius_location;

    public LightShader() {
        super("src/Shaders/ShaderFiles/light.vertex", "src/Shaders/ShaderFiles/light.frag");

        intensity_location = glGetUniformLocation(super.getID(), "intensity");
        radius_location = glGetUniformLocation(super.getID(), "radius");
    }

    public void setIntensity(float intensity){
        glUniform1f(intensity_location, intensity);
    }

    public void setRadius(float radius){
        glUniform1f(radius_location, radius);
    }

}
