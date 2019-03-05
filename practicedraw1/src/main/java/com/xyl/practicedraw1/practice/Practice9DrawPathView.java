package com.xyl.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice9DrawPathView extends View {

    private Paint paint;
    private Path path;

    public Practice9DrawPathView(Context context) {
        this(context, null);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        // 使用 path 对图形进行描述（这段描述代码不必看懂）
        //又是一个弧形的方法。一个叫 arcTo ，一个叫 addArc()，都是弧形，区别在哪里？
        // 其实很简单： addArc() 只是一个直接使用了 forceMoveTo = true 的简化版 arcTo()
        //forceMoveTo是否留下移动的痕迹。
        path = new Path();
        path.addArc(400, 200, 600, 400, -225, 225);
        path.arcTo(600, 200, 800, 400, -180, 225, false);
        path.lineTo(600, 542);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        canvas.drawPath(path, paint);

    }
}
