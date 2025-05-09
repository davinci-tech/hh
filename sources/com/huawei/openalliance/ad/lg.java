package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AppInfo;

/* loaded from: classes5.dex */
public class lg extends lh {
    @Override // com.huawei.openalliance.ad.lh
    protected void a(Context context, AppInfo appInfo, ContentRecord contentRecord, int i) {
        new com.huawei.openalliance.ad.download.app.f().a(context, appInfo, new ou(context, sc.a(context, contentRecord.e()), contentRecord), Integer.valueOf(i), false);
        new com.huawei.openalliance.ad.analysis.h(context).c(contentRecord, "1");
    }
}
