package com.jacksen.taggroup.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jacksen.taggroup.ITag;
import com.jacksen.taggroup.ITagBean;
import com.jacksen.taggroup.OnTagClickListener;
import com.jacksen.taggroup.SuperTagGroup;
import com.jacksen.taggroup.SuperTagUtil;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SuperTagGroup tagGroup;

    private String mNames[] = {"演员", "海阔天空", "变了", "Beyond", "Fade", "Lebron James",
            "父亲写的散文诗", "android studio", "xcode", "王菲", "All of me", "刘德华", "Cover", "WTF", "12134", "山丘"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagGroup = (SuperTagGroup) findViewById(R.id.tag_group);

        tagGroup.setOnTagClickListener(new OnTagClickListener.OnClickListener() {
            @Override
            public void onAppendTagClick(int position, ITag itag) {
                Toast.makeText(MainActivity.this, itag.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNormalTagClick(int position, ITag itag) {
                Toast.makeText(MainActivity.this, itag.getText(), Toast.LENGTH_SHORT).show();
            }
        });
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
}
