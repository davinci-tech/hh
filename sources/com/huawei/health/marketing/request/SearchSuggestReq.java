package com.huawei.health.marketing.request;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class SearchSuggestReq implements IRequest {

    @SerializedName("content")
    private String content;

    @SerializedName("size")
    private String size;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(GRSManager.a(BaseApplication.e()).getUrl("healthRecommendUrl"));
        sb.append(LoginInit.getInstance(BaseApplication.e()).getIsLogined() ? "/searchservice/v1/suggest" : "/searchservice/v1/suggestAnon");
        return sb.toString();
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String str) {
        this.size = str;
    }
}
