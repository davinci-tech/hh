package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
class t extends j {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        ContentRecord b = b(context, str);
        if (b == null) {
            a(remoteCallResultCallback, this.f7108a, 3002, null, true);
        } else {
            a(remoteCallResultCallback, this.f7108a, 1000, Integer.valueOf(com.huawei.openalliance.ad.utils.ao.a(context, b) ? 0 : -1), true);
        }
    }

    public t() {
        super("pps.process.whythisad");
    }
}
