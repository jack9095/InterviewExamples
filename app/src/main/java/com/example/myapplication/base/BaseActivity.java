package com.example.myapplication.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;


public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        ScreenUtil.setCustomDensity(this,getApplication());
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setStatusBar(Color.parseColor("#ffffff"));
        if (isBindEventBusHere()) {
//            EventBus.getDefault().register(this);
        }
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBindEventBusHere()) {
//            EventBus.getDefault().unregister(this);
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected void addOnClickListeners(View.OnClickListener listener, @IdRes int... ids) {
        if (ids != null) {
            for (@IdRes int id : ids) {
                findViewById(id).setOnClickListener(listener);
            }
        }
    }

    protected void showToast(String str){
        Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
    }

    protected abstract boolean isBindEventBusHere();

    public void postEventBus(String type) {
//        EventBus.getDefault().post(new EventCenter<Object>(type));
    }

    public void postEventBusSticky(String type) {
//        EventBus.getDefault().postSticky(new EventCenter<Object>(type));
    }

    public void postEventBusSticky(String type, Object obj) {
//        EventBus.getDefault().postSticky(new EventCenter<Object>(type, obj));
    }

    public void postEventBus(String type, Object obj) {
//        EventBus.getDefault().post(new EventCenter<Object>(type, obj));
    }

    protected void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            mRecyclerView.smoothScrollToPosition(position);
        }
    }

    protected void setStatusBar(@ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(color);

            if (isLightColor(color)) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }

    }

    private boolean isLightColor(@ColorInt int color) {
        return ColorUtils.calculateLuminance(color) >= 0.5;
    }

    protected @ColorInt int getStatusBarColor() {
        return Color.WHITE;
    }

    private long mExitTime;
    public void oDoubleCheck() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出甄会选", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }


    @SuppressLint("WrongConstant")
    protected Drawable getBgDrawableByBankNo(Context ctx, String bankNo) {
        int strokeWidth = dp2px(1);
        int strokeColor = Color.parseColor(bankNo);
        int fillColor = Color.parseColor(bankNo);
        int topLeftRadius = dp2px(20);
        int topRightRadius = topLeftRadius;
        int bottomRightRadius = topLeftRadius;
        int bottomLeftRadius = topLeftRadius;

        GradientDrawable gd = new GradientDrawable();
        gd.setGradientType(GradientDrawable.RECTANGLE);
        gd.setColor(fillColor);
        gd.setCornerRadii(new float[]{topLeftRadius,
                topLeftRadius, topRightRadius, topRightRadius,
                bottomRightRadius, bottomRightRadius, bottomLeftRadius,
                bottomLeftRadius});
        gd.setStroke(strokeWidth, strokeColor);
        return gd;
    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
