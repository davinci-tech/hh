package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.unite.MotionPathDetail;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes7.dex */
public class AddMotionPathReq implements IRequest {
    private List<MotionPathDetail> detailInfo;

    @SerializedName("localMaxVersion")
    private Long localMaxVersion;
    private String timeZone;

    public void setDetailInfo(List<MotionPathDetail> list) {
        this.detailInfo = list;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public void setLocalMaxVersion(Long l) {
        this.localMaxVersion = l;
    }

    public Long getLocalMaxVersion() {
        return this.localMaxVersion;
    }

    public String toString() {
        return "AddMotionPathReq{localMaxVersion =" + this.localMaxVersion + " detailInfo=" + this.detailInfo + ", timeZone='" + this.timeZone + "'}";
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataSync/path/addMotionPathData";
    }
}
