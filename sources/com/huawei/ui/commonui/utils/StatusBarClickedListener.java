package com.huawei.ui.commonui.utils;

import android.content.BroadcastReceiver;
import com.huawei.haf.application.BroadcastManager;

/* loaded from: classes6.dex */
public abstract class StatusBarClickedListener extends BroadcastReceiver {
    public StatusBarClickedListener() {
        BroadcastManager.wh_(this);
    }

    public void unregister() {
        BroadcastManager.wv_(this);
    }
}
