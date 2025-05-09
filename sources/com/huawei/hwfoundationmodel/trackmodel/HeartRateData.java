package com.huawei.hwfoundationmodel.trackmodel;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class HeartRateData implements Serializable, TimeSequence {
    private static final long serialVersionUID = -6019653484064737738L;

    @SerializedName(IndoorEquipManagerApi.KEY_HEART_RATE)
    private int heartRate;

    @SerializedName("time")
    private long time;

    public HeartRateData() {
    }

    public HeartRateData(long j, int i) {
        this.time = j;
        this.heartRate = i;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.time;
    }

    public void saveTime(long j) {
        this.time = j;
    }

    public int acquireHeartRate() {
        return this.heartRate;
    }

    public void saveHeartRate(int i) {
        this.heartRate = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.time + ",");
        if (LogUtil.c()) {
            if (this.heartRate == 0) {
                sb.append(Constants.LINK);
            } else {
                sb.append("*");
            }
        } else {
            sb.append(this.heartRate);
        }
        return sb.toString();
    }
}
