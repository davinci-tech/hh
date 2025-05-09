package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class CtagInfo implements Parcelable {
    public static final Parcelable.Creator<CtagInfo> CREATOR = new b();
    private String ctagName;
    private String ctagValue;
    private boolean expired;
    private int status;
    private String syncToken;

    static final class b implements Parcelable.Creator<CtagInfo> {
        b() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public CtagInfo[] newArray(int i) {
            return new CtagInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: eZ_, reason: merged with bridge method [inline-methods] */
        public CtagInfo createFromParcel(Parcel parcel) {
            return new CtagInfo(parcel, null);
        }
    }

    /* synthetic */ CtagInfo(Parcel parcel, b bVar) {
        this(parcel);
    }

    public static Parcelable.Creator<CtagInfo> getCreator() {
        return CREATOR;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCtagName() {
        return this.ctagName;
    }

    public String getCtagValue() {
        return this.ctagValue;
    }

    public int getStatus() {
        return this.status;
    }

    public String getSyncToken() {
        return this.syncToken;
    }

    public boolean isExpired() {
        return this.expired;
    }

    protected void readFromParcel(Parcel parcel) {
        this.ctagName = parcel.readString();
        this.ctagValue = parcel.readString();
        this.status = parcel.readInt();
    }

    public void setCtagName(String str) {
        this.ctagName = str;
    }

    public void setCtagValue(String str) {
        this.ctagValue = str;
    }

    public void setExpired(boolean z) {
        this.expired = z;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setSyncToken(String str) {
        this.syncToken = str;
    }

    public String toString() {
        return "[name=" + this.ctagName + ", ctag=" + this.ctagValue + ", syncToken=" + this.syncToken + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.ctagName);
        parcel.writeString(this.ctagValue);
        parcel.writeInt(this.status);
    }

    private CtagInfo(Parcel parcel) {
        this.expired = true;
        readFromParcel(parcel);
    }

    public CtagInfo() {
        this.expired = true;
    }

    protected CtagInfo(CtagInfo ctagInfo) {
        this.expired = true;
        if (ctagInfo != null) {
            this.ctagName = ctagInfo.getCtagName();
            this.ctagValue = ctagInfo.getCtagValue();
            this.syncToken = ctagInfo.getSyncToken();
            this.status = ctagInfo.getStatus();
            this.expired = ctagInfo.isExpired();
        }
    }
}
