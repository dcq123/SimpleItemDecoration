package com.github.qing.simpleitemdecoration.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.qing.itemdecoration.LinearDividerItemDecoration;
import com.github.qing.simpleitemdecoration.R;
import com.github.qing.simpleitemdecoration.adapter.SimpleCommonAdapter;

public class LinearDividerActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    SimpleCommonAdapter mVerticalAdapter;
    SimpleCommonAdapter mHorizontalAdapter;
    LinearLayoutManager layoutManager;
    LinearDividerItemDecoration dividerItemDecoration;
    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_layout);
        rootView = findViewById(R.id.rootView);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mVerticalAdapter = new SimpleCommonAdapter(R.layout.item_layout, 10);
        mRecyclerView.setAdapter(mVerticalAdapter);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        dividerItemDecoration = new LinearDividerItemDecoration.Builder()
                .setDividerColor(getResources().getColor(R.color.colorBg))
                .setDividerHeight(2)
                .setLeftMargin(8)
                .isShowLastDivider(true)
                .setOrientation(LinearLayoutManager.VERTICAL)
                .setLastDividerColor(Color.BLUE)
                .setLastDividerHeight(20)
                .setLastLeftMargin(20)
                .build();
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_linear_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuHorizontal && layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
            if (mHorizontalAdapter == null) {
                mHorizontalAdapter = new SimpleCommonAdapter(R.layout.item_horazontal_layout, 5);
            }
            mRecyclerView.setAdapter(mHorizontalAdapter);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.removeItemDecoration(dividerItemDecoration);
            dividerItemDecoration = new LinearDividerItemDecoration.Builder()
                    .setDividerColor(getResources().getColor(R.color.colorPrimary))
                    .setDividerHeight(10)
                    .setTopMargin(16)
                    .setBottomMargin(16)
                    .isShowLastDivider(false)
                    .setOrientation(LinearLayoutManager.HORIZONTAL)
                    .build();
            mRecyclerView.addItemDecoration(dividerItemDecoration);
            rootView.setBackgroundColor(getResources().getColor(R.color.colorBg));
        } else if (item.getItemId() == R.id.menuVertical && layoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
            mRecyclerView.setAdapter(mVerticalAdapter);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.removeItemDecoration(dividerItemDecoration);
            dividerItemDecoration = new LinearDividerItemDecoration.Builder()
                    .setDividerColor(getResources().getColor(R.color.colorBg))
                    .setDividerHeight(2)
                    .setLeftMargin(8)
                    .isShowLastDivider(false)
                    .setOrientation(LinearLayoutManager.VERTICAL)
                    .build();
            mRecyclerView.addItemDecoration(dividerItemDecoration);
            rootView.setBackgroundColor(Color.WHITE);
        }
        return true;
    }
}
