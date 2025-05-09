package com.huawei.agconnect.apms.collect.model.basic;

import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.collect.type.CollectableArray;
import com.huawei.agconnect.apms.m0;
import com.huawei.agconnect.apms.util.Session;

/* loaded from: classes2.dex */
public class RuntimeEnvInformation extends CollectableArray {
    private boolean appBackgrounded;
    private int batteryPercentage;
    private boolean deviceCharging;
    private long diskAvailable;
    private long memoryUsage;
    private String networkWanType;
    private int orientation;
    private JsonArray sessionArray = new JsonArray();

    public RuntimeEnvInformation() {
    }

    public void addSession(Session session) {
        this.sessionArray.add(session.asJsonArray());
    }

    @Override // com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(m0.abc(Boolean.valueOf(this.deviceCharging)));
        jsonArray.add(m0.abc(Integer.valueOf(this.batteryPercentage)));
        jsonArray.add(m0.abc(this.networkWanType));
        jsonArray.add(m0.abc(Integer.valueOf(this.orientation)));
        jsonArray.add(m0.abc(Boolean.valueOf(this.appBackgrounded)));
        jsonArray.add(this.sessionArray);
        jsonArray.add(m0.abc(Long.valueOf(this.diskAvailable)));
        jsonArray.add(m0.abc(Long.valueOf(this.memoryUsage)));
        return jsonArray;
    }

    public void setAppBackgrounded(boolean z) {
        this.appBackgrounded = z;
    }

    public void setBatteryPercentage(int i) {
        this.batteryPercentage = i;
    }

    public void setDeviceCharging(boolean z) {
        this.deviceCharging = z;
    }

    public void setDiskAvailable(long j) {
        this.diskAvailable = j;
    }

    public void setMemoryUsage(long j) {
        this.memoryUsage = j;
    }

    public void setNetworkWanType(String str) {
        this.networkWanType = str;
    }

    public void setOrientation(int i) {
        this.orientation = i;
    }

    public void setSessionArray(JsonArray jsonArray) {
        this.sessionArray = jsonArray;
    }

    public RuntimeEnvInformation(long j, int i, String str, long j2) {
        this.memoryUsage = j;
        this.orientation = i;
        this.networkWanType = str;
        this.diskAvailable = j2;
    }
}
