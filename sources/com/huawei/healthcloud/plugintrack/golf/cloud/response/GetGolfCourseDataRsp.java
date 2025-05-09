package com.huawei.healthcloud.plugintrack.golf.cloud.response;

import com.google.gson.annotations.SerializedName;
import com.huawei.healthcloud.plugintrack.golf.cloud.beans.CourseData;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class GetGolfCourseDataRsp extends CloudCommonReponse {

    @SerializedName("courseData")
    private List<CourseData> courseDataList = new ArrayList();

    public List<CourseData> getCourseDataList() {
        return this.courseDataList;
    }
}
