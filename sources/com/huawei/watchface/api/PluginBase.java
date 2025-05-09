package com.huawei.watchface.api;

import android.content.Context;
import com.huawei.watchface.dm;

/* loaded from: classes7.dex */
public class PluginBase {
    private PluginBaseAdapter mAdapter;

    public void finish() {
    }

    public void init(Context context) {
    }

    public PluginBaseAdapter getAdapter() {
        return (PluginBaseAdapter) dm.a(this.mAdapter);
    }

    public void setAdapter(PluginBaseAdapter pluginBaseAdapter) {
        this.mAdapter = (PluginBaseAdapter) dm.a(pluginBaseAdapter);
    }
}
