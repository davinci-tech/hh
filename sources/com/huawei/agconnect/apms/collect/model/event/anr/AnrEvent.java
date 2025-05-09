package com.huawei.agconnect.apms.collect.model.event.anr;

import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.anr.model.AnrInfo;
import com.huawei.agconnect.apms.anr.model.AnrMemInfo;
import com.huawei.agconnect.apms.anr.model.ThreadStackInfo;
import com.huawei.agconnect.apms.collect.model.basic.RuntimeEnvInformation;
import com.huawei.agconnect.apms.collect.model.event.Event;
import com.huawei.agconnect.apms.m0;
import java.util.List;

/* loaded from: classes8.dex */
public class AnrEvent extends Event {
    private List<ThreadStackInfo> allThreadStack;
    private AnrMemInfo anrMemInfo;
    private boolean isRoot;
    private String longMsg;
    private String parentActivity;
    private long timeStamp;
    private String tracesInfo;

    public AnrEvent(AnrInfo anrInfo) {
        this.isRoot = anrInfo.isRoot();
        this.parentActivity = anrInfo.getParentActivity();
        this.allThreadStack = anrInfo.getAllThreadStack();
        this.longMsg = anrInfo.getLongMsg();
        this.anrMemInfo = anrInfo.getMemInfo();
        this.runtimeEnvInformation = Agent.getRuntimeEnvInformation();
        this.timeStamp = anrInfo.getAnrTimeStamp();
        this.tracesInfo = anrInfo.getTracesInfo();
    }

    private JsonArray changeThreadStackListAsJsonArray(List<ThreadStackInfo> list) {
        JsonArray jsonArray = new JsonArray();
        for (ThreadStackInfo threadStackInfo : list) {
            if (threadStackInfo != null) {
                jsonArray.add(threadStackInfo.asJsonArray());
            }
        }
        return jsonArray;
    }

    @Override // com.huawei.agconnect.apms.collect.model.event.Event, com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        RuntimeEnvInformation runtimeEnvInformation = this.runtimeEnvInformation;
        if (runtimeEnvInformation != null) {
            jsonArray.add(runtimeEnvInformation.asJsonArray());
        }
        jsonArray.add(m0.abc(Long.valueOf(this.timeStamp)));
        jsonArray.add(m0.abc(Boolean.valueOf(this.isRoot)));
        jsonArray.add(m0.abc(this.parentActivity));
        jsonArray.add(m0.abc(this.longMsg));
        jsonArray.add(changeThreadStackListAsJsonArray(this.allThreadStack));
        jsonArray.add(this.anrMemInfo.asJsonArray());
        jsonArray.add(this.tracesInfo);
        return jsonArray;
    }
}
