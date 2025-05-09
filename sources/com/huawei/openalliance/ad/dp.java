package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.utils.y;

/* loaded from: classes5.dex */
public class dp extends Cdo {
    @Override // com.huawei.openalliance.ad.Cdo
    public void a(AppInfo appInfo, ContentRecord contentRecord, long j) {
        if (appInfo != null && contentRecord != null) {
            a(appInfo, contentRecord);
        } else {
            ho.b("ConfirmDownloadAlertStrategy", "appInfo or contentRecord is empty");
            b(appInfo);
        }
    }

    private void a(final AppInfo appInfo, final ContentRecord contentRecord) {
        ho.b("ConfirmDownloadAlertStrategy", "showConfirmDownloadAlert, context:" + a());
        new com.huawei.openalliance.ad.analysis.h(a()).a(contentRecord);
        com.huawei.openalliance.ad.download.app.c.a(a(), "11".equals(appInfo.x()), new y.a() { // from class: com.huawei.openalliance.ad.dp.1
            @Override // com.huawei.openalliance.ad.utils.y.a
            public void a(boolean z) {
                new com.huawei.openalliance.ad.analysis.h(dp.this.a()).c(contentRecord);
                dp.this.b(appInfo);
            }

            @Override // com.huawei.openalliance.ad.utils.y.a
            public void a() {
                new com.huawei.openalliance.ad.analysis.h(dp.this.a()).b(contentRecord);
                dp.this.a(appInfo);
            }
        });
    }

    public dp(Context context) {
        super(context);
    }
}
