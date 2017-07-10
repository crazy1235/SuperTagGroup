package com.jacksen.taggroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;

/**
 * Created by jacksen on 2017/7/8.
 */

public class SuperTagView extends android.support.v7.widget.AppCompatTextView {

    private boolean isAppendTag = false;

    private int horizontalPadding;

    private int verticalPadding;

    private float cornerRadius;

    private float borderWidth;

    private int borderColor;

    private int bgColor;

    //
    private boolean shouldCustomDraw = true;

    private Paint bgPaint;

    private Paint borderPaint;

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

        // init paint
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(bgColor);


        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(borderColor);

        // init rect
        bgRectF = new RectF();
        borderRectF = new RectF();

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

            setBackground(generateBackgroundDrawable());
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
                setBackground(iTag.getBackgroundDrawable());
            } else if (iTag.getBackgroundResourceId() != 0) {
                setBackgroundResource(iTag.getBackgroundResourceId());
            }

        }
    }

    private Drawable generateBackgroundDrawable() {
        return new TagBgDrawable(bgColor, bgRectF, borderColor, borderRectF, borderWidth, cornerRadius);
    }

}
