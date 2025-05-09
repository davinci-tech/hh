package com.huawei.health.tradeservice.cloud;

import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class OrderRedeliveryReq extends OrderReportResultReq {
    @Override // com.huawei.health.tradeservice.cloud.OrderReportResultReq, com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/order/redelivery";
    }
}
