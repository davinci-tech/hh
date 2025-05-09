package com.huawei.agconnect.apms;

import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.collect.type.CollectableArray;

/* loaded from: classes8.dex */
public class y extends CollectableArray {
    public long abc;
    public long bcd;
    public long cde;

    public y(long j, long j2, long j3) {
        this.abc = j;
        this.bcd = j2;
        this.cde = j3;
    }

    @Override // com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(m0.abc(Long.valueOf(this.abc)));
        jsonArray.add(m0.abc(Long.valueOf(this.bcd)));
        jsonArray.add(m0.abc(Long.valueOf(this.cde)));
        return jsonArray;
    }
}
