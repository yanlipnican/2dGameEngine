package Engine.Renderer;

import Engine.RenderObject;
import Engine.Shape.Shape;
import Engine.Vectors.vec2f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 10/30/2016.
 */
public class Camera {
    private vec2f position = new vec2f(0, 0);
    private float zoom = 1;

    public vec2f getPosition() {
        return position;
    }

    public void setPosition(vec2f position) {
        this.position = position;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public void move(vec2f position){
        this.position.x += position.x;
        this.position.y += position.y;
    }

    public void zoom(float zoom){
        this.zoom += zoom;
    }
}
