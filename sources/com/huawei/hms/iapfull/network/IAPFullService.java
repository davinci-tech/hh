package com.huawei.hms.iapfull.network;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.iapfull.network.model.AliPaySignResponse;
import com.huawei.hms.iapfull.network.model.DeveloperSignRequest;
import com.huawei.hms.iapfull.network.model.DeveloperSignResponse;
import com.huawei.hms.iapfull.network.model.PaySignRequest;
import com.huawei.hms.iapfull.network.model.ReportPayResultParams;
import com.huawei.hms.iapfull.network.model.ReportPayResultResponse;

/* loaded from: classes4.dex */
public interface IAPFullService {
    @POST("/TradeServer/client/auth/developUser.action")
    Submit<DeveloperSignResponse> developerSign(@Body DeveloperSignRequest developerSignRequest);

    @POST("/TradeServer/client/auth/paySign.action")
    Submit<AliPaySignResponse> paySign(@Body PaySignRequest paySignRequest);

    @POST("/TradeServer/client/reportV1.action")
    Submit<ReportPayResultResponse> reportPayResult(@Body ReportPayResultParams reportPayResultParams);
}
