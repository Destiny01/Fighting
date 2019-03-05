package com.xyl.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice6DrawLineView extends View {

    private Paint paint;

    public Practice6DrawLineView(Context context) {
        this(context, null);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawLine() 方法画直线
        canvas.drawLine(300, 300, 600, 500, paint);
    }
}
