package com.jacksen.taggroup;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;

/**
 * Created by jacksen on 2017/7/8.
 */

public class SuperTagUtil {

    public static final int DEFAULT_TAG_BG_COLOR = Color.parseColor("#E0EEE0");

    public static final int DEFAULT_TAG_BORDER_COLOR = Color.RED;

    public static final float DEFAULT_HORIZONTAL_PADDING = 5; // dp

    public static final float DEFAULT_VERTICAL_PADDING = 5; // dp

    public static final float DEFAULT_HORIZONTAL_SPACING = 10; // dp

    public static final float DEFAULT_VERTICAL_SPACINTG = 10; // dp

    /**
     * tags selected type
     */
    enum SelectType {
        NONE(0),
        SINGLE(1),
        MULTIPLE(2);
        int value;

        SelectType(int value) {
            this.value = value;
        }

        SelectType get(int value) {
            switch (value) {
                case 0:
                default:
                    return NONE;
                case 1:
                    return SINGLE;
                case 2:
                    return MULTIPLE;
            }
        }
    }

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
}
