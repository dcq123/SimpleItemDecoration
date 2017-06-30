package com.github.qing.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.qing.itemdecoration.utils.GridItemUtils;

/**
 * Created by dcq on 2017/3/21.
 * 适配GridLayoutManager，绘制网格线
 */

@SuppressWarnings("SuspiciousNameCombination")
public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int DEFAULT_HEIGHT = 2;
    private Drawable mDivider;
    private Builder mBuilder;
    private Drawable mLastDivider;

    private GridDividerItemDecoration(Builder builder) {
        this.mBuilder = builder;
        if (builder.dividerDrawable == null) {
            mDivider = new ColorDrawable(Color.GRAY) {
                @Override
                public int getIntrinsicHeight() {
                    return mBuilder.dividerHeight;
                }

                @Override
                public int getIntrinsicWidth() {
                    return mBuilder.dividerHeight;
                }
            };
            ((ColorDrawable) mDivider).setColor(builder.dividerColor);
        } else {
            mDivider = builder.dividerDrawable;
        }

        if (builder.lastDividerDrawable == null) {
            if (builder.lastDividerHeight == 0) {
                mLastDivider = mDivider;
            } else {
                mLastDivider = new ColorDrawable(Color.GRAY) {
                    @Override
                    public int getIntrinsicHeight() {
                        return mBuilder.lastDividerHeight;
                    }

                    @Override
                    public int getIntrinsicWidth() {
                        return mBuilder.lastDividerHeight;
                    }
                };
                ((ColorDrawable) mLastDivider).setColor(builder.lastDividerColor);
            }
        } else {
            mLastDivider = builder.dividerDrawable;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            // 最后一行，不需要绘制底边线
            if (GridItemUtils.isLastRaw(parent, i, mBuilder.colNum, childCount)) {
                continue;
            }

            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            // 最后一列，不需要绘制右边线
            if (GridItemUtils.isLastColumn(parent, i, mBuilder.colNum, childCount)) {
                continue;
            }

            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int spanCount = mBuilder.colNum;
        int itemPosition = parent.getChildAdapterPosition(view);
        int childCount = parent.getAdapter().getItemCount();
        if (GridItemUtils.isLastRaw(parent, itemPosition, spanCount, childCount)) {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else if (GridItemUtils.isLastColumn(parent, itemPosition, spanCount, childCount)) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
        }
    }

    public static class Builder {

        private int colNum;
        private Drawable dividerDrawable;
        private int dividerHeight = DEFAULT_HEIGHT;
        private int dividerColor = Color.GRAY;
        private int lastDividerHeight = 0;
        private int lastDividerColor = Color.GRAY;
        private Drawable lastDividerDrawable;

        public Builder setColNum(int colNum) {
            this.colNum = colNum;
            return this;
        }

        public Builder setDividerColor(int dividerColor) {
            this.dividerColor = dividerColor;
            return this;
        }

        public Builder setDividerHeight(int dividerHeight) {
            this.dividerHeight = dividerHeight;
            return this;
        }

        public Builder setLastDividerHeight(int dividerHeight) {
            this.lastDividerHeight = dividerHeight;
            return this;
        }

        public Builder setLastDividerColor(int lastDividerColor) {
            this.lastDividerHeight = lastDividerColor;
            return this;
        }

        public Builder setLastDivider(Drawable lastDivider) {
            this.lastDividerDrawable = lastDivider;
            return this;
        }

        public Builder setDividerDrawable(Drawable dividerDrawable) {
            this.dividerDrawable = dividerDrawable;
            return this;
        }

        public GridDividerItemDecoration build() {
            return new GridDividerItemDecoration(this);
        }
    }
}
