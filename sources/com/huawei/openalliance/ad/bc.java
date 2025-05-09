package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.inner.AdEventReport;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import java.util.List;

/* loaded from: classes5.dex */
public class bc extends ba {
    @Override // com.huawei.openalliance.ad.ba
    protected String b() {
        return "JsbReportCloseEvent";
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        ho.a("JsbReportCloseEvent", "start");
        AdEventReport adEventReport = (AdEventReport) com.huawei.openalliance.ad.utils.be.a(str, AdEventReport.class, new Class[0]);
        List<String> l = adEventReport != null ? adEventReport.l() : null;
        qq d = d(context, str);
        if (d != null) {
            d.a(0, 0, l);
        }
        b(remoteCallResultCallback, true);
    }

    public bc() {
        super("pps.event.close");
    }
}
