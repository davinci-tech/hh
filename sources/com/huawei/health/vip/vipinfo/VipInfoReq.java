package com.huawei.health.vip.vipinfo;

import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class VipInfoReq {
    private static final String MEMBER_TYPE_PATH = "&memberType=1";
    private static final String RESOURCE_TYPE_PATH = "?resourceType=4";
    private static final String TRADE_SERVICE_PATH = "/tradeservice/v1";
    private static final String TRANSFER_BENEFITS_PATH = "/transfer-benefits";
    private static final String VIP_PATH = "/tradeservice/v1/member";
    private static final String VIP_RECEIVED_SHARED_LIST_PATH = "/tradeservice/v1/member/share/sharedUser/authorizations";
    private static final String VIP_TYPE_PATH = "/tradeservice/v1/member/types";
    private static final String VIP_USER_INFO_PATH = "/tradeservice/v1/member/userInfos";

    public String getVipUserInfoUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + VIP_USER_INFO_PATH;
    }

    public String getVipTypeUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + VIP_TYPE_PATH;
    }

    public String getVipSharedInvitationListUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + VIP_RECEIVED_SHARED_LIST_PATH;
    }

    public String getVipTransferBenefitsUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/transfer-benefits?resourceType=4&memberType=1";
    }
}
