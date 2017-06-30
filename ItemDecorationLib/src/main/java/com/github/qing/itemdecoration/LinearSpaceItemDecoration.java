package com.github.qing.itemdecoration;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.qing.itemdecoration.utils.ScreenUtils;

/**
 * Created by dcq on 2017/3/21.
 * 适合LinearLayoutManager的RecyclerView,设置空白间隔
 */

public class LinearSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int spaceSize = 10;
    private int orientation;
    private int headerSpace;
    private int footerSpace;

    private LinearSpaceItemDecoration(Builder builder) {
        spaceSize = builder.spaceSize;
        orientation = builder.orientation;
        headerSpace = builder.headerSpace;
        footerSpace = builder.footerSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int itemCount = parent.getAdapter().getItemCount();

        int size = ScreenUtils.dp2px(parent.getContext(), spaceSize);
        int headerSize = ScreenUtils.dp2px(parent.getContext(), headerSpace);
        int footerSize = ScreenUtils.dp2px(parent.getContext(), footerSpace);

        if (orientation == LinearLayoutManager.VERTICAL) {
            if (position == 0) {
                outRect.set(0, headerSize, 0, size);
            } else if (position == itemCount - 1) {
                outRect.set(0, 0, 0, footerSize);
            } else {
                outRect.set(0, 0, 0, size);
            }
        } else {
            if (position == 0) {
                outRect.set(headerSize, 0, size, 0);
            } else if (position == itemCount - 1) {
                outRect.set(0, 0, footerSize, 0);
            } else {
                outRect.set(0, 0, size, 0);
            }
        }
    }

    public static class Builder {
        private int spaceSize;
        private int orientation = LinearLayoutManager.VERTICAL;
        private int headerSpace = 0;
        private int footerSpace = 0;

        public Builder setSpaceSize(int spaceSize) {
            this.spaceSize = spaceSize;
            return this;
        }

        public Builder setOrientation(@Orientation int orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder setHeaderSpace(int spaceSize) {
            this.headerSpace = spaceSize;
            return this;
        }

        public Builder setFooterSpace(int spaceSize) {
            this.footerSpace = spaceSize;
            return this;
        }

        public LinearSpaceItemDecoration build() {
            return new LinearSpaceItemDecoration(this);
        }
    }
}
