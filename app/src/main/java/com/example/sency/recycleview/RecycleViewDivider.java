package com.example.sency.recycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by sency on 2016/7/20.
 * 继承RecyclerView.ItemDecoration
 * 实现分割线
 */
public class RecycleViewDivider extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mOrientation;

    //方向
    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;

    //系统默认,要改的话需要自己写一个drawable调用
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    //构造方法
    public RecycleViewDivider(Context context, int orientation) {
        final TypedArray array = context.obtainStyledAttributes(ATTRS);
        mDivider = array.getDrawable(0);
        array.recycle();

        //指定分割线的方向
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        //如果分割线方向错误则抛出异常
        if (mOrientation != HORIZONTAL && mOrientation != VERTICAL) {
            throw new IllegalArgumentException("Wrong mOrientation!!!");
        }
        this.mOrientation = orientation;
    }

    //画分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        //水平分割线
        if (mOrientation == HORIZONTAL) {
            drawHorizontal(c, parent);
        }
        //垂直分割线
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        }
    }

    /**
     * @param c
     * @param parent 实现分割线主要是要实现分割线mDivider的setBounds(left,top,right,bottom)方法
     */
    //垂直方向分割线实现方法
    private void drawVertical(Canvas c, RecyclerView parent) {

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            //获取父类中每一个item(视图)
            final View child = parent.getChildAt(i);
            //获取每个item具体的布局(宽高)
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            //底部为顶部加上分割线高度
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            //将分割线画到画布上
            mDivider.draw(c);
        }
    }

    //水平方向分割线实现方法
    private void drawHorizontal(Canvas c, RecyclerView parent) {

        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    //为每个item设置一定的偏移量,因为你只是画出了分割线并没有将它应用到布局中
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (mOrientation==VERTICAL){
            outRect.set(0,0,0,mDivider.getIntrinsicHeight());
        }else{
            outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
        }
    }
}
