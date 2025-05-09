package com.huawei.pluginfitnessadvice.audio;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.operation.utils.Constants;
import java.util.List;

/* loaded from: classes6.dex */
public class SleepAudioSeries implements Parcelable {
    public static final Parcelable.Creator<SleepAudioSeries> CREATOR = new Parcelable.Creator<SleepAudioSeries>() { // from class: com.huawei.pluginfitnessadvice.audio.SleepAudioSeries.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmp_, reason: merged with bridge method [inline-methods] */
        public SleepAudioSeries createFromParcel(Parcel parcel) {
            return new SleepAudioSeries(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public SleepAudioSeries[] newArray(int i) {
            return new SleepAudioSeries[i];
        }
    };

    @SerializedName("audioDescription")
    private String mAudioDescription;

    @SerializedName("audioDuration")
    private long mAudioDuration;

    @SerializedName("audioType")
    private int mAudioType;

    @SerializedName("backGroundImg")
    private String mBackGroundImg;

    @SerializedName("decompressType")
    private int mDecompressType;

    @SerializedName("displayStyle")
    private int mDisplayStyle;

    @SerializedName("enableNewUrl")
    private int mEnableNewUrl;

    @SerializedName("expireTime")
    private long mExpireTime;

    @SerializedName("from")
    private int mFrom;

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

    @SerializedName("introductionImgs")
    private List<String> mIntroductionImgs;

    @SerializedName("isSelected")
    private int mIsSelected;

    @SerializedName("isShow")
    private int mIsShow;

    @SerializedName("isShowCheckBox")
    private int mIsShowCheckBox;

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

    @SerializedName("positionId")
    private String mPositionId;

    @SerializedName("positionName")
    private String mPositionName;
    private String mPullFrom;

    @SerializedName("purchasedStatus")
    private int mPurchasedStatus;

    @SerializedName("resPurchaseInfo")
    private ResPurchaseInfo mResPurchaseInfo;
    private String mResourceId;
    private String mResourceName;

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

    public int getEnableNewUrl() {
        return this.mEnableNewUrl;
    }

    public void setEnableNewUrl(int i) {
        this.mEnableNewUrl = i;
    }

    public SleepAudioSeries() {
    }

    protected SleepAudioSeries(Parcel parcel) {
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
        this.mShareTitle = parcel.readString();
        this.mShareDescription = parcel.readString();
        this.mShareIcon = parcel.readString();
        this.mPositionId = parcel.readString();
        this.mPositionName = parcel.readString();
        this.mDisplayStyle = parcel.readInt();
        this.mHeatCount = parcel.readInt();
        this.mIntroductionImgs = parcel.createStringArrayList();
        this.mPurchasedStatus = parcel.readInt();
        this.mResPurchaseInfo = (ResPurchaseInfo) parcel.readParcelable(ResPurchaseInfo.class.getClassLoader());
        this.mUserAttributes = (UserAttributes) parcel.readParcelable(UserAttributes.class.getClassLoader());
        this.mIsShowCheckBox = parcel.readInt();
        this.mIsSelected = parcel.readInt();
        this.mPlayRecord = (PlayRecordInfo) parcel.readParcelable(PlayRecordInfo.class.getClassLoader());
        this.mFrom = parcel.readInt();
        this.mExpireTime = parcel.readLong();
        this.mEnableNewUrl = parcel.readInt();
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

    public String getShareTitle() {
        return this.mShareTitle;
    }

    public String getShareDescription() {
        return this.mShareDescription;
    }

    public String getShareIcon() {
        return this.mShareIcon;
    }

    public String getPositionId() {
        return this.mPositionId;
    }

    public void setPositionId(String str) {
        this.mPositionId = str;
    }

    public String getPositionName() {
        return this.mPositionName;
    }

    public void setPositionName(String str) {
        this.mPositionName = str;
    }

    public int getDisplayStyle() {
        return this.mDisplayStyle;
    }

    public void setDisplayStyle(int i) {
        this.mDisplayStyle = i;
    }

    public int getHeatCount() {
        return this.mHeatCount;
    }

    public void setHeatCount(int i) {
        this.mHeatCount = i;
    }

    public List<String> getIntroductionImgs() {
        return this.mIntroductionImgs;
    }

    public void setIntroductionImgs(List<String> list) {
        this.mIntroductionImgs = list;
    }

    public int getPurchasedStatus() {
        return this.mPurchasedStatus;
    }

    public void setPurchasedStatus(int i) {
        this.mPurchasedStatus = i;
    }

    public ResPurchaseInfo getResPurchaseInfo() {
        return this.mResPurchaseInfo;
    }

    public void setResPurchaseInfo(ResPurchaseInfo resPurchaseInfo) {
        this.mResPurchaseInfo = resPurchaseInfo;
    }

    public UserAttributes getUserAttributes() {
        return this.mUserAttributes;
    }

    public void setUserAttributes(UserAttributes userAttributes) {
        this.mUserAttributes = userAttributes;
    }

    public int getIsShowCheckBox() {
        return this.mIsShowCheckBox;
    }

    public void setIsShowCheckBox(int i) {
        this.mIsShowCheckBox = i;
    }

    public int getIsSelected() {
        return this.mIsSelected;
    }

    public void setIsSelected(int i) {
        this.mIsSelected = i;
    }

    public long getExpireTime() {
        return this.mExpireTime;
    }

    public void setExpireTime(long j) {
        this.mExpireTime = j;
    }

    public void setFrom(int i) {
        this.mFrom = i;
    }

    public int getFrom() {
        return this.mFrom;
    }

    public PlayRecordInfo getPlayRecord() {
        return this.mPlayRecord;
    }

    public void setPlayRecord(PlayRecordInfo playRecordInfo) {
        this.mPlayRecord = playRecordInfo;
    }

    public String getResourceId() {
        return this.mResourceId;
    }

    public void setResourceId(String str) {
        this.mResourceId = str;
    }

    public String getResourceName() {
        return this.mResourceName;
    }

    public void setResourceName(String str) {
        this.mResourceName = str;
    }

    public String getPullFrom() {
        return this.mPullFrom;
    }

    public void setPullFrom(String str) {
        this.mPullFrom = str;
    }

    public String toString() {
        return "SleepAudioSeries{mId=" + this.mId + ", mName='" + this.mName + "', mAudioDescription='" + this.mAudioDescription + "', mAudioType=" + this.mAudioType + ", mDecompressType=" + this.mDecompressType + ", mImgIcon='" + this.mImgIcon + "', mBackGroundImg='" + this.mBackGroundImg + "', mSmallImage='" + this.mSmallImage + "', mIconType=" + this.mIconType + ", mIsVip=" + this.mIsVip + ", mAudioDuration=" + this.mAudioDuration + ", mInfoType=" + this.mInfoType + ", mParentAudioId=" + this.mParentAudioId + ", mSeriesAudioSize=" + this.mSeriesAudioSize + ", mSeriesAudioUseSize=" + this.mSeriesAudioUseSize + ", mIsShow=" + this.mIsShow + ", mPlaceHolder=" + this.mPlaceHolder + ", mShareTitle='" + this.mShareTitle + "', mShareDescription='" + this.mShareDescription + "', mShareIcon='" + this.mShareIcon + "', mPositionId='" + this.mPositionId + "', mPositionName='" + this.mPositionName + "', mDisplayStyle=" + this.mDisplayStyle + ", mHeatCount=" + this.mHeatCount + ", mIntroductionImgs=" + this.mIntroductionImgs + ", mPurchasedStatus=" + this.mPurchasedStatus + ", mResPurchaseInfo=" + this.mResPurchaseInfo + ", mUserAttributes=" + this.mUserAttributes + ", mIsShowCheckBox=" + this.mIsShowCheckBox + ", mIsSelected=" + this.mIsSelected + ", mExpireTime=" + this.mExpireTime + ", mPlayRecord=" + this.mPlayRecord + '}';
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
        parcel.writeString(this.mShareTitle);
        parcel.writeString(this.mShareDescription);
        parcel.writeString(this.mShareIcon);
        parcel.writeString(this.mPositionId);
        parcel.writeString(this.mPositionName);
        parcel.writeInt(this.mDisplayStyle);
        parcel.writeInt(this.mHeatCount);
        parcel.writeStringList(this.mIntroductionImgs);
        parcel.writeInt(this.mPurchasedStatus);
        parcel.writeParcelable(this.mResPurchaseInfo, i);
        parcel.writeParcelable(this.mUserAttributes, i);
        parcel.writeInt(this.mIsShowCheckBox);
        parcel.writeInt(this.mIsSelected);
        parcel.writeParcelable(this.mPlayRecord, i);
        parcel.writeInt(this.mFrom);
        parcel.writeLong(this.mExpireTime);
        parcel.writeInt(this.mEnableNewUrl);
    }
}
