package com.huawei.operation.h5pro.jsmodules.trade;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.trade.datatype.TradeViewInfo;

/* loaded from: classes5.dex */
class TradePayViewParamObj {

    @SerializedName("tradeInfo")
    private TradeViewInfo tradeInfo;

    @SerializedName(ParamConstants.Param.VIEW_TYPE)
    private String viewType = ViewType.VIP.viewType;

    @SerializedName("vipInfo")
    private VipViewInfo vipInfo;

    TradePayViewParamObj() {
    }

    public String getViewType() {
        return this.viewType;
    }

    public TradeViewInfo getTradeInfo() {
        return this.tradeInfo;
    }

    public VipViewInfo getVipInfo() {
        return this.vipInfo;
    }

    public void setViewType(String str) {
        this.viewType = str;
    }

    public void setTradeInfo(TradeViewInfo tradeViewInfo) {
        this.tradeInfo = tradeViewInfo;
    }

    public void setVipInfo(VipViewInfo vipViewInfo) {
        this.vipInfo = vipViewInfo;
    }

    static class VipViewInfo {

        @SerializedName("packageName")
        private String packageName;

        @SerializedName(BleConstants.KEY_PATH)
        private String path;

        VipViewInfo() {
        }

        public String getPackageName() {
            return this.packageName;
        }

        public String getPath() {
            return this.path;
        }

        public void setPackageName(String str) {
            this.packageName = str;
        }

        public void setPath(String str) {
            this.path = str;
        }
    }

    enum ViewType {
        VIP("VIP"),
        TRADE("TRADE");

        final String viewType;

        ViewType(String str) {
            this.viewType = str;
        }
    }
}
