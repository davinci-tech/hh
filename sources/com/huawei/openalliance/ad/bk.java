package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.openalliance.ad.beans.inner.AdEventReport;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class bk extends ba {
    @Override // com.huawei.openalliance.ad.ba
    protected String b() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        String str2;
        int i;
        ho.a("JsbReportShowEvent", "start");
        ContentRecord b = b(context, str);
        if (b != null) {
            AdEventReport adEventReport = (AdEventReport) com.huawei.openalliance.ad.utils.be.a(str, AdEventReport.class, new Class[0]);
            qq d = d(context, str);
            if (d == null || adEventReport == null) {
                str2 = this.f7108a;
                i = 3001;
            } else {
                if (adEventReport.a()) {
                    d.a(adEventReport.b() == null ? 0L : adEventReport.b().longValue(), adEventReport.c() != null ? adEventReport.c().intValue() : 0);
                } else if (a(context, b)) {
                    a.C0207a c0207a = new a.C0207a();
                    c0207a.a(adEventReport.b()).a(adEventReport.c()).b(adEventReport.d()).a(h(new JSONObject(str).optString("versionCode"))).e(e(str)).a(adEventReport.n());
                    d.a(c0207a.a());
                } else {
                    ho.a("JsbReportShowEvent", "ad is not in whitelist");
                    str2 = this.f7108a;
                    i = IEventListener.EVENT_ID_DEVICE_CONN_FAIL;
                }
                str2 = this.f7108a;
                i = 200;
            }
        } else {
            str2 = this.f7108a;
            i = 3002;
        }
        a(remoteCallResultCallback, str2, i, null, true);
    }

    public bk() {
        super("pps.event.show");
    }
}
