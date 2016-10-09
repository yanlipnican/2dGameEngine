package Shaders;

import Vectors.*;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform3f;

/**
 * Created by lipnican on 09/10/2016.
 */
public class TestShader extends Shader {

    private int color_location;

    public TestShader() {
        super("src/Shaders/shader.vertex", "src/Shaders/shader.frag");
    }

}
