# SuperTagGroup
SuperTagGroup

---

## demos

 ![img1](/others/demo1.gif)    ![img1](/others/demo2.gif) 
 
 ![img1](/others/demo3.gif)    
 
 ---
 
## how to use it?
 
 - 添加库依赖
 
 ```
     compile 'com.jacksen:supertaggroup:1.1'
 ```
 
 - 在布局文件中添加SuperTagGroup
 
 
     > 可直接在SuperTagGroup布局中添加标签SuperTagView
 
 
 
 ```
            <com.jacksen.taggroup.SuperTagGroup
                 android:id="@+id/tag_group"
                 android:layout_width="match_parent"
                 android:layout_height="wrap_content"
                 android:padding="10dp"
                 app:horizontal_spacing="5dp"
                 app:max_selected_count="1"
                 app:vertical_spacing="5dp">
     
                 <com.jacksen.taggroup.SuperTagView
                     android:layout_width="wrap_content"
                     android:layout_height="wrap_content"
                     android:background="@drawable/bg_tag"
                     android:paddingBottom="10dp"
                     android:paddingLeft="15dp"
                     android:paddingRight="15dp"
                     android:paddingTop="10dp"
                     android:text="喜欢你"
                     android:textSize="16sp" />
     
                 <com.jacksen.taggroup.SuperTagView
                     android:layout_width="wrap_content"
                     android:layout_height="wrap_content"
                     android:text="那双眼动人"
                     android:textSize="16sp"
                     app:bg_checked_color="#7cd2c3"
                     app:bg_color="#afe1af"
                     app:border_checked_color="@color/colorPrimary"
                     app:border_width="0dp"
                     app:corner_radius="10dp"
                     app:horizontal_padding="25dp"
                     app:vertical_padding="10dp" />
     
                 <com.jacksen.taggroup.SuperTagView
                     android:layout_width="wrap_content"
                     android:layout_height="wrap_content"
                     android:padding="10dp"
                     android:text="笑声迷人"
                     android:textSize="16sp" />
     
                 <com.jacksen.taggroup.SuperTagView
                     android:layout_width="wrap_content"
                     android:layout_height="wrap_content"
                     android:text="Poseidon"
                     app:corner_radius="10dp"
                     app:horizontal_padding="20dp"
                     app:vertical_padding="10dp" />
     
                 <com.jacksen.taggroup.SuperTagView
                     android:layout_width="wrap_content"
                     android:layout_height="wrap_content"
                     android:layout_margin="20dp"
                     android:text="Apollo"
                     app:corner_radius="10dp" />
     
                 <com.jacksen.taggroup.SuperTagView
                     android:layout_width="wrap_content"
                     android:layout_height="wrap_content"
                     android:text="Medusa" />
     
                 <com.jacksen.taggroup.SuperTagView
                     android:layout_width="wrap_content"
                     android:layout_height="wrap_content"
                     android:text="Prometheus"
                     app:corner_radius="5dp" />
     
     
                 <com.jacksen.taggroup.SuperTagView
                     android:layout_width="wrap_content"
                     android:layout_height="wrap_content"
                     android:text="添加更多"
                     android:textSize="16sp"
                     app:bg_color="#edb9a1"
                     app:border_width="0.5dp"
                     app:corner_radius="10dp"
                     app:horizontal_padding="20dp"
                     app:is_append_tag="true"
                     app:vertical_padding="10dp" />
     
             </com.jacksen.taggroup.SuperTagGroup>
 ```

- 运行过程中也可以动态添加删除tag

 ```
    private void addOneNewTag() {
        ITagBean.Builder builder = new ITagBean.Builder();
        ITagBean tagBean = builder.setTag(mNames[new Random().nextInt(mNames.length - 1)])
                .setCornerRadius(SuperTagUtil.dp2px(this, 7.0f))
                .setHorizontalPadding(SuperTagUtil.dp2px(this, 10))
                .setBgColor(colors[new Random().nextInt(colors.length - 1)])
                .create();
        tagGroup.appendTag(tagBean);
    }

    private void removeOneNewTag() {
        if (tagGroup.getChildCount() > 0) {
            tagGroup.removeViewAt(tagGroup.getChildCount() - 1);
        }
    }
    
```

- 设置选中个数

```
    tagGroup.setMaxSelectedNum(5); // -1 表示不限制
```


---

## 属性介绍

### SuperTagGroup

```
        <attr name="horizontal_spacing" format="dimension" />       // 标签之间X轴间距
        <attr name="vertical_spacing" format="dimension" />         // 标签之间Y轴间距
        <attr name="tag_horizontal_padding" format="dimension" />   // 标签内容padding_left padding_right
        <attr name="tag_vertical_padding" format="dimension" />     // 标签内容padding_top padding_bottom
        <attr name="tag_text_size" format="dimension" />            // 标签字体大小
        <attr name="tag_text_color" format="color" />               // 标签文字颜色
        <attr name="tag_corner_radius" format="dimension" />        // 标签圆角角度
        <attr name="tag_border_width" format="dimension" />         // 标签边框宽度
        <attr name="tag_border_color" format="color" />             // 标签边框颜色
        <attr name="tag_border_checked_color" format="color" />     // 标签选中时边框颜色
        <attr name="tag_bg_color" format="color" />                 // 标签背景颜色
        <attr name="tag_bg_checked_color" format="color" />         // 标签选中时背景颜色
        <attr name="tag_bg_drawable" format="reference" />          // 标签背景
        <attr name="max_selected_count" format="integer" />         // 最多选中个数
```

### SuperTagView

```
        <attr name="is_append_tag" format="boolean" />              // 是否是append tag
        <attr name="horizontal_padding" format="dimension" />       // 标签内容padding_left padding_right
        <attr name="vertical_padding" format="dimension" />         // 标签内容padding_top padding_bottom
        <attr name="corner_radius" format="dimension" />            // 标签圆角角度
        <attr name="border_width" format="dimension" />             // 标签边框宽度
        <attr name="border_color" format="color" />                 // 标签边框颜色
        <attr name="border_checked_color" format="color" />         // 标签选中时边框颜色
        <attr name="bg_color" format="color" />                     // 标签背景颜色
        <attr name="bg_checked_color" format="color" />             // 标签选中时背景颜色
```


### blog介绍

> http://blog.csdn.net/crazy1235/article/details/74907150