package com.huawei.profile.profile;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class ProfileResult implements Parcelable {
    public static final Parcelable.Creator<ProfileResult> CREATOR = new Parcelable.Creator<ProfileResult>() { // from class: com.huawei.profile.profile.ProfileResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProfileResult createFromParcel(Parcel parcel) {
            return new ProfileResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProfileResult[] newArray(int i) {
            return new ProfileResult[i];
        }
    };
    public static final int RET_FAIL = -1;
    public static final int RET_SUCCESS = 0;
    private int resultCode;
    private Parcelable resultData;
    private final int version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected ProfileResult(Parcel parcel) {
        this.version = parcel.readInt();
        this.resultCode = parcel.readInt();
    }

    public ProfileResult() {
        this.version = 1;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public Parcelable getResultData() {
        return this.resultData;
    }

    public void setResultData(Parcelable parcelable) {
        this.resultData = parcelable;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.version);
        parcel.writeInt(this.resultCode);
    }
}
