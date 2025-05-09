package com.huawei.hms.network.embedded;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.b6;
import com.huawei.hms.network.inner.api.NetworkReceiver;
import com.huawei.secure.android.common.intent.SafeIntent;

/* loaded from: classes9.dex */
public class m5 implements NetworkReceiver {
    public static final String b = "NetDiagBroadcaseReceive";

    /* renamed from: a, reason: collision with root package name */
    public l5 f5377a;

    @Override // com.huawei.hms.network.inner.api.NetworkReceiver
    public void onReceiveMsg(Context context, Intent intent) {
        Logger.v(b, "the broadcast has received the event!");
        String actionReturnNotNull = new SafeIntent(intent).getActionReturnNotNull();
        Message obtain = Message.obtain();
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(actionReturnNotNull)) {
            Logger.v(b, "the action is error,please check your action!");
            return;
        }
        obtain.what = 1002;
        obtain.obj = b6.m.f5184a;
        this.f5377a.b(obtain);
    }

    public m5(l5 l5Var) {
        this.f5377a = l5Var;
    }
}
