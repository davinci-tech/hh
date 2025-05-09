package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class LocalId implements Parcelable {
    public static final Parcelable.Creator<LocalId> CREATOR = new b();
    private int dirty;
    private int haveFile;
    private String id;
    private int recycleStatus;

    static final class b implements Parcelable.Creator<LocalId> {
        b() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public LocalId[] newArray(int i) {
            return new LocalId[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: fd_, reason: merged with bridge method [inline-methods] */
        public LocalId createFromParcel(Parcel parcel) {
            return new LocalId(parcel, null);
        }
    }

    /* synthetic */ LocalId(Parcel parcel, b bVar) {
        this(parcel);
    }

    public static Parcelable.Creator<LocalId> getCreator() {
        return CREATOR;
    }

    private void readFromParcel(Parcel parcel) {
        this.id = parcel.readString();
        this.dirty = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getDirty() {
        return this.dirty;
    }

    public int getHaveFile() {
        return this.haveFile;
    }

    public String getId() {
        return this.id;
    }

    public int getRecycleStatus() {
        return this.recycleStatus;
    }

    public void setDirty(int i) {
        this.dirty = i;
    }

    public void setHaveFile(int i) {
        this.haveFile = i;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setRecycleStatus(int i) {
        this.recycleStatus = i;
    }

    public String toString() {
        return "LocalId [id=" + this.id + ", dirty=" + this.dirty + ", haveFile=" + this.haveFile + ", recycleStatus=" + this.recycleStatus + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeInt(this.dirty);
    }

    public LocalId() {
    }

    private LocalId(Parcel parcel) {
        readFromParcel(parcel);
    }
}
