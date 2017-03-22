package com.github.qing.simpleitemdecoration.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dcq on 2017/3/21.
 */

public class SimpleCommonAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

    private int layoutId;
    private int itemCount;

    public SimpleCommonAdapter(int layoutId, int itemCount) {
        this.layoutId = layoutId;
        this.itemCount = itemCount;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }
}
