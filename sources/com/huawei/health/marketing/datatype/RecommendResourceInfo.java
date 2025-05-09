package com.huawei.health.marketing.datatype;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes8.dex */
public class RecommendResourceInfo {

    @SerializedName("appearTime")
    private long appearTime;

    @SerializedName("buttonDescription")
    private String buttonDescription;

    @SerializedName("coverLinkValue")
    private String coverLinkValue;

    @SerializedName("coverPicture")
    private String coverPicture;

    @SerializedName("disappearTime")
    private long disappearTime;

    @SerializedName("labelPriority")
    private String labelPriority;

    @SerializedName("labelScenario")
    private String labelScenario;

    @SerializedName("memberPrice")
    private long memberPrice;

    @SerializedName(ParsedFieldTag.PRICE)
    private long price;

    @SerializedName("priority")
    private int priority;

    @SerializedName("recommendDescription")
    private String recommendDescription;

    @SerializedName("resourceCategory")
    private String resourceCategory;

    @SerializedName("resourceId")
    private String resourceId;

    @SerializedName("resourceLabel")
    private String resourceLabel;

    @SerializedName("resourceName")
    private String resourceName;

    @SerializedName("sceneShowArticles")
    private String sceneShowArticles;

    public String getSceneShowArticles() {
        return this.sceneShowArticles;
    }

    public void setSceneShowArticles(String str) {
        this.sceneShowArticles = str;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String str) {
        this.resourceName = str;
    }

    public String getResourceCategory() {
        return this.resourceCategory;
    }

    public void setResourceCategory(String str) {
        this.resourceCategory = str;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String str) {
        this.resourceId = str;
    }

    public String getResourceLabel() {
        return this.resourceLabel;
    }

    public void setResourceLabel(String str) {
        this.resourceLabel = str;
    }

    public String getLabelScenario() {
        return this.labelScenario;
    }

    public void setLabelScenario(String str) {
        this.labelScenario = str;
    }

    public String getLabelPriority() {
        return this.labelPriority;
    }

    public void setLabelPriority(String str) {
        this.labelPriority = str;
    }

    public String getCoverPicture() {
        return this.coverPicture;
    }

    public void setCoverPicture(String str) {
        this.coverPicture = str;
    }

    public String getCoverLinkValue() {
        return this.coverLinkValue;
    }

    public void setCoverLinkValue(String str) {
        this.coverLinkValue = str;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public long getPrice() {
        return this.price;
    }

    public void setPrice(long j) {
        this.price = j;
    }

    public long getMemberPrice() {
        return this.memberPrice;
    }

    public void setMemberPrice(long j) {
        this.memberPrice = j;
    }

    public long getAppearTime() {
        return this.appearTime;
    }

    public void setAppearTime(long j) {
        this.appearTime = j;
    }

    public long getDisappearTime() {
        return this.disappearTime;
    }

    public void setDisappearTime(long j) {
        this.disappearTime = j;
    }

    public String getRecommendDescription() {
        return this.recommendDescription;
    }

    public void setRecommendDescription(String str) {
        this.recommendDescription = str;
    }

    public String getButtonDescription() {
        return this.buttonDescription;
    }

    public void setButtonDescription(String str) {
        this.buttonDescription = str;
    }

    public boolean isInvalid() {
        return TextUtils.isEmpty(getCoverPicture()) || TextUtils.isEmpty(getCoverLinkValue()) || TextUtils.isEmpty(getButtonDescription()) || TextUtils.isEmpty(getRecommendDescription()) || TextUtils.isEmpty(getResourceName());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("resourceName=");
        sb.append(this.resourceName);
        sb.append(" resourceCategory=");
        sb.append(this.resourceCategory);
        sb.append(" resourceId=");
        sb.append(this.resourceId);
        sb.append(" resourceLabel=");
        sb.append(this.resourceLabel);
        sb.append(" labelScenario=");
        sb.append(this.labelScenario);
        sb.append(" labelPriority=");
        sb.append(this.labelPriority);
        sb.append(" coverPicture=");
        sb.append(this.coverPicture);
        sb.append(" coverLinkValue=");
        sb.append(this.coverLinkValue);
        sb.append(" appearTime=");
        sb.append(this.appearTime);
        sb.append(" disappearTime=");
        sb.append(this.disappearTime);
        sb.append(" priority=");
        sb.append(this.priority);
        sb.append(" sceneShowArticles=");
        sb.append(this.sceneShowArticles);
        return sb.toString();
    }
}
