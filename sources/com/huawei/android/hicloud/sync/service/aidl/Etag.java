package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.aal;

/* loaded from: classes8.dex */
public class Etag implements Parcelable {
    public static final Parcelable.Creator<Etag> CREATOR = new d();
    private String etag;
    private int operation;
    private int status;
    private String uuid;
    private int version;

    static final class d implements Parcelable.Creator<Etag> {
        d() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Etag[] newArray(int i) {
            return new Etag[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: fb_, reason: merged with bridge method [inline-methods] */
        public Etag createFromParcel(Parcel parcel) {
            return new Etag(parcel, null);
        }
    }

    /* synthetic */ Etag(Parcel parcel, d dVar) {
        this(parcel);
    }

    private void readFromParcel(Parcel parcel) {
        this.uuid = parcel.readString();
        this.etag = parcel.readString();
        this.status = parcel.readInt();
        if (aal.a() >= 101) {
            this.operation = parcel.readInt();
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getEtag() {
        return this.etag;
    }

    public int getOperation() {
        return this.operation;
    }

    public int getStatus() {
        return this.status;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setEtag(String str) {
        this.etag = str;
    }

    public void setOperation(int i) {
        this.operation = i;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public String toString() {
        return "[uuid=" + this.uuid + ", etag=" + this.etag + ", status=" + this.status + ", operation= " + this.operation + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.uuid);
        parcel.writeString(this.etag);
        parcel.writeInt(this.status);
    }

    private Etag(Parcel parcel) {
        this.version = -1;
        this.uuid = null;
        this.etag = null;
        this.status = -1;
        this.operation = -1;
        readFromParcel(parcel);
    }

    public Etag() {
        this.version = -1;
        this.uuid = null;
        this.etag = null;
        this.status = -1;
        this.operation = -1;
    }
}
