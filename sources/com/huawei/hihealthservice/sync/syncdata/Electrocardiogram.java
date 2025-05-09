package com.huawei.hihealthservice.sync.syncdata;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;
import java.util.List;

/* loaded from: classes7.dex */
public class Electrocardiogram {

    @SerializedName(BleConstants.AVERAGE_HEART_RATE)
    private int mAverageHeartRate;

    @SerializedName("details")
    private String mDetails;

    @SerializedName("ecgArrhyType")
    private long mEcgArrhyType;

    @SerializedName("ecgDataLength")
    private int mEcgDataLength;

    @SerializedName("ecgDataList")
    private List<Float> mEcgDataList;

    @SerializedName("metadata")
    private String mMetadata;

    @SerializedName("userSymptom")
    private long mUserSymptom;

    public String getDetails() {
        return this.mDetails;
    }

    public void setDetails(String str) {
        this.mDetails = str;
    }

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

    public String getMetadata() {
        return this.mMetadata;
    }

    public void setMetadata(String str) {
        this.mMetadata = str;
    }
}
