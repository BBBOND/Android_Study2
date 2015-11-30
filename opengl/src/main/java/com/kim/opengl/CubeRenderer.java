package com.kim.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by 伟阳 on 2015/11/27.
 */
public class CubeRenderer implements GLSurfaceView.Renderer {

    private GLCube cube;
    private Context context;

    public CubeRenderer(Context context) {
        cube = new GLCube();
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.7f, 0.9f, 0.9f, 1.0f);    //设置窗口背景色
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);   //启用顶点坐标数组
        gl.glDisable(GL10.GL_DITHER);   //关闭抗抖动
        //设置系统对透视进行修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl.glShadeModel(GL10.GL_SMOOTH);    //设置阴影平滑模式
        gl.glEnable(GL10.GL_DEPTH_TEST);    //启用深度测试
        gl.glDepthFunc(GL10.GL_LEQUAL);     //设置深度测试类型
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45.0f, ratio, 1, 100f);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        cube.loadTexture(gl, context, R.drawable.ic_launcher);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl, 0, 0, -1, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        gl.glRotatef(1000, -0.1f, -0.1f, 0.05f);
        cube.draw(gl);
    }
}
