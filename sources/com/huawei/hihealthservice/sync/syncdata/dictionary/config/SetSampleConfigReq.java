package com.huawei.hihealthservice.sync.syncdata.dictionary.config;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes7.dex */
public class SetSampleConfigReq implements IRequest {

    @SerializedName("infoList")
    private List<CloudSampleConfig> mInfoList;

    public void setmInfoList(List<CloudSampleConfig> list) {
        this.mInfoList = list;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("SetSampleConfigReq { infoList = ");
        stringBuffer.append(this.mInfoList);
        stringBuffer.append(" }");
        return stringBuffer.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/user/setSampleConfig";
    }
}
