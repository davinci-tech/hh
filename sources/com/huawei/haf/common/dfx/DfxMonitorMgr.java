package com.huawei.haf.common.dfx;

import android.content.Context;

/* loaded from: classes.dex */
public interface DfxMonitorMgr {
    void installMonitor(Context context);

    void onTrimMemory(Context context, String str, int i);

    void uninstallMonitor(Context context);
}
