package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
public class av extends ag {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        com.huawei.openalliance.ad.inter.data.i d = d(context, str);
        a(remoteCallResultCallback, this.f7108a, 1000, Integer.valueOf(d != null ? com.huawei.hms.ads.jsb.a.a(context).a().f(context, d) : 0), true);
    }

    public av() {
        super("pps.download.progress");
    }
}
