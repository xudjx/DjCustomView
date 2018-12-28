package com.dj.custom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dj.custom.badgeview.BadgeActivity;
import com.dj.custom.tag.TagActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tags).setOnClickListener(clickListener);
        findViewById(R.id.badge).setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tags:
                    startActivity(new Intent(MainActivity.this, TagActivity.class));
                    break;
                case R.id.badge:
                    startActivity(new Intent(MainActivity.this, BadgeActivity.class));
                    break;
            }
        }
    };
}
