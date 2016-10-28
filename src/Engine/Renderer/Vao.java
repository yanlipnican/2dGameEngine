package Engine.Renderer;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Created by lipnican on 11/10/2016.
 */
public class Vao {

    private int ID;

    // TODO : create list of VBO locations and when binding VAO bind enable all VBO locations
    private List<Integer> locations = new ArrayList<Integer>();

    public Vao() {
        ID = glGenVertexArrays();
    }

    public void bind(){
        glBindVertexArray(ID);
    }

    public void enableLocations(){
        for(int location : locations){
            glEnableVertexAttribArray(location);
        }
    }

    public void disableLocations(){
        for(int location : locations){
            glDisableVertexAttribArray(location);
        }
    }

    public int id(){
        return ID;
    }

    public void createVBO(float[] data, int location,int VertexSize) {

        glBindVertexArray(ID);

        int vboID = glGenBuffers();
        locations.add(location);

        FloatBuffer Buffer = BufferUtils.createFloatBuffer(data.length);
        Buffer.put(data).flip();

        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, Buffer, GL_STATIC_DRAW);
        glVertexAttribPointer(location, VertexSize, GL_FLOAT, false, 0, 0);
        glDisableVertexAttribArray(vboID);
        glBindVertexArray(0);
    }

    public static int createIntElementBuffer(int[] elements) {
        int ID = glGenBuffers();

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elements, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        return ID;
    }

}
