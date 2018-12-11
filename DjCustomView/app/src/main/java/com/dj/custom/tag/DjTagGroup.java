package com.dj.custom.tag;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.dj.custom.R;

import java.util.List;

/**
 * Created by xud on 2018/12/7
 */
public class DjTagGroup extends ViewGroup {

    /** The horizontal tag spacing, default is 8.0dp. */
    private int horizontalSpacing;

    /** The vertical tag spacing, default is 8.0dp. */
    private int verticalSpacing;

    /** The max row to show, default is show all. */
    private int maxRow;

    private final float default_horizontal_spacing;
    private final float default_vertical_spacing;
    private final int MAX_ROW_COUNT;

    private TagViewHolder moreTagHolder;

    private int moreTagMeasureWidth;

    private int moreTagMeasureHeight;

    public DjTagGroup(Context context) {
        this(context, null);
    }

    public DjTagGroup(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.tagGroupStyle);
    }

    public DjTagGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        default_horizontal_spacing = dp2px(8.0f);
        default_vertical_spacing = dp2px(8.0f);
        MAX_ROW_COUNT = Integer.MAX_VALUE;

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DjTagGroup);
        try {
            horizontalSpacing = (int) typedArray.getDimension(R.styleable.DjTagGroup_tag_horizontalSpacing, default_horizontal_spacing);
            verticalSpacing = (int) typedArray.getDimension(R.styleable.DjTagGroup_tag_verticalSpacing, default_vertical_spacing);
            maxRow = typedArray.getInt(R.styleable.DjTagGroup_max_row, MAX_ROW_COUNT);
        } finally {
            typedArray.recycle();
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;

        int row = 0; // The row counter.
        int rowWidth = 0; // Calc the current row width.
        int rowMaxHeight = 0; // Calc the max tag height, in current row.

        if (moreTagHolder != null) {
            moreTagMeasureWidth = moreTagHolder.getView().getMeasuredWidth();
            moreTagMeasureHeight = moreTagHolder.getView().getMeasuredHeight();
        }

        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();

            if (child.getVisibility() != GONE) {
                // judge the max_row
                if (row + 1 >= maxRow && rowWidth + childWidth  > widthSize) {
                    break;
                }
                rowWidth += childWidth;
                if (rowWidth > widthSize) { // Next line.
                    rowWidth = childWidth; // The next row width.
                    height += rowMaxHeight + verticalSpacing;
                    rowMaxHeight = childHeight; // The next row max height.
                    row++;
                } else { // This line.
                    rowMaxHeight = Math.max(rowMaxHeight, childHeight);
                }
                rowWidth += horizontalSpacing;
            }
        }

        // Account for the last row height.
        height += rowMaxHeight;

        // Account for the padding too.
        height += getPaddingTop() + getPaddingBottom();

        // If the tags grouped in one row, set the width to wrap the tags.
        if (row == 0) {
            width = rowWidth;
            width += getPaddingLeft() + getPaddingRight();
        } else {// If the tags grouped exceed one line, set the width to match the parent.
            width = widthSize;
        }

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width,
                heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int parentLeft = getPaddingLeft();
        final int parentRight = r - l - getPaddingRight();
        final int parentTop = getPaddingTop();
        final int parentBottom = b - t - getPaddingBottom();

        int childLeft = parentLeft;
        int childTop = parentTop;

        int row = 0;
        int rowMaxHeight = 0;

        boolean showMoreTag = false;

        final int count = getChildCount();
        int unTagCount = count;
        if (moreTagHolder != null) {
            unTagCount--;
        }
        for (int i = 0; i < unTagCount; i++) {
            final View child = getChildAt(i);
            final int width = child.getMeasuredWidth();
            final int height = child.getMeasuredHeight();

            if (child.getVisibility() != GONE) {
                if (row + 1 >= maxRow && childLeft + width + (horizontalSpacing + moreTagMeasureWidth)  > parentRight) {
                    // 预留一个空位放置moreTag
                    showMoreTag = true;
                    break;
                }
                if (childLeft + width > parentRight) { // Next line
                    childLeft = parentLeft;
                    childTop += rowMaxHeight + verticalSpacing;
                    rowMaxHeight = height;
                    row++;
                } else {
                    rowMaxHeight = Math.max(rowMaxHeight, height);
                }

                // this is point
                child.layout(childLeft, childTop, childLeft + width, childTop + height);

                childLeft += width + horizontalSpacing;
            }
        }

        if (showMoreTag) {
            final View child = getChildAt(count - 1);
            final int width = child.getMeasuredWidth();
            final int height = child.getMeasuredHeight();
            child.layout(childLeft, childTop, childLeft + width, childTop + height);
        }
    }

    public void setTags(List<TagViewHolder> viewHolders) {
        setTags(viewHolders, null);
    }

    public void setTags(List<TagViewHolder> viewHolders, TagViewHolder moreTagHolder) {
        removeAllViews();
        this.moreTagHolder = moreTagHolder;
        viewHolders.add(moreTagHolder);
        for (TagViewHolder viewHolder : viewHolders) {
            addView(viewHolder.getView());
        }
    }

    public float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    public float sp2px(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }

    public interface TagViewHolder {
        View getView();
    }
}
