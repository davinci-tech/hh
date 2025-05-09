package com.huawei.operation.utils;

import android.R;
import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/* loaded from: classes5.dex */
public class AndroidBugWorkaround {
    private final Activity mActivity;
    private View mChildOfContent;
    private final FrameLayout mContent;
    private FrameLayout.LayoutParams mFrameLayoutParams;
    private int mUsableHeightPrevious;

    private AndroidBugWorkaround(Activity activity) {
        this.mActivity = activity;
        FrameLayout frameLayout = (FrameLayout) activity.findViewById(R.id.content);
        this.mContent = frameLayout;
        if (frameLayout == null) {
            return;
        }
        View childAt = frameLayout.getChildAt(0);
        this.mChildOfContent = childAt;
        if (childAt == null) {
            return;
        }
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.operation.utils.AndroidBugWorkaround$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                AndroidBugWorkaround.this.possiblyResizeChildOfContent();
            }
        });
        ViewGroup.LayoutParams layoutParams = this.mChildOfContent.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            this.mFrameLayoutParams = (FrameLayout.LayoutParams) layoutParams;
        }
    }

    public static void assistActivity(Activity activity) {
        new AndroidBugWorkaround(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void possiblyResizeChildOfContent() {
        Rect rect = new Rect();
        this.mContent.getWindowVisibleDisplayFrame(rect);
        int min = Math.min(rect.bottom, this.mContent.getMeasuredHeight());
        if (min != this.mUsableHeightPrevious) {
            int height = this.mActivity.getWindowManager().getDefaultDisplay().getHeight();
            int i = height - min;
            if (i > height / 4) {
                this.mFrameLayoutParams.height = height - i;
            } else {
                this.mFrameLayoutParams.height = min;
            }
            this.mChildOfContent.requestLayout();
            this.mUsableHeightPrevious = min;
        }
    }
}
