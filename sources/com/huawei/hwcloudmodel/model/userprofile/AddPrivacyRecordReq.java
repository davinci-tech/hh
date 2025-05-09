package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.GRSManager;

/* loaded from: classes5.dex */
public class AddPrivacyRecordReq {

    @SerializedName("description")
    private String description;

    @SerializedName("opinion")
    private Integer opinion;

    @SerializedName("privacyId")
    private Integer privacyId;

    public void setPrivacyId(Integer num) {
        this.privacyId = num;
    }

    public void setOpinion(Integer num) {
        this.opinion = num;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String toString() {
        return "AddPrivacyRecordReq{privacyId=" + this.privacyId + ", opinion=" + this.opinion + ", description='" + this.description + "'}";
    }

    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/privacy/addPrivacyRecord";
    }
}
