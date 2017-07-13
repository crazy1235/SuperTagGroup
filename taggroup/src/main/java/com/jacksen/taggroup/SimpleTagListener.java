package com.jacksen.taggroup;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by jacksen on 2016
 */

public abstract class SimpleTagListener implements OnTagClickListener {

    @Override
    public boolean onTagClick(int position, ITag tag) {
        if (tag.isAppendTag()) {
            onAppendTagClick(position, tag);
        } else {
            onNormalTagClick(position, tag);
        }
        return true;
    }

    @Override
    public void onSelected(SparseArray<View> selectedViews) {

    }

    public abstract void onAppendTagClick(int position, ITag itag);

    public abstract void onNormalTagClick(int position, ITag itag);
}
