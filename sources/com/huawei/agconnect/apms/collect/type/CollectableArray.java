package com.huawei.agconnect.apms.collect.type;

import com.google.gson.JsonArray;

/* loaded from: classes2.dex */
public abstract class CollectableArray extends BaseCollectable {
    public CollectableArray() {
        super(2);
    }

    @Override // com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public abstract JsonArray asJsonArray();
}
