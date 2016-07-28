package com.example.sency.recycleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private List<String> mDatas;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initView();
        //创建适配器对象
        mAdapter = new MyAdapter(this, mDatas);
        //传入数据
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        //为了将它显示出来使用LinearLayoutManager
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        //添加分割线
//        recyclerView.addItemDecoration(new RecycleViewDivider(this, RecycleViewDivider.VERTICAL));
//        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int x = 'A'; x < 'z'; x++) {
            mDatas.add((char) x + "");
        }
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
    }

}
