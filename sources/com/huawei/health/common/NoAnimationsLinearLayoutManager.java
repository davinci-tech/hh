package com.huawei.health.common;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import health.compact.a.LogAnonymous;

/* loaded from: classes3.dex */
public class NoAnimationsLinearLayoutManager extends HealthLinearLayoutManager {
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    public NoAnimationsLinearLayoutManager(Context context) {
        super(context);
    }

    public NoAnimationsLinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            LogUtil.b("NoAnimationsLinearLayoutManager", "onLayoutChildren ", LogAnonymous.b((Throwable) e));
        }
    }
}
