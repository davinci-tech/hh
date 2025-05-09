package com.huawei.watchface.mvp.model.datatype;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class MarketH5Bean {
    private String designerName;
    private String from;

    @SerializedName("from_sub")
    private String fromSub;
    private String hitopId;
    private String productId;
    private String watchfaceName;

    public String getHitopId() {
        return this.hitopId;
    }

    public void setHitopId(String str) {
        this.hitopId = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public String getDesignerName() {
        return this.designerName;
    }

    public void setDesignerName(String str) {
        this.designerName = str;
    }

    public String getWatchfaceName() {
        return this.watchfaceName;
    }

    public void setWatchfaceName(String str) {
        this.watchfaceName = str;
    }

    public String getFromSub() {
        return this.fromSub;
    }

    public void setFromSub(String str) {
        this.fromSub = str;
    }
}
