package com.huawei.hwcommonmodel.datatypes;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class TransferFileInfo implements Parcelable {
    public static final Parcelable.Creator<TransferFileInfo> CREATOR = new Parcelable.Creator<TransferFileInfo>() { // from class: com.huawei.hwcommonmodel.datatypes.TransferFileInfo.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: bFo_, reason: merged with bridge method [inline-methods] */
        public TransferFileInfo createFromParcel(Parcel parcel) {
            TransferFileInfo transferFileInfo = new TransferFileInfo();
            transferFileInfo.setType(parcel.readInt());
            int[] createIntArray = parcel.createIntArray();
            ArrayList arrayList = new ArrayList(16);
            for (int i : createIntArray) {
                arrayList.add(Integer.valueOf(i));
            }
            transferFileInfo.setRecordId(arrayList);
            transferFileInfo.setGpsType(parcel.readInt());
            transferFileInfo.setStartTime(parcel.readInt());
            transferFileInfo.setEndTime(parcel.readInt());
            transferFileInfo.setLevel(parcel.readInt());
            transferFileInfo.setDeviceMac(parcel.readString());
            transferFileInfo.setDeviceVersion(parcel.readString());
            transferFileInfo.setDeviceType(parcel.readInt());
            TransferFileInfo.handlePart(transferFileInfo, parcel);
            transferFileInfo.setPriority(parcel.readInt());
            transferFileInfo.setSuspend(parcel.readInt());
            transferFileInfo.setTaskType(parcel.readInt());
            transferFileInfo.setDeviceModel(parcel.readString());
            transferFileInfo.setDeviceSn(parcel.readString());
            transferFileInfo.setUdidFromDevice(parcel.readString());
            transferFileInfo.setSelectedFlag(parcel.readInt());
            transferFileInfo.setBugTypeId(parcel.readInt());
            transferFileInfo.setFileLogId(parcel.readString());
            return transferFileInfo;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public TransferFileInfo[] newArray(int i) {
            return new TransferFileInfo[0];
        }
    };
    private int mBugTypeId;
    private Object mCallback;
    private String mDeviceMac;
    private String mDeviceModel;
    private String mDeviceSn;
    private int mDeviceType;
    private String mDeviceVersion;
    private int mEndTime;
    private String mFileLogId;
    private int mGpsType;
    private int mLevel;
    private int[] mRecordIds;
    private int mStartTime;
    private int mType;
    private String mUdidFromDevice;
    private int mLogPreExport = 0;
    private String mLogPreExportKind = "";
    private int mDfxLogType = 0;
    private int mIsFromAbout = 0;
    private int mIsUploadAppLog = 0;
    private int mSelectedFlag = 0;
    private int mPriority = 2;
    private int mSuspend = 0;
    private int mTaskType = 0;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public int getBugTypeId() {
        return this.mBugTypeId;
    }

    public void setBugTypeId(int i) {
        this.mBugTypeId = i;
    }

    public String getFileLogId() {
        return this.mFileLogId;
    }

    public void setFileLogId(String str) {
        this.mFileLogId = str;
    }

    public int getSelectedFlag() {
        return this.mSelectedFlag;
    }

    public void setSelectedFlag(int i) {
        this.mSelectedFlag = i;
    }

    public List<Integer> getRecordId() {
        ArrayList arrayList = new ArrayList(16);
        int i = 0;
        while (true) {
            int[] iArr = this.mRecordIds;
            if (i >= iArr.length) {
                return arrayList;
            }
            arrayList.add(Integer.valueOf(iArr[i]));
            i++;
        }
    }

    public void setRecordId(List<Integer> list) {
        int[] iArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            iArr[i] = list.get(i).intValue();
        }
        this.mRecordIds = iArr;
    }

    public int getGpsType() {
        return this.mGpsType;
    }

    public void setGpsType(int i) {
        this.mGpsType = i;
    }

    public int getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(int i) {
        this.mStartTime = i;
    }

    public int getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(int i) {
        this.mEndTime = i;
    }

    public int getLevel() {
        return this.mLevel;
    }

    public void setLevel(int i) {
        this.mLevel = i;
    }

    public String getDeviceMac() {
        return this.mDeviceMac;
    }

    public void setDeviceMac(String str) {
        this.mDeviceMac = str;
    }

    public String getDeviceVersion() {
        return this.mDeviceVersion;
    }

    public void setDeviceVersion(String str) {
        this.mDeviceVersion = str;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int i) {
        this.mDeviceType = i;
    }

    public int getDfxLogType() {
        return this.mDfxLogType;
    }

    public void setDfxLogType(int i) {
        this.mDfxLogType = i;
    }

    public int getIsFromAbout() {
        return this.mIsFromAbout;
    }

    public void setIsFromAbout(int i) {
        this.mIsFromAbout = i;
    }

    public int getIsUploadAppLog() {
        return this.mIsUploadAppLog;
    }

    public void setIsUploadAppLog(int i) {
        this.mIsUploadAppLog = i;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public void setPriority(int i) {
        this.mPriority = i;
    }

    public int getLogPreExport() {
        return this.mLogPreExport;
    }

    public void setLogPreExport(int i) {
        this.mLogPreExport = i;
    }

    public String getLogPreExportKind() {
        return this.mLogPreExportKind;
    }

    public void setLogPreExportKind(String str) {
        this.mLogPreExportKind = str;
    }

    public int getSuspend() {
        return this.mSuspend;
    }

    public void setSuspend(int i) {
        this.mSuspend = i;
    }

    public int getTaskType() {
        return this.mTaskType;
    }

    public void setTaskType(int i) {
        this.mTaskType = i;
    }

    public Object getCallback() {
        return this.mCallback;
    }

    public void setCallback(Object obj) {
        this.mCallback = obj;
    }

    public String getDeviceModel() {
        return this.mDeviceModel;
    }

    public void setDeviceModel(String str) {
        this.mDeviceModel = str;
    }

    public String getDeviceSn() {
        return this.mDeviceSn;
    }

    public void setDeviceSn(String str) {
        this.mDeviceSn = str;
    }

    public String getUdidFromDevice() {
        return this.mUdidFromDevice;
    }

    public void setUdidFromDevice(String str) {
        this.mUdidFromDevice = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType);
        parcel.writeIntArray(this.mRecordIds);
        parcel.writeInt(this.mGpsType);
        parcel.writeInt(this.mStartTime);
        parcel.writeInt(this.mEndTime);
        parcel.writeInt(this.mLevel);
        parcel.writeString(this.mDeviceMac);
        parcel.writeString(this.mDeviceVersion);
        parcel.writeInt(this.mDeviceType);
        parcel.writeInt(this.mDfxLogType);
        parcel.writeInt(this.mIsFromAbout);
        parcel.writeInt(this.mIsUploadAppLog);
        parcel.writeInt(this.mLogPreExport);
        parcel.writeString(this.mLogPreExportKind);
        parcel.writeInt(this.mPriority);
        parcel.writeInt(this.mSuspend);
        parcel.writeInt(this.mTaskType);
        parcel.writeString(this.mDeviceModel);
        parcel.writeString(this.mDeviceSn);
        parcel.writeString(this.mUdidFromDevice);
        parcel.writeInt(this.mSelectedFlag);
        parcel.writeInt(this.mBugTypeId);
        parcel.writeString(this.mFileLogId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handlePart(TransferFileInfo transferFileInfo, Parcel parcel) {
        transferFileInfo.setDfxLogType(parcel.readInt());
        transferFileInfo.setIsFromAbout(parcel.readInt());
        transferFileInfo.setIsUploadAppLog(parcel.readInt());
        transferFileInfo.setLogPreExport(parcel.readInt());
        transferFileInfo.setLogPreExportKind(parcel.readString());
    }
}
