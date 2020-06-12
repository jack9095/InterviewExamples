package com.mobile.myapplication.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // 重写 addView
    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {

        // 在子 View 在被添加到父容器之前给进行一个包裹添加一个 ViewGroup 容器
        MyFrameLayout myFrameLayout = new MyFrameLayout(getContext(),null);
        // 把 childView 里面的自定义属性拿到，传到 MyFrameLayout 中去

        super.addView(child, index, params);
    }
}
