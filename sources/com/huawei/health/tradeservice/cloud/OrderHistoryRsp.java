package com.huawei.health.tradeservice.cloud;

import com.huawei.trade.datatype.OrderBriefInfo;
import java.util.List;

/* loaded from: classes4.dex */
public class OrderHistoryRsp extends BaseRsp {
    private List<OrderBriefInfo> historyOrders;

    public List<OrderBriefInfo> getHistoryOrders() {
        return this.historyOrders;
    }

    public void setHistoryOrders(List<OrderBriefInfo> list) {
        this.historyOrders = list;
    }
}
