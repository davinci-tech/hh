package com.huawei.hihealthservice.sync.syncdata.dictionary.config;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class SetSampleConfigRsp extends CloudCommonReponse {

    @SerializedName("failResult")
    private List<CloudSampleConfig> mFailResult;

    @SerializedName("timestamp")
    private long mTimestamp;

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public List<CloudSampleConfig> getFailResult() {
        return this.mFailResult;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("SetSampleConfigRsp {mTimestamp = ");
        stringBuffer.append(this.mTimestamp);
        stringBuffer.append("mFailResult = ").append(this.mFailResult);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
