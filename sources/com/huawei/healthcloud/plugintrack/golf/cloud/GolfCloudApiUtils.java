package com.huawei.healthcloud.plugintrack.golf.cloud;

import android.content.Context;
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
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.eil;
import defpackage.lqi;
import defpackage.lql;
import java.util.Map;

/* loaded from: classes4.dex */
public final class GolfCloudApiUtils {
    private static final String CLIENT_TYPE = "clientType";
    private static final Object LOCK = new Object();
    private static final String TAG = "GolfCloudApiUtils";
    private static volatile GolfCloudApiUtils sCloudUtils;
    private final HealthDataCloudFactory mParamsFactory = new HealthDataCloudFactory(BaseApplication.getContext());

    private GolfCloudApiUtils() {
    }

    protected GetGolfCourseDetailRsp requestCourseDetails(GetGolfCourseDetailReq getGolfCourseDetailReq) {
        Map<String, Object> body = this.mParamsFactory.getBody(getGolfCourseDetailReq);
        body.put("language", getGolfCourseDetailReq.getLanguage());
        body.put("clientType", eil.a());
        Map<String, String> headers = this.mParamsFactory.getHeaders();
        setGolfAt(BaseApplication.getContext(), headers, getGolfCourseDetailReq.isAnon());
        return (GetGolfCourseDetailRsp) lqi.d().d(getGolfCourseDetailReq.getUrl(), headers, lql.b(body), GetGolfCourseDetailRsp.class);
    }

    protected GetGolfCourseDataRsp requestCourseData(GetGolfCourseDataReq getGolfCourseDataReq) {
        Map<String, Object> body = this.mParamsFactory.getBody(getGolfCourseDataReq);
        body.put("language", getGolfCourseDataReq.getLanguage());
        body.put("clientType", eil.a());
        Map<String, String> headers = this.mParamsFactory.getHeaders();
        setGolfAt(BaseApplication.getContext(), headers);
        return (GetGolfCourseDataRsp) lqi.d().d(getGolfCourseDataReq.getUrl(), headers, lql.b(body), GetGolfCourseDataRsp.class);
    }

    protected GetGolfCourseMapDataRsp requestCourseMapData(GetGolfCourseMapDataReq getGolfCourseMapDataReq) {
        Map<String, Object> body = this.mParamsFactory.getBody(getGolfCourseMapDataReq);
        body.put("language", getGolfCourseMapDataReq.getLanguage());
        body.put("clientType", eil.a());
        Map<String, String> headers = this.mParamsFactory.getHeaders();
        setGolfAt(BaseApplication.getContext(), headers, getGolfCourseMapDataReq.isAnon());
        return (GetGolfCourseMapDataRsp) lqi.d().d(getGolfCourseMapDataReq.getUrl(), headers, lql.b(body), GetGolfCourseMapDataRsp.class);
    }

    private void setGolfAt(Context context, Map<String, String> map, boolean z) {
        String d;
        if (z) {
            map.remove("x-huid");
        } else {
            map.put(CloudParamKeys.X_TOKEN_TYPE, String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
            map.put(CloudParamKeys.X_TOKEN, LoginInit.getInstance(context).getAccountInfo(1008));
        }
        if (LoginInit.getInstance(context).isLoginedByWear()) {
            LogUtil.a(TAG, "callService appid wear logined");
            d = "com.huawei.bone";
        } else {
            LogUtil.a(TAG, "callService appid health logined");
            d = com.huawei.haf.application.BaseApplication.d();
        }
        map.put(CloudParamKeys.X_APP_ID, d);
        map.put(CloudParamKeys.X_SITE_ID, String.valueOf(LoginInit.getInstance(context).getSiteId()));
        LogUtil.a(TAG, "callService getSiteId=", Integer.valueOf(LoginInit.getInstance(context).getSiteId()));
    }

    private void setGolfAt(Context context, Map<String, String> map) {
        setGolfAt(context, map, false);
    }

    protected GolfClubListInfoDataRsp requestGolfClubListInfoData(GolfClubListDataReq golfClubListDataReq) {
        Map<String, Object> body = this.mParamsFactory.getBody(golfClubListDataReq);
        body.put("language", golfClubListDataReq.getLanguage());
        body.put("clientType", eil.a());
        Map<String, String> headers = this.mParamsFactory.getHeaders();
        setGolfAt(BaseApplication.getContext(), headers);
        return (GolfClubListInfoDataRsp) lqi.d().d(golfClubListDataReq.getUrl(), headers, lql.b(body), GolfClubListInfoDataRsp.class);
    }

    protected GolfCourseBrieInfosRsp requestGolfBrieInfoData(GolfCourseBriefInfosReq golfCourseBriefInfosReq) {
        Map<String, Object> body = this.mParamsFactory.getBody(golfCourseBriefInfosReq);
        body.put("language", golfCourseBriefInfosReq.getLanguage());
        body.put("clientType", eil.a());
        Map<String, String> headers = this.mParamsFactory.getHeaders();
        setGolfAt(BaseApplication.getContext(), headers);
        return (GolfCourseBrieInfosRsp) lqi.d().d(golfCourseBriefInfosReq.getUrl(), headers, lql.b(body), GolfCourseBrieInfosRsp.class);
    }

    protected static GolfCloudApiUtils getInstance() {
        if (sCloudUtils == null) {
            synchronized (LOCK) {
                if (sCloudUtils == null) {
                    sCloudUtils = new GolfCloudApiUtils();
                }
            }
        }
        return sCloudUtils;
    }
}
