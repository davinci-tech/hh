package com.huawei.openalliance.ad;

import android.content.Context;

/* loaded from: classes5.dex */
public class cy extends cz {

    /* renamed from: a, reason: collision with root package name */
    private gc f6686a;

    @Override // com.huawei.openalliance.ad.cz
    public boolean a() {
        String b = com.huawei.openalliance.ad.utils.ao.b("yyyy-MM-dd");
        int aI = this.f6686a.aI();
        if (!b.equals(this.f6686a.aK()) || aI <= 0 || this.f6686a.aJ() < aI) {
            return b();
        }
        ho.b("AppDayFcRule", "isTriggerDisturb true");
        return true;
    }

    public cy(Context context) {
        this.f6686a = fh.b(context);
    }
}
