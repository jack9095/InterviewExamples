package com.example.myapplication.test;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.R;
import com.example.myapplication.test.adapter.TransverseAdapter;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.test.bean.TransverseBean;
import java.util.ArrayList;
import java.util.List;

public class HousekeeperActivity extends BaseActivity {

    private RecyclerView transverseRecyclerView;
    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_housekeeper;
    }

    @Override
    protected void initView() {
        transverseRecyclerView = findViewById(R.id.transverse_recycler_view);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        transverseRecyclerView.setLayoutManager(mLinearLayoutManager);
        List<TransverseBean> datas = new ArrayList<>();

        TransverseBean mTransverseBean;

        mTransverseBean = new TransverseBean();
        mTransverseBean.title = "物业费";
        datas.add(mTransverseBean);

        mTransverseBean = new TransverseBean();
        mTransverseBean.title = "电费";
        datas.add(mTransverseBean);

        mTransverseBean = new TransverseBean();
        mTransverseBean.title = "水费";
        datas.add(mTransverseBean);

        mTransverseBean = new TransverseBean();
        mTransverseBean.title = "燃气费";
        datas.add(mTransverseBean);

        final TransverseAdapter mTransverseAdapter = new TransverseAdapter(datas);
        transverseRecyclerView.setAdapter(mTransverseAdapter);

        mTransverseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<TransverseBean> data = mTransverseAdapter.getData();
                for (int i = 0;i< mTransverseAdapter.getData().size();i++) {
                    mTransverseAdapter.getData().get(i).isSeleted = i == position;
                }
                mTransverseAdapter.notifyDataSetChanged();
            }
        });



        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
