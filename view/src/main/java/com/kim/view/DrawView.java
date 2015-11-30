package com.kim.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 伟阳 on 2015/11/25.
 */
public class DrawView extends View {


    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画变色
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setShadowLayer(2, 3, 3, Color.rgb(180, 180, 180));
        Shader shader = new LinearGradient(0, 0, 50, 50, Color.RED, Color.GREEN, Shader.TileMode.MIRROR);
        paint.setShader(shader);
        canvas.drawRect(10, 10, 100, 90, paint);
        shader = new RadialGradient(160, 110, 50, Color.RED, Color.GREEN, Shader.TileMode.MIRROR);
        paint.setShader(shader);
        canvas.drawRect(115, 10, 205, 90, paint);
        shader = new SweepGradient(265, 110, new int[]{Color.RED, Color.GREEN, Color.BLUE}, null);
        paint.setShader(shader);
        canvas.drawRect(220, 10, 310, 90, paint);

        //划线
        Paint paint1 = new Paint();
        paint1.setColor(Color.BLUE);
        paint1.setStrokeWidth(2);
        paint1.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(500, 100);
        path.lineTo(400, 200);
        path.lineTo(300, 150);
        path.lineTo(200, 200);
        path.lineTo(100, 100);
        canvas.drawPath(path, paint1);

        //画圆
        Paint paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        Path path1 = new Path();
        path1.addCircle(300, 400, 60, Path.Direction.CW);
        canvas.drawPath(path1, paint2);

        //画五环
        Paint paint3 = new Paint();
        paint3.setAntiAlias(true);
        paint3.setStrokeWidth(3);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setColor(Color.BLUE);
        canvas.drawCircle(100, 600, 30, paint3);
        paint3.setColor(Color.YELLOW);
        canvas.drawCircle(150, 600, 30, paint3);
        paint3.setColor(Color.BLACK);
        canvas.drawCircle(200, 600, 30, paint3);
        paint3.setColor(Color.GREEN);
        canvas.drawCircle(125, 640, 30, paint3);
        paint3.setColor(Color.RED);
        canvas.drawCircle(175, 640, 30, paint3);

        //文字环
        String str = "风萧萧兮易水寒，壮士一去兮不复还";
        Path path2 = new Path();
        path2.addCircle(300, 800, 48, Path.Direction.CW);
        Paint paint4 = new Paint();
        paint4.setStyle(Paint.Style.FILL);
        canvas.drawTextOnPath(str, path2, 130, -50, paint4);

        //旋转
        Paint paint5 = new Paint();
        paint5.setAntiAlias(true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Matrix matrix = new Matrix();
        matrix.setRotate(90, 100, 100);
        canvas.drawBitmap(bitmap, matrix, paint5);

        //缩放
        Matrix matrix1 = new Matrix();
        matrix1.setScale(3, 3, -50, -50);
        canvas.drawBitmap(bitmap, matrix1, paint5);

        Matrix matrix2 = new Matrix();
        matrix2.setSkew(0, 0.5f);
        canvas.drawBitmap(bitmap, matrix2, paint5);

        //平移
        Matrix matrix3 = new Matrix();
        matrix3.setRotate(30);
        matrix3.postTranslate(300, 200);
        canvas.drawBitmap(bitmap, matrix3, paint5);

        //平铺
        Paint paint6 = new Paint();
        paint6.setAntiAlias(true);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint6.setShader(bitmapShader);
        canvas.drawRect(500, 500, 1000, 1000, paint6);

        //椭圆
        Paint paint7 = new Paint();
        BitmapShader bitmapShader1 = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
        paint7.setShader(bitmapShader1);
        RectF oval = new RectF(0, 0, 500, 500);
        canvas.translate(40, 20);
        canvas.drawOval(oval, paint7);
        super.onDraw(canvas);
    }
}
