package com.huawei.hihealthservice.sync.syncdata.dictionary.config;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes7.dex */
public class DeleteSampleConfigReq implements IRequest {

    @SerializedName("idList")
    private List<HiSampleConfigKey> mKeyList;

    public void setDeleteSampleConfigKeyList(List<HiSampleConfigKey> list) {
        this.mKeyList = list;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DeleteSampleConfigRep { idList = ");
        stringBuffer.append(this.mKeyList.toString());
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/user/deleteSampleConfig";
    }
}
