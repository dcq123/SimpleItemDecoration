package com.github.qing.simpleitemdecoration.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.qing.itemdecoration.LinearSpaceItemDecoration;
import com.github.qing.simpleitemdecoration.R;
import com.github.qing.simpleitemdecoration.adapter.SimpleCommonAdapter;
import com.github.qing.simpleitemdecoration.adapter.SimpleViewHolder;


public class LinearSpaceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_layout);
        rootView = findViewById(R.id.rootView);
        rootView.setBackgroundColor(getResources().getColor(R.color.colorBg));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setAdapter(new SimpleCommonAdapter(R.layout.item_layout, 10) {
            @Override
            public void onBindViewHolder(SimpleViewHolder holder, int position) {
                holder.itemView.setBackgroundColor(Color.WHITE);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(
                new LinearSpaceItemDecoration.Builder()
                        .setOrientation(LinearLayoutManager.VERTICAL)
                        .setSpaceSize(10)
                        .build()
        );
    }
}
