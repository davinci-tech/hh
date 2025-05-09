package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.openalliance.ad.beans.inner.AdEventReport;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class bb extends ba {
    @Override // com.huawei.openalliance.ad.ba
    protected String b() {
        return "JsbReportClickEvent";
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        String str2;
        int i;
        ho.a("JsbReportClickEvent", "start");
        ContentRecord b = b(context, str);
        if (b == null) {
            ho.b("JsbReportClickEvent", "ad not exist");
            str2 = this.f7108a;
            i = 3002;
        } else if (a(context, b)) {
            qq d = d(context, str);
            boolean z = false;
            AdEventReport adEventReport = (AdEventReport) com.huawei.openalliance.ad.utils.be.a(str, AdEventReport.class, new Class[0]);
            if (d != null) {
                int i2 = adEventReport.i();
                int j = adEventReport.j();
                String k = !TextUtils.isEmpty(adEventReport.k()) ? adEventReport.k() : ClickDestination.JS_SDK_INTERFACE;
                int intValue = adEventReport.d() != null ? adEventReport.d().intValue() : 13;
                String m = adEventReport.m();
                b.a aVar = new b.a();
                JSONObject jSONObject = new JSONObject(str);
                MaterialClickInfo f = f(str);
                Integer d2 = d(str);
                if (f.h() == null && f.i() == null) {
                    z = true;
                }
                if (d2 != null && 13 == d2.intValue() && z) {
                    f.f(1);
                }
                aVar.a(i2).b(j).b(k).a(Integer.valueOf(intValue)).c(m).a(f).a(jSONObject.optString("versionCode")).a(h(jSONObject.optString("versionCode"))).d(com.huawei.openalliance.ad.utils.b.a(context));
                d.a(aVar.a());
            }
            str2 = this.f7108a;
            i = 1000;
        } else {
            ho.b("JsbReportClickEvent", "ad is not in whitelist");
            str2 = this.f7108a;
            i = IEventListener.EVENT_ID_DEVICE_CONN_FAIL;
        }
        a(remoteCallResultCallback, str2, i, null, true);
    }

    public bb() {
        super("pps.event.click");
    }
}
