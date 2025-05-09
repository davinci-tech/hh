package com.huawei.health.vip.vipinfo;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class VipTypeReq implements IRequest {
    private static final String VIP_PATH = "/tradeservice/v1/member/types";

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + VIP_PATH;
    }
}
