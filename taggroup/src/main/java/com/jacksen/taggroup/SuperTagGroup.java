package com.jacksen.taggroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jacksen on 2016
 */
public class SuperTagGroup extends ViewGroup {

    private float horizontalSpace;

    private float verticalSpace;

    // tag
    private int horizontalPadding;
    private int verticalPadding;
    private float textSize;
    private int textColor;
    private float cornerRadius;
    private float borderWidth;
    private int borderColor;
    private int tagBgColor;
    private int tagBgDrawable;
    private int tagBgCheckedColor;
    private int tagBorderCheckedColor;


    //
    private int maxSelectedNum = -1;

    private OnTagClickListener onTagClickListener;

    private MotionEvent motionEvent;

    // 选中子View 的集合
    private SparseArray<View> selectedViews = new SparseArray<>();

    // append tag
    private SuperTagView latestAppendTagView;
    private int appendTagIndex = -1;

    public SuperTagGroup(Context context) {
        this(context, null);
    }

    public SuperTagGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperTagGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SuperTagGroup, defStyleAttr, R.style.SuperTagGroup);

        horizontalSpace = ta.getDimension(R.styleable.SuperTagGroup_horizontal_spacing, 0);
        verticalSpace = ta.getDimension(R.styleable.SuperTagGroup_vertical_spacing, 0);

        horizontalPadding = (int) ta.getDimension(R.styleable.SuperTagGroup_tag_horizontal_padding, 0);
        verticalPadding = (int) ta.getDimension(R.styleable.SuperTagGroup_tag_vertical_padding, 0);

        textSize = ta.getDimension(R.styleable.SuperTagGroup_tag_text_size, 0);
        textColor = ta.getColor(R.styleable.SuperTagGroup_tag_text_color, 0);

        cornerRadius = ta.getDimension(R.styleable.SuperTagGroup_tag_corner_radius, 0);

        borderWidth = ta.getDimension(R.styleable.SuperTagGroup_tag_border_width, 0);
        borderColor = ta.getColor(R.styleable.SuperTagGroup_tag_border_color, 0);
        tagBorderCheckedColor = ta.getColor(R.styleable.SuperTagGroup_tag_border_checked_color, 0);


        tagBgColor = ta.getColor(R.styleable.SuperTagGroup_tag_bg_color, 0);
        tagBgCheckedColor = ta.getColor(R.styleable.SuperTagGroup_tag_bg_checked_color, 0);

        tagBgDrawable = ta.getResourceId(R.styleable.SuperTagGroup_tag_bg_drawable, 0);

        maxSelectedNum = ta.getInt(R.styleable.SuperTagGroup_max_selected_count, -1);

        ta.recycle();

        init();
    }

    private void init() {
        setClickable(true);

        horizontalSpace = SuperTagUtil.dp2px(getContext(), horizontalSpace);
        verticalSpace = SuperTagUtil.dp2px(getContext(), verticalSpace);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("SuperTagGroup", "onMeasure");
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

        // the width & height final result
        int resultWidth = 0;
        int resultHeight = 0;

        int lineWidth = 0;
        int lineHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }

            if (!(child instanceof SuperTagView)) {
                throw new IllegalStateException("SuperTagGroup can only has SuperTagView child");
            }

            // measure child
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            // 这里要记得加上子view的margin值
//            MarginLayoutParams childLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            lineHeight = Math.max(childHeight, lineHeight);

            if (lineWidth + childWidth > widthSize - paddingLeft - paddingRight) { // 需要换一行
                resultWidth = Math.max(resultWidth, lineWidth); // 每一行都进行比较，最终得到最宽的值
                resultHeight += verticalSpace + lineHeight;

                lineWidth = (int) (childWidth + horizontalSpace); // 新的一行的宽度
                lineHeight = childHeight; // 新的一行的高度
            } else {
                // 当前行的宽度
                lineWidth += childWidth + horizontalSpace;
                // 当前行最大的高度
                lineHeight = Math.max(lineHeight, childHeight);
            }

            // 最后一个, 需要再次比较宽
            if (i == getChildCount() - 1) {
                resultWidth = Math.max(resultWidth, lineWidth);
            }
        }

        resultWidth += paddingRight + paddingLeft;
        // 布局最终的高度
        resultHeight += lineHeight + paddingBottom + paddingTop;

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : resultWidth, heightMode == MeasureSpec.EXACTLY ? heightSize : resultHeight);

//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("SuperTagGroup", "onLayout");
        // padding
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();

        int lineHeight = 0;

        // 子view的左侧和顶部位置
        int childLeft = paddingLeft;
        int childTop = paddingTop;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }

            // 找到最后一个append tag
            if (((SuperTagView) child).isAppendTag()) {
                if (appendTagIndex != -1 && latestAppendTagView != null) {
                    latestAppendTagView.setAppendTag(false);
                }
                appendTagIndex = i;
                ((SuperTagView) child).setAppendTag(true);
                latestAppendTagView = (SuperTagView) child;
            }

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            lineHeight = Math.max(lineHeight, childHeight);

            if (childLeft + childWidth + paddingRight > getWidth()) { // 需要换行
                childLeft = paddingLeft;
                childTop += lineHeight + verticalSpace;

                lineHeight = childHeight;
            }

            // 布局
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);

            // 计算下一个子view X的位置
            childLeft += childWidth + horizontalSpace;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.onTagClickListener = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            motionEvent = MotionEvent.obtain(event);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        if (motionEvent != null) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            View view = findChildView(x, y);
            int position = findChildViewPosition(view);

            if (view != null) {
                view.performClick();
                boolean result = selectChildView((SuperTagView) view, position);
                if (onTagClickListener != null) {
                    if (result) {
                        onTagClickListener.onSelected(selectedViews);
                    }
                    return onTagClickListener.onTagClick(position, ((SuperTagView) view).getITag());
                }
            }
            motionEvent = null;
        }
        return super.performClick();
    }

    /**
     * 选中一个子view，内部判断选中个数等逻辑
     *
     * @param view
     * @param position
     * @return
     */
    private boolean selectChildView(SuperTagView view, int position) {
        if (view.isAppendTag()) {
            return false;
        }
        if (view.isChecked()) { // 已经被check
            view.setChecked(false);
            selectedViews.remove(position);
            return true;
        } else {
            if (maxSelectedNum == -1 || selectedViews.size() < maxSelectedNum) { // 选中个数没超限
                view.setChecked(true);
                selectedViews.put(position, view);
            } else if (selectedViews.size() == 1 && maxSelectedNum == 1) { // 最多选中一个
                SuperTagView preView = (SuperTagView) selectedViews.valueAt(0);
                preView.setChecked(false);
                selectedViews.clear();
                view.setChecked(true);
                selectedViews.put(position, view);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据坐标查找子view
     *
     * @param x
     * @param y
     * @return
     */
    private SuperTagView findChildView(int x, int y) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view.getVisibility() == VISIBLE) {
                Rect rect = new Rect();
                view.getHitRect(rect);
                if (rect.contains(x, y)) {
                    return (SuperTagView) view;
                }
            }
        }
        return null;
    }

    /**
     * 查找子view的位置
     *
     * @param view
     * @return
     */
    private int findChildViewPosition(View view) {
        if (view == null) {
            return -1;
        }
        for (int i = 0; i < getChildCount(); i++) {
            if (view == getChildAt(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 设置tag组
     *
     * @param tagList
     */
    public void setTagList(List<ITag> tagList) {
        removeAllViews();
        appendTagList(tagList);
    }

    /**
     * 添加tag组
     *
     * @param tagList
     */
    public void appendTagList(List<ITag> tagList) {
        for (ITag tag : tagList) {
            appendTag(tag);
        }
    }

    /**
     * append一个tag
     *
     * @param tag
     */
    public void appendTag(ITag tag) {
        SuperTagView tagView = new SuperTagView(getContext());
        tagView.setITag(generateNewITag(tag));

        if (tag.isAppendTag() && appendTagIndex != -1) { // 如果已经有了append tag，把上一个append tag状态移除
            ((SuperTagView) getChildAt(appendTagIndex)).setAppendTag(false);
        }
        if (tag.isAppendTag() || appendTagIndex == -1) { // 添加append tag或者 目前没有append tag添加普通tag 的情况
            addView(tagView);
        } else {
            addView(tagView, appendTagIndex++); // 注意 ++ 操作
        }
    }

    /**
     * 将tag部分没有设置的属性应用从xml读取的值
     *
     * @param tag
     * @return
     */
    private ITag generateNewITag(ITag tag) {
        if (tag.getHorizontalPadding() == 0) {
            tag.setHorizontalPadding(horizontalPadding);
        }
        if (tag.getVerticalPadding() == 0) {
            tag.setVerticalPadding(verticalPadding);
        }
        if (tag.getTextSize() == 0) {
            tag.setTextSize(textSize);
        }
        if (tag.getTextColor() == 0) {
            tag.setTextColor(textColor);
        }
        if (tag.getCornerRadius() == 0) {
            tag.setCornerRadius(cornerRadius);
        }
        if (tag.getBorderWidth() == 0) {
            tag.setBorderWidth(borderWidth);
        }
        if (tag.getBorderColor() == 0) {
            tag.setBorderColor(borderColor);
        }
        if (tag.getTagBgColor() == 0) {
            tag.setTagBgColor(tagBgColor);
        }
        if (tag.getBackgroundResourceId() == 0) {
            tag.tagBgDrawable(tagBgDrawable);
        }
        if (tag.getCheckedBorderColor() != 0) {
            tag.setCheckedBorderColor(tagBorderCheckedColor);
        }
        if (tag.getTagCheckedBgColor() != 0) {
            tag.setTagBgColor(tagBgCheckedColor);
        }
        return tag;
    }


    @Override
    public void removeView(View view) {
        Log.d("SuperTagGroup", "((SuperTagView) view).isAppendTag():" + ((SuperTagView) view).isAppendTag());
        if (((SuperTagView) view).isAppendTag()) {
            appendTagIndex = -1;
        }
        super.removeView(view);
    }

    @Override
    public void removeViewAt(int index) {
        Log.d("SuperTagGroup", "getChildCount():" + getChildCount());
        removeView(getChildAt(index));
    }

    @Override
    public void removeViews(int start, int count) {
        final int end = start + count;
        if (start < 0 || count < 0 || end > getChildCount()) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = start; i < end; i++) {
            removeView(getChildAt(i));
        }
    }

    public void setMaxSelectedNum(int maxSelectedNum) {
        if (selectedViews.size() > maxSelectedNum) {
            selectedViews.clear();
        }
        this.maxSelectedNum = maxSelectedNum;
    }
}
