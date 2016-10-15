package Entity;

import ObjectComponents.Component;
import Renderer.*;
import Shaders.Shader;
import Vectors.*;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;


/**
 * Created by lipnican on 09/10/2016.
 */

public class Entity {

    private vec3f color;
    private vec2f position;
    private Shader shader;
    private Vao VAO;
    private int indicesBufferID;
    private TextureMap texture;

    private vec2f size = new vec2f(0.5f, 0.5f);

    private float[] quad = new float[] {
            -1.0f, 1.0f,
            -1.0f + size.x*(Renderer.ratio), 1.0f,// up right
            -1.0f + size.x*(Renderer.ratio), 1.0f - size.y ,// down right
            -1.0f, 1.0f - size.y // down left
    };

    private int[] indices = new int[] {
            0, 1, 2,
            2, 3, 0
    };

    // tex coords has different coord system from vertices
    private float[] texCoords = new float[] {
            0,0,
            1,0,
            1,1,
            0,1
    };

    private List<Component> components = new ArrayList<Component>();

    public Entity(Shader shader){
        color = new vec3f(0.0f, 0.0f, 0.0f);
        position = new vec2f(1.0f, 1.0f);
        this.shader = shader;
        texture = new TextureMap("res/images/test3.png");

        indicesBufferID = Vao.createIntElementBuffer(indices);

        VAO = new Vao();
        VAO.createVBO(quad, 0, 2);
        VAO.createVBO(texCoords, 1, 2);

    }

    public void update(){
        for (Component component : components) {
            component.loop();
        }
    }

    public void render(){

        glUseProgram(shader.getID());

        shader.setColor(color);
        shader.setPosition(position);

        VAO.bind();
        VAO.enableLocations();

        texture.bind();

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferID);

        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        VAO.disableLocations();

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

    public void setPosition(vec2f position) {
        this.position = position;
    }

    public vec3f getColor() {
        return color;
    }

    public vec2f getPosition() {
        return position;
    }
}
