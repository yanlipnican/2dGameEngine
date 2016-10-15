package Renderer;

import Vectors.vec2f;

/**
 * Created by lipnican on 15/10/2016.
 */
public class Text {

    private FrameBuffer fb;
    private String text;
    private vec2f position;

    public Text(String text, vec2f position){
        this.text = text;
        this.position = position;
        fb = new FrameBuffer();
    }

    private void render(){
        fb.bind();

        //

        Renderer.bindScreenBuffer();
    }

    public void delete(){

    }

    public int getTextureID(){
        return fb.getTextureID();
    }

}
