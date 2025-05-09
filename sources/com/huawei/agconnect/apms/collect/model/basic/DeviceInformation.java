package com.huawei.agconnect.apms.collect.model.basic;

import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.collect.type.CollectableArray;
import com.huawei.agconnect.apms.m0;

/* loaded from: classes2.dex */
public class DeviceInformation extends CollectableArray {
    private String architecture;
    private int cpuCores;
    private String cpuModel;
    private String deviceModel;
    private long diskSize;
    private String manufacturer;
    private long ramMemory;
    private float refreshRate;
    private String resolution;
    private float screenSize;
    private String screenType;

    public DeviceInformation(String str, String str2, String str3) {
        this.manufacturer = str;
        this.architecture = str2;
        this.deviceModel = str3;
    }

    @Override // com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(m0.abc(this.manufacturer));
        jsonArray.add(m0.abc(this.architecture));
        jsonArray.add(m0.abc(this.deviceModel));
        jsonArray.add(m0.abc(Float.valueOf(this.screenSize)));
        jsonArray.add(m0.abc(this.resolution));
        jsonArray.add(m0.abc(this.screenType));
        jsonArray.add(m0.abc(Float.valueOf(this.refreshRate)));
        jsonArray.add(m0.abc(this.cpuModel));
        jsonArray.add(m0.abc(Integer.valueOf(this.cpuCores)));
        jsonArray.add(m0.abc(Long.valueOf(this.ramMemory)));
        jsonArray.add(m0.abc(Long.valueOf(this.diskSize)));
        return jsonArray;
    }

    public void setCpuCores(int i) {
        this.cpuCores = i;
    }

    public void setCpuModel(String str) {
        this.cpuModel = str;
    }

    public void setDiskSize(long j) {
        this.diskSize = j;
    }

    public void setRamMemory(long j) {
        this.ramMemory = j;
    }

    public void setRefreshRate(float f) {
        this.refreshRate = f;
    }

    public void setResolution(String str) {
        this.resolution = str;
    }

    public void setScreenSize(float f) {
        this.screenSize = f;
    }

    public void setScreenType(String str) {
        this.screenType = str;
    }
}
