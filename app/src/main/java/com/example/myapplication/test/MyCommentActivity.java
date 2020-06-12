package com.example.myapplication.test;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.test.fragment.CommentedFragment;
import com.example.myapplication.test.fragment.CommentedOnFragment;
import com.flyco.tablayout.SlidingTabLayout;
import java.util.ArrayList;

/**
 * 我的点评
 */
public class MyCommentActivity extends BaseActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"已点评","待点评"};
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    private HandlerThread handlerThread;
    private Handler mainHandler,workHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_comment;
    }

    @Override
    protected void initView() {
        mFragments.add(new CommentedFragment());
        mFragments.add(new CommentedOnFragment());
        mSlidingTabLayout = findViewById(R.id.sliding_tab_layout);
        mViewPager = findViewById(R.id.mine_view_pager);
        mSlidingTabLayout.setViewPager(mViewPager, mTitles, this, mFragments);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void onClick(View v) {

    }



}
