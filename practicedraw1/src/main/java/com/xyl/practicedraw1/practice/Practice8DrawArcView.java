package com.xyl.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice8DrawArcView extends View {

    private Paint paint;
    private RectF rectf;

    public Practice8DrawArcView(Context context) {
        this(context, null);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        rectf = new RectF(300, 200, 700, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形 顺时针画

        // 填充模式
        paint.setStyle(Paint.Style.FILL);
        // 绘制扇形
        canvas.drawArc(rectf, -110, 100, true, paint);

        // 绘制弧形
        canvas.drawArc(rectf, 20, 140, false, paint);

        // 画线模式
        paint.setStyle(Paint.Style.STROKE);
        // 绘制不封口的弧形
        canvas.drawArc(rectf, 180, 60, false, paint);
    }
}
