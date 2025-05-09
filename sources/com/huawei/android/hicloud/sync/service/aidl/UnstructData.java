package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class UnstructData implements Parcelable {
    public static final Parcelable.Creator<UnstructData> CREATOR = new e();
    protected String hash;
    protected String id;
    protected String name;
    protected int optType;
    protected String unstruct_uuid;
    protected int version;

    static final class e implements Parcelable.Creator<UnstructData> {
        e() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public UnstructData[] newArray(int i) {
            return new UnstructData[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: fi_, reason: merged with bridge method [inline-methods] */
        public UnstructData createFromParcel(Parcel parcel) {
            return new UnstructData(parcel, null);
        }
    }

    /* synthetic */ UnstructData(Parcel parcel, e eVar) {
        this(parcel);
    }

    public static Parcelable.Creator<UnstructData> getCreator() {
        return CREATOR;
    }

    private void readFromParcel(Parcel parcel) {
        this.id = parcel.readString();
        this.unstruct_uuid = parcel.readString();
        this.name = parcel.readString();
        this.hash = parcel.readString();
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

    public String getUnstruct_uuid() {
        return this.unstruct_uuid;
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

    public void setUnstruct_uuid(String str) {
        this.unstruct_uuid = str;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public String toString() {
        return "[name=" + this.name + ", hash=" + this.hash + ", unstruct uuid=" + this.unstruct_uuid + ", id=" + this.id + ", optType" + this.optType + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.unstruct_uuid);
        parcel.writeString(this.name);
        parcel.writeString(this.hash);
    }

    public UnstructData() {
        this.optType = 0;
    }

    private UnstructData(Parcel parcel) {
        this.optType = 0;
        readFromParcel(parcel);
    }
}
