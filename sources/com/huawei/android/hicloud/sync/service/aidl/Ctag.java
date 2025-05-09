package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.aal;

/* loaded from: classes8.dex */
public class Ctag implements Parcelable {
    public static final Parcelable.Creator<Ctag> CREATOR = new c();
    private String ctag;
    private boolean expired;
    private int status;
    private String syncToken;
    private int version;

    static final class c implements Parcelable.Creator<Ctag> {
        c() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Ctag[] newArray(int i) {
            return new Ctag[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: eY_, reason: merged with bridge method [inline-methods] */
        public Ctag createFromParcel(Parcel parcel) {
            return new Ctag(parcel, null);
        }
    }

    /* synthetic */ Ctag(Parcel parcel, c cVar) {
        this(parcel);
    }

    private void readFromParcel(Parcel parcel) {
        this.ctag = parcel.readString();
        this.status = parcel.readInt();
        if (aal.a() >= 101) {
            this.expired = parcel.readByte() != 0;
            this.syncToken = parcel.readString();
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCtag() {
        return this.ctag;
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

    public void setCtag(String str) {
        this.ctag = str;
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

    public void setVersion(int i) {
        this.version = i;
    }

    public String toString() {
        return "[ctag=" + this.ctag + ", status=" + this.status + ", expired=" + this.expired + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.ctag);
        parcel.writeInt(this.status);
    }

    private Ctag(Parcel parcel) {
        this.version = -1;
        this.ctag = null;
        this.status = -1;
        this.expired = true;
        readFromParcel(parcel);
    }

    public Ctag() {
        this.version = -1;
        this.ctag = null;
        this.status = -1;
        this.expired = true;
    }

    public Ctag(String str, int i, boolean z, String str2) {
        this.version = -1;
        this.ctag = str;
        this.status = i;
        this.expired = z;
        this.syncToken = str2;
    }
}
