package com.kim.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by 伟阳 on 2015/11/27.
 */
public class GLCube {
    private IntBuffer mVertexBuffer;
    private IntBuffer mTextureBuffer;

    public GLCube() {
        int one = 65536;
        int half = one / 2;
        int vertices[] = {
                //前面
                -half, -half, half, half, -half, half,
                -half, half, half, half, half, half,
                //背面
                -half, -half, -half, -half, half, -half,
                half, -half, -half, half, half, -half,
                //左面
                -half, -half, half, -half, half, half,
                -half, -half, -half, -half, half, -half,
                //右面
                half, -half, -half, half, half, -half,
                half, -half, half, half, half, half,
                //上面
                -half, half, half, half, half, half,
                -half, half, -half, half, half, -half,
                //下面
                -half, -half, half, -half, -half, -half,
                half, -half, half, half, -half, -half
        };
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asIntBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);

        int texCoords[] = {
                //前面
                0, one, one, one, 0, 0, one, 0,
                //后面
                one, one, one, 0, 0, one, 0, 0,
                //左面
                one, one, one, 0, 0, one, 0, 0,
                //右面
                one, one, one, 0, 0, one, 0, 0,
                //上面
                one, 0, 0, 0, one, one, 0, one,
                //下面
                0, 0, 0, one, one, 0, one, one
        };
        ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length * 4);
        tbb.order(ByteOrder.nativeOrder());
        mTextureBuffer = tbb.asIntBuffer();
        mTextureBuffer.put(texCoords);
        mTextureBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);
        //绘制FRONT和BACK两个面
        gl.glColor4f(1, 0, 0, 1);
        gl.glNormal3f(0, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
        gl.glColor4f(1, 0, 0.5f, 1);
        gl.glNormal3f(0, 0, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
        //绘制LEFT和RIGHT两个面
        gl.glColor4f(0, 1, 0, 1);
        gl.glNormal3f(-1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
        gl.glColor4f(0, 1, 0.5f, 1);
        gl.glNormal3f(1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
        //绘制TOP和BOTTOM两个面
        gl.glColor4f(0, 0, 1, 1);
        gl.glNormal3f(0, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
        gl.glColor4f(0, 0, 0.5f, 1);
        gl.glNormal3f(0, -1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
        gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, mTextureBuffer);
    }

    void loadTexture(GL10 gl, Context context, int resource) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resource);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
    }
}
