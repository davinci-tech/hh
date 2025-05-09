package com.huawei.health.vip.vipinfo;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class VipMessageListReq implements IRequest {
    private static final String VIP_BENEFIT_PATH = "/tradeservice/v1/serviceMessages";
    private static final String VIP_PATH = "/tradeservice/v1/member/messages";

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + VIP_PATH;
    }

    public static String getVipBenefitPath() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + VIP_BENEFIT_PATH;
    }
}
