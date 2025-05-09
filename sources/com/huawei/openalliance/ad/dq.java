package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.utils.y;

/* loaded from: classes5.dex */
public class dq extends Cdo {
    @Override // com.huawei.openalliance.ad.Cdo
    public void a(AppInfo appInfo, ContentRecord contentRecord, long j) {
        if (appInfo != null && contentRecord != null) {
            b(appInfo, contentRecord, j);
        } else {
            ho.b("TrafficReminder", "appInfo or contentRecord is empty");
            b(appInfo);
        }
    }

    private void b(final AppInfo appInfo, final ContentRecord contentRecord, long j) {
        new com.huawei.openalliance.ad.analysis.h(a()).a(contentRecord, "2");
        com.huawei.openalliance.ad.download.app.c.a(a(), j, new y.a() { // from class: com.huawei.openalliance.ad.dq.1
            @Override // com.huawei.openalliance.ad.utils.y.a
            public void a(boolean z) {
                ContentRecord contentRecord2;
                String str;
                com.huawei.openalliance.ad.analysis.h hVar = new com.huawei.openalliance.ad.analysis.h(dq.this.a());
                if (z) {
                    dq.this.c(appInfo);
                    contentRecord2 = contentRecord;
                    str = "2";
                } else {
                    dq.this.b(appInfo);
                    contentRecord2 = contentRecord;
                    str = "3";
                }
                hVar.b(contentRecord2, str);
            }

            @Override // com.huawei.openalliance.ad.utils.y.a
            public void a() {
                new com.huawei.openalliance.ad.analysis.h(dq.this.a()).b(contentRecord, "1");
                dq.this.a(appInfo);
            }
        });
    }

    public dq(Context context) {
        super(context);
    }
}
