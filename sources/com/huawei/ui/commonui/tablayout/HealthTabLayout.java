package com.huawei.ui.commonui.tablayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.tabs.TabLayout;
import defpackage.koq;
import health.compact.a.util.LogUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthTabLayout extends TabLayout {
    public HealthTabLayout(Context context) {
        this(context, null);
    }

    public HealthTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HealthTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setCustomTabs(List<View> list) {
        if (koq.b(list)) {
            LogUtil.d("HealthTabLayout", "list is empty in setCustomTabs");
            return;
        }
        if (list.size() != getTabCount()) {
            throw new IllegalArgumentException("tab views number not match");
        }
        for (int i = 0; i < getTabCount(); i++) {
            TabLayout.Tab tabAt = getTabAt(i);
            if (tabAt != null && list.get(i) != null) {
                tabAt.setCustomView(list.get(i));
            }
        }
    }
}
