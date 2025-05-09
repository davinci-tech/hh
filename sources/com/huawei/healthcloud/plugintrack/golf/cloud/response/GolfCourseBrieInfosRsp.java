package com.huawei.healthcloud.plugintrack.golf.cloud.response;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes8.dex */
public class GolfCourseBrieInfosRsp extends CloudCommonReponse {

    @SerializedName("courseBriefInfos")
    private List<CourseBriefInfo> mCourseBriefInfos;

    public List<CourseBriefInfo> getCourseBriefInfo() {
        return this.mCourseBriefInfos;
    }

    public void setCourseDetails(List<CourseBriefInfo> list) {
        this.mCourseBriefInfos = list;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GolfCourseBrieInfosRsp{mCourseBriefInfos=" + this.mCourseBriefInfos + '}';
    }
}
