package com.huawei.healthcloud.plugintrack.golf.cloud.response;

import com.google.gson.annotations.SerializedName;
import com.huawei.healthcloud.plugintrack.golf.cloud.beans.CourseMapData;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class GetGolfCourseMapDataRsp extends CloudCommonReponse {

    @SerializedName("courseMapData")
    private List<CourseMapData> courseMapDataList = new ArrayList();

    public List<CourseMapData> getCourseMapDataList() {
        return this.courseMapDataList;
    }
}
