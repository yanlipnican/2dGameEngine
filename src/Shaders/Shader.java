package Shaders;

import Util.File;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by lipnican on 09/10/2016.
 */

public class Shader {
    private int programID;
    private int fragmentShaderID;
    private int vertexShaderID;

    public Shader(String vs_filename, String frag_filename){

        String vs_source = "";
        String frag_source = "";

        try {
            vs_source = File.read(vs_filename);
            frag_source = File.read(frag_filename);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            programID = glCreateProgram();

            vertexShaderID = attachShaderToProgram(vs_source, GL_VERTEX_SHADER);
            fragmentShaderID = attachShaderToProgram(frag_source, GL_FRAGMENT_SHADER);

            // Link this program
            glLinkProgram(programID);

            // Check for linking errors
            if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE)
                throw new RuntimeException("Unable to link shader program:");
        }

    }

    public void delete(){
        glDetachShader(programID, vertexShaderID);
        glDetachShader(programID, fragmentShaderID);

        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);

        glDeleteProgram(programID);
    }

    public int getID(){
        return programID;
    }

    private int attachShaderToProgram(String source, int type){

        int shaderID = glCreateShader(type);
        glShaderSource(shaderID, source);

        // Compile the shader
        glCompileShader(shaderID);

        // Check for errors
        if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE)
            throw new RuntimeException("Error creating shader\n"
                    + glGetShaderInfoLog(shaderID, glGetShaderi(shaderID, GL_INFO_LOG_LENGTH)));

        // Attach the shader
        glAttachShader(programID, shaderID);

        return shaderID;
    }

}
