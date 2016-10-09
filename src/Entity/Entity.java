package Entity;

import ObjectComponents.Component;
import Shaders.Shader;
import Vectors.*;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by lipnican on 09/10/2016.
 */

public class Entity {

    private vec3f color;
    private vec2f position;
    private Shader shader;

    private List<Component> components = new ArrayList<Component>();

    public Entity(Shader shader){
        color = new vec3f(1.0f, 0.0f, 0.0f);
        position = new vec2f(0.0f, 0.0f);
        this.shader = shader;
    }

    public void update(){
        for (int i = 0; i < components.size(); i++){
            components.get(i).loop();
        }
    }

    public void render(){

        glUseProgram(shader.getID());

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
