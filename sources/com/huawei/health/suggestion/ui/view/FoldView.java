package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/* loaded from: classes8.dex */
public class FoldView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private View f3421a;
    private int c;

    public FoldView(Context context) {
        this(context, null);
    }

    public FoldView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setView(View view) {
        this.f3421a = view;
        addView(view);
    }

    public void setFiexdHeight(int i) {
        this.c = i;
    }

    private void setAnimatedHeight(int i) {
        if (this.f3421a.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f3421a.getLayoutParams();
            layoutParams.height = i;
            this.f3421a.setLayoutParams(layoutParams);
        }
    }
}
