package com.huawei.hihealthservice.auth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes7.dex */
public class HiAuthorization implements Parcelable {
    public static final Parcelable.Creator<HiAuthorization> CREATOR = new Parcelable.Creator<HiAuthorization>() { // from class: com.huawei.hihealthservice.auth.HiAuthorization.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiAuthorization createFromParcel(Parcel parcel) {
            return new HiAuthorization(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiAuthorization[] newArray(int i) {
            return new HiAuthorization[i];
        }
    };
    private String mCertFingerprint;
    private List<HiScope> mScopes;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiAuthorization(Parcel parcel) {
        this.mCertFingerprint = parcel.readString();
    }

    public List<HiScope> getScopes() {
        return this.mScopes;
    }

    public String getCertFingerprint() {
        return this.mCertFingerprint;
    }

    public String toString() {
        return "HiAuthorization [mScopes=" + this.mScopes + ", mCertFingerprint=" + this.mCertFingerprint + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCertFingerprint);
    }
}
