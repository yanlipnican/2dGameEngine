package Renderer;

import Shaders.FrameBufferShader;
import Shaders.Shader;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.glFramebufferTexture;

/**
 * Created by lipnican on 11/10/2016.
 */

public class FrameBuffer {

    private int bufferID;
    private int textureID;
    private int vaoID;
    private int vboID;
    private Shader shader;

    private float[] quad = new float[] {
            -1.0f, -1.0f,
            1.0f, -1.0f,
            -1.0f,  1.0f,
            1.0f, 1.0f,
            -1.0f, 1.0f,
            1.0f, -1.0f
    };

    public FrameBuffer() {

        createFrameBuffer();
        createTexture();
        drawBuffers();
        createQuad();

        this.shader = new FrameBufferShader();

    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(){

        glUseProgram(shader.getID());

        int texID = glGetUniformLocation(shader.getID(), "renderedTexture");

        glBindTexture(GL_TEXTURE_2D, textureID);

        // Bind the vertex array and enable our location
        glBindVertexArray(vaoID);
        glEnableVertexAttribArray(0);

        glDrawArrays(GL_TRIANGLES, 0, quad.length);

        // Disable our location
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);

    }

    public void bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, bufferID);
        glViewport(0,0,Renderer.width, Renderer.height);
    }

    private void createQuad() {
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(quad.length);
        verticesBuffer.put(quad).flip();

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);

//        Bind to VAO
//        glBindVertexArray(vaoID);
//        int tex_vboID = glGenBuffers();
//        FloatBuffer texBuffer = BufferUtils.createFloatBuffer(quad_texture.length);
//        verticesBuffer.put(quad_texture).flip();
//        glBindBuffer(GL_ARRAY_BUFFER, tex_vboID);
//        glBufferData(GL_ARRAY_BUFFER, texBuffer, GL_STATIC_DRAW);
//        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
//        glBindVertexArray(0)


        glBindVertexArray(0);
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
