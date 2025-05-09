package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HiUserPreference implements Parcelable {
    public static final Parcelable.Creator<HiUserPreference> CREATOR = new Parcelable.Creator<HiUserPreference>() { // from class: com.huawei.hihealth.HiUserPreference.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bva_, reason: merged with bridge method [inline-methods] */
        public HiUserPreference createFromParcel(Parcel parcel) {
            return new HiUserPreference(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HiUserPreference[] newArray(int i) {
            return new HiUserPreference[i];
        }
    };
    private long createTime;
    private int id;
    private String key;
    private long modifiedTime;
    private int syncStatus;
    private int userId;
    private String value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiUserPreference() {
        this.modifiedTime = System.currentTimeMillis();
    }

    public HiUserPreference(String str, String str2) {
        this.modifiedTime = System.currentTimeMillis();
        this.key = str;
        this.value = str2;
    }

    protected HiUserPreference(Parcel parcel) {
        this.modifiedTime = System.currentTimeMillis();
        this.id = parcel.readInt();
        this.key = parcel.readString();
        this.value = parcel.readString();
        this.userId = parcel.readInt();
        this.syncStatus = parcel.readInt();
        this.createTime = parcel.readLong();
        this.modifiedTime = parcel.readLong();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getValue() {
        return (String) ResultUtils.a(this.value);
    }

    public void setValue(String str) {
        this.value = str;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int i) {
        this.userId = i;
    }

    public int getSyncStatus() {
        return this.syncStatus;
    }

    public void setSyncStatus(int i) {
        this.syncStatus = i;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public long getModifiedTime() {
        return this.modifiedTime;
    }

    public void setModifiedTime(long j) {
        if (j <= 0) {
            return;
        }
        this.modifiedTime = j;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiUserPreference{id=");
        stringBuffer.append(this.id);
        stringBuffer.append(", key='").append(this.key).append("', value='");
        stringBuffer.append(this.value).append("', userId='");
        stringBuffer.append(this.userId).append("', syncStatus=");
        stringBuffer.append(this.syncStatus);
        stringBuffer.append(", createTime=").append(this.createTime);
        stringBuffer.append(", modifiedTime=").append(this.modifiedTime);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.key);
        parcel.writeString(this.value);
        parcel.writeInt(this.userId);
        parcel.writeInt(this.syncStatus);
        parcel.writeLong(this.createTime);
        parcel.writeLong(this.modifiedTime);
    }
}
