package TestGame;

import Engine.RenderObject;
import Engine.Renderer.Camera;
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
public class Map extends RenderObject{
    int map[][];

    float tileSize;

    private Shader shader = new TestShader();

    private Shape tile;
    private Shape block;

    private FrameBuffer frameBuffer = new FrameBuffer();

    public Map(int sizeX, int sizeY, float tileSize, Camera camera) {

        block = new Rectangle(shader, "res/images/block_tile.png", camera);
        tile = new Rectangle(shader, "res/images/grass_tile.png", camera);

        map = new int[sizeX][sizeY];
        this.tileSize = tileSize;
        tile.setSize(new vec2f(tileSize, tileSize/2.0f));
        block.setSize(new vec2f(tileSize, tileSize));
    }

    public void renderTiles(){
        frameBuffer.bind();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        frameBuffer.clear();

        for (float x = 0; x < map.length; x++){
            for (float y = 0; y < map[0].length; y++){
                if(x == 9 && y == 0){
                    block.setPosition(new vec2f((x - y) * tileSize / 2f, (x + y) * tileSize / 4f - tileSize/2f));
                    block.render();
                } else {
                    tile.setPosition(new vec2f((x - y) * tileSize / 2f, (x + y) * tileSize / 4f));
                    tile.render();
                }
            }
        }
    }

    public void render() {
        frameBuffer.render();
    }
}
