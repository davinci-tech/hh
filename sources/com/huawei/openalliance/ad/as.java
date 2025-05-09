package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
public class as extends aq {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        b().c(remoteCallResultCallback, this.f7108a, this.c);
    }

    public as() {
        super("pps.listener.webloadfinish");
    }
}
