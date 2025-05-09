package com.huawei.health.marketing.request;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class SearchContentReq implements IRequest {

    @SerializedName("categoryId")
    private String categoryId;

    @SerializedName("content")
    private String content;

    @SerializedName("from")
    private String from;

    @SerializedName("searchScope")
    private String searchScope;

    @SerializedName("size")
    private String size;

    @SerializedName("subCategoryId")
    private String subCategoryId;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(GRSManager.a(BaseApplication.e()).getUrl("healthRecommendUrl"));
        sb.append(LoginInit.getInstance(BaseApplication.e()).getIsLogined() ? "/searchservice/v1/content" : "/searchservice/v1/contentAnon");
        return sb.toString();
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String str) {
        this.categoryId = str;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String str) {
        this.size = str;
    }

    public String getSearchScope() {
        return this.searchScope;
    }

    public void setSearchScope(String str) {
        this.searchScope = str;
    }

    public String getSubCategoryId() {
        return this.subCategoryId;
    }

    public void setSubCategoryId(String str) {
        this.subCategoryId = str;
    }
}
