package ObjectComponents;

import Entity.Entity;
import Vectors.vec3f;

/**
 * Created by lipnican on 09/10/2016.
 */
public class BasicController extends Controller {

    @Override
    public void loop() {

    }

    @Override
    public void init(Entity entity) {
        super.init(entity);
        super.entity.setColor(new vec3f(1.0f, 0.4f, 0.0f));
        System.out.println(super.entity.getColor().x);
    }
}
