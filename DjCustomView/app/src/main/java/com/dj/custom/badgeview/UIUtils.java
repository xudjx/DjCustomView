package com.dj.custom.badgeview;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class UIUtils {

    public static int dip2Px(int dp) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return (int) (displayMetrics.density * dp);
    }
}
