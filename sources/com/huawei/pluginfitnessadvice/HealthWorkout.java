package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class HealthWorkout extends Workout {
    public static final Parcelable.Creator<HealthWorkout> CREATOR = new Parcelable.Creator<HealthWorkout>() { // from class: com.huawei.pluginfitnessadvice.HealthWorkout.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: clU_, reason: merged with bridge method [inline-methods] */
        public HealthWorkout createFromParcel(Parcel parcel) {
            return new HealthWorkout(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public HealthWorkout[] newArray(int i) {
            return new HealthWorkout[i];
        }
    };

    @SerializedName("aggregateTitle")
    private String mAggregateTitle;

    @SerializedName("chapterCount")
    private int mChapterCount;

    @SerializedName("commodityFlag")
    private int mCommodityFlag;

    @SerializedName("disPlaybit")
    private int mDisPlaybit;

    @SerializedName("enableNewUrl")
    private int mEnableNewUrl;

    @SerializedName("labelType")
    private int mLayType;

    @SerializedName("memberPrice")
    private String mMemberPrice;

    @SerializedName("normalPrice")
    private String mNormalPrice;

    @SerializedName("picture")
    private String mPicture;

    @SerializedName("squrePicture")
    private String mSqurePicture;

    @SerializedName("subTitle")
    private String mSubTitle;

    @SerializedName("users")
    private int mUsers;

    public static boolean isDisplayBitOne(int i, int i2) {
        return (i & i2) == i2;
    }

    @Override // com.huawei.pluginfitnessadvice.Workout, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HealthWorkout() {
    }

    protected HealthWorkout(Parcel parcel) {
        super(parcel);
        if (parcel == null) {
            return;
        }
        this.mUsers = parcel.readInt();
        this.mAggregateTitle = parcel.readString();
        this.mPicture = parcel.readString();
        this.mSqurePicture = parcel.readString();
        this.mLayType = parcel.readInt();
        this.mCommodityFlag = parcel.readInt();
        this.mChapterCount = parcel.readInt();
        this.mSubTitle = parcel.readString();
        this.mMemberPrice = parcel.readString();
        this.mNormalPrice = parcel.readString();
        this.mDisPlaybit = parcel.readInt();
        this.mEnableNewUrl = parcel.readInt();
    }

    @Override // com.huawei.pluginfitnessadvice.Workout, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mUsers);
        parcel.writeString(this.mAggregateTitle);
        parcel.writeString(this.mPicture);
        parcel.writeString(this.mSqurePicture);
        parcel.writeInt(this.mLayType);
        parcel.writeInt(this.mCommodityFlag);
        parcel.writeInt(this.mChapterCount);
        parcel.writeString(this.mSubTitle);
        parcel.writeString(this.mMemberPrice);
        parcel.writeString(this.mNormalPrice);
        parcel.writeInt(this.mDisPlaybit);
        parcel.writeInt(this.mEnableNewUrl);
    }

    public int getEnableNewUrl() {
        return this.mEnableNewUrl;
    }

    public void setEnableNewUrl(int i) {
        this.mEnableNewUrl = i;
    }

    public int getUsers() {
        return this.mUsers;
    }

    public void setUsers(int i) {
        this.mUsers = i;
    }

    public String getPicture() {
        return this.mPicture;
    }

    public String getAggregateTitle() {
        return this.mAggregateTitle;
    }

    public void setAggregateTitle(String str) {
        this.mAggregateTitle = str;
    }

    public void setPicture(String str) {
        this.mPicture = str;
    }

    public String getSqurePicture() {
        return this.mSqurePicture;
    }

    public void setSqurePicture(String str) {
        this.mSqurePicture = str;
    }

    public int getLayType() {
        return this.mLayType;
    }

    public void setLayType(int i) {
        this.mLayType = i;
    }

    public int getCommodityFlag() {
        return this.mCommodityFlag;
    }

    public void setCommodityFlag(int i) {
        this.mCommodityFlag = i;
    }

    public int getChapterCount() {
        return this.mChapterCount;
    }

    public void setChapterCount(int i) {
        this.mChapterCount = i;
    }

    public String getSubtitle() {
        return this.mSubTitle;
    }

    public void setSubtitle(String str) {
        this.mSubTitle = str;
    }

    public String getMemberPrice() {
        return this.mMemberPrice;
    }

    public void setMemberPrice(String str) {
        this.mMemberPrice = str;
    }

    public String getNormalPrice() {
        return this.mNormalPrice;
    }

    public void setNormalPrice(String str) {
        this.mNormalPrice = str;
    }

    public int getDisPlaybit() {
        return this.mDisPlaybit;
    }

    public void setDisPlaybit(int i) {
        this.mDisPlaybit = i;
    }
}
