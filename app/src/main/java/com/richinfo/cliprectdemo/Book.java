package com.richinfo.cliprectdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

public class Book extends View{
    Context mContext;
    Paint mPaint;
    Path mPath;
    public Book(Context context) {
        super(context);
        init();
    }

    public Book(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Book(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6);
        mPaint.setTextSize(16);
        mPaint.setTextAlign(Paint.Align.RIGHT);

        mPath = new Path();
    }

    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.GRAY);

        canvas.save();
        canvas.translate(10, 10);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(160, 10);
        canvas.clipRect(10, 10, 90, 90);
        canvas.clipRect(30, 30, 70, 70, Region.Op.XOR);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(10, 160);
        mPath.reset();
//        canvas.clipPath(mPath); // makes the clip empty
//        mPath.addCircle(50, 50, 50, Path.Direction.CCW);
        mPath.cubicTo(0, 0, 100, 0, 100, 100);
        mPath.cubicTo(100, 100, 0, 100, 0, 0);
        canvas.clipPath(mPath, Region.Op.REPLACE);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(160, 160);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.UNION);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(10, 310);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.XOR);
        drawScene(canvas);
        canvas.restore();

        canvas.save();

        canvas.translate(160, 310);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.REVERSE_DIFFERENCE);
        drawScene(canvas);
        canvas.restore();
    }

    private void drawScene(Canvas canvas) {
        canvas.clipRect(0, 0, 100, 100);

        canvas.drawColor(Color.WHITE);

        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, 100, 100, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(30, 70, 30, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawText("Clipping", 100, 30, mPaint);
    }
}