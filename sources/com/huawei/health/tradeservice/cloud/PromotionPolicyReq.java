package com.huawei.health.tradeservice.cloud;

import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class PromotionPolicyReq implements IRequest {
    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("tradeService") + "/tradeservice/v1/promotion-policy";
    }
}
