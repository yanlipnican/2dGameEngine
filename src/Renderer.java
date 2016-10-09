/**
 * Created by lipnican on 09/10/2016.
 * Main game loop and init
 * in Main class is only window init and handling
 */

import java.util.*;

public class Renderer {

    private static List<Object> renderQueue = new ArrayList<Object>();

    public static void init(){

    }

    public static void loop(){
        for (int i = 0; i < renderQueue.size(); i++){
            renderQueue.get(i).render();
        }
    }

    public static void addToRenderQueue(Object object){
        renderQueue.add(object);
    }
}
