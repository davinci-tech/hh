package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HiHealthUserPermission implements Parcelable {
    public static final Parcelable.Creator<HiHealthUserPermission> CREATOR = new Parcelable.Creator<HiHealthUserPermission>() { // from class: com.huawei.hihealth.HiHealthUserPermission.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buO_, reason: merged with bridge method [inline-methods] */
        public HiHealthUserPermission createFromParcel(Parcel parcel) {
            return new HiHealthUserPermission(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public HiHealthUserPermission[] newArray(int i) {
            return new HiHealthUserPermission[i];
        }
    };
    private int mAllowRead;
    private int mAllowWrite;
    private int mAppId;
    private int mId;
    private String mModifiedTime;
    private int mScopeId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiHealthUserPermission() {
    }

    protected HiHealthUserPermission(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mAppId = parcel.readInt();
        this.mScopeId = parcel.readInt();
        this.mAllowRead = parcel.readInt();
        this.mAllowWrite = parcel.readInt();
        this.mModifiedTime = parcel.readString();
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public int getAppId() {
        return this.mAppId;
    }

    public void setAppId(int i) {
        this.mAppId = i;
    }

    public int getScopeId() {
        return this.mScopeId;
    }

    public void setScopeId(int i) {
        this.mScopeId = i;
    }

    public int getAllowRead() {
        return this.mAllowRead;
    }

    public void setAllowRead(int i) {
        this.mAllowRead = i;
    }

    public int getAllowWrite() {
        return this.mAllowWrite;
    }

    public void setAllowWrite(int i) {
        this.mAllowWrite = i;
    }

    public String getModifiedTime() {
        return this.mModifiedTime;
    }

    public void setModifiedTime(String str) {
        this.mModifiedTime = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mAppId);
        parcel.writeInt(this.mScopeId);
        parcel.writeInt(this.mAllowRead);
        parcel.writeInt(this.mAllowWrite);
        parcel.writeString(this.mModifiedTime);
    }
}
