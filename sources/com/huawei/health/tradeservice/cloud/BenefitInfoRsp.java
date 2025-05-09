package com.huawei.health.tradeservice.cloud;

import com.huawei.trade.datatype.RepeatResourceBenefitInfo;

/* loaded from: classes4.dex */
public class BenefitInfoRsp extends BaseRsp {
    private RepeatResourceBenefitInfo benefitResult;

    public RepeatResourceBenefitInfo getBenefitResult() {
        return this.benefitResult;
    }

    public void setBenefitResult(RepeatResourceBenefitInfo repeatResourceBenefitInfo) {
        this.benefitResult = repeatResourceBenefitInfo;
    }
}
