package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
public class ah extends ag {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        com.huawei.openalliance.ad.inter.data.i d = d(context, str);
        if (d == null) {
            a(remoteCallResultCallback, this.f7108a, 3002, null, true);
        } else {
            e(context, str).d(context, d);
            b(remoteCallResultCallback, true);
        }
    }

    public ah() {
        super("pps.download.cancel");
    }
}
