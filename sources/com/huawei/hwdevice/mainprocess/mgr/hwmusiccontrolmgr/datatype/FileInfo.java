package com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class FileInfo implements Parcelable {
    public static final Parcelable.Creator<FileInfo> CREATOR = new Parcelable.Creator<FileInfo>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.FileInfo.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: bHy_, reason: merged with bridge method [inline-methods] */
        public FileInfo createFromParcel(Parcel parcel) {
            return new FileInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public FileInfo[] newArray(int i) {
            return new FileInfo[i];
        }
    };
    private String fileName;
    private String filePath;
    private int fileType;
    private String packageName;
    private long sourceId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FileInfo() {
    }

    public FileInfo(Parcel parcel) {
        this.fileName = parcel.readString();
        this.fileType = parcel.readInt();
        this.packageName = parcel.readString();
        this.filePath = parcel.readString();
        this.sourceId = parcel.readLong();
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public int getFileType() {
        return this.fileType;
    }

    public void setFileType(int i) {
        this.fileType = i;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public long getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(long j) {
        this.sourceId = j;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fileName);
        parcel.writeInt(this.fileType);
        parcel.writeString(this.packageName);
        parcel.writeString(this.filePath);
        parcel.writeLong(this.sourceId);
    }

    public void readFromParcel(Parcel parcel) {
        this.fileName = parcel.readString();
        this.fileType = parcel.readInt();
        this.packageName = parcel.readString();
        this.filePath = parcel.readString();
        this.sourceId = parcel.readLong();
    }
}
