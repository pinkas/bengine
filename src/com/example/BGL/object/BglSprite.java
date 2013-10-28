package com.example.BGL.object;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.BGL.object.BglObject;

import static android.opengl.GLES20.glGenTextures;

// TODO rename this class
public class BglSprite extends BglObject {

	protected Bitmap bitmap;
    protected int texture_id;
	protected int [] textureHandle = new int [1];


    // we show the whole texture, nothing less, nothing more
    protected static float textcoords[] = {
    	0.0f, 0.0f, 0.0f,
    	0.0f, 1.0f, 0.0f,
    	1.0f, 1.0f, 0.0f,
    	1.0f, 0.0f, 0.0f,
    };
	
    // TODO should be final, cf AnimatedObject, je devrais pas overwriter en gros. Une implementation
    // TODO differente pour les objects animes de ceux pas animes
    protected FloatBuffer[] textCoordBuffer;
    
	public BglSprite( float x, float y, float w, float h, int texture_id ){

        super(x,y,w,h);
        this.texture_id = texture_id;
        // texture coord
    	ByteBuffer bb = ByteBuffer.allocateDirect(textcoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        textCoordBuffer = new FloatBuffer[1];
        textCoordBuffer[0] = bb.asFloatBuffer();
        textCoordBuffer[0].put(textcoords);
        textCoordBuffer[0].position(0);

	}

    public void loadTexture(Context context){
        //create a bitmap, from image to pixel data, has to be done whenever we reload the
        //texture since we "recycle" the bitmap at the end
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), texture_id, options);
        //TODO don't like the gl call here ...
        glGenTextures( 1, textureHandle, 0);
        mShader.loadTexture(bitmap, textureHandle[0]);
        bitmap.recycle();
    }

	
	public FloatBuffer textCoordBufferGet() {
		return textCoordBuffer[0];
	}
	
	public int  textureHandleGet() {
		return textureHandle[0];
	}

	
}
