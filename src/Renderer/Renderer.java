package Renderer; /**
 * Created by lipnican on 09/10/2016.
 * Main game loop and init
 * in Main class is only window init and handling
 */

import ObjectComponents.BasicController;
import ObjectComponents.Component;
import Entity.Entity;
import Shaders.Shader;
import Shaders.TestShader;

import java.util.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Renderer {

    private static List<Entity> renderQueue = new ArrayList<Entity>();

    public static void init(){

        System.out.println(glGetString(GL_SHADING_LANGUAGE_VERSION));

        Shader shader = new TestShader();
        Entity test = new Entity(shader);

        Component component = new BasicController();
        test.addComponent(component);

        addToRenderQueue(test);
    }

    public static void loop(){
        for(Entity entity : renderQueue){
            entity.update();
        }

        for (Entity entity : renderQueue) {
            entity.render();
        }
    }

    public static void addToRenderQueue(Entity entity){
        renderQueue.add(entity);
    }
}
