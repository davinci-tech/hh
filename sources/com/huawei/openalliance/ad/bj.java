package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
public class bj extends ba {
    @Override // com.huawei.openalliance.ad.ba
    protected String b() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        ho.a("JsbReportPraiseEvent", "start");
        ContentRecord b = b(context, str);
        if (b == null) {
            ho.c("JsbReportPraiseEvent", "%s: content is null", b());
        } else {
            b(context).a(b);
            b(remoteCallResultCallback, true);
        }
    }

    public bj() {
        super("pps.event.praise");
    }
}
