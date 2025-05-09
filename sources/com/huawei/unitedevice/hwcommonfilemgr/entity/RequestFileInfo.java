package com.huawei.unitedevice.hwcommonfilemgr.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class RequestFileInfo implements Parcelable {
    public static final Parcelable.Creator<RequestFileInfo> CREATOR = new Parcelable.Creator<RequestFileInfo>() { // from class: com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: ejX_, reason: merged with bridge method [inline-methods] */
        public RequestFileInfo createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            RequestFileInfo requestFileInfo = new RequestFileInfo();
            requestFileInfo.setFileName(parcel.readString());
            requestFileInfo.setFileType(parcel.readInt());
            requestFileInfo.setFileId(parcel.readInt());
            requestFileInfo.setFileSize(parcel.readInt());
            requestFileInfo.setTimes(parcel.createIntArray());
            requestFileInfo.setDescription(parcel.readString());
            requestFileInfo.setSourcePackageName(parcel.readString());
            requestFileInfo.setDestinationPackageName(parcel.readString());
            requestFileInfo.setSourceCertificate(parcel.readString());
            requestFileInfo.setDestinationCertificate(parcel.readString());
            requestFileInfo.setCancelTransmission(parcel.readByte() != 0);
            requestFileInfo.setNeedVerify(parcel.readByte() != 0);
            requestFileInfo.setKit(parcel.readByte() != 0);
            requestFileInfo.setDeviceReport(parcel.readByte() != 0);
            requestFileInfo.setDeviceMac(parcel.readString());
            requestFileInfo.setDictTypeId(parcel.readInt());
            return requestFileInfo;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public RequestFileInfo[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new RequestFileInfo[i];
        }
    };
    private boolean cancelTransmission = false;
    private String description;
    private String destinationCertificate;
    private String destinationPackageName;
    private String deviceMac;
    private boolean deviceReport;
    private int dictTypeId;
    private int fileId;
    private String fileName;
    private int fileSize;
    private int fileType;
    private boolean isNeedVerify;
    private boolean kit;
    private String savePath;
    private String sourceCertificate;
    private String sourcePackageName;
    private int[] times;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getSavePath() {
        return this.savePath;
    }

    public void setSavePath(String str) {
        this.savePath = str;
    }

    public boolean isDeviceReport() {
        return this.deviceReport;
    }

    public void setDeviceReport(boolean z) {
        this.deviceReport = z;
    }

    public boolean isKit() {
        return this.kit;
    }

    public void setKit(boolean z) {
        this.kit = z;
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

    public int[] getTimes() {
        int[] iArr = this.times;
        if (iArr != null) {
            return iArr;
        }
        return null;
    }

    public void setTimes(int[] iArr) {
        this.times = iArr;
    }

    public boolean isNeedVerify() {
        return this.isNeedVerify;
    }

    public void setNeedVerify(boolean z) {
        this.isNeedVerify = z;
    }

    public int getFileId() {
        return this.fileId;
    }

    public void setFileId(int i) {
        this.fileId = i;
    }

    public int getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(int i) {
        this.fileSize = i;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
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

    public boolean isCancelTransmission() {
        return this.cancelTransmission;
    }

    public void setCancelTransmission(boolean z) {
        this.cancelTransmission = z;
    }

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public int getDictTypeId() {
        return this.dictTypeId;
    }

    public void setDictTypeId(int i) {
        this.dictTypeId = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fileName);
        parcel.writeInt(this.fileType);
        parcel.writeInt(this.fileId);
        parcel.writeInt(this.fileSize);
        parcel.writeIntArray(this.times);
        parcel.writeString(this.description);
        parcel.writeString(this.sourcePackageName);
        parcel.writeString(this.destinationPackageName);
        parcel.writeString(this.sourceCertificate);
        parcel.writeString(this.destinationCertificate);
        parcel.writeByte(this.cancelTransmission ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isNeedVerify ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.kit ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.deviceReport ? (byte) 1 : (byte) 0);
        parcel.writeString(this.deviceMac);
        parcel.writeInt(this.dictTypeId);
    }
}
