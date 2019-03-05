package com.xyl.fighting.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author xyl on 2019/3/5.
 */
public class MyView01 extends View {
    private Paint paint;

    public MyView01(Context context) {
        this(context, null);
    }

    public MyView01(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView01(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        //线条宽度
        paint.setStrokeWidth(2);
        paint.setTextSize(20);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(300, 300, 100, paint);
        //canvas.drawColor(Color.parseColor("#88880000"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }
}
