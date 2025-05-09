package com.huawei.operation.beans;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class EcgFilterRawData {

    @SerializedName("accData")
    private List<EcgFilterAccSignal> accData;

    @SerializedName("accLen")
    private int accLen;

    @SerializedName("accTimeStamp")
    private long accTimeStamp;

    @SerializedName("ecgData")
    private List<Double> ecgData;

    @SerializedName("ecgLen")
    private int ecgLen;

    @SerializedName("ecgTimeStamp")
    private long ecgTimeStamp;

    public long getAccTimeStamp() {
        return this.accTimeStamp;
    }

    public void setAccTimeStamp(long j) {
        this.accTimeStamp = j;
    }

    public long getEcgTimeStamp() {
        return this.ecgTimeStamp;
    }

    public void setEcgTimeStamp(long j) {
        this.ecgTimeStamp = j;
    }

    public List<EcgFilterAccSignal> getAccData() {
        return this.accData;
    }

    public void setAccData(List<EcgFilterAccSignal> list) {
        this.accData = list;
    }

    public List<Double> getEcgData() {
        return this.ecgData;
    }

    public void setEcgData(List<Double> list) {
        this.ecgData = list;
    }

    public int getAccLen() {
        return this.accLen;
    }

    public void setAccLen(int i) {
        this.accLen = i;
    }

    public int getEcgLen() {
        return this.ecgLen;
    }

    public void setEcgLen(int i) {
        this.ecgLen = i;
    }
}
