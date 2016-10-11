package Renderer;

import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Created by lipnican on 11/10/2016.
 */
public class Vao {

    private int ID;

    public Vao() {
        ID = glGenVertexArrays();
    }

    public void bindVBO() {
        glBindVertexArray(ID);
        int vboID = glGenBuffers();
        FloatBuffer texBuffer = BufferUtils.createFloatBuffer(quad_texture.length);
        verticesBuffer.put(quad_texture).flip();
        glBindBuffer(GL_ARRAY_BUFFER, tex_vboID);
        glBufferData(GL_ARRAY_BUFFER, texBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
        glBindVertexArray(0)
    }

}
