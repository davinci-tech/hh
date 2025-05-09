package com.huawei.agconnect.apms.collect.model.event.webview;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.collect.model.event.Event;

/* loaded from: classes2.dex */
public class WebViewLoadEvent extends Event {
    private JsonObject h5LoadEvent;

    public WebViewLoadEvent(long j, JsonObject jsonObject) {
        this.timestamp = j;
        this.h5LoadEvent = jsonObject;
        this.runtimeEnvInformation = Agent.getRuntimeEnvInformation();
    }

    @Override // com.huawei.agconnect.apms.collect.model.event.Event, com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(this.runtimeEnvInformation.asJsonArray());
        jsonArray.add(this.h5LoadEvent);
        return jsonArray;
    }
}
