package com.xyl.practicedraw4.practice;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.xyl.practicedraw4.R;

import androidx.annotation.Nullable;

public class Practice13CameraRotateHittingFaceView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point = new Point(200, 50);
    Camera camera = new Camera();
    Matrix matrix = new Matrix();
    int degree;
    ObjectAnimator animator = ObjectAnimator.ofInt(this, "degree", 0, 360);

    public Practice13CameraRotateHittingFaceView(Context context) {
        super(context);
    }

    public Practice13CameraRotateHittingFaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice13CameraRotateHittingFaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 2, bitmap.getHeight() * 2, true);
        bitmap.recycle();
        bitmap = scaledBitmap;

        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        //在 Camera 中，相机的默认位置是 (0, 0, -8)（英寸）。8 x 72 = 576，所以它的默认位置是 (0, 0, -576)（像素）。
        //
        //如果绘制的内容过大，当它翻转起来的时候，就有可能出现图像投影过大的「糊脸」效果。
        // 而且由于换算单位被写死成了 72 像素，而不是和设备 dpi 相关的，所以在像素越大的手机上，这种「糊脸」效果会越明显。
        //糊脸修正 而使用 setLocation() 方法来把相机往后移动，就可以修复这种问题
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = -displayMetrics.density * 6;
        camera.setLocation(0, 0, newZ);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    @SuppressWarnings("unused")
    public void setDegree(int degree) {
        this.degree = degree;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int centerX = point.x + bitmapWidth / 2;
        int centerY = point.y + bitmapHeight / 2;

        camera.save();
        matrix.reset();
        camera.rotateX(degree);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point.x, point.y, paint);
        canvas.restore();
    }
}