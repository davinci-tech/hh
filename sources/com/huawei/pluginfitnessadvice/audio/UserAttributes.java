package com.huawei.pluginfitnessadvice.audio;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class UserAttributes implements Parcelable {
    public static final Parcelable.Creator<UserAttributes> CREATOR = new Parcelable.Creator<UserAttributes>() { // from class: com.huawei.pluginfitnessadvice.audio.UserAttributes.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmq_, reason: merged with bridge method [inline-methods] */
        public UserAttributes createFromParcel(Parcel parcel) {
            return new UserAttributes(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public UserAttributes[] newArray(int i) {
            return new UserAttributes[i];
        }
    };

    @SerializedName("favorites")
    private int mFavorites;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UserAttributes() {
    }

    protected UserAttributes(Parcel parcel) {
        this.mFavorites = parcel.readInt();
    }

    public int getFavorites() {
        return this.mFavorites;
    }

    public void setFavorites(int i) {
        this.mFavorites = i;
    }

    public String toString() {
        return "UserAttributes{mFavorites=" + this.mFavorites + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mFavorites);
    }
}
