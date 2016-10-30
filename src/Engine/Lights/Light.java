package Engine.Lights;

import Engine.RenderObject;
import Engine.Renderer.*;
import Engine.Shaders.LightShader;
import Engine.Vectors.vec2f;
import Engine.Vectors.vec3f;
import Engine.Window.Window;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * Created by lipnican on 15/10/2016.
 */
public class Light extends RenderObject{

    private float radius;
    private float intensity;
    private LightShader shader;
    private vec2f position;
    private vec3f color;
    private FrameBuffer fb = new FrameBuffer();
    private Camera camera;

    public Light(LightShader shader, float radius, float intensity, Camera camera) {
        this.shader = shader;
        setIntensity(intensity);
        setRadius(radius);
        this.camera = camera;
        fb.setShader(shader);
    }

    public vec3f getColor() {
        return color;
    }

    public float getRadius() {
        return radius;
    }

    public float getIntensity() {
        return intensity;
    }

    public void render(){

        glUseProgram(shader.getID());

        glEnable (GL_BLEND);
        glBlendFunc (GL_SRC_ALPHA, GL_ONE);

        shader.setIntensity(intensity);
        shader.setRadius(radius);
        shader.setRatio(Window.getRatio());
        shader.setColor(color);
        shader.setPosition(position);
        shader.setCamera(camera);

        fb.render();

    }

    public void setPosition(vec2f position){
        position.y *= Window.getRatio();
        this.position = new vec2f(-1.0f + position.x, 1.0f - position.y);
    }

    public void setColor(vec3f color) {
        this.color = color;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public LightShader getShader() {
        return shader;
    }

    public vec2f getPosition() {
        return position;
    }

    public void move(vec2f position){
        this.position.x += position.x;
        this.position.y += position.y * Window.getRatio();
    }
}
