package ObjectComponents;

import Entity.Entity;

/**
 * Created by lipnican on 09/10/2016.
 */

public interface Component {
    public void loop();
    public void init(Entity entity);
}
