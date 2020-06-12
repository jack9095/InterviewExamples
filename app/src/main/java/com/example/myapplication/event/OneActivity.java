package com.example.myapplication.event;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.animator.ViewWrapper;
import com.example.myapplication.widget.CustomView;

public class OneActivity extends AppCompatActivity {

    private View customView;
    private int containerWidth;
    private int containerHeight;
    float lastX, lastY;
    RelativeLayout mRelativeLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        customView = findViewById(R.id.btn);
        mRelativeLayout = findViewById(R.id.rl);
//        customView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(OneActivity.this,TwoActivity.class));
//            }
//        });

        ViewTreeObserver viewTreeObserver = customView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

            }
        });

//        customView.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getActionMasked()) {
//                    case MotionEvent.ACTION_DOWN:
//                        lastX = event.getRawX();
//                        lastY = event.getRawY();
//                        return true;
//                    case MotionEvent.ACTION_MOVE:
//                        //  不要直接用getX和getY,这两个获取的数据已经是经过处理的,容易出现图片抖动的情况
//                        float distanceX = lastX - event.getRawX();
//                        float distanceY = lastY - event.getRawY();
//
//                        float nextY = customView.getY() - distanceY;
//                        float nextX = customView.getX() - distanceX;
//
//                        // 不能移出屏幕
//                        if (nextY < 0) {
//                            nextY = 0;
//                        } else if (nextY > containerHeight - customView.getHeight()) {
//                            nextY = containerHeight - customView.getHeight();
//                        }
//                        if (nextX < 0) {
//                            nextX = 0;
//                        }else if (nextX > containerWidth - customView.getWidth()) {
//                            nextX = containerWidth - customView.getWidth();
//                        }
//                        // 属性动画移动
//                        ObjectAnimator y = ObjectAnimator.ofFloat(customView, "y", customView.getY(), nextY);
//                        ObjectAnimator x = ObjectAnimator.ofFloat(customView, "x", customView.getX(), nextX);
//
//                        x.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                            @Override
//                            public void onAnimationUpdate(ValueAnimator animation) {
//                                Log.e("OneActivity",animation.toString());
//                            }
//                        });
//
//                        AnimatorSet animatorSet = new AnimatorSet();
//                        animatorSet.playTogether(x, y);
//                        animatorSet.setDuration(0);
//                        animatorSet.start();
//
//                        lastX = event.getRawX();
//                        lastY = event.getRawY();
//                }
//                return false;
//            }
//        });

        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("OneActivity","View点击事件");
                Log.e("OneActivity","View宽度 ="+ customView.getWidth());

                // 包装类的属性动画 提供 get 和 set 方法
                ViewWrapper wrapper = new ViewWrapper(customView);
//                wrapper.setWidth(dp2px(80));
                ObjectAnimator.ofInt(wrapper,"width",dp2px(500))
                        .setDuration(1000).start();
                startActivity(new Intent(OneActivity.this,MainActivity.class));
            }
        });

//        ObjectAnimator.ofFloat(customView,"translationX",0f,300f).setDuration(2000).start();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // 这里来获取容器的宽和高
        if (hasFocus) {
            containerHeight = mRelativeLayout.getHeight();
            containerWidth = mRelativeLayout.getWidth();
        }
    }

    public int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    // 表示 Activity 的布局已经添加到 DecorView 中了
    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

}
