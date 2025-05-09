package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;

/* loaded from: classes6.dex */
public class Audio implements Parcelable {
    public static final Parcelable.Creator<Audio> CREATOR = new Parcelable.Creator<Audio>() { // from class: com.huawei.pluginfitnessadvice.Audio.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: clC_, reason: merged with bridge method [inline-methods] */
        public Audio createFromParcel(Parcel parcel) {
            return new Audio(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Audio[] newArray(int i) {
            return new Audio[i];
        }
    };

    @SerializedName("during")
    private int mDuration;

    @SerializedName(CommonConstant.KEY_GENDER)
    private int mGender;

    @SerializedName("id")
    private String mId;

    @SerializedName("identification")
    private String mIdentification;

    @SerializedName("length")
    private int mLength;

    @SerializedName("name")
    private String mName;

    @SerializedName("url")
    private String mUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Audio() {
    }

    protected Audio(Parcel parcel) {
        this.mId = parcel.readString();
        this.mUrl = parcel.readString();
        this.mIdentification = parcel.readString();
        this.mLength = parcel.readInt();
        this.mName = parcel.readString();
        this.mGender = parcel.readInt();
        this.mDuration = parcel.readInt();
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void saveUrl(String str) {
        this.mUrl = str;
    }

    public String getIdentification() {
        return this.mIdentification;
    }

    public void setIdentification(String str) {
        this.mIdentification = str;
    }

    public int getLength() {
        return this.mLength;
    }

    public void saveLength(int i) {
        this.mLength = i;
    }

    public String getName() {
        return this.mName;
    }

    public void saveName(String str) {
        this.mName = str;
    }

    public int getGender() {
        return this.mGender;
    }

    public void saveGender(int i) {
        this.mGender = i;
    }

    public int getDuring() {
        return this.mDuration;
    }

    public void setDuring(int i) {
        this.mDuration = i;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mId);
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mIdentification);
        parcel.writeInt(this.mLength);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mGender);
        parcel.writeInt(this.mDuration);
    }
}
