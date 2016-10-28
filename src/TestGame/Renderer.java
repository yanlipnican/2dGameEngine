package TestGame;

import Engine.Lights.LightFrameBuffer;
import Engine.Renderer.FrameBuffer;
import Engine.Shaders.Shader;
import Engine.Shaders.TestShader;
import Engine.Shape.Rectangle;
import Engine.Shape.Shape;
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

    private List<Shape> renderQueue = new ArrayList<Shape>();

    public float ratio;

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

        Shape test3 = new Rectangle(shader, "res/images/test.png");

        test3.setPosition(new vec2f(0.0f, 0.0f));
        test3.setSize(new vec2f(2.0f, 2.4f));

        addToRenderQueue(test3);
    }

    @Override
    protected void loop() {
        lfb.renderLights();

        fb.bind();
        fb.clear();

        for (Shape shape : renderQueue) {
            shape.render();
        }

        bindScreenBuffer();

        fb.render();
        lfb.render();
    }

    private void bindScreenBuffer(){
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        glViewport(0,0,Window.WIDTH, Window.HEIGHT);
    }

    private void addToRenderQueue(Shape shape){
        renderQueue.add(shape);
    }

}
