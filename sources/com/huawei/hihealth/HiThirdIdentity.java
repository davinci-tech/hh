package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HiThirdIdentity implements Parcelable {
    public static final Parcelable.Creator<HiThirdIdentity> CREATOR = new Parcelable.Creator<HiThirdIdentity>() { // from class: com.huawei.hihealth.HiThirdIdentity.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buX_, reason: merged with bridge method [inline-methods] */
        public HiThirdIdentity createFromParcel(Parcel parcel) {
            return new HiThirdIdentity(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public HiThirdIdentity[] newArray(int i) {
            return new HiThirdIdentity[i];
        }
    };
    private String fingerprint;
    private int id;
    private int identity;
    private int level;
    private String packageName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiThirdIdentity() {
    }

    protected HiThirdIdentity(Parcel parcel) {
        this.id = parcel.readInt();
        this.packageName = parcel.readString();
        this.fingerprint = parcel.readString();
        this.level = parcel.readInt();
        this.identity = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.packageName);
        parcel.writeString(this.fingerprint);
        parcel.writeInt(this.level);
        parcel.writeInt(this.identity);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public int getIdentity() {
        return this.identity;
    }

    public void setIdentity(int i) {
        this.identity = i;
    }

    public String getFingerprint() {
        return this.fingerprint;
    }

    public void setFingerprint(String str) {
        this.fingerprint = str;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }
}
