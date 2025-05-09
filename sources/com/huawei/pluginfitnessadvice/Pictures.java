package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class Pictures implements Parcelable {
    public static final Parcelable.Creator<Pictures> CREATOR = new Parcelable.Creator<Pictures>() { // from class: com.huawei.pluginfitnessadvice.Pictures.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: clX_, reason: merged with bridge method [inline-methods] */
        public Pictures createFromParcel(Parcel parcel) {
            return new Pictures(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Pictures[] newArray(int i) {
            return new Pictures[i];
        }
    };
    private static final String TAG = "Pictures";

    @SerializedName("backMusclePicUrl")
    private String mBackMusclePicUrl;

    @SerializedName("displayorder")
    private String mDisplayorder;

    @SerializedName("frontMusclePicUrl")
    private String mFrontMusclePicUrl;

    @SerializedName("sex")
    private String mSex;

    @SerializedName("sexId")
    private String mSexId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Pictures(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mBackMusclePicUrl = parcel.readString();
        this.mDisplayorder = parcel.readString();
        this.mFrontMusclePicUrl = parcel.readString();
        this.mSex = parcel.readString();
        this.mSexId = parcel.readString();
    }

    public Pictures() {
        this(null);
    }

    public String getBackMusclePicUrl() {
        return this.mBackMusclePicUrl;
    }

    public void setBackMusclePicUrl(String str) {
        this.mBackMusclePicUrl = str;
    }

    public String getDisplayorder() {
        return this.mDisplayorder;
    }

    public void setDisplayorder(String str) {
        this.mDisplayorder = str;
    }

    public String getFrontMusclePicUrl() {
        return this.mFrontMusclePicUrl;
    }

    public void setFrontMusclePicUrl(String str) {
        this.mFrontMusclePicUrl = str;
    }

    public String getSex() {
        return this.mSex;
    }

    public void setSex(String str) {
        this.mSex = str;
    }

    public String getSexId() {
        return this.mSexId;
    }

    public void setSexId(String str) {
        this.mSexId = str;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeString(this.mBackMusclePicUrl);
        parcel.writeString(this.mDisplayorder);
        parcel.writeString(this.mFrontMusclePicUrl);
        parcel.writeString(this.mSex);
        parcel.writeString(this.mSexId);
    }
}
