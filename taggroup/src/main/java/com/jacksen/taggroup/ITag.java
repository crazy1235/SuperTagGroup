package com.jacksen.taggroup;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * @author jacksen
 * @since 2016
 */

public interface ITag {

    CharSequence getText();

    int getId();

    float getCornerRadius();

    void setCornerRadius(float cornerRadius);

    Drawable getBackgroundDrawable();

    @DrawableRes
    int getBackgroundResourceId();

    void tagBgDrawable(int drawableRes);

    boolean isAppendTag();

    void setHorizontalPadding(int horizontalPadding);

    int getHorizontalPadding();

    void setVerticalPadding(int verticalPadding);

    int getVerticalPadding();

    void setTextSize(float textSize);

    float getTextSize();

    void setTextColor(int textColor);

    int getTextColor();

    void setBorderWidth(float borderWidth);

    float getBorderWidth();

    void setBorderColor(int borderColor);

    int getBorderColor();

    void setTagBgColor(int tagBgColor);

    int getTagBgColor();

    void setTagCheckedBgColor(int tagCheckedBgColor);

    int getTagCheckedBgColor();

    void setCheckedBorderColor(int checkedBorderColor);

    int getCheckedBorderColor();

}
