package Engine.Shape;

import Engine.Renderer.Camera;
import Engine.Shaders.Shader;
import Engine.Vectors.vec2f;
import Engine.Vectors.vec3f;
import Engine.Window.Window;

/**
 * Created by lipnican on 10/28/16.
 */
public class Rectangle extends Shape{

    public Rectangle(Shader shader, String tex_filename, Camera camera) {
        super(shader, tex_filename, camera);
        init();
    }

    protected void init(){
        super.indices = new int[] {
            0, 1, 2,
            2, 3, 0
        };

        super.texCoords = new float[] {
            0,0,
            1,0,
            1,1,
            0,1
        };

    }

    protected void createShape(){
        super.vertices = new float[] {
                -1.0f, 1.0f,
                -1.0f + size.x, 1.0f,// up right
                -1.0f + size.x, 1.0f - size.y * Window.getRatio(),// down right
                -1.0f, 1.0f - size.y * Window.getRatio()// down left
        };

        VAO.createVBO(vertices, 0, 2);
    }

}
