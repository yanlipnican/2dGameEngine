package Renderer;

import Shaders.FrameBufferShader;
import Shaders.Shader;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.glFramebufferTexture;

/**
 * Created by lipnican on 11/10/2016.
 */

public class FrameBuffer {

    private int bufferID;
    private int textureID;
    private Vao VAO;
    private Shader shader;
    private int indicesBufferID;

    private float[] quad = new float[] {
            -1.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, -1.0f,
            -1.0f, -1.0f
    };

    private int[] indices = new int[] {
            0, 1, 2,
            2, 3, 0
    };

    // tex coords has different coord system from vertices
    private float[] texCoords = new float[] {
            0, 1,
            1, 1,
            1, 0,
            0, 0,
    };

    public FrameBuffer() {

        shader = new FrameBufferShader();
        indicesBufferID = Vao.createIntElementBuffer(indices);
        VAO = new Vao();

        createFrameBuffer();
        createTexture();
        drawBuffers();
        createQuad();

    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(){

        glUseProgram(shader.getID());

        VAO.bind();
        VAO.enableLocations();

        glBindTexture(GL_TEXTURE_2D, textureID);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferID);

        // draw without indices
        //glDrawArrays(GL_TRIANGLES, 0, quad.length);

        // with indices
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        VAO.disableLocations();

    }

    public void bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, bufferID);
        glViewport(0,0,Renderer.width, Renderer.height);
    }

    private void createQuad() {
        VAO.createVBO(quad, 0, 2);
        VAO.createVBO(texCoords, 1, 2);
    }

    private void createFrameBuffer(){

        bufferID = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, bufferID);

    }

    private void createTexture(){

        textureID = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, textureID);

        glTexImage2D(GL_TEXTURE_2D, 0,GL_RGB, Renderer.width, Renderer.height, 0,GL_RGB, GL_UNSIGNED_BYTE, 0);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GL_REPEAT);

        glFramebufferTexture(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, textureID, 0);

    }

    private void drawBuffers(){

        IntBuffer DrawBuffers = BufferUtils.createIntBuffer(1);

        DrawBuffers.put(GL_COLOR_ATTACHMENT0);
        DrawBuffers.flip();

        GL20.glDrawBuffers(DrawBuffers);

        if(glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE)
            System.out.println("Error : framebuffer is not ok");

    }
}
