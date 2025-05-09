package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
public class au extends ag {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        com.huawei.openalliance.ad.inter.data.i d = d(context, str);
        int[] iArr = {-1};
        if (d == null) {
            a(remoteCallResultCallback, this.f7108a, 3002, null, true);
        } else {
            iArr[0] = e(context, str).c(context, d);
            a(remoteCallResultCallback, this.f7108a, 1000, Integer.valueOf(iArr[0]), true);
        }
    }

    public au() {
        super("pps.download.pause");
    }
}
