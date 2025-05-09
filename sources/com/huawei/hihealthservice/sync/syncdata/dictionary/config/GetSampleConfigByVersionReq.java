package com.huawei.hihealthservice.sync.syncdata.dictionary.config;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes7.dex */
public class GetSampleConfigByVersionReq implements IRequest {

    @SerializedName("version")
    private long mVersion;

    public Long getVersion() {
        return Long.valueOf(this.mVersion);
    }

    public void setVersion(long j) {
        this.mVersion = j;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetSampleConfigByVersionReq { version = ");
        stringBuffer.append(this.mVersion);
        stringBuffer.append(" }");
        return stringBuffer.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/user/getSampleConfigByVersion";
    }
}
