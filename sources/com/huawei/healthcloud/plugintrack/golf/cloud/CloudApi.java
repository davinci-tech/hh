package com.huawei.healthcloud.plugintrack.golf.cloud;

import com.huawei.healthcloud.plugintrack.golf.cloud.requests.GetGolfCourseDataReq;
import com.huawei.healthcloud.plugintrack.golf.cloud.requests.GetGolfCourseDetailReq;
import com.huawei.healthcloud.plugintrack.golf.cloud.requests.GetGolfCourseMapDataReq;
import com.huawei.healthcloud.plugintrack.golf.cloud.requests.GolfClubListDataReq;
import com.huawei.healthcloud.plugintrack.golf.cloud.requests.GolfCourseBriefInfosReq;
import com.huawei.healthcloud.plugintrack.golf.cloud.response.GetGolfCourseDataRsp;
import com.huawei.healthcloud.plugintrack.golf.cloud.response.GetGolfCourseDetailRsp;
import com.huawei.healthcloud.plugintrack.golf.cloud.response.GetGolfCourseMapDataRsp;
import com.huawei.healthcloud.plugintrack.golf.cloud.response.GolfClubListInfoDataRsp;
import com.huawei.healthcloud.plugintrack.golf.cloud.response.GolfCourseBrieInfosRsp;

/* loaded from: classes4.dex */
public interface CloudApi {
    GolfClubListInfoDataRsp getGolfClubList(GolfClubListDataReq golfClubListDataReq);

    GolfCourseBrieInfosRsp getGolfCourseBrieInfos(GolfCourseBriefInfosReq golfCourseBriefInfosReq);

    GetGolfCourseDataRsp getGolfCourseData(GetGolfCourseDataReq getGolfCourseDataReq);

    GetGolfCourseDetailRsp getGolfCourseDetail(GetGolfCourseDetailReq getGolfCourseDetailReq);

    GetGolfCourseMapDataRsp getGolfCourseMapData(GetGolfCourseMapDataReq getGolfCourseMapDataReq);
}
