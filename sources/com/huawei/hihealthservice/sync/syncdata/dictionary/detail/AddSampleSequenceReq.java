package com.huawei.hihealthservice.sync.syncdata.dictionary.detail;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcloudmodel.healthdatacloud.model.SampleSequence;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes7.dex */
public class AddSampleSequenceReq implements IRequest {

    @SerializedName("localMaxVersion")
    private Long localMaxVersion;

    @SerializedName("detailInfo")
    private List<SampleSequence> mDetailInfos;

    public AddSampleSequenceReq(List<SampleSequence> list) {
        this.mDetailInfos = list;
    }

    public void setLocalMaxVersion(Long l) {
        this.localMaxVersion = l;
    }

    public Long getLocalMaxVersion() {
        return this.localMaxVersion;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AddSampleSequenceReq { detailInfos = ");
        stringBuffer.append(this.mDetailInfos);
        stringBuffer.append(" localMaxVersion =");
        stringBuffer.append(this.localMaxVersion);
        stringBuffer.append(" }");
        return stringBuffer.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataSync/sequence/addSampleSequence";
    }
}
