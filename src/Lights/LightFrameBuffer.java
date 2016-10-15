package Lights;

import Entity.Entity;
import Renderer.FrameBuffer;
import Shaders.LightFrameBufferShader;
import Shaders.LightShader;
import Shaders.Shader;
import Vectors.vec2f;
import Vectors.vec3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL20.glUseProgram;

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
        Light light = new Light(shader, 1f, 1);
        light.setPosition(new vec2f(0.0f, -0.2f));
        light.setColor(new vec3f(1.0f, 1.0f, 1.0f));
        lights.add(light);
    }

    public void renderLights(){
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        fb.bind();
        fb.clear();

        for(Light light : lights){
            light.render();
        }

    }

    public void render(){
        fb.render();
    }

}
