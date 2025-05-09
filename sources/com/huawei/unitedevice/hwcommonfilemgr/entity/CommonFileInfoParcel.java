package com.huawei.unitedevice.hwcommonfilemgr.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class CommonFileInfoParcel implements Parcelable {
    public static final Parcelable.Creator<CommonFileInfoParcel> CREATOR = new Parcelable.Creator<CommonFileInfoParcel>() { // from class: com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: ejV_, reason: merged with bridge method [inline-methods] */
        public CommonFileInfoParcel createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            CommonFileInfoParcel commonFileInfoParcel = new CommonFileInfoParcel();
            commonFileInfoParcel.setFileName(parcel.readString());
            commonFileInfoParcel.setFileType(parcel.readInt());
            commonFileInfoParcel.setFilePath(parcel.readString());
            commonFileInfoParcel.setSourcePackageName(parcel.readString());
            commonFileInfoParcel.setDestinationPackageName(parcel.readString());
            commonFileInfoParcel.setSourceCertificate(parcel.readString());
            commonFileInfoParcel.setDestinationCertificate(parcel.readString());
            commonFileInfoParcel.setDescription(parcel.readString());
            commonFileInfoParcel.setSha256Result(parcel.readString());
            commonFileInfoParcel.setInTheQueue(parcel.readByte() != 0);
            commonFileInfoParcel.setIdentify(parcel.readString());
            return commonFileInfoParcel;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public CommonFileInfoParcel[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new CommonFileInfoParcel[i];
        }
    };
    private String description;
    private String destinationCertificate;
    private String destinationPackageName;
    private String filePath;
    private int fileType;
    private String identify;
    private String sha256Result;
    private String sourceCertificate;
    private String sourcePackageName;
    private String fileName = "";
    private boolean isInTheQueue = false;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
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

    public String getSourcePackageName() {
        return this.sourcePackageName;
    }

    public void setSourcePackageName(String str) {
        this.sourcePackageName = str;
    }

    public String getDestinationPackageName() {
        return this.destinationPackageName;
    }

    public void setDestinationPackageName(String str) {
        this.destinationPackageName = str;
    }

    public String getSourceCertificate() {
        return this.sourceCertificate;
    }

    public void setSourceCertificate(String str) {
        this.sourceCertificate = str;
    }

    public String getDestinationCertificate() {
        return this.destinationCertificate;
    }

    public void setDestinationCertificate(String str) {
        this.destinationCertificate = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getSha256Result() {
        return this.sha256Result;
    }

    public void setSha256Result(String str) {
        this.sha256Result = str;
    }

    public boolean isInTheQueue() {
        return this.isInTheQueue;
    }

    public void setInTheQueue(boolean z) {
        this.isInTheQueue = z;
    }

    public String getIdentify() {
        return this.identify;
    }

    public void setIdentify(String str) {
        this.identify = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fileName);
        parcel.writeInt(this.fileType);
        parcel.writeString(this.filePath);
        parcel.writeString(this.sourcePackageName);
        parcel.writeString(this.destinationPackageName);
        parcel.writeString(this.sourceCertificate);
        parcel.writeString(this.destinationCertificate);
        parcel.writeString(this.description);
        parcel.writeString(this.sha256Result);
        parcel.writeByte(this.isInTheQueue ? (byte) 1 : (byte) 0);
        parcel.writeString(this.identify);
    }
}
