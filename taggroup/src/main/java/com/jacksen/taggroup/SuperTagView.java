package com.jacksen.taggroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.Checkable;

/**
 * Created by jacksen on 2016.
 */

public class SuperTagView extends android.support.v7.widget.AppCompatTextView implements Checkable {

    private boolean isAppendTag = false;

    private int horizontalPadding;
    private int verticalPadding;
    private float cornerRadius;
    private float borderWidth;

    private int borderColor;
    private int bgColor;

    private int borderCheckedColor;
    private int bgCheckedColor;

    private boolean shouldCustomDraw = true;

    private RectF bgRectF;
    private RectF borderRectF;

    private ITag iTag;

    public ITag getITag() {
        return iTag;
    }

    public void setITag(ITag iTag) {
        this.iTag = iTag;
        applyITag();
    }

    public void setHorizontalPadding(int horizontalPadding) {
        this.horizontalPadding = horizontalPadding;
    }

    public void setVerticalPadding(int verticalPadding) {
        this.verticalPadding = verticalPadding;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public void setBorderCheckedColor(int borderCheckedColor) {
        this.borderCheckedColor = borderCheckedColor;
    }

    public void setBgCheckedColor(int bgCheckedColor) {
        this.bgCheckedColor = bgCheckedColor;
    }

    public SuperTagView(Context context) {
        this(context, null);
    }

    public SuperTagView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperTagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SuperTagView, defStyleAttr, R.style.SuperTagGroup_TagView);

        isAppendTag = ta.getBoolean(R.styleable.SuperTagView_is_append_tag, false);

        horizontalPadding = (int) ta.getDimension(R.styleable.SuperTagView_horizontal_padding, SuperTagUtil.dp2px(context, SuperTagUtil.DEFAULT_HORIZONTAL_PADDING));
        verticalPadding = (int) ta.getDimension(R.styleable.SuperTagView_vertical_padding, SuperTagUtil.dp2px(context, SuperTagUtil.DEFAULT_VERTICAL_PADDING));

        cornerRadius = ta.getDimension(R.styleable.SuperTagView_corner_radius, 0);

        borderWidth = ta.getDimension(R.styleable.SuperTagView_border_width, 0);

        borderColor = ta.getColor(R.styleable.SuperTagView_border_color, SuperTagUtil.DEFAULT_TAG_BORDER_COLOR);
        bgColor = ta.getColor(R.styleable.SuperTagView_bg_color, SuperTagUtil.DEFAULT_TAG_BG_COLOR);

        borderCheckedColor = ta.getColor(R.styleable.SuperTagView_border_checked_color, 0);
        bgCheckedColor = ta.getColor(R.styleable.SuperTagView_bg_checked_color, 0);

        ta.recycle();

        init();
    }


    /**
     * init
     */
    private void init() {
        setGravity(Gravity.CENTER);

        resetITag();

        if (getBackground() != null) {
            shouldCustomDraw = false;
            return;
        }
        //
        borderWidth = SuperTagUtil.dp2px(getContext(), borderWidth);
        cornerRadius = SuperTagUtil.dp2px(getContext(), cornerRadius);

//        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);

        // init rect
        bgRectF = new RectF();
        borderRectF = new RectF();
    }

    public boolean isAppendTag() {
        return isAppendTag;
    }

    public void setAppendTag(boolean appendTag) {
        isAppendTag = appendTag;
    }

    /**
     * 针对在布局中添加的view 进行设置
     */
    private void resetITag() {
        if (iTag == null) {
            iTag = new ITagBean.Builder().setTag(getText())
                    .setCornerRadius(cornerRadius)
                    .setHorizontalPadding(horizontalPadding)
                    .setVerticalPadding(verticalPadding)
                    .setBorderColor(borderColor)
                    .setBorderWidth(borderWidth)
                    .setBgColor(bgColor)
                    .setAppendTag(isAppendTag)
                    .create();
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("SuperTagView", "onSizeChanged");
        if (shouldCustomDraw) {

            float left = borderWidth;
            float right = left + w - borderWidth * 2;

            float top = borderWidth;
            float bottom = top + h - borderWidth * 2;

            bgRectF.set(left, top, right, bottom);

            if (borderWidth != 0) {
                borderRectF.set(borderWidth / 2, borderWidth / 2, w - borderWidth / 2, h - borderWidth / 2);
            }
            setBackgroundDrawable(generateBackgroundDrawable());
        }
    }

    /**
     * 应用ITag属性
     */
    private void applyITag() {
        if (iTag != null) {
            setText(iTag.getText());
            setId(iTag.getId());
            this.cornerRadius = iTag.getCornerRadius();
            if (iTag.getBackgroundDrawable() != null) {
                setBackgroundDrawable(iTag.getBackgroundDrawable());
            } else if (iTag.getBackgroundResourceId() != 0) {
                setBackgroundResource(iTag.getBackgroundResourceId());
            }


            if (iTag.getHorizontalPadding() != 0) {
                setHorizontalPadding(iTag.getHorizontalPadding());
            }
            if (iTag.getVerticalPadding() != 0) {
                setVerticalPadding(iTag.getVerticalPadding());
            }
            if (iTag.getTextSize() != 0) {
                setTextSize(iTag.getTextSize());
            }
            if (iTag.getTextColor() != 0) {
                setTextColor(iTag.getTextColor());
            }
            if (iTag.getCornerRadius() != 0) {
                setCornerRadius(iTag.getCornerRadius());
            }
            if (iTag.getBorderWidth() != 0) {
                setBorderWidth(iTag.getBorderWidth());
            }
            if (iTag.getBorderColor() != 0) {
                setBorderColor(iTag.getBorderColor());
            }
            if (iTag.getTagBgColor() != 0) {
                setBgColor(iTag.getTagBgColor());
            }
            if (iTag.getCheckedBorderColor() != 0) {
                setBorderCheckedColor(iTag.getCheckedBorderColor());
            }
            if (iTag.getTagCheckedBgColor() != 0) {
                setBgCheckedColor(iTag.getTagCheckedBgColor());
            }

            requestLayout();
            invalidate();
        }
    }

    /**
     * background drawable
     *
     * @return
     */
    private Drawable generateBackgroundDrawable() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(CHECK_STATE, new TagBgDrawable(bgCheckedColor, bgRectF, borderCheckedColor, borderRectF, borderWidth, cornerRadius));
        stateListDrawable.addState(new int[]{}, new TagBgDrawable(bgColor, bgRectF, borderColor, borderRectF, borderWidth, cornerRadius));
        return stateListDrawable;
    }

    //
    private static final int[] CHECK_STATE = new int[]{android.R.attr.state_checked, android.R.attr.state_selected};

    private boolean isChecked;

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        int[] states = super.onCreateDrawableState(extraSpace + CHECK_STATE.length);
        if (isChecked()) {
            mergeDrawableStates(states, CHECK_STATE);
        }
        return states;
    }

    @Override
    public void setChecked(boolean checked) {
        if (this.isChecked != checked) {
            this.isChecked = checked;
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }
}
