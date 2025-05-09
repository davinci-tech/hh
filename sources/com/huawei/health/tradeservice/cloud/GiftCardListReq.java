package com.huawei.health.tradeservice.cloud;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class GiftCardListReq implements IRequest {
    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/giftCards";
    }
}
