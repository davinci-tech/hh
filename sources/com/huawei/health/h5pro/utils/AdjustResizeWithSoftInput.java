package com.huawei.health.h5pro.utils;

import android.R;
import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/* loaded from: classes3.dex */
public class AdjustResizeWithSoftInput {
    public FrameLayout.LayoutParams b;
    public View c;
    public FrameLayout d;
    public int e;

    /* JADX INFO: Access modifiers changed from: private */
    public void XQ_(Activity activity) {
        Rect rect = new Rect();
        this.d.getWindowVisibleDisplayFrame(rect);
        int min = Math.min(rect.bottom, this.d.getMeasuredHeight());
        if (min != this.e) {
            this.b.height = min;
            this.c.requestLayout();
            this.e = min;
        }
    }

    public static boolean isFullscreen(Activity activity) {
        return (activity.getWindow().getDecorView().getSystemUiVisibility() & 256) == 256;
    }

    public static void assistActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        new AdjustResizeWithSoftInput(activity);
    }

    public AdjustResizeWithSoftInput(final Activity activity) {
        if (!isFullscreen(activity)) {
            LogUtil.i("H5PRO_AdjustResizeWithSoftInput", "AdjustResizeWithSoftInput: isFullscreen");
        }
        FrameLayout frameLayout = (FrameLayout) activity.findViewById(R.id.content);
        this.d = frameLayout;
        if (frameLayout == null || frameLayout.getChildAt(0) == null) {
            return;
        }
        View childAt = this.d.getChildAt(0);
        this.c = childAt;
        if (childAt == null) {
            return;
        }
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.h5pro.utils.AdjustResizeWithSoftInput.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                AdjustResizeWithSoftInput.this.XQ_(activity);
            }
        });
        this.b = (FrameLayout.LayoutParams) this.c.getLayoutParams();
    }
}
