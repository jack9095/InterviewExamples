package com.example.myapplication.test.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;

public class CommentedFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_commented;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
