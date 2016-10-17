package Lights;

import Entity.Entity;
import Renderer.FrameBuffer;
import Renderer.Renderer;
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

        Light light = new Light(shader, 0.5f, 0.5f);
        light.setColor(new vec3f(1, 0, 0));
        light.setPosition(new vec2f(0.5f, 0.5f));

        lights.add(light);

    }

    private boolean direction = false;

    public void renderLights(){
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        fb.bind();
        fb.clear();

        for(Light light : lights){
//            if(light.getPosition().x < -2.7){
//                direction = true;
//            }
//
//            if(light.getPosition().x > 1.3){
//                direction = false;
//            }
//            if(!direction) {
//                light.move(new vec2f(-0.005f, 0));
//            } else {
//                light.move(new vec2f(0.005f, 0));
//            }
            light.render();
        }

    }

    public void render(){
        //glEnable (GL_BLEND);
        //glBlendFunc (GL_ZERO, GL_SRC_COLOR);
        fb.render();
    }

}
