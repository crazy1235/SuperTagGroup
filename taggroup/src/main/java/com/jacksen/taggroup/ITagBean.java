package com.jacksen.taggroup;

import android.graphics.drawable.Drawable;

/**
 * Created by jacksen on 2016
 */

public class ITagBean implements ITag {

    private CharSequence tag;

    private int horizontalPadding;

    private int verticalPadding;

    private float cornerRadius;

    private float borderWidth;

    private int borderColor;

    private int bgColor;

    private int checkedBorderColor;

    private int checkedBgColor;

    private Drawable backgroundDrawable;

    private int backgroundResourceId;

    private boolean isAppendTag;

    private float textSize;

    private int textColor;

    private ITagBean(Builder builder) {
        this.tag = builder.tag;
        this.horizontalPadding = builder.horizontalPadding;
        this.verticalPadding = builder.verticalPadding;
        this.cornerRadius = builder.cornerRadius;
        this.borderWidth = builder.borderWidth;
        this.borderColor = builder.borderColor;
        this.bgColor = builder.bgColor;
        this.backgroundDrawable = builder.backgroundDrawable;
        this.backgroundResourceId = builder.backgroundResourceId;
        this.isAppendTag = builder.isAppendTag;
        this.textSize = builder.textSize;
        this.textColor = builder.textColor;
        this.checkedBgColor = builder.checkedBgColor;
        this.checkedBorderColor = builder.checkedBorderColor;
    }

    @Override
    public CharSequence getText() {
        return tag;
    }

    @Override
    public int getId() {
        return SuperTagUtil.generateViewId();
    }

    public int getHorizontalPadding() {
        return horizontalPadding;
    }

    @Override
    public void setVerticalPadding(int verticalPadding) {
        this.verticalPadding = verticalPadding;
    }

    public int getVerticalPadding() {
        return verticalPadding;
    }

    @Override
    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    @Override
    public float getTextSize() {
        return this.textSize;
    }

    @Override
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    @Override
    public int getTextColor() {
        return this.textColor;
    }

    @Override
    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    @Override
    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    @Override
    public Drawable getBackgroundDrawable() {
        return this.backgroundDrawable;
    }

    @Override
    public int getBackgroundResourceId() {
        return this.backgroundResourceId;
    }

    @Override
    public void tagBgDrawable(int drawableRes) {
        this.backgroundResourceId = drawableRes;
    }

    @Override
    public boolean isAppendTag() {
        return this.isAppendTag;
    }

    @Override
    public void setHorizontalPadding(int horizontalPadding) {
        this.horizontalPadding = horizontalPadding;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    @Override
    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderColor() {
        return borderColor;
    }

    @Override
    public void setTagBgColor(int tagBgColor) {
        this.bgColor = tagBgColor;
    }

    @Override
    public int getTagBgColor() {
        return this.bgColor;
    }

    @Override
    public void setTagCheckedBgColor(int tagCheckedBgColor) {
        this.checkedBgColor = tagCheckedBgColor;
    }

    @Override
    public int getTagCheckedBgColor() {
        return this.checkedBgColor;
    }

    @Override
    public void setCheckedBorderColor(int checkedBorderColor) {
        this.checkedBorderColor = checkedBorderColor;
    }

    @Override
    public int getCheckedBorderColor() {
        return this.checkedBorderColor;
    }

    public int getBgColor() {
        return bgColor;
    }

    public static class Builder {
        private CharSequence tag;

        private int horizontalPadding;

        private int verticalPadding;

        private float cornerRadius;

        private float borderWidth;

        private int borderColor;

        private int bgColor;

        private Drawable backgroundDrawable;

        private int backgroundResourceId;

        private boolean isAppendTag;

        private float textSize;

        private int textColor;

        private int checkedBorderColor;

        private int checkedBgColor;

        public Builder setTag(CharSequence tag) {
            this.tag = tag;
            return this;
        }

        public Builder setHorizontalPadding(int horizontalPadding) {
            this.horizontalPadding = horizontalPadding;
            return this;
        }

        public Builder setVerticalPadding(int verticalPadding) {
            this.verticalPadding = verticalPadding;
            return this;
        }

        public Builder setCornerRadius(float cornerRadius) {
            this.cornerRadius = cornerRadius;
            return this;
        }

        public Builder setBorderWidth(float borderWidth) {
            this.borderWidth = borderWidth;
            return this;
        }

        public Builder setBorderColor(int borderColor) {
            this.borderColor = borderColor;
            return this;
        }

        public Builder setBgColor(int bgColor) {
            this.bgColor = bgColor;
            return this;
        }

        public Builder setBackgroundDrawable(Drawable backgroundDrawable) {
            this.backgroundDrawable = backgroundDrawable;
            return this;
        }

        public Builder setBackgroundResourceId(int backgroundResourceId) {
            this.backgroundResourceId = backgroundResourceId;
            return this;
        }

        public Builder setAppendTag(boolean appendTag) {
            isAppendTag = appendTag;
            return this;
        }

        public Builder setTextSize(float textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder setTextColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder setCheckedBorderColor(int checkedBorderColor) {
            this.checkedBorderColor = checkedBorderColor;
            return this;
        }

        public Builder setCheckedBgColor(int checkedBgColor) {
            this.checkedBgColor = checkedBgColor;
            return this;
        }

        public ITagBean create() {
            return new ITagBean(this);
        }
    }

}
