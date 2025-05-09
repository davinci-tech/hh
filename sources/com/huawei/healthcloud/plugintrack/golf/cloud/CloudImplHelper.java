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
public class CloudImplHelper {
    private static final String TAG = "CloudImplHelper";

    private CloudImplHelper() {
    }

    protected static GetGolfCourseDataRsp getGolfCourseDataRsp(GetGolfCourseDataReq getGolfCourseDataReq) {
        return GolfCloudApiUtils.getInstance().requestCourseData(getGolfCourseDataReq);
    }

    protected static GetGolfCourseDetailRsp getCourseDetailsRsp(GetGolfCourseDetailReq getGolfCourseDetailReq) {
        return GolfCloudApiUtils.getInstance().requestCourseDetails(getGolfCourseDetailReq);
    }

    protected static GetGolfCourseMapDataRsp getGolfCourseMapDataRsp(GetGolfCourseMapDataReq getGolfCourseMapDataReq) {
        return GolfCloudApiUtils.getInstance().requestCourseMapData(getGolfCourseMapDataReq);
    }

    protected static GolfClubListInfoDataRsp getGolfClubListInfoDataRsp(GolfClubListDataReq golfClubListDataReq) {
        return GolfCloudApiUtils.getInstance().requestGolfClubListInfoData(golfClubListDataReq);
    }

    protected static GolfCourseBrieInfosRsp getGolfCourseBrieInfos(GolfCourseBriefInfosReq golfCourseBriefInfosReq) {
        return GolfCloudApiUtils.getInstance().requestGolfBrieInfoData(golfCourseBriefInfosReq);
    }
}
