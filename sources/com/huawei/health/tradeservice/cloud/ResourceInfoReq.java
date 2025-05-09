package com.huawei.health.tradeservice.cloud;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class ResourceInfoReq implements IRequest {
    private static final String TAG = "ResourceInfoReq";

    @SerializedName(CloudParamKeys.CLIENT_TYPE)
    private int clientType;

    @SerializedName("country")
    private String country;

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
            sb.append("/tradeservice/v1/resource/summaryInfoAnon?");
        } else {
            sb.append("/tradeservice/v1/resource/summaryInfo?");
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

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }
}
