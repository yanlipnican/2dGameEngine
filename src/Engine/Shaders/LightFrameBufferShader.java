package Engine.Shaders;

/**
 * Created by lipnican on 15/10/2016.
 */
public class LightFrameBufferShader extends Shader{
    public LightFrameBufferShader(){
        super("src/Engine/Shaders/ShaderFiles/lightFrameBuffer.vertex", "src/Engine/Shaders/ShaderFiles/lightFrameBuffer.frag");
    }
}
