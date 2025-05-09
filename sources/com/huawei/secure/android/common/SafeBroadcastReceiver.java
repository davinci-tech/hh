package com.huawei.secure.android.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

@Deprecated
/* loaded from: classes9.dex */
public abstract class SafeBroadcastReceiver extends BroadcastReceiver {
    private boolean a(Context context, Intent intent) {
        if (context != null && intent != null) {
            try {
                intent.getStringExtra("TestIntent");
                if (intent.getAction() != null) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    @Override // android.content.BroadcastReceiver
    @Deprecated
    public final void onReceive(Context context, Intent intent) {
        if (a(context, intent)) {
            onReceiveMsg(context, new SafeIntent(intent));
        }
    }

    @Deprecated
    public abstract void onReceiveMsg(Context context, Intent intent);
}
