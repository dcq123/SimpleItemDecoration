package com.github.qing.simpleitemdecoration.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.qing.itemdecoration.GridSpaceItemDecoration;
import com.github.qing.simpleitemdecoration.R;
import com.github.qing.simpleitemdecoration.adapter.SimpleCommonAdapter;


public class GridSpaceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    View rootView;
    int colNum = 3;
    int space = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_layout);
        rootView = findViewById(R.id.rootView);
        rootView.setBackgroundColor(getResources().getColor(R.color.colorBg));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new SimpleCommonAdapter(R.layout.item_grid_layout, 11));
        recyclerView.setLayoutManager(new GridLayoutManager(this, colNum));
        recyclerView.addItemDecoration(
                new GridSpaceItemDecoration.Builder(recyclerView)
                        .setColNum(colNum)
                        .setSpaceSize(space)
                        .build()
        );
    }


}
