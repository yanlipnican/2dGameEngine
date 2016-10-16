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

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
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

        Light light = new Light(shader, 2f, 1f);
        light.setPosition(new vec2f(0.3f, 0f));
        light.setColor(new vec3f(0.4f, 0.4f, 1f));

        lights.add(light);
        lights.add(light);
        lights.add(light);
        lights.add(light);

        light = new Light(shader, 0.9f, 1f);
        light.setPosition(new vec2f(0.7f, -0.3f));
        light.setColor(new vec3f(1.0f, 1.0f, 1.0f));

        //lights.add(light);

        light = new Light(shader, 0.9f, 1f);
        light.setPosition(new vec2f(0.9f, -0.3f));
        light.setColor(new vec3f(1.0f, 1.0f, 0.0f));

        //lights.add(light);

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
        glEnable (GL_BLEND);
        glBlendFunc (GL_ZERO, GL_SRC_COLOR);
        fb.render();
    }

}
