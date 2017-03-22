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

    private LinearSpaceItemDecoration(Builder builder) {
        spaceSize = builder.spaceSize;
        orientation = builder.orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int itemCount = parent.getAdapter().getItemCount();
        // skip last item
        if (position == itemCount - 1) {
            return;
        }
        int size = ScreenUtils.dp2px(parent.getContext(), spaceSize);
        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, size);
        } else {
            outRect.set(0, 0, size, 0);
        }
    }

    public static class Builder {

        private int spaceSize;
        private int orientation = LinearLayoutManager.VERTICAL;

        public Builder setSpaceSize(int spaceSize) {
            this.spaceSize = spaceSize;
            return this;
        }

        public Builder setOrientation(@Orientation int orientation) {
            this.orientation = orientation;
            return this;
        }

        public LinearSpaceItemDecoration build() {
            return new LinearSpaceItemDecoration(this);
        }
    }
}
