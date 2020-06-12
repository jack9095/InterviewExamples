package com.example.myapplication.animator;

import android.view.View;

public class ViewWrapper {

    private View mTarget;

    public ViewWrapper(View target) {
        this.mTarget = target;
    }

    public int getWidth() {
        return mTarget.getLayoutParams().width;
    }

    public void setWidth(int width) {
        mTarget.getLayoutParams().width = width;
        mTarget.requestLayout(); // 会走 ViewRootImpl#performTraversals() 即 View 的重新绘制重新测量
    }
}
