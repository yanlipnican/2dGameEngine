package TestGame;

import Engine.Lights.LightFrameBuffer;
import Engine.Renderer.Camera;
import Engine.Renderer.FrameBuffer;
import Engine.Shaders.Shader;
import Engine.Shaders.TestShader;
import Engine.Shape.Rectangle;
import Engine.Shape.Shape;
import Engine.Vectors.vec2f;
import Engine.Window.Window;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL30.glBindFramebuffer;
import static org.lwjgl.opengles.GLES20.GL_FRAMEBUFFER;

/**
 * Created by lipnican on 10/28/16.
 */

public class Renderer extends Window{

    private List<Shape> renderQueue = new ArrayList<Shape>();

    public float ratio;

    private FrameBuffer fb;
    private LightFrameBuffer lfb;
    Map map;
    Camera camera;

    public Renderer() {
        super(1280, 720, "TestGame");
    }

    @Override
    protected void init() {

        camera = new Camera();

        fb = new FrameBuffer();
        //lfb =  new LightFrameBuffer();

//        lfb.addLight();

        Shader shader = new TestShader();

        Shape test3 = new Rectangle(shader, "res/images/test.png", camera);

        test3.setPosition(new vec2f(0.2f, 0.2f));
        test3.setSize(new vec2f(0.2f, 0.2f));

        addToRenderQueue(test3);
        map = new Map(10, 10, 0.2f, camera);
    }

    @Override
    protected void loop() {

  //      lfb.renderLights();
        map.renderTiles();
        fb.bind();
        fb.clear();

        for (Shape shape : renderQueue) {
            shape.render();
        }
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_UP ) {
                camera.move(new vec2f(0, 0.01f));
            }
            if ( key == GLFW_KEY_DOWN ){
                camera.move(new vec2f(0, -0.01f));
            }
            if ( key == GLFW_KEY_LEFT ) {
                camera.move(new vec2f(-0.01f, 0));
            }
            if ( key == GLFW_KEY_RIGHT ){
                camera.move(new vec2f(0.01f, 0));
            }
        });
        //map.render();

        bindScreenBuffer();

        fb.render();
        map.render();
        //lfb.render();
    }

    private void bindScreenBuffer(){
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        glViewport(0,0,Window.getWidth(), Window.getHeight());
    }

    private void addToRenderQueue(Shape shape){
        renderQueue.add(shape);
    }

}
