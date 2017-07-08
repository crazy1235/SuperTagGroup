package com.jacksen.taggroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * Created by jacksen on 2017/7/8.
 */

public class SuperTagView extends android.support.v7.widget.AppCompatTextView {

    private boolean isAppendTag = false;

    private int horizontalPadding;

    private int verticalPadding;

    private float textSize;

    private int textColor;

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

//        textSize = ta.getDimension(R.styleable.SuperTagGroup_text_size, 0);
//        textColor = ta.getColor(R.styleable.SuperTagGroup_text_color, 0);

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

        if (getBackground() != null) {
            shouldCustomDraw = false;
            return;
        }

        //
        borderWidth = SuperTagUtil.dp2px(getContext(), borderWidth);
        cornerRadius = SuperTagUtil.dp2px(getContext(), cornerRadius);

        //
//        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);

//        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (shouldCustomDraw){
            float left = borderWidth;
            float right = left + w - borderWidth * 2;

            float top = borderWidth;
            float bottom = top + h - borderWidth * 2;

            bgRectF.set(left, top, right, bottom);

            borderRectF.set(0, 0, w, h);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (shouldCustomDraw) {
            // draw bg
            canvas.drawRoundRect(bgRectF, cornerRadius, cornerRadius, bgPaint);

            if (shouldDrawBorder()) {
                // draw border
                canvas.drawRoundRect(borderRectF, cornerRadius, cornerRadius, borderPaint);
            }
            canvas.translate(horizontalPadding, verticalPadding);
        }
        super.onDraw(canvas);
    }

    /**
     * judge should draw the border or not
     */
    private boolean shouldDrawBorder() {
        return borderWidth != 0;
    }
}
