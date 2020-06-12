package com.example.myapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * 定义一个粘性带头部的布局
 */
public class StickLayout extends LinearLayout {

    public StickLayout(Context context) {
        super(context);
        init();
    }

    public StickLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // TouchSlop 是系统所能识别出滑动的最小距离，两次滑动距离的差值小于这个值，认为没有滑动
    private int mTouchSlop;

    // 记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    private void init(){
        ClassLoader parent = getClass().getClassLoader().getParent();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int intercepted = 0;
        int x = (int) ev.getX(); // 当前 View 左上角的横坐标
        int y = (int) ev.getY();

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mLastXIntercept = x;
                mLastYIntercept = y;
                intercepted = 0;
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            ViewGroup.LayoutParams layoutParams = childView.getLayoutParams();
//            layoutParams.height =

            childView.setLayoutParams(layoutParams);
        }
    }
}
