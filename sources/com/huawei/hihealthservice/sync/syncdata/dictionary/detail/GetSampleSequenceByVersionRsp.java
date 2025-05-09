package com.huawei.hihealthservice.sync.syncdata.dictionary.detail;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.healthdatacloud.model.BaseMetaData;
import com.huawei.hwcloudmodel.healthdatacloud.model.SampleSequence;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class GetSampleSequenceByVersionRsp extends CloudCommonReponse {

    @SerializedName("currentVersion")
    private long mCurrentVersion;

    @SerializedName("deleteInfos")
    private List<BaseMetaData> mDeleteInfos;

    @SerializedName("detailInfos")
    private List<SampleSequence> mDetailInfos;

    public List<SampleSequence> getDetailInfos() {
        return this.mDetailInfos;
    }

    public long getCurrentVersion() {
        return this.mCurrentVersion;
    }

    public List<BaseMetaData> getDeleteInfos() {
        return this.mDeleteInfos;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetSampleSequenceByVersionRsp{detailInfos = ");
        stringBuffer.append(this.mDetailInfos);
        stringBuffer.append(",currentVersion = ").append(this.mCurrentVersion);
        stringBuffer.append(",deleteInfos = ").append(this.mDeleteInfos);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
