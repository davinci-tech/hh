package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.Intent;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AppInfo;

/* loaded from: classes5.dex */
public class lh implements lk {
    protected void a(Context context, AppInfo appInfo, ContentRecord contentRecord, int i) {
        ho.b("AppNotificationBaseAction", "do nothing at base action!");
    }

    @Override // com.huawei.openalliance.ad.lk
    public void a(Context context, Intent intent) {
        StringBuilder sb;
        try {
            AppInfo appInfo = (AppInfo) intent.getSerializableExtra("appInfo");
            ContentRecord contentRecord = (ContentRecord) intent.getSerializableExtra(MapKeyNames.CONTENT_RECORD);
            int intExtra = intent.getIntExtra(AdShowExtras.DOWNLOAD_SOURCE, 1);
            if (appInfo != null && contentRecord != null) {
                if (ll.a(context).c(appInfo.getPackageName())) {
                    a(context, appInfo, contentRecord, intExtra);
                    ll.a(context).b(appInfo.getPackageName());
                } else {
                    ho.b("AppNotificationBaseAction", "packageName may be illegal:" + appInfo.getPackageName());
                }
            }
        } catch (IllegalStateException e) {
            e = e;
            sb = new StringBuilder("AppNotificationBaseAction.onReceive IllegalStateException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AppNotificationBaseAction", sb.toString());
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("AppNotificationBaseAction.onReceive Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("AppNotificationBaseAction", sb.toString());
        }
    }
}
