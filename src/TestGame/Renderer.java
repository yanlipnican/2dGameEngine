package TestGame;

import Engine.Entity.Entity;
import Engine.Lights.LightFrameBuffer;
import Engine.ObjectComponents.BasicController;
import Engine.Renderer.FrameBuffer;
import Engine.Shaders.Shader;
import Engine.Shaders.TestShader;
import Engine.Vectors.vec2f;
import Engine.Window.Window;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL30.glBindFramebuffer;
import static org.lwjgl.opengles.GLES20.GL_FRAMEBUFFER;

/**
 * Created by lipnican on 10/28/16.
 */

public class Renderer extends Window{

    private List<Entity> renderQueue = new ArrayList<Entity>();

    public int width = 1280;
    public int height = 720;
    public float ratio = (float)width/(float)height;

    private FrameBuffer fb;
    private LightFrameBuffer lfb;

    public Renderer() {
        super(1280, 720, "TestGame");
    }

    @Override
    protected void init() {

        fb = new FrameBuffer();
        lfb =  new LightFrameBuffer();

        lfb.addLight();

        Shader shader = new TestShader();

        Entity test = new Entity(shader, "res/images/test3.png");
        Entity test2 = new Entity(shader, "res/images/test3.png");
        Entity test3 = new Entity(shader, "res/images/test.png");

        test2.setSize(new vec2f(1f, 1f));
        test2.setPosition(new vec2f(0.4f, 0.2f));

        test3.setPosition(new vec2f(0.0f, 0.0f));
        test3.setSize(new vec2f(2.0f, 2.4f));

        test.addComponent(new BasicController());

        //addToRenderQueue(test);
        //addToRenderQueue(test2);
        addToRenderQueue(test3);

    }

    @Override
    protected void loop() {
        for(Entity entity : renderQueue){
            entity.update();
        }

        lfb.renderLights();

        fb.bind();
        fb.clear();

        for (Entity entity : renderQueue) {
            entity.render();
        }

        bindScreenBuffer();

        fb.render();
        lfb.render();
    }

    private void bindScreenBuffer(){
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        glViewport(0,0,width,height);
    }

    private void addToRenderQueue(Entity entity){
        renderQueue.add(entity);
    }

}
