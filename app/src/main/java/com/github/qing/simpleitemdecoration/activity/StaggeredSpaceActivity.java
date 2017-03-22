package com.github.qing.simpleitemdecoration.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.github.qing.itemdecoration.GridSpaceItemDecoration;
import com.github.qing.itemdecoration.utils.ScreenUtils;
import com.github.qing.simpleitemdecoration.R;
import com.github.qing.simpleitemdecoration.adapter.SimpleCommonAdapter;
import com.github.qing.simpleitemdecoration.adapter.SimpleViewHolder;

import java.util.Random;


public class StaggeredSpaceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    View rootView;
    int colNum = 3;
    int space = 30;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_layout);
        rootView = findViewById(R.id.rootView);
        rootView.setBackgroundColor(getResources().getColor(R.color.colorBg));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new SimpleCommonAdapter(R.layout.item_grid_layout, 21) {
            @Override
            public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                SimpleViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams();
                int randomValue = random.nextInt(100);
                params.height = ScreenUtils.dp2px(parent.getContext(), 150 + randomValue);
                return viewHolder;
            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(colNum, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(
                new GridSpaceItemDecoration.Builder(recyclerView)
                        .setColNum(colNum)
                        .setSpaceSize(space)
                        .build()
        );
    }


}
