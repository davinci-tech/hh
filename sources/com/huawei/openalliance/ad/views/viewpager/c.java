package com.huawei.openalliance.ad.views.viewpager;

import android.graphics.Rect;
import android.view.View;
import android.view.WindowInsets;

/* loaded from: classes9.dex */
class c implements View.OnApplyWindowInsetsListener {

    /* renamed from: a, reason: collision with root package name */
    private final Rect f8168a = new Rect();
    private e b;

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
        if (onApplyWindowInsets.isConsumed()) {
            return onApplyWindowInsets;
        }
        Rect rect = this.f8168a;
        rect.left = onApplyWindowInsets.getSystemWindowInsetLeft();
        rect.top = onApplyWindowInsets.getSystemWindowInsetTop();
        rect.right = onApplyWindowInsets.getSystemWindowInsetRight();
        rect.bottom = onApplyWindowInsets.getSystemWindowInsetBottom();
        int childCount = this.b.getChildCount();
        for (int i = 0; i < childCount; i++) {
            WindowInsets dispatchApplyWindowInsets = this.b.getChildAt(i).dispatchApplyWindowInsets(onApplyWindowInsets);
            rect.left = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetLeft(), rect.left);
            rect.top = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetTop(), rect.top);
            rect.right = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetRight(), rect.right);
            rect.bottom = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetBottom(), rect.bottom);
        }
        return onApplyWindowInsets.replaceSystemWindowInsets(rect.left, rect.top, rect.right, rect.bottom);
    }

    public c(e eVar) {
        this.b = eVar;
    }
}
