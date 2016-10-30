package Engine.Lights;

import Engine.RenderObject;
import Engine.Renderer.Camera;
import Engine.Renderer.FrameBuffer;
import Engine.Shaders.LightFrameBufferShader;
import Engine.Shaders.LightShader;
import Engine.Vectors.vec2f;
import Engine.Vectors.vec3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by lipnican on 15/10/2016.
 */
public class LightFrameBuffer extends RenderObject{


    private FrameBuffer fb = new FrameBuffer();
    private List<Light> lights = new ArrayList<Light>();
    private LightFrameBufferShader shader = new LightFrameBufferShader();
    private Camera camera;

    public LightFrameBuffer(Camera camera){
        fb.setShader(shader);
    }

    public void addLight(){
        LightShader shader = new LightShader();

        Light light = new Light(shader, 1f, 0.5f, camera);
        light.setColor(new vec3f(1, 0, 0.4f));
        light.setPosition(new vec2f(0.5f, 0.5f));

        lights.add(light);

        light = new Light(shader, 1f, 0.5f, camera);
        light.setColor(new vec3f(1, 1, 0.4f));
        light.setPosition(new vec2f(0.0f, 0.5f));

        lights.add(light);

        light = new Light(shader, 1f, 0.5f, camera);
        light.setColor(new vec3f(0.4f, 0.4f, 1f));
        light.setPosition(new vec2f(0.9f, 0.5f));

        lights.add(light);

        light = new Light(shader, 1f, 0.5f, camera);
        light.setColor(new vec3f(0.4f, 1.0f, 0.4f));
        light.setPosition(new vec2f(0.4f, 1f));

        lights.add(light);

    }

    private boolean direction = false;

    public void renderLights(){
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        fb.bind();
        fb.clear();

        for(Light light : lights){
            if(light.getPosition().x < -1.0){
                direction = true;
            }

            if(light.getPosition().x > 1.0){
                direction = false;
            }
            if(!direction) {
                light.move(new vec2f(-0.005f, 0));
            } else {
                light.move(new vec2f(0.005f, 0));
            }
            light.render();
        }

    }

    public void render(){
        glEnable (GL_BLEND);
        glBlendFunc (GL_ZERO, GL_SRC_COLOR);
        fb.render();
    }

}
