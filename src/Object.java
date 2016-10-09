import Vectors.vec2f;
import Vectors.vec3f;

/**
 * Created by lipnican on 09/10/2016.
 */

public class Object {

    private vec3f color;
    private vec2f position;

    public Object(){
        color = new vec3f(1.0f, 0.0f, 0.0f);
        position = new vec2f(0.0f, 0.0f);
    }

    public void render(){

    }

    public void move(vec2f position){
        this.position.x += position.x;
        this.position.y += position.y;
    }

    public void setColor(vec3f color){
        this.color = color;
    }
}
