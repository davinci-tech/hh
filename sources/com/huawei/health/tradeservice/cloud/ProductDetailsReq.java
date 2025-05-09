package com.huawei.health.tradeservice.cloud;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class ProductDetailsReq implements IRequest {
    private static final String TAG = "ProductDetailsReq";

    @SerializedName(CloudParamKeys.CLIENT_TYPE)
    private int clientType;

    @SerializedName("country")
    private String country;

    @SerializedName("language")
    private String language;

    @SerializedName("productId")
    private String productId;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("tradeService");
        if (TextUtils.isEmpty(url)) {
            LogUtil.h(TAG, "host is empty");
            return "";
        }
        StringBuilder sb = new StringBuilder(url);
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            sb.append("/tradeservice/v1/product/detailsAnon?");
        } else {
            sb.append("/tradeservice/v1/product/details?");
        }
        return sb.toString();
    }

    public int getClientType() {
        return this.clientType;
    }

    public void setClientType(int i) {
        this.clientType = i;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }
}
