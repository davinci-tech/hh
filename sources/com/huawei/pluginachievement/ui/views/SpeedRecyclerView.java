package com.huawei.pluginachievement.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;

/* loaded from: classes6.dex */
public class SpeedRecyclerView extends HealthRecycleView {
    public SpeedRecyclerView(Context context) {
        super(context);
    }

    public SpeedRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SpeedRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.ui.commonui.recycleview.HealthRecycleView, com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView, androidx.recyclerview.widget.RecyclerView
    public boolean fling(int i, int i2) {
        return super.fling(b(i), b(i2));
    }

    private int b(int i) {
        if (i > 0) {
            return Math.min(i, 3000);
        }
        return Math.max(i, -3000);
    }
}
