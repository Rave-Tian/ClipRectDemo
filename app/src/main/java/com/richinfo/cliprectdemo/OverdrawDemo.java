package com.richinfo.cliprectdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/1.
 */
public class OverdrawDemo extends View {
    private Bitmap[] mCards = new Bitmap[3];

    private int[] mIngId = new int[]{R.drawable.icon_a, R.drawable.icon_k, R.drawable.icon_q};

    public OverdrawDemo(Context context) {
        super(context);
        initBitmap();
    }

    public OverdrawDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBitmap();
    }

    public OverdrawDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmap();
    }

    private void initBitmap() {
        Bitmap bitmap = null;
        for (int i = 0; i < mCards.length; i++) {
            bitmap = BitmapFactory.decodeResource(getResources(), mIngId[i]);
//            创建缩放图
            mCards[i] = Bitmap.createScaledBitmap(bitmap, 400, 600, false);
            mCards[i] = bitmap;
        }

        setBackgroundColor(0xffffffff);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(120, 0);
/*        for (Bitmap bm : mCards) {
            canvas.translate(120, 0);
            canvas.drawBitmap(bm, 0, 0, null);
        }*/
        for (int i = 0; i < mCards.length; i++) {
            canvas.translate(120, 0);
            canvas.save();
            if (i < mCards.length - 1) {
                canvas.clipRect(0, 0, 120, mCards[i].getHeight());
            }
            canvas.drawBitmap(mCards[i], 0, 0, null);
            canvas.restore();
        }

        canvas.restore();
    }
}
