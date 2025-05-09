package com.huawei.ui.device.activity.adddevice;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class DeviceCustomLinearLayoutManager extends LinearLayoutManager {
    public DeviceCustomLinearLayoutManager(Context context) {
        super(context);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException unused) {
            LogUtil.b("DeviceCustomLinearLayoutManager", "RecyclerView onLayoutChildren exception");
        }
    }
}
