# DjCustomView
This is a project for some useful  customView, such as TagGroup, BadgeView ...

## 自定义TagGroup

![](https://upload-images.jianshu.io/upload_images/1420866-326f7fd26b3af8f3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/480)


## 自定义BadgeView

用法非常简单：

1、在xml中用BadgeLayout将要显示Badge的结构包起来
```
<com.dj.custom.badgeview.BadgeLayout
    android:id="@+id/badge3"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:badgeColor="#e65446"
    app:badgeOffSetX="0.6"
    app:badgeOffSetY="0.3"
    app:badgePaddingBottom="2dp"
    app:badgePaddingLeft="4dp"
    app:badgePaddingRight="4dp"
    app:badgePaddingTop="2dp"
    app:badgeTextColor="#ffffff"
    app:badgeType="text">

    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal|bottom"
        android:src="@mipmap/ic_launcher"/>
</com.dj.custom.badgeview.BadgeLayout>
```

2、在代码中动态设置

```
badgeLayout3.setBadgeType(BadgeView.BadgeType.TEXT);
badgeLayout3.setBadgeText("新活动");
```

实际效果

![](https://upload-images.jianshu.io/upload_images/1420866-1764e727d41cf57b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
