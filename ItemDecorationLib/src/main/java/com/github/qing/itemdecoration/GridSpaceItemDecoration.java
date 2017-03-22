package com.github.qing.itemdecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.qing.itemdecoration.utils.GridItemUtils;
import com.github.qing.itemdecoration.utils.ScreenUtils;

/**
 * Created by dcq on 2017/3/21.
 * 适配GridLayoutManager，绘制间隔
 */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private static final int DEFAULT_HEIGHT = 10;
    private int spaceSize;
    private int colNum;

    private GridSpaceItemDecoration(Builder builder) {
        this.spaceSize = builder.spaceSize;
        this.colNum = builder.colNum;
        RecyclerView recyclerView = builder.recyclerView;
        // 此处需要设置RecyclerView的leftPadding，才能保证item间隔的均分
        recyclerView.setPadding(recyclerView.getPaddingLeft() + ScreenUtils.dp2px(recyclerView.getContext(), spaceSize), recyclerView.getPaddingTop(), recyclerView.getPaddingRight(), recyclerView.getPaddingBottom());
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemPosition = parent.getChildAdapterPosition(view);
        int childCount = parent.getAdapter().getItemCount();
        int size = ScreenUtils.dp2px(parent.getContext(), spaceSize);
        if (GridItemUtils.isLastRaw(parent, itemPosition, colNum, childCount)) {
            outRect.set(0, 0, size, 0);
        } else {
            outRect.set(0, 0, size, size);
        }
    }

    public static class Builder {

        private int colNum;
        private int spaceSize = DEFAULT_HEIGHT;
        private RecyclerView recyclerView;

        public Builder(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        public Builder setColNum(int colNum) {
            this.colNum = colNum;
            return this;
        }

        public Builder setSpaceSize(int spaceSize) {
            this.spaceSize = spaceSize;
            return this;
        }

        public GridSpaceItemDecoration build() {
            return new GridSpaceItemDecoration(this);
        }
    }
}
