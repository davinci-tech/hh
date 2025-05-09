package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes7.dex */
public class GetUserProfileReq implements IRequest {

    @SerializedName("customDefine")
    private List<String> customDefine;

    @SerializedName("profileType")
    private List<Integer> profileType;

    public List<Integer> getProfileType() {
        return this.profileType;
    }

    public void setProfileType(List<Integer> list) {
        this.profileType = list;
    }

    public List<String> getCustomDefine() {
        return this.customDefine;
    }

    public void setCustomDefine(List<String> list) {
        this.customDefine = list;
    }

    public String toString() {
        return "GetUserProfileReq{profileType=" + this.profileType + ", customDefine=" + this.customDefine + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/user/getUserProfile";
    }
}
