package com.jacksen.taggroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;

/**
 * Created by jacksen on 2017/7/7.
 */
public class SuperTagGroup extends ViewGroup {

    private boolean isAppendMode = false;

    private float horizontalSpace;

    private float verticalSpace;

    private float horizontalPadding;

    private float verticalPadding;

    private float textSize;

    private int textColor;

    private float cornerRadius;

    private float borderWidth;

    private int borderColor;

    private int bgColor;

    public SuperTagGroup(Context context) {
        this(context, null);
    }

    public SuperTagGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperTagGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SuperTagGroup, defStyleAttr, R.style.SuperTagGroup);
        isAppendMode = ta.getBoolean(R.styleable.SuperTagGroup_is_append_mode, false);

        horizontalSpace = ta.getDimension(R.styleable.SuperTagGroup_horizontal_space, 0);
        verticalSpace = ta.getDimension(R.styleable.SuperTagGroup_vertical_space, 0);

        horizontalPadding = ta.getDimension(R.styleable.SuperTagGroup_horizontal_padding, 0);
        verticalPadding = ta.getDimension(R.styleable.SuperTagGroup_vertical_padding, 0);

        textSize = ta.getDimension(R.styleable.SuperTagGroup_text_size, 0);
        textColor = ta.getColor(R.styleable.SuperTagGroup_text_color, 0);

        cornerRadius = ta.getDimension(R.styleable.SuperTagGroup_corner_radius, 0);

        borderWidth = ta.getDimension(R.styleable.SuperTagGroup_border_width, 0);
        borderColor = ta.getColor(R.styleable.SuperTagGroup_border_color, 0);

        bgColor = ta.getColor(R.styleable.SuperTagGroup_bg_color, 0);

        ta.recycle();
    }

    private void init() {

    }


    private int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    private float sp2px(float spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, getResources().getDisplayMetrics());
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
