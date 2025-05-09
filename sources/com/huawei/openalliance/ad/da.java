package com.huawei.openalliance.ad;

import android.content.Context;

/* loaded from: classes5.dex */
public class da extends cz {

    /* renamed from: a, reason: collision with root package name */
    private gc f6689a;

    @Override // com.huawei.openalliance.ad.cz
    public boolean a() {
        if (this.f6689a.aO() < com.huawei.openalliance.ad.utils.ao.c()) {
            return b();
        }
        ho.b("UserCloseRule", "isTriggerDisturb true");
        return true;
    }

    public da(Context context) {
        this.f6689a = fh.b(context);
    }
}
