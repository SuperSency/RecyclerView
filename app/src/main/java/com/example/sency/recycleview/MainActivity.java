package com.example.sency.recycleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
        //为了将它显示出来使用LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //添加分割线
        //  recyclerView.addItemDecoration(new RecycleViewDivider(this, RecycleViewDivider.VERTICAL));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Toast.makeText(MainActivity.this,"click:"+pos,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View v, int pos) {
                Toast.makeText(MainActivity.this,"long click:"+pos,Toast.LENGTH_SHORT).show();
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (id) {
            case R.id.action_add:
                mAdapter.addData(1);
                break;
            case R.id.action_delete:
                mAdapter.delete(1);
                break;
            case R.id.action_gridview:
                //第二个参数为列数
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.action_listview:
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_hor_gridview:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(7,
                        StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_staggered:
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
