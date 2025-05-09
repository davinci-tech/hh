package com.huawei.hihealthservice.sync.syncdata.dictionary.config;

import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class DeleteSampleConfigRsp extends CloudCommonReponse {

    @SerializedName("failResult")
    private List<HiSampleConfigKey> mFailResult;

    public List<HiSampleConfigKey> getFailResult() {
        return this.mFailResult;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DeleteSampleConfigRsp {mFailResult = ");
        stringBuffer.append(this.mFailResult);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
