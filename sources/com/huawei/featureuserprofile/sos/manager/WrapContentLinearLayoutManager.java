package com.huawei.featureuserprofile.sos.manager;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;

/* loaded from: classes7.dex */
public class WrapContentLinearLayoutManager extends LinearLayoutManager {
    public WrapContentLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            LogUtil.b("WrapContentLinearLayoutManager", "onLayoutChildren ", LogAnonymous.b((Throwable) e));
        }
    }
}
