package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.healthdatacloud.model.BaseMetaData;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class GetMotionPathByVersionRsp extends CloudCommonReponse {
    private Long currentVersion;
    private List<MotionPathDetail> detailInfos;

    @SerializedName("deleteInfos")
    private List<BaseMetaData> mDeleteInfos;

    public List<MotionPathDetail> getDetailInfos() {
        return this.detailInfos;
    }

    public void setDetailInfos(List<MotionPathDetail> list) {
        this.detailInfos = list;
    }

    public Long getCurrentVersion() {
        return this.currentVersion;
    }

    public void setCurrentVersion(Long l) {
        this.currentVersion = l;
    }

    public List<BaseMetaData> getDeleteInfos() {
        return this.mDeleteInfos;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetMotionPathByVersionRsp{detailInfos=");
        stringBuffer.append(this.detailInfos);
        stringBuffer.append(", deleteInfos = ").append(this.mDeleteInfos);
        stringBuffer.append(", currentVersion=").append(this.currentVersion);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
