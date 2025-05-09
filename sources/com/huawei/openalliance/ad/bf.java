package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.inner.AdEventReport;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;

/* loaded from: classes5.dex */
public class bf extends ba {
    @Override // com.huawei.openalliance.ad.ba
    protected String b() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        ho.a("JsbReportShowEvent", "start");
        AdEventReport adEventReport = (AdEventReport) com.huawei.openalliance.ad.utils.be.a(str, AdEventReport.class, new Class[0]);
        qq d = d(context, str);
        if (d != null && adEventReport != null) {
            d.b(adEventReport.e() == null ? 0L : adEventReport.e().longValue(), adEventReport.f() != null ? adEventReport.f().longValue() : 0L, adEventReport.g() == null ? 0 : adEventReport.g().intValue(), adEventReport.h() != null ? adEventReport.h().intValue() : 0);
        }
        b(remoteCallResultCallback, true);
    }

    public bf() {
        super("pps.event.show");
    }
}
