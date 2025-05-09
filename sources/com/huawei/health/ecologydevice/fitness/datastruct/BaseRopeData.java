package com.huawei.health.ecologydevice.fitness.datastruct;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.device.datatype.AbstractFitnessData;
import defpackage.cyf;

/* loaded from: classes3.dex */
public class BaseRopeData extends AbstractFitnessData {
    public static final Parcelable.Creator<BaseRopeData> CREATOR = new Parcelable.Creator<BaseRopeData>() { // from class: com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: RX_, reason: merged with bridge method [inline-methods] */
        public BaseRopeData createFromParcel(Parcel parcel) {
            return new BaseRopeData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public BaseRopeData[] newArray(int i) {
            return new BaseRopeData[i];
        }
    };
    public static final int FLAG_INIT = 0;
    private boolean isRopeConfigCommand;
    private boolean isSubcontracting;
    protected int mCode;
    protected int mCommand;
    protected byte[] mData;
    protected int mDataLength;
    protected int mFlag;
    protected int mOffset;
    protected int[] mPara;

    public cyf getTransmitCommand() {
        return null;
    }

    public boolean isAllSet() {
        return true;
    }

    public BaseRopeData parseData() {
        return this;
    }

    public BaseRopeData() {
    }

    BaseRopeData(int i) {
        setFitnessDataType(i);
    }

    public BaseRopeData(Parcel parcel) {
        super(parcel);
        this.mData = parcel.createByteArray();
        this.mCommand = parcel.readInt();
        this.mCode = parcel.readInt();
        this.mFlag = parcel.readInt();
        this.mOffset = parcel.readInt();
        this.mDataLength = parcel.readInt();
        this.mPara = parcel.createIntArray();
        this.isSubcontracting = parcel.readBoolean();
        this.isRopeConfigCommand = parcel.readBoolean();
    }

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeByteArray(this.mData);
        parcel.writeInt(this.mCommand);
        parcel.writeInt(this.mCode);
        parcel.writeInt(this.mFlag);
        parcel.writeInt(this.mOffset);
        parcel.writeInt(this.mDataLength);
        parcel.writeIntArray(this.mPara);
        parcel.writeBoolean(this.isSubcontracting);
        parcel.writeBoolean(this.isRopeConfigCommand);
    }

    public byte[] getData() {
        return this.mData;
    }

    public void setData(byte[] bArr) {
        this.mData = bArr;
    }

    public int getCode() {
        return this.mCode;
    }

    public void setCode(int i) {
        this.mCode = i;
    }

    public int getCommand() {
        return this.mCommand;
    }

    public void setCommand(int i) {
        this.mCommand = i;
    }

    public int getFlag() {
        return this.mFlag;
    }

    public void setFlag(int i) {
        this.mFlag = i;
    }

    public int getOffset() {
        return this.mOffset;
    }

    public void setOffset(int i) {
        this.mOffset = i;
    }

    public int getDataLength() {
        return this.mDataLength;
    }

    public void setDataLength(int i) {
        this.mDataLength = i;
    }

    public int[] getPara() {
        return this.mPara;
    }

    public void setPara(int[] iArr) {
        this.mPara = iArr;
    }

    public boolean isSubcontracting() {
        return this.isSubcontracting;
    }

    public void setSubcontracting(boolean z) {
        this.isSubcontracting = z;
    }

    public boolean isRopeConfigCommand() {
        return this.isRopeConfigCommand;
    }

    public void setRopeConfigCommand(boolean z) {
        this.isRopeConfigCommand = z;
    }
}
