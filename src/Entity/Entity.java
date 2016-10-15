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

    protected vec3f color;
    protected vec2f position;
    protected Shader shader;
    protected Vao VAO;
    private int indicesBufferID;
    private int textureID;

    private vec2f size = new vec2f(0.5f, 0.6f);

    private float[] quad;

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

    private void init(){
        color = new vec3f(0.0f, 0.0f, 0.0f);
        position = new vec2f(0.0f, 0.0f);

        indicesBufferID = Vao.createIntElementBuffer(indices);
        VAO = new Vao();
        createQuad();
        VAO.createVBO(texCoords, 1, 2);
    }

    public Entity(Shader shader, String tex_filename){
        init();
        this.shader = shader;
        textureID = TextureMap.loadTexture(tex_filename);
    }

    public Entity(Shader shader){
        init();
        this.shader = shader;
    }

    private void createQuad(){
        quad = new float[] {
                -1.0f, 1.0f,
                -1.0f + size.x*(Renderer.ratio), 1.0f,// up right
                -1.0f + size.x*(Renderer.ratio), 1.0f - size.y ,// down right
                -1.0f, 1.0f - size.y // down left
        };

        VAO.createVBO(quad, 0, 2);
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

        glBindTexture(GL_TEXTURE_2D, textureID);

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
        createQuad();
    }

    public void setSize(vec2f size){
        this.size = size;
    }

    public vec3f getColor() {
        return color;
    }

    public vec2f getPosition() {
        return position;
    }

    public void setTexture(int id){
        textureID = id;
    }
}
