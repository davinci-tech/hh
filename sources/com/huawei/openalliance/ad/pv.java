package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.tf;

/* loaded from: classes5.dex */
public class pv extends px {
    @Override // com.huawei.openalliance.ad.pz
    public int b() {
        return 2;
    }

    @Override // com.huawei.openalliance.ad.px
    protected boolean a(Content content, int i) {
        MetaData c = content.c();
        if (c == null) {
            ho.c(a(), "metaData is null");
            return false;
        }
        String n = c.n();
        ApkInfo r = c.r();
        String a2 = r == null ? null : r.a();
        tf.a aVar = new tf.a();
        aVar.b(content.h());
        aVar.a(content.g());
        aVar.a(i);
        boolean z = com.huawei.openalliance.ad.utils.i.b(this.f7460a, n, a2, aVar.a()) == null;
        if (z) {
            com.huawei.openalliance.ad.utils.cl.a(this.f7460a, content, 2, i);
        }
        return z;
    }

    @Override // com.huawei.openalliance.ad.px
    protected String a() {
        return "AppPromoteAdFilter";
    }

    pv(Context context) {
        super(context);
    }
}
