package Engine.Shape;

import Engine.Renderer.TextureMap;
import Engine.Renderer.Vao;
import Engine.Shaders.Shader;
import Engine.Vectors.vec2f;
import Engine.Vectors.vec3f;
import Engine.Window.Window;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * Created by lipnican on 10/28/16.
 */

public abstract class Shape {

    protected Shader shader;
    protected Vao VAO;
    private int indicesBufferID;
    protected int textureID;

    private int blendFunction = GL_ONE_MINUS_SRC_ALPHA;
    private boolean useBlend = true;

    protected vec2f size = new vec2f(0.5f, 0.6f);
    protected vec3f color = new vec3f(1.0f, 0.0f, 0.0f);
    protected vec2f position = new vec2f(0.0f, 0.0f);

    protected float[] vertices;
    protected int[] indices;
    protected float[] texCoords;

    protected abstract void init();
    protected abstract void createShape();

    public Shape(Shader shader, String tex_filename){
        VAO = new Vao();

        init();
        createShape();

        indicesBufferID = Vao.createIntElementBuffer(indices);
        VAO.createVBO(texCoords, 1, 2);

        this.shader = shader;
        textureID = TextureMap.loadTexture(tex_filename);
    }

    public void render(){

        glUseProgram(shader.getID());

        shader.setColor(color);
        shader.setPosition(position);

        VAO.bind();
        VAO.enableLocations();

        if(useBlend){
            glEnable (GL_BLEND);
            glBlendFunc (GL_SRC_ALPHA, blendFunction);
        }

        glBindTexture(GL_TEXTURE_2D, textureID);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferID);

        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        VAO.disableLocations();

    }

    public void move(vec2f position){
        this.position.x += position.x;
        this.position.y += position.y * Window.ratio;
    }

    public void setColor(vec3f color){
        this.color = color;
    }

    public void setPosition(vec2f position) {
        position.y *= Window.ratio;
        this.position = position;
        createShape();
    }

    public void setSize(vec2f size){
        this.size = size;
        createShape();
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

    public void blend(boolean blend){
        useBlend = blend;
    }

    public void setBlendFunction(int blendFunction){
        this.blendFunction = blendFunction;
    }

}
