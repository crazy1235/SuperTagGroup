package com.jacksen.taggroup;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by jacksen on 2016
 */

public interface OnTagClickListener {

    boolean onTagClick(int position, ITag tag);

    void onSelected(SparseArray<View> selectedViews);

}
