package com.jacksen.taggroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
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

    private int tagBgColor;

    private int tagBgDrawable;


    ////

    private final int DEFAULT_MAX_SELECTED_NUM = 5;

    private final int maxSelectedNum = DEFAULT_MAX_SELECTED_NUM;


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

        horizontalPadding = ta.getDimension(R.styleable.SuperTagGroup_tag_horizontal_padding, 0);
        verticalPadding = ta.getDimension(R.styleable.SuperTagGroup_tag_vertical_padding, 0);

        textSize = ta.getDimension(R.styleable.SuperTagGroup_tag_text_size, 0);
        textColor = ta.getColor(R.styleable.SuperTagGroup_tag_text_color, 0);

        cornerRadius = ta.getDimension(R.styleable.SuperTagGroup_tag_corner_radius, 0);

        borderWidth = ta.getDimension(R.styleable.SuperTagGroup_tag_border_width, 0);
        borderColor = ta.getColor(R.styleable.SuperTagGroup_tag_border_color, 0);

        tagBgColor = ta.getColor(R.styleable.SuperTagGroup_tag_bg_color, 0);

        tagBgDrawable = ta.getResourceId(R.styleable.SuperTagGroup_tag_bg_drawable, 0);

        ta.recycle();
    }

    private void init() {

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    public void setTags() {

    }
}
