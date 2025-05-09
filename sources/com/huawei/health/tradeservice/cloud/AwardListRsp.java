package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.trade.datatype.AwardRecordsBean;
import java.util.List;

/* loaded from: classes4.dex */
public class AwardListRsp extends BaseRsp {

    @SerializedName("awardRecords")
    private List<AwardRecordsBean> awardRecords;

    public List<AwardRecordsBean> getAwardRecords() {
        return this.awardRecords;
    }

    public void setAwardRecords(List<AwardRecordsBean> list) {
        this.awardRecords = list;
    }
}
