package com.dj.custom.badgeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;

import com.dj.custom.R;

/**
 * Created by xud on 2018/12/28
 *
 * number 为0
 * text 为空 不显示
 */
public class BadgeView extends AppCompatTextView {

    public static final int DEFAULT_POINT_RADIUS = UIUtils.dip2Px(4);

    public static final int DEFAULT_TEXT_RADIUS = UIUtils.dip2Px(8);

    public static final int MAX_NUMBERS = 99;
    public static final int MAX_TEXT_LENGTH = 4;
    public static final String MAX_NUMBER_PLACEHOLDER = "...";

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private RectF mRectF = new RectF();

    private int badgeType = BadgeType.POINT;

    private int pointRadius = DEFAULT_POINT_RADIUS;

    private int textRadius = DEFAULT_TEXT_RADIUS;

    @ColorInt
    private int badgeColor = 0;

    public BadgeView(Context context) {
        super(context);
        init(context);
    }

    public BadgeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BadgeView);
        badgeType = ta.getInt(R.styleable.BadgeView_badgeType, badgeType);
        pointRadius = ta.getDimensionPixelSize(R.styleable.BadgeView_pointRadius, pointRadius);
        textRadius = ta.getDimensionPixelSize(R.styleable.BadgeView_textRadius, textRadius);
        badgeColor = ta.getColor(R.styleable.BadgeView_badgeColor, badgeColor);
        ta.recycle();
        init(context);
    }

    public BadgeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BadgeView);
        badgeType = ta.getInt(R.styleable.BadgeView_badgeType, badgeType);
        pointRadius = ta.getDimensionPixelSize(R.styleable.BadgeView_pointRadius, pointRadius);
        textRadius = ta.getDimensionPixelSize(R.styleable.BadgeView_textRadius, textRadius);
        badgeColor = ta.getColor(R.styleable.BadgeView_badgeColor, badgeColor);
        ta.recycle();
        init(context);
    }

    private void init(Context context) {
        setGravity(Gravity.CENTER);
        setMaxLines(1);
    }

    @Override
    public final void setBackgroundResource(int resId) {
    }

    @Override
    public final void setBackground(Drawable background) {
    }

    @Override
    public final void setBackgroundColor(int color) {
    }

    @Override
    public void setMaxLines(int maxLines) {
        super.setMaxLines(1);
    }

    @Override
    public final void setGravity(int gravity) {
        super.setGravity(Gravity.CENTER);
    }

    @Override
    public final int getGravity() {
        return Gravity.CENTER;
    }

    public void setBadgeType(int badgeType) {
        this.badgeType = badgeType;
        switch (badgeType) {
            case BadgeType.NUMBERS:
                setText("0");
                break;
            case BadgeType.POINT:
                setText("");
                break;
            case BadgeType.TEXT:
                setText("");
                break;
        }
        requestLayout();
        invalidate();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (badgeType == BadgeType.TEXT) {
            if (!TextUtils.isEmpty(text) && text.length() > MAX_TEXT_LENGTH) {
                text = text.subSequence(0, MAX_TEXT_LENGTH);
            }
        }
        super.setText(text, type);
    }

    public void setBadgeColor(int badgeColor) {
        this.badgeColor = badgeColor;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (badgeType == BadgeType.POINT) {
            int width = pointRadius * 2;
            int height = pointRadius * 2;
            setMeasuredDimension(width, height);
        } else if (badgeType == BadgeType.NUMBERS) {
            String text = (String) getText();
            setText("99");
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int size = Math.max(getMeasuredWidth(), getMeasuredHeight());
            setMeasuredDimension(size, size);
            int number = 0;
            try {
                number = Integer.valueOf(text);
            } catch (NumberFormatException e) {
                if (!TextUtils.isEmpty(text) && text.equals(MAX_NUMBER_PLACEHOLDER)) {
                    number = Integer.MAX_VALUE;
                } else {
                    number = 0;
                }
            }
            setText(number > MAX_NUMBERS ? MAX_NUMBER_PLACEHOLDER : text);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (needDraw()) {
            super.draw(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(badgeColor);
        mPaint.setStyle(Paint.Style.FILL);
        switch (badgeType) {
            case BadgeType.POINT:
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, pointRadius, mPaint);
                break;
            case BadgeType.NUMBERS:
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);
                break;
            case BadgeType.TEXT:
                mRectF.set(0, 0, getWidth(), getHeight());
                canvas.drawRoundRect(mRectF, textRadius, textRadius, mPaint);
                break;
        }
        super.onDraw(canvas);
    }

    private boolean needDraw() {
        boolean needDraw = true;
        String text = (String) getText();
        switch (badgeType) {
            case BadgeType.NUMBERS:
                int number = 0;
                try {
                    number = Integer.valueOf(text);
                } catch (NumberFormatException e) {
                    if (!TextUtils.isEmpty(text) && text.equals(MAX_NUMBER_PLACEHOLDER)) {
                        number = Integer.MAX_VALUE;
                    } else {
                        number = 0;
                    }
                }
                if (number == 0) {
                    needDraw = false;
                }
                break;
            case BadgeType.TEXT:
                if (TextUtils.isEmpty(text)) {
                    needDraw = false;
                }
                break;
            default:
                needDraw = true;
                break;
        }
        return needDraw;
    }

    public static class BadgeType {

        public static final int POINT = 0x01;

        public static final int NUMBERS = 0x02;

        public static final int TEXT = 0x04;

    }

}
