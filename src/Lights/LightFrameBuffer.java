package Lights;

import Entity.Entity;
import Renderer.FrameBuffer;
import Shaders.LightFrameBufferShader;
import Shaders.LightShader;
import Shaders.Shader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipnican on 15/10/2016.
 */
public class LightFrameBuffer {


    private FrameBuffer fb = new FrameBuffer();
    private List<Light> lights = new ArrayList<Light>();
    private LightFrameBufferShader shader = new LightFrameBufferShader();

    public LightFrameBuffer(){
        fb.setShader(shader);
    }

    public void addLight(){
        LightShader shader = new LightShader();
        Light light = new Light(shader);
        lights.add(light);
    }

    public void renderLights(){

        fb.bind();

        for(Light light : lights){
            light.render();
        }

    }

    public void render(){

    }

}
