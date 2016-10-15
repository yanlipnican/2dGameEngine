package Renderer; /**
 * Created by lipnican on 09/10/2016.
 * Main game loop and init
 * in Main class is only window init and handling
 */

import Lights.Light;
import ObjectComponents.BasicController;
import Entity.Entity;
import Shaders.Shader;
import Shaders.TestShader;
import Vectors.vec2f;

import java.util.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindFramebuffer;
import static org.lwjgl.opengles.GLES20.GL_FRAMEBUFFER;

public class Renderer {

    private static List<Entity> renderQueue = new ArrayList<Entity>();

    public static int width = 640;
    public static int height = 420;
    public static float ratio = (float)height/(float)width;

    private static FrameBuffer fb = new FrameBuffer();

    public static void init() {

        //BitmapLoader image = new BitmapLoader("res/images/test.bmp");

        Shader shader = new TestShader();

        Entity test = new Entity(shader, "res/images/test3.png");
        Entity test2 = new Entity(shader, "res/images/test3.png");
        Entity test3 = new Entity(shader, "res/images/test.png");

        test2.setSize(new vec2f(0.8f, 0.9f));
        test2.setPosition(new vec2f(0.2f, 0.2f));

        test3.setPosition(new vec2f(0.1f, 1.0f));

        test.addComponent(new BasicController());

        addToRenderQueue(test);
        addToRenderQueue(test2);
        addToRenderQueue(test3);

    }

    public static void loop(float delta){

        //System.out.println(1000/delta);

        fb.bind();
        fb.clear();

        for(Entity entity : renderQueue){
            entity.update();
        }

        for (Entity entity : renderQueue) {
            entity.render();
        }

        bindScreenBuffer();

        fb.render();
    }

    public static void bindScreenBuffer(){
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        glViewport(0,0,width,height);
    }

    public static void addToRenderQueue(Entity entity){
        renderQueue.add(entity);
    }
}
