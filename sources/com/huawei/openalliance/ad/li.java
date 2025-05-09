package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AppInfo;

/* loaded from: classes5.dex */
public class li extends lh {
    @Override // com.huawei.openalliance.ad.lh
    protected void a(Context context, AppInfo appInfo, ContentRecord contentRecord, int i) {
        new com.huawei.openalliance.ad.analysis.h(context).c(contentRecord, "2");
    }
}
