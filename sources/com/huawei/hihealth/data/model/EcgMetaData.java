package com.huawei.hihealth.data.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;
import java.util.List;

/* loaded from: classes.dex */
public class EcgMetaData {

    @SerializedName(BleConstants.AVERAGE_HEART_RATE)
    private int mAverageHeartRate;

    @SerializedName("dataSources")
    private String mDataSources;

    @SerializedName("ecgAppVersion")
    private String mEcgAppVersion;

    @SerializedName("ecgArrhyType")
    private long mEcgArrhyType;

    @SerializedName("ecgDataLength")
    private int mEcgDataLength;

    @SerializedName("ecgDataList")
    private List<Float> mEcgDataList;

    @SerializedName("packageName")
    private String mPackageName;

    @SerializedName("userSymptom")
    private long mUserSymptom;

    public long getEcgArrhyType() {
        return this.mEcgArrhyType;
    }

    public void setEcgArrhyType(long j) {
        this.mEcgArrhyType = j;
    }

    public int getAverageHeartRate() {
        return this.mAverageHeartRate;
    }

    public void setAverageHeartRate(int i) {
        this.mAverageHeartRate = i;
    }

    public long getUserSymptom() {
        return this.mUserSymptom;
    }

    public void setUserSymptom(long j) {
        this.mUserSymptom = j;
    }

    public List<Float> getEcgDataList() {
        return this.mEcgDataList;
    }

    public void setEcgDataList(List<Float> list) {
        this.mEcgDataList = list;
    }

    public int getEcgDataLength() {
        return this.mEcgDataLength;
    }

    public void setEcgDataLength(int i) {
        this.mEcgDataLength = i;
    }

    public String getEcgAppVersion() {
        return this.mEcgAppVersion;
    }

    public void setEcgAppVersion(String str) {
        this.mEcgAppVersion = str;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    public String getDataSources() {
        return this.mDataSources;
    }

    public void setDataSources(String str) {
        this.mDataSources = str;
    }
}
