package com.huawei.agconnect.apms.collect.model.event.fps;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.collect.model.EventType;
import com.huawei.agconnect.apms.collect.model.event.Event;
import com.huawei.agconnect.apms.m0;
import java.util.List;

/* loaded from: classes2.dex */
public class FpsDropEvent extends Event {
    private final long fpsCost;
    private final List<String> stacktraceList;

    public FpsDropEvent(List<String> list, long j, long j2) {
        this.stacktraceList = list;
        this.fpsCost = j;
        this.timestamp = j2;
        this.runtimeEnvInformation = Agent.getRuntimeEnvInformation();
        this.eventName = EventType.FPS_DROP;
    }

    @Override // com.huawei.agconnect.apms.collect.model.event.Event, com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray asJsonArray = new Gson().toJsonTree(this.stacktraceList).getAsJsonArray();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(asJsonArray);
        jsonArray.add(this.runtimeEnvInformation.asJsonArray());
        jsonArray.add(m0.abc(Long.valueOf(this.timestamp)));
        jsonArray.add(m0.abc(Long.valueOf(this.fpsCost)));
        return jsonArray;
    }
}
