package com.jacksen.taggroup;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author jacksen
 * @since 2016
 */

public class TagBgDrawable extends Drawable {

    private Paint bgPaint;

    private Paint borderPaint;

    private RectF bgRectF;

    private RectF borderRectF;

    private float cornerRadius;

    private boolean drawBorderFlag = false;


    private TagBgDrawable() {

    }

    public TagBgDrawable(int bgColor, RectF bgRectF, int borderColor, RectF borderRectF, float borderWidth, float cornerRadius) {
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(bgColor);
        bgPaint.setStyle(Paint.Style.FILL);
        this.bgRectF = bgRectF;

        if (borderWidth != 0) {
            borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            borderPaint.setStyle(Paint.Style.STROKE);
            borderPaint.setColor(borderColor);
            borderPaint.setStrokeWidth(borderWidth);
            this.borderRectF = borderRectF;

            drawBorderFlag = true;
        }

        this.cornerRadius = cornerRadius;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawRoundRect(bgRectF, cornerRadius, cornerRadius, bgPaint);
        if (drawBorderFlag){
            canvas.drawRoundRect(borderRectF, cornerRadius, cornerRadius, borderPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        bgPaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        bgPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
