package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class FileDownloadProgress implements Parcelable {
    public static final Parcelable.Creator<FileDownloadProgress> CREATOR = new c();
    private long alreadyLoadSize;
    private String dataType;
    private long fileSize;
    private String guid;
    private String id;
    private String name;
    private long progress;
    private String syncType;

    static final class c implements Parcelable.Creator<FileDownloadProgress> {
        c() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public FileDownloadProgress[] newArray(int i) {
            return new FileDownloadProgress[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: fc_, reason: merged with bridge method [inline-methods] */
        public FileDownloadProgress createFromParcel(Parcel parcel) {
            return new FileDownloadProgress(parcel, null);
        }
    }

    /* synthetic */ FileDownloadProgress(Parcel parcel, c cVar) {
        this(parcel);
    }

    private void readFromParcel(Parcel parcel) {
        this.syncType = parcel.readString();
        this.dataType = parcel.readString();
        this.id = parcel.readString();
        this.guid = parcel.readString();
        this.name = parcel.readString();
        this.progress = parcel.readLong();
        this.fileSize = parcel.readLong();
        this.alreadyLoadSize = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getAlreadyLoadSize() {
        return this.alreadyLoadSize;
    }

    public String getDataType() {
        return this.dataType;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public long getProgress() {
        return this.progress;
    }

    public String getSyncType() {
        return this.syncType;
    }

    public void setAlreadyLoadSize(long j) {
        this.alreadyLoadSize = j;
    }

    public void setDataType(String str) {
        this.dataType = str;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
    }

    public void setGuid(String str) {
        this.guid = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setProgress(long j) {
        this.progress = j;
    }

    public void setSyncType(String str) {
        this.syncType = str;
    }

    public String toString() {
        return "[dataType='" + this.dataType + "', id='" + this.id + "', guid='" + this.guid + "', name='" + this.name + "', total=" + this.fileSize + ", increase=" + this.alreadyLoadSize + ']';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.syncType);
        parcel.writeString(this.dataType);
        parcel.writeString(this.id);
        parcel.writeString(this.guid);
        parcel.writeString(this.name);
        parcel.writeLong(this.progress);
        parcel.writeLong(this.fileSize);
        parcel.writeLong(this.alreadyLoadSize);
    }

    private FileDownloadProgress(Parcel parcel) {
        readFromParcel(parcel);
    }

    public FileDownloadProgress() {
    }
}
