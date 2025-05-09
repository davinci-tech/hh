package com.huawei.health.marketing.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.util.Objects;

/* loaded from: classes3.dex */
public class SingleHotSeriesRecommendContent extends BaseColumnContent {

    @SerializedName("backgroundPicture")
    private String mBackgroundPicture;

    @SerializedName("corner")
    private String mCorner;

    @SerializedName("cornerColor")
    private String mCornerColor;

    @SerializedName("dataOne")
    private String mDataOne;

    @SerializedName("dataOneDescription")
    private String mDataOneDescription;

    @SerializedName("dataTwo")
    private String mDataTwo;

    @SerializedName("dataTwoDescription")
    private String mDataTwoDescription;

    @SerializedName(JsbMapKeyNames.H5_TEXT_DETAIL)
    private String mDetail;

    @SerializedName("detailVisibility")
    private boolean mDetailVisibility;

    @SerializedName("dynamicDataId")
    private String mDynamicDataId;

    @SerializedName("itemCategory")
    private String mItemCategory;

    @SerializedName("leftButtonDesc")
    private String mLeftButtonDesc;

    @SerializedName("leftButtonLink")
    private String mLeftButtonLink;

    @SerializedName("link")
    private String mLink;

    @SerializedName("numOfAttemptingVisibility")
    private boolean mNumOfAttemptingVisibility;

    @SerializedName("numOfBuyersVisibility")
    private boolean mNumOfBuyersVisibility;

    @SerializedName("picture")
    private String mPicture;

    @SerializedName("rightButtonColor")
    private String mRightButtonColor;

    @SerializedName("rightButtonDescAfter")
    private String mRightButtonDescAfter;

    @SerializedName("rightButtonDescBefore")
    private String mRightButtonDescBefore;

    @SerializedName("rightButtonLinkAfter")
    private String mRightButtonLinkAfter;

    @SerializedName("rightButtonLinkBefore")
    private String mRightButtonLinkBefore;

    @SerializedName("title")
    private String mTitle;

    public String getLink() {
        return this.mLink;
    }

    public void setLink(String str) {
        this.mLink = str;
    }

    public String getDetail() {
        return this.mDetail;
    }

    public void setDetail(String str) {
        this.mDetail = str;
    }

    public boolean isDetailVisibility() {
        return this.mDetailVisibility;
    }

    public void setDetailVisibility(boolean z) {
        this.mDetailVisibility = z;
    }

    public String getCorner() {
        return this.mCorner;
    }

    public void setCorner(String str) {
        this.mCorner = str;
    }

    public String getCornerColor() {
        return this.mCornerColor;
    }

    public void setCornerColor(String str) {
        this.mCornerColor = str;
    }

    public String getPicture() {
        return this.mPicture;
    }

    public void setPicture(String str) {
        this.mPicture = str;
    }

    public String getBackgroundPicture() {
        return this.mBackgroundPicture;
    }

    public void setBackgroundPicture(String str) {
        this.mBackgroundPicture = str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getDataOne() {
        return this.mDataOne;
    }

    public void setDataOne(String str) {
        this.mDataOne = str;
    }

    public String getDataOneDescription() {
        return this.mDataOneDescription;
    }

    public void setDataOneDescription(String str) {
        this.mDataOneDescription = str;
    }

    public boolean isNumOfAttemptingVisibility() {
        return this.mNumOfAttemptingVisibility;
    }

    public void setNumOfAttemptingVisibility(boolean z) {
        this.mNumOfAttemptingVisibility = z;
    }

    public String getDataTwo() {
        return this.mDataTwo;
    }

    public void setDataTwo(String str) {
        this.mDataTwo = str;
    }

    public String getDataTwoDescription() {
        return this.mDataTwoDescription;
    }

    public void setDataTwoDescription(String str) {
        this.mDataTwoDescription = str;
    }

    public boolean isNumOfBuyersVisibility() {
        return this.mNumOfBuyersVisibility;
    }

    public void setNumOfBuyersVisibility(boolean z) {
        this.mNumOfBuyersVisibility = z;
    }

    public String getLeftButtonDesc() {
        return this.mLeftButtonDesc;
    }

    public void setLeftButtonDesc(String str) {
        this.mLeftButtonDesc = str;
    }

    public String getLeftButtonLink() {
        return this.mLeftButtonLink;
    }

    public void setLeftButtonLink(String str) {
        this.mLeftButtonLink = str;
    }

    public String getRightButtonDescBefore() {
        return this.mRightButtonDescBefore;
    }

    public void setRightButtonDescBefore(String str) {
        this.mRightButtonDescBefore = str;
    }

    public String getRightButtonLinkBefore() {
        return this.mRightButtonLinkBefore;
    }

    public void setRightButtonLinkBefore(String str) {
        this.mRightButtonLinkBefore = str;
    }

    public String getRightButtonDescAfter() {
        return this.mRightButtonDescAfter;
    }

    public void setRightButtonDescAfter(String str) {
        this.mRightButtonDescAfter = str;
    }

    public String getRightButtonLinkAfter() {
        return this.mRightButtonLinkAfter;
    }

    public void setRightButtonLinkAfter(String str) {
        this.mRightButtonLinkAfter = str;
    }

    public String getRightButtonColor() {
        return this.mRightButtonColor;
    }

    public void setRightButtonColor(String str) {
        this.mRightButtonColor = str;
    }

    public String getDynamicDataId() {
        return this.mDynamicDataId;
    }

    public void setDynamicDataId(String str) {
        this.mDynamicDataId = str;
    }

    public String getItemCategory() {
        return this.mItemCategory;
    }

    public void setItemCategory(String str) {
        this.mItemCategory = str;
    }

    @Override // com.huawei.health.marketing.datatype.BaseColumnContent
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SingleHotSeriesRecommendContent) || !super.equals(obj)) {
            return false;
        }
        SingleHotSeriesRecommendContent singleHotSeriesRecommendContent = (SingleHotSeriesRecommendContent) obj;
        return isDetailVisibility() == singleHotSeriesRecommendContent.isDetailVisibility() && isNumOfAttemptingVisibility() == singleHotSeriesRecommendContent.isNumOfAttemptingVisibility() && isNumOfBuyersVisibility() == singleHotSeriesRecommendContent.isNumOfBuyersVisibility() && Objects.equals(getLink(), singleHotSeriesRecommendContent.getLink()) && Objects.equals(getDetail(), singleHotSeriesRecommendContent.getDetail()) && Objects.equals(getCorner(), singleHotSeriesRecommendContent.getCorner()) && Objects.equals(getCornerColor(), singleHotSeriesRecommendContent.getCornerColor()) && Objects.equals(getPicture(), singleHotSeriesRecommendContent.getPicture()) && Objects.equals(getBackgroundPicture(), singleHotSeriesRecommendContent.getBackgroundPicture()) && Objects.equals(getTitle(), singleHotSeriesRecommendContent.getTitle()) && Objects.equals(getDataOne(), singleHotSeriesRecommendContent.getDataOne()) && Objects.equals(getDataOneDescription(), singleHotSeriesRecommendContent.getDataOneDescription()) && Objects.equals(getDataTwo(), singleHotSeriesRecommendContent.getDataTwo()) && Objects.equals(getDataTwoDescription(), singleHotSeriesRecommendContent.getDataTwoDescription()) && Objects.equals(getLeftButtonDesc(), singleHotSeriesRecommendContent.getLeftButtonDesc()) && Objects.equals(getLeftButtonLink(), singleHotSeriesRecommendContent.getLeftButtonLink()) && Objects.equals(getRightButtonDescBefore(), singleHotSeriesRecommendContent.getRightButtonDescBefore()) && Objects.equals(getRightButtonLinkBefore(), singleHotSeriesRecommendContent.getRightButtonLinkBefore()) && Objects.equals(getRightButtonDescAfter(), singleHotSeriesRecommendContent.getRightButtonDescAfter()) && Objects.equals(getRightButtonLinkAfter(), singleHotSeriesRecommendContent.getRightButtonLinkAfter()) && Objects.equals(getRightButtonColor(), singleHotSeriesRecommendContent.getRightButtonColor()) && Objects.equals(getDynamicDataId(), singleHotSeriesRecommendContent.getDynamicDataId()) && Objects.equals(getItemCategory(), singleHotSeriesRecommendContent.getItemCategory());
    }

    @Override // com.huawei.health.marketing.datatype.BaseColumnContent
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), getLink(), getDetail(), Boolean.valueOf(isDetailVisibility()), getCorner(), getCornerColor(), getPicture(), getBackgroundPicture(), getTitle(), getDataOne(), getDataOneDescription(), Boolean.valueOf(isNumOfAttemptingVisibility()), getDataTwo(), getDataTwoDescription(), Boolean.valueOf(isNumOfBuyersVisibility()), getLeftButtonDesc(), getLeftButtonLink(), getRightButtonDescBefore(), getRightButtonLinkBefore(), getRightButtonDescAfter(), getRightButtonLinkAfter(), getRightButtonColor(), getDynamicDataId(), getItemCategory());
    }

    @Override // com.huawei.health.marketing.datatype.BaseColumnContent
    public String toString() {
        return "SingleHotSeriesRecommendContent{link='" + this.mLink + "', detail='" + this.mDetail + "', detailVisibility=" + this.mDetailVisibility + ", corner='" + this.mCorner + "', cornerColor='" + this.mCornerColor + "', picture='" + this.mPicture + "', backgroundPicture='" + this.mBackgroundPicture + "', title='" + this.mTitle + "', dataOne='" + this.mDataOne + "', dataOneDescription='" + this.mDataOneDescription + "', numOfAttemptingVisibility=" + this.mNumOfAttemptingVisibility + ", dataTwo='" + this.mDataTwo + "', dataTwoDescription='" + this.mDataTwoDescription + "', numOfBuyersVisibility=" + this.mNumOfBuyersVisibility + ", leftButtonDesc='" + this.mLeftButtonDesc + "', leftButtonLink='" + this.mLeftButtonLink + "', rightButtonDescBefore='" + this.mRightButtonDescBefore + "', rightButtonLinkBefore='" + this.mRightButtonLinkBefore + "', rightButtonDescAfter='" + this.mRightButtonDescAfter + "', rightButtonLinkAfter='" + this.mRightButtonLinkAfter + "', rightButtonColor='" + this.mRightButtonColor + "', dynamicDataId='" + this.mDynamicDataId + "', itemCategory='" + this.mItemCategory + "'}";
    }
}
