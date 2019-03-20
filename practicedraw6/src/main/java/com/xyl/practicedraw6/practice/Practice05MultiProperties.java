package com.xyl.practicedraw6.practice;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xyl.practicedraw6.R;
import com.xyl.practicedraw6.Utils;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Practice05MultiProperties extends ConstraintLayout {
    Button animateBt;
    ImageView imageView;
    boolean animated;
    public Practice05MultiProperties(Context context) {
        super(context);
    }

    public Practice05MultiProperties(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice05MultiProperties(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        animateBt = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setScaleX(0);
        imageView.setScaleY(0);
        imageView.setAlpha(0f);
        animateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 在这里处理点击事件，同时对多个属性做动画
                if (animated) {
                    imageView.animate()
                            .scaleX(1)
                            .scaleY(1)
                            .alpha(1)
                            .rotation(360)
                            .translationX(Utils.dpToPixel(200));
                } else {
                    imageView.animate()
                            .scaleX(0)
                            .scaleY(0)
                            .alpha(0)
                            .rotation(0)
                            .translationX(0);
                }
                animated = !animated;
            }
        });
    }
}
