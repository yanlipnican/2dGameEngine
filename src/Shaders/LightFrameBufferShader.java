package Shaders;

/**
 * Created by lipnican on 15/10/2016.
 */
public class LightFrameBufferShader extends Shader{
    public LightFrameBufferShader(){
        super("src/Shaders/ShaderFiles/lightFrameBuffer.vertex", "src/Shaders/ShaderFiles/lightFrameBuffer.frag");
    }
}
