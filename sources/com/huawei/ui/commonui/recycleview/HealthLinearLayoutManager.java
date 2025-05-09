package com.huawei.ui.commonui.recycleview;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.common.dfx.DfxUtils;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class HealthLinearLayoutManager extends LinearLayoutManager {
    private static final String TAG = "R_HealthLinearLayoutManager";

    public HealthLinearLayoutManager(Context context) {
        super(context);
    }

    public HealthLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public HealthLinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            ReleaseLogUtil.c(TAG, "onLayoutChildren exception ", DfxUtils.d(Thread.currentThread(), e));
        }
    }
}
