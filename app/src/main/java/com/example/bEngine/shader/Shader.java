package com.example.bEngine.shader;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.bEngine.object.BglObject;
import com.example.bEngine.TextResourceReader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.*;
import static android.opengl.GLUtils.*;

public abstract class Shader {
	
	protected final int mProgram;
    protected String name;

    protected static FloatBuffer vertexBuffer;

    protected static float objCoords[] = {
            -1, 1, 0,    // top left
            -1, -1, 0,   // bottom left
            1, -1, 0,   // bottom right
            1,  1, 0 }; // top right

	
	public Shader( Context context, int vertexCodeId, int fragmentCodeId ) {

        ByteBuffer bb = ByteBuffer.allocateDirect( objCoords.length * 4);
        bb.order( ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(objCoords);
        vertexBuffer.position(0);

		String vertexShaderCode = TextResourceReader.readTextFileFromResource(context, vertexCodeId);
		String fragmentShaderCode = TextResourceReader.readTextFileFromResource(context, fragmentCodeId );
        int vertexShader = loadShader(GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GL_FRAGMENT_SHADER, fragmentShaderCode);
        // create a GL program, add the shaders to it, and link.
        mProgram = glCreateProgram();             
        glAttachShader(mProgram, vertexShader);   
        glAttachShader(mProgram, fragmentShader); 
        glLinkProgram(mProgram);
	}
	
    public static int loadShader(int type, String shaderCode){
        int shader = glCreateShader(type);
        // add the source code to the shader and compile it
        glShaderSource(shader, shaderCode);
        glCompileShader(shader);
        // check if compilation was successful
        final int[] compileStatus = new int[1];
        glGetShaderiv(shader, GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == 0) {
        	// compilation failed
        	glDeleteShader(shader);
        	System.out.println( "======>shader compilation failed" );
        }
        else
        	System.out.println( "compilation success!");
        return shader;
    }

	public int get_program () {
		return mProgram;
	}

	public abstract void sendParametersToShader(BglObject obj, float[] mat);
	
}
