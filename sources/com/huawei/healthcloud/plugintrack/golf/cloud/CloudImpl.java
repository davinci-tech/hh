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
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;

/* loaded from: classes4.dex */
public class CloudImpl implements CloudApi {
    private static final String TAG = "Track_CloudImpl";

    @Override // com.huawei.healthcloud.plugintrack.golf.cloud.CloudApi
    public GetGolfCourseDetailRsp getGolfCourseDetail(GetGolfCourseDetailReq getGolfCourseDetailReq) {
        LogUtil.d(TAG, "getGolfCourseDetail: Request Received, processing.....");
        GetGolfCourseDetailRsp courseDetailsRsp = CloudImplHelper.getCourseDetailsRsp(getGolfCourseDetailReq);
        if (courseDetailsRsp != null) {
            ReleaseLogUtil.e(TAG, "getGolfCourseDetail: ", courseDetailsRsp.getResultCode());
            return courseDetailsRsp;
        }
        ReleaseLogUtil.c(TAG, "getGolfCourseDetail: Invalid Response!");
        return new GetGolfCourseDetailRsp();
    }

    @Override // com.huawei.healthcloud.plugintrack.golf.cloud.CloudApi
    public GetGolfCourseDataRsp getGolfCourseData(GetGolfCourseDataReq getGolfCourseDataReq) {
        LogUtil.d(TAG, "getGolfCourseData: Request Received, processing.....");
        GetGolfCourseDataRsp golfCourseDataRsp = CloudImplHelper.getGolfCourseDataRsp(getGolfCourseDataReq);
        if (golfCourseDataRsp != null) {
            ReleaseLogUtil.e(TAG, "getGolfCourseData: ", golfCourseDataRsp.getResultCode());
            return golfCourseDataRsp;
        }
        ReleaseLogUtil.c(TAG, "getGolfCourseData: Invalid Response!");
        return new GetGolfCourseDataRsp();
    }

    @Override // com.huawei.healthcloud.plugintrack.golf.cloud.CloudApi
    public GetGolfCourseMapDataRsp getGolfCourseMapData(GetGolfCourseMapDataReq getGolfCourseMapDataReq) {
        LogUtil.d(TAG, "getGolfCourseMapData: Request Received, processing.....");
        GetGolfCourseMapDataRsp golfCourseMapDataRsp = CloudImplHelper.getGolfCourseMapDataRsp(getGolfCourseMapDataReq);
        if (golfCourseMapDataRsp != null) {
            ReleaseLogUtil.e(TAG, "getGolfCourseMapData: ", golfCourseMapDataRsp.getResultCode());
            return golfCourseMapDataRsp;
        }
        ReleaseLogUtil.c(TAG, "getGolfCourseMapData: Invalid Response!");
        return new GetGolfCourseMapDataRsp();
    }

    @Override // com.huawei.healthcloud.plugintrack.golf.cloud.CloudApi
    public GolfClubListInfoDataRsp getGolfClubList(GolfClubListDataReq golfClubListDataReq) {
        GolfClubListInfoDataRsp golfClubListInfoDataRsp = CloudImplHelper.getGolfClubListInfoDataRsp(golfClubListDataReq);
        if (golfClubListInfoDataRsp != null) {
            ReleaseLogUtil.e(TAG, "getGolfClubList:", golfClubListInfoDataRsp.getResultCode());
            return golfClubListInfoDataRsp;
        }
        ReleaseLogUtil.d(TAG, "getGolfClubList response == null");
        return null;
    }

    @Override // com.huawei.healthcloud.plugintrack.golf.cloud.CloudApi
    public GolfCourseBrieInfosRsp getGolfCourseBrieInfos(GolfCourseBriefInfosReq golfCourseBriefInfosReq) {
        GolfCourseBrieInfosRsp golfCourseBrieInfos = CloudImplHelper.getGolfCourseBrieInfos(golfCourseBriefInfosReq);
        if (golfCourseBrieInfos != null) {
            ReleaseLogUtil.e(TAG, "getGolfCourseBrieInfos:", golfCourseBrieInfos.getResultCode());
            return golfCourseBrieInfos;
        }
        ReleaseLogUtil.d(TAG, "getGolfCourseBrieInfos response == null");
        return null;
    }
}
