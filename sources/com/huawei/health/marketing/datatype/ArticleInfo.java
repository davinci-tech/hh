package com.huawei.health.marketing.datatype;

import java.util.List;

/* loaded from: classes8.dex */
public class ArticleInfo {
    private int articleId;
    private List<ArticleImage> articleImgList;
    private String articleTitle;
    private String articleType;
    private long createTime;

    public int getArticleId() {
        return this.articleId;
    }

    public void setArticleId(int i) {
        this.articleId = i;
    }

    public String getArticleTitle() {
        return this.articleTitle;
    }

    public void setArticleTitle(String str) {
        this.articleTitle = str;
    }

    public String getArticleType() {
        return this.articleType;
    }

    public void setArticleType(String str) {
        this.articleType = str;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public List<ArticleImage> getArticleImgList() {
        return this.articleImgList;
    }

    public void setArticleImgList(List<ArticleImage> list) {
        this.articleImgList = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("articleId=");
        sb.append(this.articleId);
        sb.append(" articleImgList=");
        sb.append(this.articleImgList);
        sb.append(" articleType=");
        sb.append(this.articleTitle);
        sb.append(" articleTitle=");
        sb.append(this.articleImgList);
        sb.append(" articleImgList=");
        sb.append(this.articleImgList);
        sb.append(" createTime=");
        sb.append(this.createTime);
        return sb.toString();
    }
}
