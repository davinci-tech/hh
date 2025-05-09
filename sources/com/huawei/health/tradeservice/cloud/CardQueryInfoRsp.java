package com.huawei.health.tradeservice.cloud;

import com.huawei.trade.datatype.ThirdCardInfo;
import java.util.List;

/* loaded from: classes4.dex */
public class CardQueryInfoRsp extends BaseRsp {
    private List<ThirdCardInfo> thirdCardInfos;

    public List<ThirdCardInfo> getThirdCardInfos() {
        return this.thirdCardInfos;
    }

    public void setThirdCardInfos(List<ThirdCardInfo> list) {
        this.thirdCardInfos = list;
    }
}
