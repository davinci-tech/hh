package com.huawei.openalliance.ad;

import android.content.Context;

/* loaded from: classes9.dex */
public class gu {
    public static rz a(Context context, gt gtVar) {
        rz siVar;
        ho.b("LinkedEventStrategyFactory", "createBaseEventStrategy");
        int j = gtVar != null ? gtVar.j() : -1;
        if (j != 1) {
            if (j == 3) {
                siVar = new sf(context, j);
                return siVar;
            }
            if (j != 18) {
                return new sb(context);
            }
        }
        siVar = new si(context, j);
        return siVar;
    }
}
