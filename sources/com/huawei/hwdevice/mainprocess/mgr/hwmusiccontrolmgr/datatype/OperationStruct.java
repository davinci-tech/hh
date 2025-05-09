package com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.jdy;
import java.util.List;

/* loaded from: classes5.dex */
public class OperationStruct implements Parcelable {
    public static final Parcelable.Creator<OperationStruct> CREATOR = new Parcelable.Creator<OperationStruct>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.OperationStruct.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public OperationStruct[] newArray(int i) {
            return new OperationStruct[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: bHG_, reason: merged with bridge method [inline-methods] */
        public OperationStruct createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
            String readString = parcel.readString();
            int readInt3 = parcel.readInt();
            OperationStruct operationStruct = new OperationStruct();
            operationStruct.setOperationType(readInt);
            operationStruct.setFolderIndex(readInt2);
            operationStruct.setFolderName(readString);
            operationStruct.setOperateResult(readInt3);
            return operationStruct;
        }
    };
    private int mFolderIndex;
    private String mFolderName;
    private List<Integer> mMusicIndexs;
    private int mOperateResult;
    private int mOperationType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getOperationType() {
        return ((Integer) jdy.d(Integer.valueOf(this.mOperationType))).intValue();
    }

    public void setOperationType(int i) {
        this.mOperationType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getFolderIndex() {
        return ((Integer) jdy.d(Integer.valueOf(this.mFolderIndex))).intValue();
    }

    public void setFolderIndex(int i) {
        this.mFolderIndex = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public List<Integer> getMusicIndexs() {
        return (List) jdy.d(this.mMusicIndexs);
    }

    public void setMusicIndexs(List<Integer> list) {
        this.mMusicIndexs = (List) jdy.d(list);
    }

    public int getOperateResult() {
        return ((Integer) jdy.d(Integer.valueOf(this.mOperateResult))).intValue();
    }

    public void setOperateResult(int i) {
        this.mOperateResult = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String getFolderName() {
        return (String) jdy.d(this.mFolderName);
    }

    public void setFolderName(String str) {
        this.mFolderName = (String) jdy.d(str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mOperationType);
        parcel.writeInt(this.mFolderIndex);
        parcel.writeString(this.mFolderName);
        parcel.writeInt(this.mOperateResult);
    }

    public String toString() {
        List<Integer> list = this.mMusicIndexs;
        String obj = list != null ? list.toString() : "";
        StringBuilder sb = new StringBuilder(16);
        sb.append("{operationType:");
        sb.append(this.mOperationType);
        sb.append(",folderIndex:");
        sb.append(this.mFolderIndex);
        sb.append(",musicIndex:");
        sb.append(obj);
        sb.append(",folderName:");
        sb.append(this.mFolderName);
        sb.append(",operateResult:");
        sb.append(this.mOperateResult);
        sb.append("}");
        return sb.toString();
    }
}
