package com.jacksen.taggroup;

/**
 * Created by jacksen on 2017/7/10.
 */

public interface OnTagClickListener {

    boolean onTagClick(int position, ITag tag);

    abstract class OnClickListener implements OnTagClickListener {

        @Override
        public boolean onTagClick(int position, ITag tag) {
            if (tag.isAppendTag()) {
                onAppendTagClick(position, tag);
            } else {
                onNormalTagClick(position, tag);
            }
            return true;
        }

        public abstract void onAppendTagClick(int position, ITag itag);

        public abstract void onNormalTagClick(int position, ITag itag);
    }
}
