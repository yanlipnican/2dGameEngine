package Engine.ObjectComponents;


import Engine.Entity.Entity;

/**
 * Created by lipnican on 09/10/2016.
 */
public abstract class Controller implements Component{

    public Entity entity;

    @Override
    public void init(Entity entity) {
        this.entity = entity;
    }
}
