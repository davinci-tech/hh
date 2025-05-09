package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public class RtlLinearLayout extends LinearLayout {
    private boolean c;

    public RtlLinearLayout(Context context) {
        super(context);
    }

    public RtlLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        LogUtil.a("Suggestion_RtlLinearLayout", "onLayout childCount = ", Integer.valueOf(childCount));
        if (!this.c && getLayoutDirection() == 1) {
            ArrayList arrayList = new ArrayList(16);
            for (int i5 = 0; i5 < childCount; i5++) {
                arrayList.add(getChildAt(i5));
            }
            removeAllViews();
            for (int i6 = childCount - 1; i6 >= 0; i6--) {
                addView((View) arrayList.get(i6));
            }
            this.c = true;
        }
        super.onLayout(z, i, i2, i3, i4);
    }
}
