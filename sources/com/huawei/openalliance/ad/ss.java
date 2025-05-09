package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AppInfo;

/* loaded from: classes5.dex */
public class ss extends ta {
    private qq c;

    @Override // com.huawei.openalliance.ad.ta
    public boolean a() {
        try {
            ho.b("HarmonyServiceAction", "handle harmony service action");
            AppInfo ae = this.b.ae();
            if (ae == null || TextUtils.isEmpty(ae.getPackageName()) || TextUtils.isEmpty(ae.y())) {
                ho.b("HarmonyServiceAction", "parameters occur error");
            } else {
                String packageName = ae.getPackageName();
                if (com.huawei.openalliance.ad.utils.am.a(this.f7529a, packageName, ae.y())) {
                    b(ClickDestination.HARMONY_SERVICE);
                    com.huawei.openalliance.ad.download.app.l.a(this.f7529a, this.b.ae());
                    this.c.a(EventType.INTENTSUCCESS, (Integer) 1, (Integer) null);
                    return true;
                }
                this.c.a(EventType.INTENTFAIL, (Integer) 1, Integer.valueOf(com.huawei.openalliance.ad.utils.am.a(this.f7529a, packageName) ? 2 : 1));
            }
        } catch (Throwable th) {
            ho.c("HarmonyServiceAction", "handle uri exception: %s", th.getClass().getSimpleName());
        }
        return b();
    }

    public ss(Context context, ContentRecord contentRecord) {
        super(context, contentRecord);
        ou ouVar = new ou(context, sc.a(context, contentRecord.e()));
        this.c = ouVar;
        ouVar.a(contentRecord);
    }
}
