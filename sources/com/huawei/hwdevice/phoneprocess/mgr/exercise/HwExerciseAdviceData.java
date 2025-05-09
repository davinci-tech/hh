package com.huawei.hwdevice.phoneprocess.mgr.exercise;

import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwExerciseAdviceData {
    private int dataCount;
    private int dataIndex;
    private DeviceCapability deviceCapability;
    private long time;
    private long timeInterval;
    private JSONObject total;

    public HwExerciseAdviceData(JSONObject jSONObject) {
        this.total = jSONObject;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public void setDataCount(int i) {
        this.dataCount = i;
    }

    public void setDataIndex(int i) {
        this.dataIndex = i;
    }

    public void setTimeInterval(long j) {
        this.timeInterval = j;
    }

    public void setDeviceCapability(DeviceCapability deviceCapability) {
        this.deviceCapability = deviceCapability;
    }

    public int getDataCount() {
        return this.dataCount;
    }

    public long getTime() {
        return this.time;
    }

    public long getTimeInterval() {
        return this.timeInterval;
    }

    public int getDataIndex() {
        return this.dataIndex;
    }

    public JSONObject getTotal() {
        return this.total;
    }

    public DeviceCapability getDeviceCapability() {
        return this.deviceCapability;
    }
}
