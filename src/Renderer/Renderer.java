package Renderer; /**
 * Created by lipnican on 09/10/2016.
 * Main game loop and init
 * in Main class is only window init and handling
 */

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

    private static FrameBuffer fb = new FrameBuffer();

    public static void init() {

        Shader shader = new TestShader();

        Entity test = new Entity(shader);

        test.addComponent(new BasicController());

        addToRenderQueue(test);

    }

    public static void loop(){

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
