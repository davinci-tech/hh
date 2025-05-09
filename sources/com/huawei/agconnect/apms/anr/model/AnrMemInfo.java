package com.huawei.agconnect.apms.anr.model;

import android.app.ActivityManager;
import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.collect.type.CollectableArray;
import com.huawei.agconnect.apms.m0;

/* loaded from: classes8.dex */
public class AnrMemInfo extends CollectableArray {
    private long appFreeMem;
    private long appMaxMem;
    private long appUsedMem;
    private long cleanThreshold;
    private boolean lowMemoryFlag;
    private long sysAvailMem;
    private long sysTotalMem;

    public AnrMemInfo(Runtime runtime, ActivityManager.MemoryInfo memoryInfo) {
        this.appMaxMem = runtime.maxMemory();
        this.appUsedMem = runtime.totalMemory() - runtime.freeMemory();
        this.appFreeMem = runtime.freeMemory();
        this.sysAvailMem = memoryInfo.availMem;
        this.sysTotalMem = memoryInfo.totalMem;
        this.cleanThreshold = memoryInfo.threshold;
        this.lowMemoryFlag = memoryInfo.lowMemory;
    }

    @Override // com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(m0.abc(Long.valueOf(this.appMaxMem)));
        jsonArray.add(m0.abc(Long.valueOf(this.appUsedMem)));
        jsonArray.add(m0.abc(Long.valueOf(this.appFreeMem)));
        jsonArray.add(m0.abc(Long.valueOf(this.sysAvailMem)));
        jsonArray.add(m0.abc(Long.valueOf(this.sysTotalMem)));
        jsonArray.add(m0.abc(Long.valueOf(this.cleanThreshold)));
        jsonArray.add(m0.abc(Boolean.valueOf(this.lowMemoryFlag)));
        return jsonArray;
    }
}
