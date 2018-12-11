package com.dj.custom.tag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dj.custom.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xud on 2018/12/7
 */
public class TagActivity extends AppCompatActivity {

    Context context;

    DjTagGroup djTagGroup;

    DjTagGroup djTagGroup2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_tag);
        djTagGroup = (DjTagGroup) findViewById(R.id.dj_tag_group);
        djTagGroup2 = (DjTagGroup) findViewById(R.id.dj_tag_group2);

        initDjTags();
        initDjTags2();
    }

    private void initDjTags() {
        String[] tags = TagGenarator.generate(10, 6);
        List<DjTagGroup.TagViewHolder> viewHolders = new ArrayList<>();
        for (String tag: tags) {
            DjTagViewHolder viewHolder = new DjTagViewHolder(LayoutInflater.from(context).inflate(R.layout.view_tag, djTagGroup, false),
                    tag);
            viewHolders.add(viewHolder);
        }
        DjTagViewHolder moreHolder = new DjTagViewHolder(LayoutInflater.from(context).inflate(R.layout.view_tag, djTagGroup, false),
                "更多 ...");
        djTagGroup.setTags(viewHolders, moreHolder);
    }

    private void initDjTags2() {
        String[] tags = TagGenarator.generate(20, 6);
        List<DjTagGroup.TagViewHolder> viewHolders = new ArrayList<>();
        for (String tag: tags) {
            DjTagViewHolder viewHolder = new DjTagViewHolder(LayoutInflater.from(context).inflate(R.layout.view_tag, djTagGroup, false),
                    tag);
            viewHolders.add(viewHolder);
        }
        DjTagViewHolder moreHolder = new DjTagViewHolder(LayoutInflater.from(context).inflate(R.layout.view_tag, djTagGroup, false),
                "更多 ...");
        djTagGroup2.setTags(viewHolders, moreHolder);
    }


    public class DjTagViewHolder implements DjTagGroup.TagViewHolder {

        public String content;

        public View rootView;

        public TextView tagView;

        public DjTagViewHolder(View itemView,final String content) {
            this.rootView = itemView;
            tagView = (TextView) itemView.findViewById(R.id.tag);
            tagView.setText(content);

            tagView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "点击了：" + content, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public View getView() {
            return rootView;
        }
    }
}
