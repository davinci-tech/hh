package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes5.dex */
public class GetUserMergeInfoRsp extends CloudCommonReponse {

    @SerializedName("userMergeInfo")
    private List<UserMergeInfo> userMergeInfo;

    public List<UserMergeInfo> getUserMergeInfos() {
        return this.userMergeInfo;
    }

    public void setUserMergeInfos(List<UserMergeInfo> list) {
        this.userMergeInfo = list;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetUserMergeInfoRsp{userMergeInfos=" + this.userMergeInfo + '}';
    }
}
