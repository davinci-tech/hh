package com.huawei.health.marketing.request;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class ArticleInfoReq implements IRequest {
    private static final String ARTICLE_PATH = "/messageCenter/getArticleInfo";

    @SerializedName("articleId")
    private int articleId;

    @SerializedName("timeZone")
    private String timeZone;

    public int getArticleId() {
        return this.articleId;
    }

    public void setArticleId(int i) {
        this.articleId = i;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("domainMessagecenterHicloudNew") + ARTICLE_PATH;
    }

    public String toString() {
        return "ArticleInfoReq{articleId=" + this.articleId + ", timeZone=" + this.timeZone + '}';
    }
}
