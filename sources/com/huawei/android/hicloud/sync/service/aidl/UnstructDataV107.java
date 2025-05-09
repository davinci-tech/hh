package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class UnstructDataV107 implements Parcelable {
    public static final Parcelable.Creator<UnstructDataV107> CREATOR = new d();
    private String hash;
    private String id;
    private String name;
    private int optType;
    private String unstructUuid;
    private int version;

    static final class d implements Parcelable.Creator<UnstructDataV107> {
        d() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public UnstructDataV107[] newArray(int i) {
            return new UnstructDataV107[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: fj_, reason: merged with bridge method [inline-methods] */
        public UnstructDataV107 createFromParcel(Parcel parcel) {
            return new UnstructDataV107(parcel, null);
        }
    }

    /* synthetic */ UnstructDataV107(Parcel parcel, d dVar) {
        this(parcel);
    }

    private void readFromParcel(Parcel parcel) {
        this.version = parcel.readInt();
        this.id = parcel.readString();
        this.unstructUuid = parcel.readString();
        this.name = parcel.readString();
        this.hash = parcel.readString();
        this.optType = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getHash() {
        return this.hash;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getOptType() {
        return this.optType;
    }

    public String getUnstructUuid() {
        return this.unstructUuid;
    }

    public int getVersion() {
        return this.version;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOptType(int i) {
        this.optType = i;
    }

    public void setUnstructUuid(String str) {
        this.unstructUuid = str;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.version);
        parcel.writeString(this.id);
        parcel.writeString(this.unstructUuid);
        parcel.writeString(this.name);
        parcel.writeString(this.hash);
        parcel.writeInt(this.optType);
    }

    private UnstructDataV107(Parcel parcel) {
        this.optType = 0;
        readFromParcel(parcel);
    }

    public UnstructDataV107(int i, String str, String str2, String str3, String str4, int i2) {
        this.version = i;
        this.id = str;
        this.unstructUuid = str2;
        this.name = str3;
        this.hash = str4;
        this.optType = i2;
    }
}
