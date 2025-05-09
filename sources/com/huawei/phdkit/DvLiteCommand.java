package com.huawei.phdkit;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.mbg;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class DvLiteCommand implements Parcelable {
    public static final Parcelable.Creator<DvLiteCommand> CREATOR = new Parcelable.Creator<DvLiteCommand>() { // from class: com.huawei.phdkit.DvLiteCommand.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public DvLiteCommand[] newArray(int i) {
            return (i > 65535 || i < 0) ? new DvLiteCommand[0] : new DvLiteCommand[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ccN_, reason: merged with bridge method [inline-methods] */
        public DvLiteCommand createFromParcel(Parcel parcel) {
            byte[] bArr;
            DvLiteCommand dvLiteCommand = new DvLiteCommand();
            if (parcel == null) {
                return dvLiteCommand;
            }
            dvLiteCommand.setServiceId(parcel.readInt());
            dvLiteCommand.setCommandId(parcel.readInt());
            dvLiteCommand.setUdid(parcel.readString());
            int readInt = parcel.readInt();
            if (readInt <= 0 || readInt >= 2048) {
                bArr = null;
            } else {
                bArr = new byte[readInt];
                parcel.readByteArray(bArr);
            }
            dvLiteCommand.setDataContents(bArr);
            dvLiteCommand.setPriority(parcel.readInt());
            return dvLiteCommand;
        }
    };
    private static final int STRING_LENGTH = 16;
    private int mCommandId;
    private byte[] mDataContents;
    private int mPriority = 1;
    private int mServiceId;
    private String mUdid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setServiceId(int i) {
        this.mServiceId = i;
    }

    public int getServiceId() {
        return this.mServiceId;
    }

    public void setCommandId(int i) {
        this.mCommandId = i;
    }

    public int getCommandId() {
        return this.mCommandId;
    }

    public void setDataContents(byte[] bArr) {
        if (bArr != null) {
            this.mDataContents = Arrays.copyOf(bArr, bArr.length);
        }
    }

    public byte[] getDataContents() {
        byte[] bArr = this.mDataContents;
        return bArr != null ? Arrays.copyOf(bArr, bArr.length) : new byte[0];
    }

    public void setPriority(int i) {
        this.mPriority = i;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public String getUdid() {
        return this.mUdid;
    }

    public void setUdid(String str) {
        this.mUdid = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mServiceId);
        parcel.writeInt(this.mCommandId);
        parcel.writeString(this.mUdid);
        byte[] bArr = this.mDataContents;
        if (bArr == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(bArr.length);
        }
        byte[] bArr2 = this.mDataContents;
        if (bArr2 != null && bArr2.length > 0 && bArr2.length < 2048) {
            parcel.writeByteArray(bArr2);
        }
        parcel.writeInt(this.mPriority);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("DeviceCommand{serviceID:'");
        sb.append(this.mServiceId);
        sb.append("', commandID:'");
        sb.append(this.mCommandId);
        sb.append("', dataContents:'");
        sb.append(mbg.c(this.mDataContents));
        sb.append("'}");
        return sb.toString();
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mServiceId = parcel.readInt();
        this.mCommandId = parcel.readInt();
        this.mUdid = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt > 0 && readInt < 2048) {
            byte[] bArr = new byte[readInt];
            parcel.readByteArray(bArr);
            this.mDataContents = bArr;
        }
        this.mPriority = parcel.readInt();
    }
}
