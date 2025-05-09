package com.huawei.health.tradeservice.cloud;

import com.huawei.trade.datatype.GiftCard;
import java.util.List;

/* loaded from: classes4.dex */
public class GiftCardListRsp extends BaseRsp {
    private List<GiftCard> giftCardList;

    public List<GiftCard> getGiftCardList() {
        return this.giftCardList;
    }

    public void setGiftCardList(List<GiftCard> list) {
        this.giftCardList = list;
    }
}
