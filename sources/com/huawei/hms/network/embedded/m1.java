package com.huawei.hms.network.embedded;

import android.content.Context;
import android.content.Intent;
import com.huawei.hms.network.inner.api.NetworkReceiver;

/* loaded from: classes9.dex */
public class m1 implements NetworkReceiver {

    /* renamed from: a, reason: collision with root package name */
    public l1 f5373a;

    @Override // com.huawei.hms.network.inner.api.NetworkReceiver
    public void onReceiveMsg(Context context, Intent intent) {
        l1 l1Var = this.f5373a;
        if (l1Var != null) {
            l1Var.processNetworkReceiveMsg();
        }
    }

    public m1(l1 l1Var) {
        this.f5373a = l1Var;
    }
}
