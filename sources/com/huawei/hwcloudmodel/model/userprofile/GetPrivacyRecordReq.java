package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.GRSManager;

/* loaded from: classes5.dex */
public class GetPrivacyRecordReq {

    @SerializedName("privacyId")
    private Integer privacyId;

    @SerializedName("recordCount")
    private Integer recordCount;

    public Integer getPrivacyId() {
        return this.privacyId;
    }

    public void setPrivacyId(Integer num) {
        this.privacyId = num;
    }

    public Integer getRecordCount() {
        return this.recordCount;
    }

    public void setRecordCount(Integer num) {
        this.recordCount = num;
    }

    public String toString() {
        return "GetPrivacyRecordReq{privacyId=" + this.privacyId + ", recordCount=" + this.recordCount + '}';
    }

    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/privacy/getPrivacyRecord";
    }
}
