package com.github.qing.itemdecoration.utils;

import android.content.Context;

/**
 * Created by dingchangqing on 2017/3/21.
 * 单位转换工具
 */

public class ScreenUtils {


    public static int dp2px(Context context, int dpValue) {
        int density = (int) context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }
}
