package com.huawei.hmf.orb.aidl.communicate;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class DataBuffer implements Parcelable {
    public static final Parcelable.Creator<DataBuffer> CREATOR = new Parcelable.Creator<DataBuffer>() { // from class: com.huawei.hmf.orb.aidl.communicate.DataBuffer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataBuffer createFromParcel(Parcel parcel) {
            return new DataBuffer(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataBuffer[] newArray(int i) {
            return new DataBuffer[i];
        }
    };
    public String URI;
    private Bundle body;
    public Bundle header;
    private int version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private DataBuffer(Parcel parcel) {
        this.version = 1;
        this.header = null;
        this.body = null;
        readFromParcel(parcel);
    }

    public DataBuffer() {
        this.version = 1;
        this.header = null;
        this.body = null;
    }

    public DataBuffer(String str) {
        this.version = 1;
        this.header = null;
        this.body = null;
        this.URI = str;
    }

    public DataBuffer(String str, int i) {
        this.header = null;
        this.body = null;
        this.URI = str;
        this.version = i;
    }

    public DataBuffer addBody(Bundle bundle) {
        this.body = bundle;
        return this;
    }

    public Bundle getBody() {
        return this.body;
    }

    public int getBodySize() {
        return this.body == null ? 0 : 1;
    }

    private void readFromParcel(Parcel parcel) {
        this.version = parcel.readInt();
        this.URI = parcel.readString();
        this.header = parcel.readBundle(getClassLoader(getClass()));
        this.body = parcel.readBundle(getClassLoader(getClass()));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.version);
        parcel.writeString(this.URI);
        parcel.writeBundle(this.header);
        parcel.writeBundle(this.body);
    }

    public int getProtocol() {
        return this.version;
    }

    private static ClassLoader getClassLoader(Class cls) {
        return cls == null ? ClassLoader.getSystemClassLoader() : cls.getClassLoader();
    }
}
