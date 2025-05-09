package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.MetaData;

/* loaded from: classes5.dex */
public class pu extends px {
    @Override // com.huawei.openalliance.ad.pz
    public int b() {
        return 1;
    }

    @Override // com.huawei.openalliance.ad.px
    protected boolean a(Content content, int i) {
        String a2;
        String str;
        MetaData c = content.c();
        if (c == null) {
            a2 = a();
            str = "metaData is null";
        } else {
            ApkInfo r = c.r();
            if (r != null) {
                boolean a3 = com.huawei.openalliance.ad.utils.i.a(this.f7460a, r.a());
                if (a3) {
                    com.huawei.openalliance.ad.utils.cl.a(this.f7460a, content, 1, i);
                }
                return a3;
            }
            a2 = a();
            str = "apkInfo is null";
        }
        ho.c(a2, str);
        return false;
    }

    @Override // com.huawei.openalliance.ad.px
    protected String a() {
        return "AppInstallAdFilter";
    }

    pu(Context context) {
        super(context);
    }
}
