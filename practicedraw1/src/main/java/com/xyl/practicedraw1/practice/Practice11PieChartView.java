package com.xyl.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;

public class Practice11PieChartView extends View {
    /**
     * 名字
     */
    private String[] names = {"Froyo", "Gingerbread", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow"};
    private Integer[] colors = {Color.GREEN, Color.parseColor("#990066"), Color.GRAY, Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW};
    private Integer[] percent = {5, 15, 10, 50, 100, 130, 50};


    List<String> deviceNames;//名字
    List<Integer> colorInteger;//颜色
    List<Integer> precentInteger;//百分比

    Paint paintPie;//画饼
    Paint paintText;//画字体
    Paint paintLine;//画线
    RectF rectfCommon;//不移动的部分
    RectF rectfMove;//移动的部分

    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        deviceNames = Arrays.asList(names);
        colorInteger = Arrays.asList(colors);
        precentInteger = Arrays.asList(percent);

        //饼
        paintPie = new Paint(Paint.ANTI_ALIAS_FLAG);

        //画线
        paintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintLine.setStrokeWidth(5);
        paintLine.setColor(Color.LTGRAY);
        //文字
        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setTextSize(30);
        paintText.setColor(Color.LTGRAY);
        //正常
        rectfCommon = new RectF(-300, -300, 300, 300);
        //Lollipop
        rectfMove = new RectF(-320, -320, 280, 280);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        int width = getWidth();
        int height = getHeight();

        canvas.translate(width / 2, height / 2);

        float startAngle = 0;
        float sweepAngle = 0;
        for (int i = 0; i < deviceNames.size(); i++) {
//            画饼图
            paintPie.setStrokeWidth(10);
            paintPie.setColor(colorInteger.get(i));
            sweepAngle = precentInteger.get(i);
            String deviceName = deviceNames.get(i);
//            第五个饼会离开一段距离
            if (i == 5) {
                canvas.drawArc(rectfMove, startAngle + 1, sweepAngle - 1, true, paintPie);
            } else {
                canvas.drawArc(rectfCommon, startAngle + 1, sweepAngle - 1, true, paintPie);
            }
//画斜线
// 角（弧度）＝弧长/半径
//圆的周长是半径的 2π倍，所以一个周角（360度）是 2π弧度。 半圆的长度是半径的 π倍，所以一个平角（180度）是 π弧度。

//            据上所述，一个平角是 π 弧度。 即
//            180度＝π弧度
//            由此可知：
//            1度＝π/180 弧度 ( ≈0.017453弧度 )
//            因此，得到 把度化成弧度的公式：
//            弧度＝角度度×π/180
            //上式中，l为弧长，α为角度（弧度制），r为半径。
            //推导：由弧度定义  得 l = |α|r
            //Math.sin()需要传弧度制
            float textAngle = startAngle + precentInteger.get(i) / 2;//获取每个弧度中点，中点话延长线
            float pointY = (float) (Math.sin(textAngle * Math.PI / 180) * 300.0);
            float pointX = (float) (Math.cos(textAngle * Math.PI / 180) * 300.0);

            float lineY = (float) (Math.sin(textAngle * Math.PI / 180) * 325.0);
            float lineX = (float) (Math.cos(textAngle * Math.PI / 180) * 325.0);

            if (i == 5) {
                canvas.translate(-20, -20);
                canvas.drawLine(pointX, pointY, lineX, lineY, paintLine);
                canvas.translate(20, 20);
            } else {
                canvas.drawLine(pointX, pointY, lineX, lineY, paintLine);
            }

//            画直线
            if (lineX < 0) {
                Rect textRect = getTextRect(deviceName, paintText);
                if (i == 5) {
                    canvas.translate(-20, -20);
                    canvas.drawLine(lineX, lineY, -400, lineY, paintLine);
                    canvas.drawText(deviceName, -420 - textRect.width(), lineY, paintText);
                    canvas.translate(20, 20);
                } else {
                    canvas.drawLine(lineX, lineY, -400, lineY, paintLine);
                    canvas.drawText(deviceName, -420 - textRect.width(), lineY, paintText);
                }
            } else {
                canvas.drawLine(lineX, lineY, 400, lineY, paintLine);
                canvas.drawText(deviceName, 400, lineY, paintText);
            }
//            初始化每个饼的起始角度
            startAngle = startAngle + sweepAngle;
        }

    }

    /**
     * 获取文字的 Rect
     *
     * @param deviceName
     * @param paint
     * @return
     */
    private Rect getTextRect(String deviceName, Paint paint) {
        Rect mBound = new Rect();
        paint.getTextBounds(deviceName, 0, deviceName.length(), mBound);
        return mBound;
    }
}
