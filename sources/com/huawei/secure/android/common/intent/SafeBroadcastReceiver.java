package com.huawei.secure.android.common.intent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes6.dex */
public abstract class SafeBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (IntentUtils.hasIntentBomb(intent)) {
            return;
        }
        onReceiveMsg(context, new SafeIntent(intent));
    }

    public abstract void onReceiveMsg(Context context, Intent intent);
}
