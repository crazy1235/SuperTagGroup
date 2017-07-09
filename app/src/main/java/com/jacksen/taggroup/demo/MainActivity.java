package com.jacksen.taggroup.demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jacksen.taggroup.SuperTagGroup;

public class MainActivity extends AppCompatActivity {

    private SuperTagGroup tagGroup;

    private String mNames[] = {"holidy", "shoot", "activity", "difference", "flow", "Lebron James",
            "ok", "android studio", "content", "strings", "padding", "child", "gradle", "back", "font", "turnoff"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagGroup = (SuperTagGroup) findViewById(R.id.tag_group);

//        initChildViews();
    }

    private void initChildViews() {
        ViewGroup.LayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < mNames.length; i++) {
            TextView view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextColor(Color.BLACK);
            view.setBackgroundResource(R.drawable.bg_tag_view);
            tagGroup.addView(view, lp);
        }
    }
}
