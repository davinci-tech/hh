package com.huawei.wearengine.device;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class TransparentCommandParcel implements Parcelable {
    public static final Parcelable.Creator<TransparentCommandParcel> CREATOR = new Parcelable.Creator<TransparentCommandParcel>() { // from class: com.huawei.wearengine.device.TransparentCommandParcel.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: fdl_, reason: merged with bridge method [inline-methods] */
        public TransparentCommandParcel createFromParcel(Parcel parcel) {
            TransparentCommandParcel transparentCommandParcel = new TransparentCommandParcel();
            if (parcel != null) {
                transparentCommandParcel.setCommandType(parcel.readInt());
                transparentCommandParcel.setServiceUuid(parcel.readString());
                transparentCommandParcel.setCharacteristicUuid(parcel.readString());
                transparentCommandParcel.setDeviceMac(parcel.readString());
                transparentCommandParcel.setCommand(parcel.createByteArray());
                transparentCommandParcel.setPriorityType(parcel.readInt());
                transparentCommandParcel.setNeedEncrypt(parcel.readByte() != 0);
                transparentCommandParcel.setExtendJson(parcel.readString());
            }
            return transparentCommandParcel;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public TransparentCommandParcel[] newArray(int i) {
            return (i > 65535 || i < 0) ? new TransparentCommandParcel[0] : new TransparentCommandParcel[i];
        }
    };
    private String mCharacteristicUuid;
    private byte[] mCommand;
    private int mCommandType;
    private String mDeviceMac;
    private String mExtendJson;
    private boolean mIsNeedEncrypt;
    private int mPriority;
    private String mServiceUuid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCommandType() {
        return this.mCommandType;
    }

    public void setCommandType(int i) {
        this.mCommandType = i;
    }

    public String getServiceUuid() {
        return this.mServiceUuid;
    }

    public void setServiceUuid(String str) {
        this.mServiceUuid = str;
    }

    public String getCharacteristicUuid() {
        return this.mCharacteristicUuid;
    }

    public void setCharacteristicUuid(String str) {
        this.mCharacteristicUuid = str;
    }

    public String getDeviceMac() {
        return this.mDeviceMac;
    }

    public void setDeviceMac(String str) {
        this.mDeviceMac = str;
    }

    public byte[] getCommand() {
        byte[] bArr = this.mCommand;
        return bArr != null ? (byte[]) bArr.clone() : new byte[0];
    }

    public void setCommand(byte[] bArr) {
        if (bArr != null) {
            this.mCommand = (byte[]) bArr.clone();
        } else {
            this.mCommand = null;
        }
    }

    public int getPriorityType() {
        return this.mPriority;
    }

    public void setPriorityType(int i) {
        this.mPriority = i;
    }

    public boolean isNeedEncrypt() {
        return this.mIsNeedEncrypt;
    }

    public void setNeedEncrypt(boolean z) {
        this.mIsNeedEncrypt = z;
    }

    public void setExtendJson(String str) {
        this.mExtendJson = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mCommandType);
        parcel.writeString(this.mServiceUuid);
        parcel.writeString(this.mCharacteristicUuid);
        parcel.writeString(this.mDeviceMac);
        parcel.writeByteArray(this.mCommand);
        parcel.writeInt(this.mPriority);
        parcel.writeByte(this.mIsNeedEncrypt ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mExtendJson);
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mCommandType = parcel.readInt();
        this.mServiceUuid = parcel.readString();
        this.mCharacteristicUuid = parcel.readString();
        this.mDeviceMac = parcel.readString();
        this.mCommand = parcel.createByteArray();
        this.mPriority = parcel.readInt();
        this.mIsNeedEncrypt = parcel.readByte() != 0;
        this.mExtendJson = parcel.readString();
    }
}
