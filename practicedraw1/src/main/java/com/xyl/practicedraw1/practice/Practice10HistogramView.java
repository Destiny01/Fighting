package com.xyl.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice10HistogramView extends View {
    /**
     * 名字
     */
    private String[] names = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};
    /**
     * 直方图高度
     */
    private float[] heights = {1, 20, 20, 200, 350, 400, 150};
    /**
     * 直方图宽度
     */
    private int width = 100;
    /**
     * 直方图之间的间距
     * (1000-100)/8 = 25
     */
    private int gap = 25;
    /**
     * 画直线所需的点坐标
     */
    private float[] points = {100, 50, 100, 600, 100, 600, 1000, 600};
    Paint paint;

    public Practice10HistogramView(Context context) {
        this(context, null);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        //1、先画两条白线
        paint.setColor(Color.WHITE);
        canvas.drawLines(points, paint);
        //2、画绿色的直方图
        paint.setColor(Color.GREEN);
        for (int i = 0; i < names.length; i++) {
            canvas.drawRect(100 + (i + 1) * gap + i * width, 600 - heights[i], 100 + (i + 1) * gap + (i + 1) * width, 600, paint);
        }
        //3、写文字
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        for (int i = 0; i < names.length; i++) {
            canvas.drawText(names[i], 100 + (i + 1) * gap + 50 + i * width, 630, paint);
        }
    }
}
