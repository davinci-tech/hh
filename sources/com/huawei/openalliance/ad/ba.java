package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.hms.ads.jsb.inner.data.H5Ad;
import com.huawei.openalliance.ad.db.bean.ContentRecord;

/* loaded from: classes5.dex */
public abstract class ba extends j {
    protected abstract String b();

    protected qq d(Context context, String str) {
        H5Ad h5Ad = (H5Ad) com.huawei.openalliance.ad.utils.be.a(str, H5Ad.class, new Class[0]);
        ContentRecord b = b(context, str);
        if (b == null) {
            ho.c("JsbBaseReportEvent", "%s: content is null", b());
            return null;
        }
        ou ouVar = new ou(context, sc.a(context, h5Ad.a()));
        ouVar.a(b);
        return ouVar;
    }

    protected com.huawei.openalliance.ad.analysis.c b(Context context) {
        return new com.huawei.openalliance.ad.analysis.c(context);
    }

    public ba(String str) {
        super(str);
    }
}
