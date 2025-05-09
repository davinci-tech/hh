package com.huawei.trade.datatype;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/* loaded from: classes6.dex */
public class TradeBaseInfo {

    @SerializedName("biParams")
    protected Map<String, String> biParams;

    @SerializedName("productId")
    protected String productId;

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public Map<String, String> getBiParams() {
        return this.biParams;
    }

    public void setBiParams(Map<String, String> map) {
        this.biParams = map;
    }
}
