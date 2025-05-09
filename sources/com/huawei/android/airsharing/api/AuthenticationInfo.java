package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class AuthenticationInfo implements Parcelable {
    public static final int AUTH_CANCEL = 0;
    public static final int AUTH_CONFIRM = 1;
    public static final Parcelable.Creator<AuthenticationInfo> CREATOR = new Parcelable.Creator<AuthenticationInfo>() { // from class: com.huawei.android.airsharing.api.AuthenticationInfo.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: dq_, reason: merged with bridge method [inline-methods] */
        public AuthenticationInfo createFromParcel(Parcel parcel) {
            return new AuthenticationInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public AuthenticationInfo[] newArray(int i) {
            return new AuthenticationInfo[i];
        }
    };
    private int mAuthAction;
    private String mAuthCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AuthenticationInfo() {
        this.mAuthAction = 0;
        this.mAuthCode = null;
    }

    public AuthenticationInfo(int i, String str) {
        this.mAuthAction = i;
        this.mAuthCode = str;
    }

    protected AuthenticationInfo(Parcel parcel) {
        this.mAuthAction = 0;
        this.mAuthCode = null;
        this.mAuthAction = parcel.readInt();
        this.mAuthCode = parcel.readString();
    }

    public void setAuthAction(int i) {
        this.mAuthAction = i;
    }

    public int getAuthAction() {
        return this.mAuthAction;
    }

    public void setAuthCode(String str) {
        this.mAuthCode = str;
    }

    public String getAuthCode() {
        return this.mAuthCode;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mAuthAction);
        parcel.writeString(this.mAuthCode);
    }
}
