package com.dj.custom.badgeview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dj.custom.R;

/**
 * Created by xud on 2018/12/28
 *
 * 设置padding match_parent无效
 * badgeLayout的size 根据子view 以及 badgeView算出
 */
public class BadgeLayout extends FrameLayout {

    private static final int BADGE_PADDING = UIUtils.dip2Px(4);

    private static final int BADGE_TEXT_SIZE = 10;

    private boolean addBadgeViewFromXml = true;

    private int badgeType = BadgeView.BadgeType.NUMBERS;

    private @ColorInt
    int badgeTextColor = 0;

    private @ColorInt
    int badgeColor = 0;

    private int badgeTextSize = UIUtils.dip2Px(10);

    /**
     * badge 在x轴向右偏移率
     */
    private float badgeOffSetX = 0.5f;

    /**
     * badge 在Y轴向上偏移率
     */
    private float badgeOffSetY = 0.5f;

    private float maxBadgeOffsetX = 1.0f;

    private float maxBadgeOffsetY = 1.0f;

    private int badgePaddingLeft = BADGE_PADDING;

    private int badgePaddingTop = BADGE_PADDING;

    private int badgePaddingRight = BADGE_PADDING;

    private int badgePaddingBottom = BADGE_PADDING;

    public BadgeLayout(@NonNull Context context) {
        super(context);
    }

    public BadgeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BadgeLayout);
        badgeType = ta.getInt(R.styleable.BadgeLayout_badgeType, badgeType);
        badgeTextColor = ta.getColor(R.styleable.BadgeLayout_badgeTextColor, badgeTextColor);
        badgeColor = ta.getColor(R.styleable.BadgeLayout_badgeColor, badgeColor);
        badgeTextSize = ta.getDimensionPixelSize(R.styleable.BadgeLayout_badgeTextSize, badgeTextSize);
        setBadgeOffSetX(ta.getFloat(R.styleable.BadgeLayout_badgeOffSetX, badgeOffSetX));
        setBadgeOffSetY(ta.getFloat(R.styleable.BadgeLayout_badgeOffSetY, badgeOffSetY));
        badgePaddingLeft = ta.getDimensionPixelOffset(R.styleable.BadgeLayout_badgePaddingLeft, badgePaddingLeft);
        badgePaddingTop = ta.getDimensionPixelOffset(R.styleable.BadgeLayout_badgePaddingTop, badgePaddingTop);
        badgePaddingRight = ta.getDimensionPixelOffset(R.styleable.BadgeLayout_badgePaddingRight, badgePaddingRight);
        badgePaddingBottom = ta.getDimensionPixelOffset(R.styleable.BadgeLayout_badgePaddingBottom, badgePaddingBottom);
        ta.recycle();
        init(context);
    }

    public BadgeLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BadgeLayout);
        badgeType = ta.getInt(R.styleable.BadgeLayout_badgeType, badgeType);
        badgeTextColor = ta.getColor(R.styleable.BadgeLayout_badgeTextColor, badgeTextColor);
        badgeColor = ta.getColor(R.styleable.BadgeLayout_badgeColor, badgeColor);
        badgeTextSize = ta.getDimensionPixelSize(R.styleable.BadgeLayout_badgeTextSize, badgeTextSize);
        setBadgeOffSetX(ta.getFloat(R.styleable.BadgeLayout_badgeOffSetX, badgeOffSetX));
        setBadgeOffSetY(ta.getFloat(R.styleable.BadgeLayout_badgeOffSetY, badgeOffSetY));
        badgePaddingLeft = ta.getDimensionPixelOffset(R.styleable.BadgeLayout_badgePaddingLeft, badgePaddingLeft);
        badgePaddingTop = ta.getDimensionPixelOffset(R.styleable.BadgeLayout_badgePaddingTop, badgePaddingTop);
        badgePaddingRight = ta.getDimensionPixelOffset(R.styleable.BadgeLayout_badgePaddingRight, badgePaddingRight);
        badgePaddingBottom = ta.getDimensionPixelOffset(R.styleable.BadgeLayout_badgePaddingBottom, badgePaddingBottom);
        ta.recycle();
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BadgeLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BadgeLayout);
        badgeType = ta.getInt(R.styleable.BadgeLayout_badgeType, badgeType);
        badgeTextColor = ta.getColor(R.styleable.BadgeLayout_badgeTextColor, badgeTextColor);
        badgeColor = ta.getColor(R.styleable.BadgeLayout_badgeColor, badgeColor);
        badgeTextSize = ta.getDimensionPixelSize(R.styleable.BadgeLayout_badgeTextSize, badgeTextSize);
        setBadgeOffSetX(ta.getFloat(R.styleable.BadgeLayout_badgeOffSetX, badgeOffSetX));
        setBadgeOffSetY(ta.getFloat(R.styleable.BadgeLayout_badgeOffSetY, badgeOffSetY));
        badgePaddingLeft = ta.getDimensionPixelOffset(R.styleable.BadgeLayout_badgePaddingLeft, badgePaddingLeft);
        badgePaddingTop = ta.getDimensionPixelOffset(R.styleable.BadgeLayout_badgePaddingTop, badgePaddingTop);
        badgePaddingRight = ta.getDimensionPixelOffset(R.styleable.BadgeLayout_badgePaddingRight, badgePaddingRight);
        badgePaddingBottom = ta.getDimensionPixelOffset(R.styleable.BadgeLayout_badgePaddingBottom, badgePaddingBottom);
        ta.recycle();
        init(context);
    }

    private void init(Context context) {

    }

    public void setBadgeOffSetX(float badgeOffSetX) {
        if (Float.compare(badgeOffSetX,0) < 0) {
            this.badgeOffSetX = 0;
        } else if (Float.compare(badgeOffSetX,1) > 0) {
            this.badgeOffSetX = 1;
        } else {
            this.badgeOffSetX = badgeOffSetX;
        }
        requestLayout();
        invalidate();
    }

    public void setBadgeOffSetY(float badgeOffSetY) {
        if (Float.compare(badgeOffSetY,0) < 0) {
            this.badgeOffSetY = 0;
        } else if (Float.compare(badgeOffSetY,1) > 0) {
            this.badgeOffSetY = 1;
        } else {
            this.badgeOffSetY = badgeOffSetY;
        }
        requestLayout();
        invalidate();
    }

    public void setBadgeType(int badgeType) {
        this.badgeType = badgeType;
        if(getChildCount() > 1) {
            BadgeView badgeView = (BadgeView) getChildAt(1);
            badgeView.setBadgeType(badgeType);
        }
    }

    public void setBadgeText(String text) {
        if(getChildCount() > 1) {
            BadgeView badgeView = (BadgeView) getChildAt(1);
            badgeView.setText(text);
        }
    }

    public void setBadgeTextSize(int textSize) {
        if(getChildCount() > 1) {
            BadgeView badgeView = (BadgeView) getChildAt(1);
            badgeView.setTextSize(textSize);
        }
    }

    public void setBadgePadding(int left, int top, int right, int bottom) {
        if(getChildCount() > 1) {
            BadgeView badgeView = (BadgeView) getChildAt(1);
            badgeView.setPadding(left, top, right, bottom);
        }
    }

    public void showBadge(boolean showBadge) {
        if(getChildCount() > 1) {
            BadgeView badgeView = (BadgeView) getChildAt(1);
            badgeView.setVisibility(showBadge ? VISIBLE:INVISIBLE);
        }
    }

    @Override
    public final void addView(View child) {
        if (child instanceof BadgeView && addBadgeViewFromXml) {
            throw new IllegalStateException("BadgeLayout can not add badge view from xml");
        }
        if (getChildCount() >= 2) {
            throw new IllegalStateException("BadgeLayout can host only one direct child which is not instance of badgeView");
        }
        super.addView(child);
    }

    @Override
    public final void addView(View child, int index) {
        if (child instanceof BadgeView && addBadgeViewFromXml) {
            throw new IllegalStateException("BadgeLayout can not add badge view from xml");
        }
        if (getChildCount() >= 2) {
            throw new IllegalStateException("BadgeLayout can host only one direct child which is not instance of badgeView");
        }
        super.addView(child, index);
    }

    @Override
    public final void addView(View child, ViewGroup.LayoutParams params) {
        if (child instanceof BadgeView && addBadgeViewFromXml) {
            throw new IllegalStateException("BadgeLayout can not add badge view from xml");
        }
        if (getChildCount() >= 2) {
            throw new IllegalStateException("BadgeLayout can host only one direct child which is not instance of badgeView");
        }
        super.addView(child, params);
    }

    @Override
    public final void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof BadgeView && addBadgeViewFromXml) {
            throw new IllegalStateException("BadgeLayout can not add badge view from xml");
        }
        if (getChildCount() >= 2) {
            throw new IllegalStateException("BadgeLayout can host only one direct child which is not instance of badgeView");
        }
        super.addView(child, index, params);
        if (!(child instanceof BadgeView)) {
            addBadgeView();
        }
    }

    private void addBadgeView() {
        BadgeView badgeView = new BadgeView(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.TOP | Gravity.RIGHT;
        badgeView.setBadgeType(badgeType);
        badgeView.setTextColor(badgeTextColor);
        badgeView.setBadgeColor(badgeColor);
        badgeView.setTextSize(BADGE_TEXT_SIZE);
        badgeView.setPadding(badgePaddingLeft, badgePaddingTop, badgePaddingRight, badgePaddingBottom);
        addBadgeViewFromXml = false;
        addView(badgeView, params);
        addBadgeViewFromXml = true;
    }

    @Override
    public final void onViewRemoved(View child) {
        super.onViewRemoved(child);
        if (!(child instanceof BadgeView)) {
            /**
             * 移除badgeView
             */
            removeAllViews();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        View targetView = getChildAt(0);
        View badgeView = getChildAt(1);
        LayoutParams params = (LayoutParams) targetView.getLayoutParams();
        /**
         * 暂时不考虑 RTL LTR 布局变化
         */
        int targetViewLayoutHorizontalGravity = params.gravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int targetViewLayoutVerticalGravity = params.gravity & Gravity.VERTICAL_GRAVITY_MASK;
        if (targetView == null || badgeView == null) {
            return;
        }
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int targetViewWidth = targetView.getMeasuredWidth();
        int targetViewHeight = targetView.getMeasuredHeight();
        int badgeViewWidth = badgeView.getMeasuredWidth();
        int badgeViewHeight = badgeView.getMeasuredHeight();

        int remainWidth = widthSize - targetViewWidth;
        int remainHeight = heightSize - targetViewHeight;
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                if(targetViewLayoutHorizontalGravity == Gravity.RIGHT) {
                    maxBadgeOffsetX = 0;
                }else if(targetViewLayoutHorizontalGravity == Gravity.CENTER_HORIZONTAL){
                    maxBadgeOffsetX = Math.max(0,
                            Math.min(remainWidth / 2,badgeViewWidth) * 1.0f / badgeViewWidth);
                }else {
                    if(targetViewLayoutHorizontalGravity == Gravity.NO_GRAVITY) {
                        params.gravity = Gravity.LEFT;
                    }else {
                        params.gravity |= Gravity.LEFT;
                    }
                    maxBadgeOffsetX = Math.max(0, Math.min(remainWidth, badgeViewWidth) * 1.0f / badgeViewWidth);
                }
                break;
            case MeasureSpec.UNSPECIFIED:
                maxBadgeOffsetX = 1;
                break;
        }
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                if(targetViewLayoutVerticalGravity == Gravity.TOP) {
                    maxBadgeOffsetY = 0;
                }else if(targetViewLayoutVerticalGravity == Gravity.CENTER_VERTICAL){
                    maxBadgeOffsetY = Math.max(0,
                            Math.min(remainHeight / 2,badgeViewHeight) * 1.0f / badgeViewHeight);
                }else {
                    if(targetViewLayoutVerticalGravity == Gravity.NO_GRAVITY) {
                        params.gravity = Gravity.BOTTOM;
                    }else {
                        params.gravity |= Gravity.BOTTOM;
                    }
                    maxBadgeOffsetY = Math.max(0, Math.min(remainHeight, badgeViewHeight) * 1.0f / badgeViewHeight);
                }
                break;
            case MeasureSpec.UNSPECIFIED:
                maxBadgeOffsetY = 1;
                break;
        }
        badgeOffSetX = Math.min(badgeOffSetX, maxBadgeOffsetX);
        badgeOffSetY = Math.min(badgeOffSetY, maxBadgeOffsetY);
        int resizeWidth = targetViewWidth + (int) (badgeViewWidth * badgeOffSetX * (targetViewLayoutHorizontalGravity == Gravity.CENTER_HORIZONTAL ? 2 : 1));
        int resizeHeight = targetViewHeight + (int) (badgeViewHeight * badgeOffSetY * (targetViewLayoutVerticalGravity == Gravity.CENTER_VERTICAL ? 2 : 1));
        setMeasuredDimension(resizeWidth,resizeHeight);
    }

}
