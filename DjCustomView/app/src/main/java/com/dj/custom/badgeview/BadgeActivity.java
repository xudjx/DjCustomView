package com.dj.custom.badgeview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dj.custom.R;

/**
 * Created by xud on 2018/12/28
 */
public class BadgeActivity extends AppCompatActivity {

    BadgeLayout badgeLayout1;

    BadgeLayout badgeLayout2;

    BadgeLayout badgeLayout3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);

        badgeLayout1 = findViewById(R.id.badge1);
        badgeLayout2 = findViewById(R.id.badge2);
        badgeLayout3 = findViewById(R.id.badge3);

        badgeLayout1.setBadgeType(BadgeView.BadgeType.POINT);

        badgeLayout2.setBadgeType(BadgeView.BadgeType.NUMBERS);
        badgeLayout2.setBadgeText("99");

        badgeLayout3.setBadgeType(BadgeView.BadgeType.TEXT);
        badgeLayout3.setBadgeText("新活动");
    }
}
