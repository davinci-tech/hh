package com.huawei.basefitnessadvice.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginfitnessadvice.CoachBar;
import java.util.List;

/* loaded from: classes3.dex */
public class Introduction {

    @SerializedName("beginTime")
    private long mBeginTime;

    @SerializedName("cardChartPictureUrl")
    private String mCardChartPictureUrl;

    @SerializedName("coachInfo")
    private CoachBar mCoachInfo;

    @SerializedName("columnName")
    private String mColumnName;

    @SerializedName("descriptionPageTahitiUrl")
    private String mDescriptionPageTahitiUrl;

    @SerializedName("descriptionPageUrl")
    private String mDescriptionPageUrl;

    @SerializedName("introductionImgs")
    private List<String> mIntroductionImgs;

    @SerializedName("positionId")
    private int mPositionId;

    @SerializedName("positionName")
    private String mPositionName;

    @SerializedName("previewImgUrl")
    private String mPreviewImgUrl;

    @SerializedName("previewVideoId")
    private int mPreviewVideoId;

    @SerializedName("reportPageTahitiUrl")
    private String mReportPageTahitiUrl;

    @SerializedName("reportPageUrl")
    private String mReportPageUrl;

    public CoachBar acquireCoachInfo() {
        return this.mCoachInfo;
    }

    public void saveCoachInfo(CoachBar coachBar) {
        this.mCoachInfo = coachBar;
    }

    public List<String> acquireIntroductionImgs() {
        return this.mIntroductionImgs;
    }

    public void saveIntroductionImgs(List<String> list) {
        this.mIntroductionImgs = list;
    }

    public int acquirePositionId() {
        return this.mPositionId;
    }

    public void savePositionId(int i) {
        this.mPositionId = i;
    }

    public String acquirePositionName() {
        return this.mPositionName;
    }

    public void savePositionName(String str) {
        this.mPositionName = str;
    }

    public int acquirePreviewVideoId() {
        return this.mPreviewVideoId;
    }

    public void savePreviewVideoId(int i) {
        this.mPreviewVideoId = i;
    }

    public String acquirePreviewImgUrl() {
        return this.mPreviewImgUrl;
    }

    public void savePreviewImgUrl(String str) {
        this.mPreviewImgUrl = str;
    }

    public String acquireColumnName() {
        return this.mColumnName;
    }

    public void saveColumnName(String str) {
        this.mColumnName = str;
    }

    public long acquireBeginTime() {
        return this.mBeginTime;
    }

    public void saveBeginTime(long j) {
        this.mBeginTime = j;
    }

    public String acquireCardChartPictureUrl() {
        return this.mCardChartPictureUrl;
    }

    public void saveCardChartPictureUrl(String str) {
        this.mCardChartPictureUrl = str;
    }

    public String acquireDescriptionPageUrl() {
        return this.mDescriptionPageUrl;
    }

    public void saveDescriptionPageUrll(String str) {
        this.mDescriptionPageUrl = str;
    }

    public String acquireDescriptionPageTahitiUrl() {
        return this.mDescriptionPageTahitiUrl;
    }

    public void saveDescriptionPageTahitiUrl(String str) {
        this.mDescriptionPageTahitiUrl = str;
    }

    public String acquireReportPageUrl() {
        return this.mReportPageUrl;
    }

    public void saveReportPageUrl(String str) {
        this.mReportPageUrl = str;
    }

    public String acquireReportPageTahitiUrl() {
        return this.mReportPageTahitiUrl;
    }

    public void saveReportPageTahitiUrl(String str) {
        this.mReportPageTahitiUrl = str;
    }

    public String toString() {
        return "Introduction{mCoachInfo=" + this.mCoachInfo + ", mIntroductionImgs=" + this.mIntroductionImgs + ", mPositionId=" + this.mPositionId + ", mPositionName='" + this.mPositionName + "', mPreviewVideoId=" + this.mPreviewVideoId + ", mPreviewImgUrl='" + this.mPreviewImgUrl + "', mColumnName='" + this.mColumnName + "', mBeginTime=" + this.mBeginTime + ", mCardChartPictureUrl='" + this.mCardChartPictureUrl + "', mDescriptionPageUrl='" + this.mDescriptionPageUrl + "', mDescriptionPageTahitiUrl='" + this.mDescriptionPageTahitiUrl + "', mReportPageUrl='" + this.mReportPageUrl + "', mReportPageTahitiUrl='" + this.mReportPageTahitiUrl + "'}";
    }
}
