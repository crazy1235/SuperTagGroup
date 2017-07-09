package com.jacksen.taggroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

        horizontalSpace = ta.getDimension(R.styleable.SuperTagGroup_horizontal_space, SuperTagUtil.dp2px(context, SuperTagUtil.DEFAULT_HORIZONTAL_SPACING));
        verticalSpace = ta.getDimension(R.styleable.SuperTagGroup_vertical_space, SuperTagUtil.dp2px(context, SuperTagUtil.DEFAULT_VERTICAL_SPACINTG));

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

        init();
    }

    private void init() {
        horizontalSpace = SuperTagUtil.dp2px(getContext(), horizontalSpace);
        verticalSpace = SuperTagUtil.dp2px(getContext(), verticalSpace);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // width size & mode
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        // height size & mode
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // padding
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int linePaddingLeft = paddingLeft;
        int linePaddingTop = paddingTop;

        // the width & height final result
        int resultWidth = 0;
        int resultHeight = 0;

        int lineHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }

            // measure child
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams childLayoutParams = (MarginLayoutParams) child.getLayoutParams();

            // remember add child's margin value
            Log.d("SuperTagGroup", "childLayoutParams.leftMargin:" + childLayoutParams.leftMargin);
            int childWidth = child.getMeasuredWidth() + childLayoutParams.leftMargin + childLayoutParams.rightMargin;
            int childHeight = child.getMeasuredHeight() + childLayoutParams.topMargin + childLayoutParams.bottomMargin;

            lineHeight = Math.max(childHeight, lineHeight);

            if (linePaddingLeft + childWidth + paddingRight >= widthSize) { // need a new line
                linePaddingLeft = paddingLeft;
                linePaddingTop += lineHeight + getChildYOffset(childLayoutParams);
                lineHeight = 0;
            } else {
                linePaddingLeft += childWidth + getChildXOffset(childLayoutParams);
            }
        }


        resultHeight = linePaddingTop + lineHeight + paddingBottom;

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : resultWidth, heightMode == MeasureSpec.EXACTLY ? heightSize : resultHeight);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private float getChildXOffset(MarginLayoutParams layoutParams) {
        if (layoutParams.leftMargin != 0 || layoutParams.rightMargin != 0) {
            return 0;
        }
        return horizontalSpace;
    }

    private float getChildYOffset(MarginLayoutParams layoutParams) {
        if (layoutParams.topMargin != 0 || layoutParams.bottomMargin != 0) {
            return 0;
        }
        return verticalSpace;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // padding
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int childLeft = paddingLeft;
        int childTop = paddingTop;

        int lineHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            lineHeight = Math.max(lineHeight, childHeight);

            if (childLeft + childWidth + paddingRight > getWidth()) {
                childLeft = paddingLeft;
                childTop += verticalSpace + lineHeight;
                lineHeight = childHeight;
            }

            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            childLeft += childWidth + horizontalSpace;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public void setTags() {

    }
}
