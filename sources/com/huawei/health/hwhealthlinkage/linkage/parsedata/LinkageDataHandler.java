package com.huawei.health.hwhealthlinkage.linkage.parsedata;

import defpackage.cvv;
import defpackage.ldo;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public interface LinkageDataHandler {
    void handle(double d, ldo ldoVar);

    default cvv packageSamplePointInfo(JSONObject jSONObject, String str, int i) {
        return new cvv();
    }
}
