package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
public class bl extends ba {
    @Override // com.huawei.openalliance.ad.ba
    protected String b() {
        return "JsbReportShowStartEvent";
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        ho.a("JsbReportShowStartEvent", "CmdReportShowStartEvent");
        qq d = d(context, str);
        if (d != null) {
            d.b();
        }
        b(remoteCallResultCallback, true);
    }

    public bl() {
        super("pps.event.showstart");
    }
}
