package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.Content;

/* loaded from: classes5.dex */
public class py extends px {
    @Override // com.huawei.openalliance.ad.pz
    public int b() {
        return 99;
    }

    @Override // com.huawei.openalliance.ad.px
    protected boolean a(Content content, int i) {
        com.huawei.openalliance.ad.utils.cl.a(this.f7460a, content, 99, i);
        return true;
    }

    @Override // com.huawei.openalliance.ad.px
    protected String a() {
        return "ForceFilter";
    }

    py(Context context) {
        super(context);
    }
}
