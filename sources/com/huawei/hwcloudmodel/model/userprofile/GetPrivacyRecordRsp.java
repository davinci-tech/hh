package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes5.dex */
public class GetPrivacyRecordRsp extends CloudCommonReponse {

    @SerializedName("privacyRecords")
    private List<PrivacyRecord> privacyRecords;

    public List<PrivacyRecord> getPrivacyRecords() {
        return this.privacyRecords;
    }

    public void setPrivacyRecords(List<PrivacyRecord> list) {
        this.privacyRecords = list;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetPrivacyRecordRsp{privacyRecords=" + this.privacyRecords + '}';
    }
}
