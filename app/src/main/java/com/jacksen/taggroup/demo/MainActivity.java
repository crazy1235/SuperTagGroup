package com.jacksen.taggroup.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jacksen.taggroup.ITag;
import com.jacksen.taggroup.ITagBean;
import com.jacksen.taggroup.SimpleTagListener;
import com.jacksen.taggroup.SuperTagGroup;
import com.jacksen.taggroup.SuperTagUtil;
import com.jacksen.taggroup.SuperTagView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SuperTagGroup tagGroup;

    private TextView checked_views_tv;
    private TextView clicked_view_tv;

    private SuperTagView limitOne, limitFive, noLimit;

    private String mNames[] = {"演员", "海阔天空", "变了", "Beyond", "Fade", "Lebron James",
            "父亲写的散文诗", "android studio", "xcode", "王菲", "All of me", "刘德华", "Cover", "WTF", "12134", "山丘"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagGroup = (SuperTagGroup) findViewById(R.id.tag_group);

        tagGroup.setOnTagClickListener(new SimpleTagListener() {
            @Override
            public void onAppendTagClick(int position, ITag itag) {
                Toast.makeText(MainActivity.this, "我是一个append tag", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNormalTagClick(int position, ITag itag) {
                clicked_view_tv.setText("position: " + position + " -- tag's text: " + itag.getText());
            }

            @Override
            public void onSelected(SparseArray<View> selectedViews) {
                super.onSelected(selectedViews);
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < selectedViews.size() - 1; i++) {
                    result.append(selectedViews.keyAt(i)).append(", ");
                }
                result.append(selectedViews.keyAt(selectedViews.size() - 1));
                checked_views_tv.setText("选中tag的下标集合: " + result);
            }
        });

        tagGroup.setMaxSelectedNum(5);

        checked_views_tv = (TextView) findViewById(R.id.checked_views_tv);
        clicked_view_tv = (TextView) findViewById(R.id.clicked_view_tv);

        limitOne = (SuperTagView) findViewById(R.id.limit_one);
        limitFive = (SuperTagView) findViewById(R.id.limit_five);
        noLimit = (SuperTagView) findViewById(R.id.no_limit);

        limitOne.setOnClickListener(this);
        limitFive.setOnClickListener(this);
        noLimit.setOnClickListener(this);
    }

    private void addOneNewTag() {
        ITagBean.Builder builder = new ITagBean.Builder();
        ITagBean tagBean = builder.setTag(mNames[new Random().nextInt(mNames.length - 1)])
                .setCornerRadius(SuperTagUtil.dp2px(this, 7.0f))
                .setHorizontalPadding(SuperTagUtil.dp2px(this, 10))
                .create();
        tagGroup.appendTag(tagBean);
    }

    private void removeOneNewTag() {
        if (tagGroup.getChildCount() > 0) {
            tagGroup.removeViewAt(tagGroup.getChildCount() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                addOneNewTag();
                break;
            case R.id.menu_remove:
                removeOneNewTag();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.limit_one:
                tagGroup.setMaxSelectedNum(1);
                break;
            case R.id.limit_five:
                tagGroup.setMaxSelectedNum(5);
                break;
            case R.id.no_limit:
                tagGroup.setMaxSelectedNum(-1);
                break;
        }
    }
}
