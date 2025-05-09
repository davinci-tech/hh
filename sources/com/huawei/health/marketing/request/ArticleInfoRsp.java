package com.huawei.health.marketing.request;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.marketing.datatype.ArticleInfo;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes8.dex */
public class ArticleInfoRsp extends CloudCommonReponse {

    @SerializedName("articleInfo")
    private ArticleInfo articleInfo;

    public ArticleInfo getArticleInfo() {
        return this.articleInfo;
    }

    public void setArticleInfo(ArticleInfo articleInfo) {
        this.articleInfo = articleInfo;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("articleInfo=");
        sb.append(this.articleInfo);
        return sb.toString();
    }
}
