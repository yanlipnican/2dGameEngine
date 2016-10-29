package TestGame;

import Engine.Renderer.FrameBuffer;
import Engine.Shaders.Shader;
import Engine.Shaders.TestShader;
import Engine.Shape.Rectangle;
import Engine.Shape.Shape;
import Engine.Vectors.vec2f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_SRC_COLOR;
import static org.lwjgl.opengl.GL11.GL_ZERO;

/**
 * Created by Jan on 10/29/2016.
 */
public class Map {
    int map[][];

    float tileSize;
    private String texture = "res/images/grass_tile.png";

    private Shader shader = new TestShader();
    private Shape tile = new Rectangle(shader, texture);

    private FrameBuffer frameBuffer = new FrameBuffer();

    public Map(int sizeX, int sizeY, float tileSize) {
        map = new int[sizeX][sizeY];
        this.tileSize = tileSize;
        tile.setSize(new vec2f(tileSize, tileSize/2.0f));
    }

    public void renderTiles(){
        frameBuffer.bind();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        frameBuffer.clear();

        for (float x = 0; x < map.length; x++){
            for (float y = 0; y < map[0].length; y++){
                tile.setPosition(new vec2f((x - y) * tileSize/2f + 1, (x + y) * tileSize/4f + 0.2f) );
                tile.render();
            }
        }
    }

    public void render() {
        frameBuffer.render();
    }
}
