package com.huawei.hihealthservice.sync.syncdata.dictionary.config;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class GetSampleConfigByVersionRsp extends CloudCommonReponse {

    @SerializedName("currentVersion")
    private long mCurrentVersion;

    @SerializedName("infoList")
    private List<CloudSampleConfig> mInfoList;

    public List<CloudSampleConfig> getInfoList() {
        return this.mInfoList;
    }

    public long getCurrentVersion() {
        return this.mCurrentVersion;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetSampleConfigByVersionRsp{infoList = ");
        stringBuffer.append(this.mInfoList);
        stringBuffer.append("currentVersion = ").append(this.mCurrentVersion);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
