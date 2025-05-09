package com.huawei.hwsmartinteractmgr.data;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class SmartMsgDbObject implements Parcelable {
    public static final Parcelable.Creator<SmartMsgDbObject> CREATOR = new Parcelable.Creator<SmartMsgDbObject>() { // from class: com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SmartMsgDbObject createFromParcel(Parcel parcel) {
            return new SmartMsgDbObject(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SmartMsgDbObject[] newArray(int i) {
            return new SmartMsgDbObject[i];
        }
    };
    private long mCreateTime;
    private long mExpireTime;
    private String mHuid;
    private int mId;
    private String mMsgContent;
    private int mMsgContentType;
    private int mMsgId;
    private int mMsgSrc;
    private int mMsgType;
    private int mPriority;
    private int mShowCount;
    private String mShowMethod;
    private String mShowTime;
    private int mStatus;
    private long mUpdateTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SmartMsgDbObject() {
    }

    public SmartMsgDbObject(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mId = parcel.readInt();
        this.mMsgId = parcel.readInt();
        this.mMsgType = parcel.readInt();
        this.mMsgSrc = parcel.readInt();
        this.mMsgContentType = parcel.readInt();
        this.mMsgContent = parcel.readString();
        this.mShowMethod = parcel.readString();
        this.mCreateTime = parcel.readLong();
        this.mUpdateTime = parcel.readLong();
        this.mExpireTime = parcel.readLong();
        this.mStatus = parcel.readInt();
        this.mHuid = parcel.readString();
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public int getMsgId() {
        return this.mMsgId;
    }

    public void setMsgId(int i) {
        this.mMsgId = i;
    }

    public int getMsgType() {
        return this.mMsgType;
    }

    public void setMsgType(int i) {
        this.mMsgType = i;
    }

    public int getMsgSrc() {
        return this.mMsgSrc;
    }

    public void setMsgSrc(int i) {
        this.mMsgSrc = i;
    }

    public int getMsgContentType() {
        return this.mMsgContentType;
    }

    public void setMsgContentType(int i) {
        this.mMsgContentType = i;
    }

    public String getMsgContent() {
        return this.mMsgContent;
    }

    public void setMsgContent(String str) {
        this.mMsgContent = str;
    }

    public String getShowMethod() {
        return this.mShowMethod;
    }

    public void setShowMethod(String str) {
        this.mShowMethod = str;
    }

    public long getCreateTime() {
        return this.mCreateTime;
    }

    public void setCreateTime(long j) {
        this.mCreateTime = j;
    }

    public long getUpdateTime() {
        return this.mUpdateTime;
    }

    public void setUpdateTime(long j) {
        this.mUpdateTime = j;
    }

    public long getExpireTime() {
        return this.mExpireTime;
    }

    public void setExpireTime(long j) {
        this.mExpireTime = j;
    }

    public String getShowTime() {
        return this.mShowTime;
    }

    public void setShowTime(String str) {
        this.mShowTime = str;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public String getHuid() {
        return this.mHuid;
    }

    public void setHuid(String str) {
        this.mHuid = str;
    }

    public int getMessagePriority() {
        return this.mPriority;
    }

    public void setMessagePriority(int i) {
        this.mPriority = i;
    }

    public int getShowCount() {
        return this.mShowCount;
    }

    public void setShowCount(int i) {
        this.mShowCount = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("SmartMsgDbObject{mId=");
        stringBuffer.append(this.mId);
        stringBuffer.append(", mMsgId=").append(this.mMsgId);
        stringBuffer.append(", mMsgType=").append(this.mMsgType);
        stringBuffer.append(", mMsgSrc=").append(this.mMsgSrc);
        stringBuffer.append(", mMsgContentType=").append(this.mMsgContentType);
        stringBuffer.append(", mMsgContent='").append(this.mMsgContent).append("', mShowMethod='");
        stringBuffer.append(this.mShowMethod).append("', mCreateTime=");
        stringBuffer.append(this.mCreateTime);
        stringBuffer.append(", mUpdateTime=").append(this.mUpdateTime);
        stringBuffer.append(", mExpireTime=").append(this.mExpireTime);
        stringBuffer.append(", mShowTime='").append(this.mShowTime).append("', mStatus=");
        stringBuffer.append(this.mStatus);
        stringBuffer.append(", mHuid='").append(this.mHuid).append("', mPriority=");
        stringBuffer.append(this.mPriority);
        stringBuffer.append(", mShowCount=").append(this.mShowCount);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mMsgId);
        parcel.writeInt(this.mMsgType);
        parcel.writeInt(this.mMsgSrc);
        parcel.writeInt(this.mMsgContentType);
        parcel.writeString(this.mMsgContent);
        parcel.writeString(this.mShowMethod);
        parcel.writeLong(this.mCreateTime);
        parcel.writeLong(this.mUpdateTime);
        parcel.writeLong(this.mExpireTime);
        parcel.writeInt(this.mStatus);
        parcel.writeString(this.mHuid);
    }
}
