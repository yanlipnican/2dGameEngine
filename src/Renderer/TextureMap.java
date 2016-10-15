package Renderer;

import Util.BitmapLoader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_BGR;

/**
 * Created by lipnican on 15/10/2016.
 */
public class Texture {

    private BitmapLoader bmp;
    private int ID;

    public Texture(String fileName) {
        bmp = new BitmapLoader(fileName);
        ID = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, ID);
        glTexImage2D(GL_TEXTURE_2D, 0,GL_RGB, bmp.width(), bmp.height(), 0, GL_BGR, GL_UNSIGNED_BYTE, bmp.data());

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, ID);
    }

}
