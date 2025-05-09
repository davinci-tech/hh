package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.server.AdContentReq;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.beans.server.AdPreReq;
import com.huawei.openalliance.ad.beans.server.AdPreRsp;
import com.huawei.openalliance.ad.beans.server.AnalysisReportReq;
import com.huawei.openalliance.ad.beans.server.AppConfigReq;
import com.huawei.openalliance.ad.beans.server.AppConfigRsp;
import com.huawei.openalliance.ad.beans.server.ConsentConfigReq;
import com.huawei.openalliance.ad.beans.server.ConsentConfigRsp;
import com.huawei.openalliance.ad.beans.server.EventReportReq;
import com.huawei.openalliance.ad.beans.server.EventReportRsp;
import com.huawei.openalliance.ad.beans.server.PermissionReq;
import com.huawei.openalliance.ad.beans.server.PermissionRsp;
import com.huawei.openalliance.ad.net.http.Response;
import java.util.Map;

/* loaded from: classes5.dex */
public interface ju {
    @kl(a = "adxServerFb")
    @kg
    Response<AdContentRsp> a(@ka int i, @jz AdContentReq adContentReq, @ke Map<String, String> map);

    @kl(a = "adxServer")
    @kg
    Response<AdPreRsp> a(@jz AdPreReq adPreReq);

    @kl(a = "analyticsServer")
    @kg
    Response<EventReportRsp> a(@jz AnalysisReportReq analysisReportReq, @ke Map<String, String> map);

    @kl(a = "consentConfigServer")
    @kg
    Response<ConsentConfigRsp> a(@jz ConsentConfigReq consentConfigReq, @ke Map<String, String> map);

    @kl(a = "eventServer")
    @kg
    Response<EventReportRsp> a(@jz EventReportReq eventReportReq, @ke Map<String, String> map);

    @kl(a = "permissionServer")
    @kg
    Response<PermissionRsp> a(@jz PermissionReq permissionReq, @ke Map<String, String> map);

    @kl(a = "adxServer")
    @kg
    Response<AdContentRsp> a(@kc boolean z, @jz AdContentReq adContentReq, @ke Map<String, String> map);

    @kl(a = "configServer")
    @kg
    Response<AppConfigRsp> a(@kc boolean z, @jz AppConfigReq appConfigReq, @ke Map<String, String> map);

    @kl(a = "adxServer")
    @kg
    Response<String> b(@kc boolean z, @jz AdContentReq adContentReq, @ke Map<String, String> map);
}
