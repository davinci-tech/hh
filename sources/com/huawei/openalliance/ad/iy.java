package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.constant.AdLoadMode;

/* loaded from: classes9.dex */
public class iy {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7103a = "iy";

    public static ix a(AdLoadMode adLoadMode, com.huawei.openalliance.ad.views.interfaces.l lVar) {
        ho.b(f7103a, "create ad mediator: %s", adLoadMode);
        return (adLoadMode == AdLoadMode.REAL || adLoadMode == AdLoadMode.REAL_NEW) ? new ja(lVar) : new iz(lVar);
    }
}
