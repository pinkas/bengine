package com.example.bEngine.object;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.bEngine.BtextureManager;
import com.example.bEngine.shader.Shader;

import static android.opengl.GLES20.glGenTextures;

public class BglSprite extends BglObject {

	protected Bitmap bitmap;
    protected int resource;
	protected int [] textureHandle = new int [1];
    // we show the whole texture, nothing less, nothing more


	public BglSprite( float x, float y, float w, float h, int resource ){

        super(x,y,w,h);

        this.shaderName = "basic";
        this.resource = resource;
        // texture coord


	}

    /* TODO should this method take the handle as a parameter and be like:
        setTextureHandle( BtextureManager.findHandle( oneAndroidResource )
     */
    public void setTextureHandle(){
        textureHandle[0] = BtextureManager.getInstance().findHandle(resource);
    }

    @Override
	public int  textureHandleGet() {
		return textureHandle[0];
	}

    @Override
    public float[] getColor(){
        float color[] = new float[4];
        return color;
    }



    @Override
    public void collision(){}

}
