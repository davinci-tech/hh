package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;

/* loaded from: classes5.dex */
public abstract class ag extends j {
    protected com.huawei.openalliance.ad.download.app.interfaces.b e(Context context, String str) {
        com.huawei.openalliance.ad.download.app.interfaces.b a2 = com.huawei.hms.ads.jsb.a.a(context).a();
        a2.a(d(str));
        return a2;
    }

    protected com.huawei.openalliance.ad.inter.data.i d(Context context, String str) {
        ContentRecord b = b(context, str);
        if (b != null) {
            return new com.huawei.openalliance.ad.inter.data.i(b);
        }
        ho.a("JsbBaseDownload", "content is null");
        return null;
    }

    protected com.huawei.openalliance.ad.inter.data.i a(ContentRecord contentRecord) {
        if (contentRecord != null) {
            return new com.huawei.openalliance.ad.inter.data.i(contentRecord);
        }
        ho.a("JsbBaseDownload", "content is null");
        return null;
    }

    public ag(String str) {
        super(str);
    }
}
