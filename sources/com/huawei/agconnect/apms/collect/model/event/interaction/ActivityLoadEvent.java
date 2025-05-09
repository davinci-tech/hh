package com.huawei.agconnect.apms.collect.model.event.interaction;

import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.collect.model.EventType;
import com.huawei.agconnect.apms.collect.model.event.Event;
import com.huawei.agconnect.apms.m0;

/* loaded from: classes2.dex */
public class ActivityLoadEvent extends Event {
    private String activityName;
    private long loadCost;
    private String loadType;
    private long viewLoadCost;
    private long viewPreparedCost;

    /* loaded from: classes8.dex */
    public interface LoadType {
        public static final String COLD_LOAD = "cold_load";
    }

    public ActivityLoadEvent(long j, String str, String str2, long j2, long j3, long j4) {
        this.timestamp = j;
        this.eventName = EventType.ACTIVITY_LOAD;
        this.activityName = str;
        this.loadType = str2;
        this.loadCost = j2;
        this.viewPreparedCost = j3;
        this.viewLoadCost = j4;
        this.runtimeEnvInformation = Agent.getRuntimeEnvInformation();
    }

    @Override // com.huawei.agconnect.apms.collect.model.event.Event, com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(this.runtimeEnvInformation.asJsonArray());
        jsonArray.add(m0.abc(Long.valueOf(this.timestamp)));
        jsonArray.add(m0.abc(this.activityName));
        jsonArray.add(m0.abc(this.loadType));
        jsonArray.add(m0.abc(Long.valueOf(this.loadCost)));
        jsonArray.add(m0.abc(Long.valueOf(this.viewPreparedCost)));
        jsonArray.add(m0.abc(Long.valueOf(this.viewLoadCost)));
        return jsonArray;
    }
}
