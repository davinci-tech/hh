package com.huawei.pluginfitnessadvice.pricetagbean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class PriceTagBean implements Parcelable {
    public static final Parcelable.Creator<PriceTagBean> CREATOR = new Parcelable.Creator<PriceTagBean>() { // from class: com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmu_, reason: merged with bridge method [inline-methods] */
        public PriceTagBean createFromParcel(Parcel parcel) {
            return new PriceTagBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public PriceTagBean[] newArray(int i) {
            return new PriceTagBean[i];
        }
    };
    private static final String TAG = "Suggestion_PriceTagBean";

    @SerializedName("cornerImgLayout")
    private int mPosition;

    @SerializedName("cornerImgEndTime")
    private long mPriceEndTime;

    @SerializedName("cornerImgStartTime")
    private long mPriceStartTime;

    @SerializedName("cornerImgUrl")
    private String mUrl;

    @SerializedName("weight")
    private int mWeight;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PriceTagBean() {
    }

    private PriceTagBean(Parcel parcel) {
        this.mPriceStartTime = parcel.readLong();
        this.mPriceEndTime = parcel.readLong();
        this.mUrl = parcel.readString();
        this.mPosition = parcel.readInt();
        this.mWeight = parcel.readInt();
    }

    public long getPriceStartTime() {
        return this.mPriceStartTime;
    }

    public PriceTagBean setPriceStartTime(long j) {
        this.mPriceStartTime = j;
        return this;
    }

    public long getPriceEndTime() {
        return this.mPriceEndTime;
    }

    public PriceTagBean setPriceEndTime(long j) {
        this.mPriceEndTime = j;
        return this;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public PriceTagBean setUrl(String str) {
        this.mUrl = str;
        return this;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public PriceTagBean setPosition(int i) {
        this.mPosition = i;
        return this;
    }

    public int getWeight() {
        return this.mWeight;
    }

    public PriceTagBean setWeight(int i) {
        this.mWeight = i;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeLong(this.mPriceStartTime);
        parcel.writeLong(this.mPriceEndTime);
        parcel.writeString(this.mUrl);
        parcel.writeInt(this.mPosition);
        parcel.writeInt(this.mWeight);
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
