package Renderer;

import org.newdawn.slick.opengl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_BGR;

/**
 * Created by lipnican on 15/10/2016.
 */
public class TextureMap {

    private int ID;
    private Texture texture;

    public TextureMap(String fileName) {

        ID = glGenTextures();
        PNGDecoder decoder = null;
        ByteBuffer buffer = null;

        InputStream in;

        try {
            in = new FileInputStream(fileName);
            decoder = new PNGDecoder(in);

            buffer = ByteBuffer.allocateDirect(4*decoder.getWidth()*decoder.getHeight());
            decoder.decode(buffer, decoder.getWidth()*4, PNGDecoder.RGBA);
            buffer.flip();
            in.close();

            glBindTexture(GL_TEXTURE_2D, ID);
            glEnable (GL_BLEND);
            glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            glTexImage2D(GL_TEXTURE_2D, 0,GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, ID);
    }

}
