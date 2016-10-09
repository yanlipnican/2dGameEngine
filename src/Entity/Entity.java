package Entity;

import ObjectComponents.Component;
import Shaders.Shader;
import Shaders.TestShader;
import Vectors.*;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;


/**
 * Created by lipnican on 09/10/2016.
 */

public class Entity {

    private vec3f color;
    private vec2f position;
    private Shader shader;

    private int vaoID;
    private int vboID;

    private List<Component> components = new ArrayList<Component>();

    public Entity(Shader shader){
        color = new vec3f(0.0f, 0.0f, 0.0f);
        position = new vec2f(0.5f, 0.0f);
        this.shader = shader;

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        float[] vertices = new float[]
                {
                        +0.0f, +0.8f,    // Top coordinate
                        -0.8f, -0.8f,    // Bottom-left coordinate
                        +0.8f, -0.8f     // Bottom-right coordinate
                };

        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
        verticesBuffer.put(vertices).flip();

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
        glBindVertexArray(0);

    }

    public void update(){
        for (Component component : components) {
            component.loop();
        }
    }

    public void render(){

        glUseProgram(shader.getID());

        shader.setColor(color);
        shader.setPoition(position);

        // Bind the vertex array and enable our location
        glBindVertexArray(vaoID);
        glEnableVertexAttribArray(0);

        // Draw a triangle of 3 vertices
        glDrawArrays(GL_TRIANGLES, 0, 3);

        // Disable our location
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);

    }

    public void move(vec2f position){
        this.position.x += position.x;
        this.position.y += position.y;
    }

    public void setColor(vec3f color){
        this.color = color;
    }

    public void addComponent(Component component){
        components.add(component);
        component.init(this);
    }

    public vec3f getColor() {
        return color;
    }

    public vec2f getPosition() {
        return position;
    }
}
