package com.example.fullscreenthemesoftinputbugutil;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public class FullScreenContainerView extends FrameLayout {
    private static final String TAG = "FullScreenContainerView";
    private View mContentView;
    private int time = -1;
    private int mScreenHeight;
    private static final int REQUEST_LAYOUT_MIN_TIME = 6;//子View重绘的最少次数

    public FullScreenContainerView(Context context) {
        this(context, null);
    }

    public FullScreenContainerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FullScreenContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenHeight = metric.heightPixels;   // 屏幕高度（像素）
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = getChildAt(0);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout: " + "changed=>" + getPaddingBottom() + "\n");
        if (mContentView != null) {
            if (getHeight() < mScreenHeight) {
                time++;
                if (time >= 0 && time < REQUEST_LAYOUT_MIN_TIME) {
                    mContentView.requestLayout();
                }
            }
            if (getHeight() == mScreenHeight) {
                time = -1;
            }
        }
    }
}
