package com.huawei.agconnect.apms;

import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.collect.type.CollectableArray;

/* loaded from: classes8.dex */
public class x extends CollectableArray {
    public long abc;
    public long bcd;
    public long cde;
    public long def;

    public x(long j, long j2, long j3, long j4) {
        this.abc = j;
        this.bcd = j2;
        this.cde = j3;
        this.def = j4;
    }

    @Override // com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(m0.abc(Long.valueOf(this.abc)));
        jsonArray.add(m0.abc(Long.valueOf(this.bcd)));
        jsonArray.add(m0.abc(Long.valueOf(this.cde)));
        jsonArray.add(m0.abc(Long.valueOf(this.def)));
        return jsonArray;
    }
}
