package com.huawei.watchface.mvp.model.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class MaterialFileUploadInfo implements Parcelable {
    public static final Parcelable.Creator<MaterialFileUploadInfo> CREATOR = new Parcelable.Creator<MaterialFileUploadInfo>() { // from class: com.huawei.watchface.mvp.model.datatype.MaterialFileUploadInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MaterialFileUploadInfo createFromParcel(Parcel parcel) {
            return new MaterialFileUploadInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MaterialFileUploadInfo[] newArray(int i) {
            return new MaterialFileUploadInfo[i];
        }
    };
    private String appContentId;
    private String fileId;
    private String fileName;
    private String index;
    private MaterialFileUploadURLInfo uploadInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getIndex() {
        return this.index;
    }

    public String getFileId() {
        return this.fileId;
    }

    public MaterialFileUploadURLInfo getUploadInfo() {
        return this.uploadInfo;
    }

    public String getAppContentId() {
        return this.appContentId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setIndex(String str) {
        this.index = str;
    }

    public void setFileId(String str) {
        this.fileId = str;
    }

    public void setUploadInfo(MaterialFileUploadURLInfo materialFileUploadURLInfo) {
        this.uploadInfo = materialFileUploadURLInfo;
    }

    public void setAppContentId(String str) {
        this.appContentId = str;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    protected MaterialFileUploadInfo(Parcel parcel) {
        this.index = parcel.readString();
        this.fileId = parcel.readString();
        this.uploadInfo = (MaterialFileUploadURLInfo) parcel.readParcelable(MaterialFileUploadURLInfo.class.getClassLoader());
        this.appContentId = parcel.readString();
        this.fileName = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.index);
        parcel.writeString(this.fileId);
        parcel.writeParcelable(this.uploadInfo, i);
        parcel.writeString(this.appContentId);
        parcel.writeString(this.fileName);
    }
}
