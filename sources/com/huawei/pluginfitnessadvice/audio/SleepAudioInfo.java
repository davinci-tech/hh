package com.huawei.pluginfitnessadvice.audio;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.operation.utils.Constants;

/* loaded from: classes6.dex */
public class SleepAudioInfo implements Parcelable {
    public static final Parcelable.Creator<SleepAudioInfo> CREATOR = new Parcelable.Creator<SleepAudioInfo>() { // from class: com.huawei.pluginfitnessadvice.audio.SleepAudioInfo.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmn_, reason: merged with bridge method [inline-methods] */
        public SleepAudioInfo createFromParcel(Parcel parcel) {
            return new SleepAudioInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SleepAudioInfo[] newArray(int i) {
            return new SleepAudioInfo[i];
        }
    };

    @SerializedName("audioDescription")
    private String mAudioDescription;

    @SerializedName("audioDuration")
    private long mAudioDuration;

    @SerializedName("audioType")
    private int mAudioType;

    @SerializedName("audioUrl")
    private String mAudioUrl;

    @SerializedName("auditionUrl")
    private String mAuditionUrl;

    @SerializedName("backGroundImg")
    private String mBackGroundImg;

    @SerializedName("decompressType")
    private int mDecompressType;

    @SerializedName("firstTitle")
    private String mFirstTitle;

    @SerializedName("heatCount")
    private int mHeatCount;

    @SerializedName("iconType")
    private int mIconType;

    @SerializedName("id")
    private int mId;

    @SerializedName("imgIcon")
    private String mImgIcon;

    @SerializedName("infoType")
    private int mInfoType;

    @SerializedName("isShow")
    private int mIsShow;

    @SerializedName("isVip")
    private int mIsVip;

    @SerializedName("name")
    private String mName;

    @SerializedName("parentAudioId")
    private int mParentAudioId;

    @SerializedName("placeHolder")
    private int mPlaceHolder;

    @SerializedName("playRecord")
    private PlayRecordInfo mPlayRecord;

    @SerializedName("secondTitle")
    private String mSecondTitle;

    @SerializedName("seriesAudioSize")
    private int mSeriesAudioSize;

    @SerializedName("seriesAudioUseSize")
    private int mSeriesAudioUseSize;

    @SerializedName("shareDescription")
    private String mShareDescription;

    @SerializedName("shareIcon")
    private String mShareIcon;

    @SerializedName(Constants.SHARE_TITLE)
    private String mShareTitle;

    @SerializedName("smallImage")
    private String mSmallImage;

    @SerializedName("userAttributes")
    private UserAttributes mUserAttributes;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SleepAudioInfo() {
    }

    protected SleepAudioInfo(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mName = parcel.readString();
        this.mAudioDescription = parcel.readString();
        this.mAudioType = parcel.readInt();
        this.mDecompressType = parcel.readInt();
        this.mImgIcon = parcel.readString();
        this.mBackGroundImg = parcel.readString();
        this.mSmallImage = parcel.readString();
        this.mIconType = parcel.readInt();
        this.mIsVip = parcel.readInt();
        this.mAudioDuration = parcel.readLong();
        this.mInfoType = parcel.readInt();
        this.mParentAudioId = parcel.readInt();
        this.mSeriesAudioSize = parcel.readInt();
        this.mSeriesAudioUseSize = parcel.readInt();
        this.mIsShow = parcel.readInt();
        this.mPlaceHolder = parcel.readInt();
        this.mHeatCount = parcel.readInt();
        this.mAudioUrl = parcel.readString();
        this.mShareTitle = parcel.readString();
        this.mShareDescription = parcel.readString();
        this.mShareIcon = parcel.readString();
        this.mFirstTitle = parcel.readString();
        this.mSecondTitle = parcel.readString();
        this.mPlayRecord = (PlayRecordInfo) parcel.readParcelable(PlayRecordInfo.class.getClassLoader());
        this.mAuditionUrl = parcel.readString();
        this.mUserAttributes = (UserAttributes) parcel.readParcelable(UserAttributes.class.getClassLoader());
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getAudioDescription() {
        return this.mAudioDescription;
    }

    public void setAudioDescription(String str) {
        this.mAudioDescription = str;
    }

    public int getAudioType() {
        return this.mAudioType;
    }

    public void setAudioType(int i) {
        this.mAudioType = i;
    }

    public int getDecompressType() {
        return this.mDecompressType;
    }

    public void setDecompressType(int i) {
        this.mDecompressType = i;
    }

    public String getImgIcon() {
        return this.mImgIcon;
    }

    public void setImgIcon(String str) {
        this.mImgIcon = str;
    }

    public String getBackGroundImg() {
        return this.mBackGroundImg;
    }

    public void setBackGroundImg(String str) {
        this.mBackGroundImg = str;
    }

    public String getSmallImage() {
        return this.mSmallImage;
    }

    public void setSmallImage(String str) {
        this.mSmallImage = str;
    }

    public int getIconType() {
        return this.mIconType;
    }

    public void setIconType(int i) {
        this.mIconType = i;
    }

    public int getIsVip() {
        return this.mIsVip;
    }

    public void setIsVip(int i) {
        this.mIsVip = i;
    }

    public long getAudioDuration() {
        return this.mAudioDuration;
    }

    public void setAudioDuration(long j) {
        this.mAudioDuration = j;
    }

    public int getInfoType() {
        return this.mInfoType;
    }

    public void setInfoType(int i) {
        this.mInfoType = i;
    }

    public int getParentAudioId() {
        return this.mParentAudioId;
    }

    public void setParentAudioId(int i) {
        this.mParentAudioId = i;
    }

    public int getSeriesAudioSize() {
        return this.mSeriesAudioSize;
    }

    public void setSeriesAudioSize(int i) {
        this.mSeriesAudioSize = i;
    }

    public int getSeriesAudioUseSize() {
        return this.mSeriesAudioUseSize;
    }

    public void setSeriesAudioUseSize(int i) {
        this.mSeriesAudioUseSize = i;
    }

    public int getIsShow() {
        return this.mIsShow;
    }

    public void setIsShow(int i) {
        this.mIsShow = i;
    }

    public int getPlaceHolder() {
        return this.mPlaceHolder;
    }

    public void setPlaceHolder(int i) {
        this.mPlaceHolder = i;
    }

    public int getHeatCount() {
        return this.mHeatCount;
    }

    public void setHeatCount(int i) {
        this.mHeatCount = i;
    }

    public String getAudioUrl() {
        return this.mAudioUrl;
    }

    public void setAudioUrl(String str) {
        this.mAudioUrl = str;
    }

    public String getShareTitle() {
        return this.mShareTitle;
    }

    public void setShareTitle(String str) {
        this.mShareTitle = str;
    }

    public String getShareDescription() {
        return this.mShareDescription;
    }

    public void setShareDescription(String str) {
        this.mShareDescription = str;
    }

    public String getShareIcon() {
        return this.mShareIcon;
    }

    public void setShareIcon(String str) {
        this.mShareIcon = str;
    }

    public String getFirstTitle() {
        return this.mFirstTitle;
    }

    public void setFirstTitle(String str) {
        this.mFirstTitle = str;
    }

    public String getSecondTitle() {
        return this.mSecondTitle;
    }

    public void setSecondTitle(String str) {
        this.mSecondTitle = str;
    }

    public PlayRecordInfo getPlayRecord() {
        return this.mPlayRecord;
    }

    public void setPlayRecord(PlayRecordInfo playRecordInfo) {
        this.mPlayRecord = playRecordInfo;
    }

    public String getAuditionUrl() {
        return this.mAuditionUrl;
    }

    public void setAuditionUrl(String str) {
        this.mAuditionUrl = str;
    }

    public UserAttributes getUserAttributes() {
        return this.mUserAttributes;
    }

    public void setUserAttributes(UserAttributes userAttributes) {
        this.mUserAttributes = userAttributes;
    }

    public String toString() {
        return "SleepAudioInfo{mId=" + this.mId + ", mName='" + this.mName + "', mAudioDescription='" + this.mAudioDescription + "', mAudioType=" + this.mAudioType + ", mDecompressType=" + this.mDecompressType + ", mImgIcon='" + this.mImgIcon + "', mBackGroundImg='" + this.mBackGroundImg + "', mSmallImage='" + this.mSmallImage + "', mIconType=" + this.mIconType + ", mIsVip=" + this.mIsVip + ", mAudioDuration=" + this.mAudioDuration + ", mInfoType=" + this.mInfoType + ", mParentAudioId=" + this.mParentAudioId + ", mSeriesAudioSize=" + this.mSeriesAudioSize + ", mSeriesAudioUseSize=" + this.mSeriesAudioUseSize + ", mIsShow=" + this.mIsShow + ", mPlaceHolder=" + this.mPlaceHolder + ", mHeatCount=" + this.mHeatCount + ", mAudioUrl='" + this.mAudioUrl + "', mShareTitle='" + this.mShareTitle + "', mShareDescription='" + this.mShareDescription + "', mShareIcon='" + this.mShareIcon + "', mFirstTitle='" + this.mFirstTitle + "', mSecondTitle='" + this.mSecondTitle + "', mPlayRecord=" + this.mPlayRecord + ", mAuditionUrl='" + this.mAuditionUrl + "', mUserAttributes=" + this.mUserAttributes + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeString(this.mName);
        parcel.writeString(this.mAudioDescription);
        parcel.writeInt(this.mAudioType);
        parcel.writeInt(this.mDecompressType);
        parcel.writeString(this.mImgIcon);
        parcel.writeString(this.mBackGroundImg);
        parcel.writeString(this.mSmallImage);
        parcel.writeInt(this.mIconType);
        parcel.writeInt(this.mIsVip);
        parcel.writeLong(this.mAudioDuration);
        parcel.writeInt(this.mInfoType);
        parcel.writeInt(this.mParentAudioId);
        parcel.writeInt(this.mSeriesAudioSize);
        parcel.writeInt(this.mSeriesAudioUseSize);
        parcel.writeInt(this.mIsShow);
        parcel.writeInt(this.mPlaceHolder);
        parcel.writeInt(this.mHeatCount);
        parcel.writeString(this.mAudioUrl);
        parcel.writeString(this.mShareTitle);
        parcel.writeString(this.mShareDescription);
        parcel.writeString(this.mShareIcon);
        parcel.writeString(this.mFirstTitle);
        parcel.writeString(this.mSecondTitle);
        parcel.writeParcelable(this.mPlayRecord, i);
        parcel.writeString(this.mAuditionUrl);
        parcel.writeParcelable(this.mUserAttributes, i);
    }
}
