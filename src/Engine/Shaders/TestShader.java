package Engine.Shaders;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;

/**
 * Created by lipnican on 09/10/2016.
 */
public class TestShader extends Shader {

    private int color_location;

    public TestShader() {
        super("src/Engine/Shaders/ShaderFiles/shader.vertex", "src/Engine/Shaders/ShaderFiles/shader.frag");
    }

}
