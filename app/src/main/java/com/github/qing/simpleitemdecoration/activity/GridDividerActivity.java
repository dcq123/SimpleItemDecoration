package com.github.qing.simpleitemdecoration.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.qing.itemdecoration.GridDividerItemDecoration;
import com.github.qing.simpleitemdecoration.R;
import com.github.qing.simpleitemdecoration.adapter.SimpleCommonAdapter;


public class GridDividerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    int colNum = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new SimpleCommonAdapter(R.layout.item_grid_layout, 11));
        recyclerView.setLayoutManager(new GridLayoutManager(this, colNum));
        recyclerView.addItemDecoration(
                new GridDividerItemDecoration.Builder()
                        .setColNum(colNum)
                        .setDividerHeight(5)
                        .setDividerColor(getResources().getColor(R.color.colorBg))
                        .build()
        );
    }


}
