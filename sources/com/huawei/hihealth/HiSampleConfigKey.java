package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class HiSampleConfigKey implements Parcelable {
    public static final Parcelable.Creator<HiSampleConfigKey> CREATOR = new Parcelable.Creator<HiSampleConfigKey>() { // from class: com.huawei.hihealth.HiSampleConfigKey.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buS_, reason: merged with bridge method [inline-methods] */
        public HiSampleConfigKey createFromParcel(Parcel parcel) {
            return new HiSampleConfigKey(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public HiSampleConfigKey[] newArray(int i) {
            return new HiSampleConfigKey[i];
        }
    };

    @SerializedName("id")
    private final String mConfigId;

    @SerializedName("scopeApp")
    private final String mScopeApp;

    @SerializedName("scopeDeviceType")
    private final String mScopeDeviceType;

    @SerializedName("type")
    private final String mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiSampleConfigKey(Builder builder) {
        this.mType = builder.d;
        this.mConfigId = builder.c;
        this.mScopeApp = builder.e;
        this.mScopeDeviceType = builder.f4050a;
    }

    public String getType() {
        return this.mType;
    }

    public String getConfigId() {
        return this.mConfigId;
    }

    public String getScopeApp() {
        return this.mScopeApp;
    }

    public String getScopeDeviceType() {
        return this.mScopeDeviceType;
    }

    protected HiSampleConfigKey(Parcel parcel) {
        this.mType = parcel.readString();
        this.mConfigId = parcel.readString();
        this.mScopeApp = parcel.readString();
        this.mScopeDeviceType = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mType);
        parcel.writeString(this.mConfigId);
        parcel.writeString(this.mScopeApp);
        parcel.writeString(this.mScopeDeviceType);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiSampleConfigKey{tp=");
        stringBuffer.append(this.mType);
        stringBuffer.append(",cfgId=").append(this.mConfigId);
        stringBuffer.append(",scApp=").append(this.mScopeApp);
        stringBuffer.append(",scDevTp=").append(this.mScopeDeviceType);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HiSampleConfigKey hiSampleConfigKey = (HiSampleConfigKey) obj;
        return this.mType.equals(hiSampleConfigKey.getType()) && this.mConfigId.equals(hiSampleConfigKey.getConfigId()) && this.mScopeApp.equals(hiSampleConfigKey.getScopeApp()) && this.mScopeDeviceType.equals(hiSampleConfigKey.getScopeDeviceType());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mType);
        sb.append(this.mConfigId);
        sb.append(this.mScopeApp);
        sb.append(this.mScopeDeviceType);
        return sb.hashCode();
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private String f4050a;
        private String c;
        private String d;
        private String e;

        public Builder b(String str) {
            this.d = str;
            return this;
        }

        public Builder d(String str) {
            this.c = str;
            return this;
        }

        public Builder e(String str) {
            this.e = str;
            return this;
        }

        public Builder a(String str) {
            this.f4050a = str;
            return this;
        }

        public HiSampleConfigKey d() {
            return new HiSampleConfigKey(this);
        }
    }
}
