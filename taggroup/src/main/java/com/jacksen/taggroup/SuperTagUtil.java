package com.jacksen.taggroup;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jacksen on 2017/7/8.
 */

public class SuperTagUtil {

    public static final int DEFAULT_TAG_BG_COLOR = Color.parseColor("#E0EEE0");

    public static final int DEFAULT_TAG_BORDER_COLOR = Color.RED;

    public static final float DEFAULT_HORIZONTAL_PADDING = 5; // dp

    public static final float DEFAULT_VERTICAL_PADDING = 5; // dp

    public static final float DEFAULT_HORIZONTAL_SPACING = 10; // dp

    public static final float DEFAULT_VERTICAL_SPACING = 10; // dp

    public static final int DEFAULT_MAX_SELECTED_NUM = 5;

    /**
     * dp to px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    /**
     * sp to px
     *
     * @param context
     * @param spValue
     * @return
     */
    public static float sp2px(Context context, float spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * generate view id
     *
     * @return
     */
    public static int generateViewId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return View.generateViewId();
        } else {
            for (; ; ) {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        }
    }
}
