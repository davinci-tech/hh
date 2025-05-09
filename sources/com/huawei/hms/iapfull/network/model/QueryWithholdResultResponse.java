package com.huawei.hms.iapfull.network.model;

import java.util.List;

/* loaded from: classes9.dex */
public class QueryWithholdResultResponse extends BaseResponse {
    private List<WithholdQueryResultData> data;
    private String enReturnDesc;
    private String offsetHour;

    public void setOffsetHour(String str) {
        this.offsetHour = str;
    }

    public void setEnReturnDesc(String str) {
        this.enReturnDesc = str;
    }

    public void setData(List<WithholdQueryResultData> list) {
        this.data = list;
    }

    public String getOffsetHour() {
        return this.offsetHour;
    }

    public String getEnReturnDesc() {
        return this.enReturnDesc;
    }

    public List<WithholdQueryResultData> getData() {
        return this.data;
    }
}
