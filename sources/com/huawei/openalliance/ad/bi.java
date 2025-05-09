package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.inner.AdEventReport;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
public class bi extends ba {
    @Override // com.huawei.openalliance.ad.ba
    protected String b() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        ho.a("JsbReportPlayTimeEvent", "start");
        AdEventReport adEventReport = (AdEventReport) com.huawei.openalliance.ad.utils.be.a(str, AdEventReport.class, new Class[0]);
        qq d = d(context, str);
        if (d != null) {
            d.a(adEventReport.o() == null ? 0L : adEventReport.o().longValue());
        }
        b(remoteCallResultCallback, true);
    }

    public bi() {
        super("pps.event.show");
    }
}
