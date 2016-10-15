package ObjectComponents;

import Entity.Entity;
import Vectors.vec2f;
import Vectors.vec3f;

/**
 * Created by lipnican on 09/10/2016.
 */
public class BasicController extends Controller {

    private vec3f color = new vec3f(0.2f, 0.1f, 0.5f);

    @Override
    public void loop() {

        color.x += 0.001f;

        //super.entity.move(new vec2f(-0.001f, 0.0f));

        super.entity.setColor(color);
    }

    @Override
    public void init(Entity entity) {
        super.init(entity);
        super.entity.setColor(new vec3f(1.0f, 0.0f, 0.0f));

    }
}
