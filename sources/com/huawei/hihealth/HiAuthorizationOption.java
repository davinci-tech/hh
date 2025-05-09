package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HiAuthorizationOption implements Parcelable {
    public static final Parcelable.Creator<HiAuthorizationOption> CREATOR = new Parcelable.Creator<HiAuthorizationOption>() { // from class: com.huawei.hihealth.HiAuthorizationOption.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buo_, reason: merged with bridge method [inline-methods] */
        public HiAuthorizationOption createFromParcel(Parcel parcel) {
            return new HiAuthorizationOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public HiAuthorizationOption[] newArray(int i) {
            return new HiAuthorizationOption[i];
        }
    };
    private String accessToken;
    private String appId;
    private String fingerprint;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiAuthorizationOption() {
    }

    public HiAuthorizationOption(String str) {
        this.accessToken = str;
    }

    protected HiAuthorizationOption(Parcel parcel) {
        this.accessToken = parcel.readString();
        this.appId = parcel.readString();
        this.fingerprint = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.accessToken);
        parcel.writeString(this.appId);
        parcel.writeString(this.fingerprint);
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getFingerprint() {
        return this.fingerprint;
    }

    public void setFingerprint(String str) {
        this.fingerprint = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiAuthorizationOption{, appId='");
        stringBuffer.append(this.appId).append("'}");
        return stringBuffer.toString();
    }
}
