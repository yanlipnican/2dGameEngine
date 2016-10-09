package Renderer; /**
 * Created by lipnican on 09/10/2016.
 * Main game loop and init
 * in Main class is only window init and handling
 */

import ObjectComponents.BasicController;
import ObjectComponents.Component;
import Entity.Entity;

import java.util.*;

public class Renderer {

    private static List<Entity> renderQueue = new ArrayList<Entity>();

    public static void init(){
        Entity test = new Entity();
        Component component = new BasicController();
        test.addComponent(component);
    }

    public static void loop(){
        for (int i = 0; i < renderQueue.size(); i++){
            renderQueue.get(i).update();
            renderQueue.get(i).render();
        }
    }

    public static void addToRenderQueue(Entity entity){
        renderQueue.add(entity);
    }
}
