package com.huawei.pluginachievement.manager.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class KakaRedeemInfo implements Parcelable {
    public static final Parcelable.Creator<KakaRedeemInfo> CREATOR = new Parcelable.Creator<KakaRedeemInfo>() { // from class: com.huawei.pluginachievement.manager.model.KakaRedeemInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KakaRedeemInfo createFromParcel(Parcel parcel) {
            return new KakaRedeemInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KakaRedeemInfo[] newArray(int i) {
            return new KakaRedeemInfo[i];
        }
    };
    private int mDailyLimit;
    private int mExchangeProportion;
    private int mKakaSum;
    private int mRedeemTimes;
    private int mRedeemableKaka;
    private Integer mRemainingKaka;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getKakaSum() {
        return this.mKakaSum;
    }

    public void setKakaSum(int i) {
        this.mKakaSum = i;
    }

    public int getExchangeProportion() {
        return this.mExchangeProportion;
    }

    public void setExchangeProportion(int i) {
        this.mExchangeProportion = i;
    }

    public int getDailyLimit() {
        return this.mDailyLimit;
    }

    public void setDailyLimit(int i) {
        this.mDailyLimit = i;
    }

    public int getRedeemTimes() {
        return this.mRedeemTimes;
    }

    public void setRedeemTimes(int i) {
        this.mRedeemTimes = i;
    }

    public Integer getRemainingKaka() {
        return this.mRemainingKaka;
    }

    public void setRemainingKaka(Integer num) {
        this.mRemainingKaka = num;
    }

    public int getRedeemableKaka() {
        return this.mRedeemableKaka;
    }

    public void setRedeemableKaka(int i) {
        this.mRedeemableKaka = i;
    }

    public KakaRedeemInfo() {
        this(null);
    }

    protected KakaRedeemInfo(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mKakaSum = parcel.readInt();
        this.mExchangeProportion = parcel.readInt();
        this.mDailyLimit = parcel.readInt();
        this.mRedeemTimes = parcel.readInt();
        this.mRemainingKaka = Integer.valueOf(parcel.readInt());
        this.mRedeemableKaka = parcel.readInt();
    }

    public String toString() {
        return "KakaRedeemInfo{mKakaSum=" + this.mKakaSum + ", mExchangeProportion=" + this.mExchangeProportion + ", mDailyLimit=" + this.mDailyLimit + ", mRedeemTimes=" + this.mRedeemTimes + ", mRemainingKaka=" + this.mRemainingKaka + ", mRedeemableKaka=" + this.mRedeemableKaka + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mKakaSum);
        parcel.writeInt(this.mExchangeProportion);
        parcel.writeInt(this.mDailyLimit);
        parcel.writeInt(this.mRedeemTimes);
        Integer num = this.mRemainingKaka;
        parcel.writeInt(num == null ? 0 : num.intValue());
        parcel.writeInt(this.mRedeemableKaka);
    }
}
