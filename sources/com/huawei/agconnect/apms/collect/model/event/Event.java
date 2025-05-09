package com.huawei.agconnect.apms.collect.model.event;

import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.collect.model.basic.RuntimeEnvInformation;
import com.huawei.agconnect.apms.collect.type.CollectableArray;
import com.huawei.agconnect.apms.m0;

/* loaded from: classes2.dex */
public abstract class Event extends CollectableArray {
    public String eventName;
    public RuntimeEnvInformation runtimeEnvInformation;
    public long timestamp;

    @Override // com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(this.runtimeEnvInformation.asJsonArray());
        jsonArray.add(m0.abc(Long.valueOf(this.timestamp)));
        jsonArray.add(m0.abc(this.eventName));
        return jsonArray;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
