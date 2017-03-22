package com.github.qing.simpleitemdecoration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.qing.itemdecoration.LinearDividerItemDecoration;
import com.github.qing.simpleitemdecoration.activity.GridDividerActivity;
import com.github.qing.simpleitemdecoration.activity.GridSpaceActivity;
import com.github.qing.simpleitemdecoration.activity.LinearDividerActivity;
import com.github.qing.simpleitemdecoration.activity.LinearSpaceActivity;
import com.github.qing.simpleitemdecoration.activity.StaggeredSpaceActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<ItemInfo> datas = new ArrayList<>();
    RecyclerView recyclerView;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mainAdapter = new MainAdapter();
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(
                new LinearDividerItemDecoration.Builder()
                        .setDividerColor(getResources().getColor(R.color.colorBg))
                        .setDividerHeight(2)
                        .build());
    }

    private void initData() {
        datas.add(new ItemInfo("LinearLayoutManager简单分割线", LinearDividerActivity.class));
        datas.add(new ItemInfo("LinearLayoutManager空白间隔", LinearSpaceActivity.class));
        datas.add(new ItemInfo("GridLayoutManager分割线", GridDividerActivity.class));
        datas.add(new ItemInfo("GridLayoutManager空白间隔", GridSpaceActivity.class));
        datas.add(new ItemInfo("StaggeredGridLayoutManager空白间隔", StaggeredSpaceActivity.class));
    }

    private class ItemInfo {
        String title;
        Class clazz;

        ItemInfo(String title, Class clazz) {
            this.title = title;
            this.clazz = clazz;
        }
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        MainViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    private class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_layout, parent, false);
            return new MainViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            final ItemInfo itemInfo = datas.get(position);
            holder.title.setText(itemInfo.title);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, itemInfo.clazz));
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

}
