package com.example.myapplication.test.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.test.bean.TransverseBean;

import java.util.List;

public class TransverseAdapter extends BaseQuickAdapter<TransverseBean, BaseViewHolder> {

    public TransverseAdapter(List<TransverseBean> data) {
        super(R.layout.transverse_adapter_layout, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, TransverseBean item) {
        helper.setText(R.id.transverse_recycler_tv, item.title);
        TextView mTextView = helper.getView(R.id.transverse_recycler_tv);

        if (item.isSeleted) {
            mTextView.setTextColor(Color.parseColor("#FF8300"));
        } else {
            mTextView.setTextColor(Color.parseColor("#666666"));
        }
    }

}
