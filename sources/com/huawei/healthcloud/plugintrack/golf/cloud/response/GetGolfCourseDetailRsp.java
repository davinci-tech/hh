package com.huawei.healthcloud.plugintrack.golf.cloud.response;

import com.google.gson.annotations.SerializedName;
import com.huawei.healthcloud.plugintrack.golf.cloud.beans.CourseDetail;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class GetGolfCourseDetailRsp extends CloudCommonReponse {

    @SerializedName("courseDetails")
    private List<CourseDetail> courseDetails = new ArrayList();

    public List<CourseDetail> getCourseDetails() {
        return this.courseDetails;
    }
}
