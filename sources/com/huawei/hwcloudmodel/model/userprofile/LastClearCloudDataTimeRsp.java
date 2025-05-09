package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes5.dex */
public class LastClearCloudDataTimeRsp extends CloudCommonReponse {

    @SerializedName("lastRecord")
    private DeleteOperationRecord lastRecord;

    public DeleteOperationRecord getLastRecord() {
        return this.lastRecord;
    }

    public void setLastRecord(DeleteOperationRecord deleteOperationRecord) {
        this.lastRecord = deleteOperationRecord;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "LastClearCloudDataTimeRsp{lastRecord=" + this.lastRecord + '}';
    }
}
