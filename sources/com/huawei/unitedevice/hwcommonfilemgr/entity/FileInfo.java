package com.huawei.unitedevice.hwcommonfilemgr.entity;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import java.util.List;

/* loaded from: classes7.dex */
public class FileInfo implements Parcelable {
    public static final Parcelable.Creator<FileInfo> CREATOR = new Parcelable.Creator<FileInfo>() { // from class: com.huawei.unitedevice.hwcommonfilemgr.entity.FileInfo.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: ejW_, reason: merged with bridge method [inline-methods] */
        public FileInfo createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(parcel.readString());
            fileInfo.setFileType(parcel.readInt());
            fileInfo.setPackageName(parcel.readString());
            fileInfo.setFilePath(parcel.readString());
            fileInfo.setSourceId(parcel.readLong());
            fileInfo.setIdentify(parcel.readString());
            fileInfo.setFileSize(parcel.readInt());
            fileInfo.setFileDescriptor((ParcelFileDescriptor) parcel.readParcelable(ParcelFileDescriptor.class.getClassLoader()));
            return fileInfo;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public FileInfo[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new FileInfo[i];
        }
    };
    private List<Integer> attentionTypes;
    ParcelFileDescriptor fileDescriptor;
    private String fileName;
    private String filePath;
    private int fileSize;
    private int fileType;
    private String identify;
    private String packageName;
    private TransferFileReqType requestType;
    private long sourceId;
    private int transferDirect = 1;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TransferFileReqType getRequestType() {
        return this.requestType;
    }

    public void setRequestType(TransferFileReqType transferFileReqType) {
        this.requestType = transferFileReqType;
    }

    public List<Integer> getAttentionTypes() {
        return this.attentionTypes;
    }

    public void setAttentionTypes(List<Integer> list) {
        this.attentionTypes = list;
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

    public String getIdentify() {
        return this.identify;
    }

    public void setIdentify(String str) {
        this.identify = str;
    }

    public int getTransferDirect() {
        return this.transferDirect;
    }

    public void setTransferDirect(int i) {
        this.transferDirect = i;
    }

    public ParcelFileDescriptor getFileDescriptor() {
        return this.fileDescriptor;
    }

    public void setFileDescriptor(ParcelFileDescriptor parcelFileDescriptor) {
        this.fileDescriptor = parcelFileDescriptor;
    }

    public int getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(int i) {
        this.fileSize = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fileName);
        parcel.writeInt(this.fileType);
        parcel.writeString(this.packageName);
        parcel.writeString(this.filePath);
        parcel.writeLong(this.sourceId);
        parcel.writeString(this.identify);
        parcel.writeInt(this.fileSize);
        parcel.writeParcelable(this.fileDescriptor, i);
    }
}
