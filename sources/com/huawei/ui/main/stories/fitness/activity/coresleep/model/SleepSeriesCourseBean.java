package com.huawei.ui.main.stories.fitness.activity.coresleep.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;

/* loaded from: classes6.dex */
public class SleepSeriesCourseBean implements Parcelable {
    public static final Parcelable.Creator<SleepSeriesCourseBean> CREATOR = new Parcelable.Creator<SleepSeriesCourseBean>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.model.SleepSeriesCourseBean.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: drP_, reason: merged with bridge method [inline-methods] */
        public SleepSeriesCourseBean createFromParcel(Parcel parcel) {
            return new SleepSeriesCourseBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public SleepSeriesCourseBean[] newArray(int i) {
            return new SleepSeriesCourseBean[i];
        }
    };

    @SerializedName("itemCategory")
    private String mCategory;

    @SerializedName("dynamicDataId")
    private String mDataId;

    @SerializedName("description")
    private String mDesc;

    @SerializedName("descriptionVisibility")
    private boolean mDescVisibility;

    @SerializedName("itemEffectiveTime")
    private String mEffectiveTime;

    @SerializedName("itemExpirationTime")
    private String mExpirationTime;

    @SerializedName("linkValue")
    private String mLinkValue;

    @SerializedName("picture")
    private String mPictureUrl;

    @SerializedName(ParsedFieldTag.PRICE)
    private String mPrice;

    @SerializedName("priority")
    private int mPriority;
    private SleepAudioPackage mSleepAudioPackage;

    @SerializedName("tabName")
    private String mTabName;

    @SerializedName("theme")
    private String mTheme;

    @SerializedName("themeVisibility")
    private boolean mThemeVisibility;

    @SerializedName("cornerVisibility")
    private boolean mVisibility;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected SleepSeriesCourseBean(Parcel parcel) {
        this.mVisibility = parcel.readByte() != 0;
        this.mDesc = parcel.readString();
        this.mDescVisibility = parcel.readByte() != 0;
        this.mDataId = parcel.readString();
        this.mCategory = parcel.readString();
        this.mEffectiveTime = parcel.readString();
        this.mExpirationTime = parcel.readString();
        this.mLinkValue = parcel.readString();
        this.mPictureUrl = parcel.readString();
        this.mPriority = parcel.readInt();
        this.mTheme = parcel.readString();
        this.mThemeVisibility = parcel.readByte() != 0;
        this.mPrice = parcel.readString();
        this.mTabName = parcel.readString();
    }

    public boolean isVisibility() {
        return this.mVisibility;
    }

    public void setVisibility(boolean z) {
        this.mVisibility = z;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public void setDesc(String str) {
        this.mDesc = str;
    }

    public boolean isDescVisibility() {
        return this.mDescVisibility;
    }

    public void setDescVisibility(boolean z) {
        this.mDescVisibility = z;
    }

    public String getDataId() {
        return this.mDataId;
    }

    public void setDataId(String str) {
        this.mDataId = str;
    }

    public String getCategory() {
        return this.mCategory;
    }

    public void setCategory(String str) {
        this.mCategory = str;
    }

    public String getEffectiveTime() {
        return this.mEffectiveTime;
    }

    public void setEffectiveTime(String str) {
        this.mEffectiveTime = str;
    }

    public String getExpirationTime() {
        return this.mExpirationTime;
    }

    public void setExpirationTime(String str) {
        this.mExpirationTime = str;
    }

    public String getLinkValue() {
        return this.mLinkValue;
    }

    public void setLinkValue(String str) {
        this.mLinkValue = str;
    }

    public String getPictureUrl() {
        return this.mPictureUrl;
    }

    public void setPictureUrl(String str) {
        this.mPictureUrl = str;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public void setPriority(int i) {
        this.mPriority = i;
    }

    public String getTheme() {
        return this.mTheme;
    }

    public void setTheme(String str) {
        this.mTheme = str;
    }

    public boolean isThemeVisibility() {
        return this.mThemeVisibility;
    }

    public void setThemeVisibility(boolean z) {
        this.mThemeVisibility = z;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public void setPrice(String str) {
        this.mPrice = str;
    }

    public String getTabName() {
        return this.mTabName;
    }

    public void setTabName(String str) {
        this.mTabName = str;
    }

    public SleepAudioPackage getSleepAudioPackage() {
        return this.mSleepAudioPackage;
    }

    public void setSleepAudioPackage(SleepAudioPackage sleepAudioPackage) {
        this.mSleepAudioPackage = sleepAudioPackage;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mVisibility ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mDesc);
        parcel.writeByte(this.mDescVisibility ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mDataId);
        parcel.writeString(this.mCategory);
        parcel.writeString(this.mEffectiveTime);
        parcel.writeString(this.mExpirationTime);
        parcel.writeString(this.mLinkValue);
        parcel.writeString(this.mPictureUrl);
        parcel.writeInt(this.mPriority);
        parcel.writeString(this.mTheme);
        parcel.writeByte(this.mThemeVisibility ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mPrice);
        parcel.writeString(this.mTabName);
    }

    public String toString() {
        return "SleepSeriesCourseBean{mVisibility=" + this.mVisibility + ", mDesc='" + this.mDesc + "', mDescVisibility=" + this.mDescVisibility + ", mDataId='" + this.mDataId + "', mCategory='" + this.mCategory + "', mEffectiveTime='" + this.mEffectiveTime + "', mExpirationTime='" + this.mExpirationTime + "', mLinkValue='" + this.mLinkValue + "', mPictureUrl='" + this.mPictureUrl + "', mPriority=" + this.mPriority + ", mTheme='" + this.mTheme + "', mThemeVisibility=" + this.mThemeVisibility + ", mTabName='" + this.mTabName + "', mPrice='" + this.mPrice + "', mSleepAudioPackage=" + this.mSleepAudioPackage + '}';
    }
}
