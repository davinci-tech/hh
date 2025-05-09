package com.huawei.devicesdk.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class CommandMessageParcel implements Parcelable {
    public static final Parcelable.Creator<CommandMessageParcel> CREATOR = new Parcelable.Creator<CommandMessageParcel>() { // from class: com.huawei.devicesdk.entity.CommandMessageParcel.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: rS_, reason: merged with bridge method [inline-methods] */
        public CommandMessageParcel createFromParcel(Parcel parcel) {
            CommandMessageParcel commandMessageParcel = new CommandMessageParcel();
            commandMessageParcel.setCommandType(parcel.readInt());
            commandMessageParcel.setServiceUuid(parcel.readString());
            commandMessageParcel.setCharacteristicUuid(parcel.readString());
            commandMessageParcel.setCommand(parcel.createByteArray());
            commandMessageParcel.setPriorityType(parcel.readInt());
            commandMessageParcel.setNeedEncrypt(parcel.readByte() != 0);
            commandMessageParcel.setExtendJson(parcel.readString());
            commandMessageParcel.setOptionsType(parcel.readInt());
            commandMessageParcel.setSocketChannel(parcel.readInt());
            return commandMessageParcel;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public CommandMessageParcel[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new CommandMessageParcel[i];
        }
    };
    private String mCharacteristicUuid;
    private byte[] mCommand;
    private int mCommandType;
    private String mExtendJson;
    private boolean mIsNeedEncrypt;
    private int mOptionsType;
    private int mPriority;
    private String mServiceUuid;
    private int mSocketChannel;

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

    public int getOptionsType() {
        return this.mOptionsType;
    }

    public void setOptionsType(int i) {
        this.mOptionsType = i;
    }

    public int getSocketChannel() {
        return this.mSocketChannel;
    }

    public void setSocketChannel(int i) {
        this.mSocketChannel = i;
    }

    public byte[] getCommand() {
        byte[] bArr = this.mCommand;
        return bArr != null ? bArr : new byte[0];
    }

    public void setCommand(byte[] bArr) {
        this.mCommand = bArr;
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
        parcel.writeInt(this.mCommandType);
        parcel.writeString(this.mServiceUuid);
        parcel.writeString(this.mCharacteristicUuid);
        parcel.writeByteArray(this.mCommand);
        parcel.writeInt(this.mPriority);
        parcel.writeByte(this.mIsNeedEncrypt ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mExtendJson);
        parcel.writeInt(this.mOptionsType);
        parcel.writeInt(this.mSocketChannel);
    }

    public void readFromParcel(Parcel parcel) {
        this.mCommandType = parcel.readInt();
        this.mServiceUuid = parcel.readString();
        this.mCharacteristicUuid = parcel.readString();
        this.mCommand = parcel.createByteArray();
        this.mPriority = parcel.readInt();
        this.mIsNeedEncrypt = parcel.readByte() != 0;
        this.mExtendJson = parcel.readString();
        this.mOptionsType = parcel.readInt();
        this.mSocketChannel = parcel.readInt();
    }
}
