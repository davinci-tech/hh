package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.tf;

/* loaded from: classes5.dex */
public class pw extends px {
    @Override // com.huawei.openalliance.ad.pz
    public int b() {
        return 4;
    }

    @Override // com.huawei.openalliance.ad.px
    protected boolean a(Content content, int i) {
        String a2;
        String str;
        MetaData c = content.c();
        if (c == null) {
            a2 = a();
            str = "isNeedDiscard metaData is null";
        } else {
            ApkInfo r = c.r();
            if (r != null) {
                if (com.huawei.openalliance.ad.utils.i.a(this.f7460a, r.a())) {
                    String n = c.n();
                    tf.a aVar = new tf.a();
                    aVar.a(content.g()).b(content.h()).a(i);
                    if (com.huawei.openalliance.ad.utils.i.b(this.f7460a, n, r.a(), aVar.a()) == null) {
                        com.huawei.openalliance.ad.utils.cl.a(this.f7460a, content, 4, i);
                        ho.c(a(), "isNeedDiscard intent check fail");
                        return true;
                    }
                }
                return false;
            }
            a2 = a();
            str = "isNeedDiscard apkInfo is null";
        }
        ho.c(a2, str);
        return false;
    }

    @Override // com.huawei.openalliance.ad.px
    protected String a() {
        return "AppPropagandaFilter";
    }

    pw(Context context) {
        super(context);
    }
}
