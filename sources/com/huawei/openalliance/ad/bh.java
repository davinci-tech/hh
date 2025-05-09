package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
public class bh extends ba {
    @Override // com.huawei.openalliance.ad.ba
    protected String b() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        ho.a("JsbReportPlayStartEvent", "start");
        qq d = d(context, str);
        if (d != null) {
            d.c();
        }
        b(remoteCallResultCallback, true);
    }

    public bh() {
        super("pps.event.show");
    }
}
