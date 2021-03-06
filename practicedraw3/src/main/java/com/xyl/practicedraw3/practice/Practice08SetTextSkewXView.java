package com.xyl.practicedraw3.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice08SetTextSkewXView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    String text = "Hello HenCoder";

    public Practice08SetTextSkewXView(Context context) {
        super(context);
    }

    public Practice08SetTextSkewXView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice08SetTextSkewXView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(60);

        // 使用 Paint.setTextSkewX() 来让文字倾斜
        //设置文字横向错切角度。其实就是文字倾斜度的啦。
        paint.setTextSkewX(-0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(text, 50, 100, paint);
    }
}
