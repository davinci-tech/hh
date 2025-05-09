package com.huawei.hwcloudmodel.model.ecgservice;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes5.dex */
public class EcgServiceActivationData extends CloudCommonReponse {

    @SerializedName("probationEndTime")
    private long probationEndTime;

    @SerializedName("probationStartTime")
    private long probationStartTime;

    @SerializedName("probationUser")
    private int probationUser;

    @SerializedName("status")
    private int status;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getProbationUser() {
        return this.probationUser;
    }

    public void setProbationUser(int i) {
        this.probationUser = i;
    }

    public long getProbationStartTime() {
        return this.probationStartTime;
    }

    public void setProbationStartTime(long j) {
        this.probationStartTime = j;
    }

    public long getProbationEndTime() {
        return this.probationEndTime;
    }

    public void setProbationEndTime(long j) {
        this.probationEndTime = j;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "EcgServiceActivationData{resultCode=" + getResultCode() + ", resultDesc='" + getResultDesc() + "status=" + this.status + ", probationUser='" + this.probationUser + "probationStartTime=" + this.probationStartTime + ", probationEndTime='" + this.probationEndTime + "}";
    }
}
