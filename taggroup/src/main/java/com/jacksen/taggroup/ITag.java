package com.jacksen.taggroup;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * @author jacksen
 *         <br/>
 * @since 2017/7/9
 */

public interface ITag {

    CharSequence getText();

    int getId();

    float getCornerRadius();

    Drawable getBackgroundDrawable();

    @DrawableRes
    int getBackgroundResourceId();

    boolean isAppendTag();

}
