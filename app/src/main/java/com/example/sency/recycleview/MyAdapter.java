package com.example.sency.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sency on 2016/7/19.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<String> mDatas;


    private List<Integer> mHeights;

    //构造方法进行初始化
    public MyAdapter(Context context,List<String> data){
        this.mContext = context;
        this.mDatas = data;
        layoutInflater = LayoutInflater.from(context);

        mHeights = new ArrayList<>();
        for (int i = 0;i<mDatas.size();i++){
            mHeights.add((int) (100+Math.random()*300));
        }
    }

    //创建一个ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item,parent,false);
        //将view作为MyViewHolder参数传入来创建MyViewHolder对象
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    //绑定ViewHolder
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //为每个item设置随机高度
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.itemView.setLayoutParams(lp);
        //给每个item赋值
        holder.text.setText(mDatas.get(position));

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    public TextView text;

    public MyViewHolder(View itemView) {
        super(itemView);
        text = (TextView)itemView.findViewById(R.id.text);
    }
}
