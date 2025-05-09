package com.huawei.wearengine.p2p;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class MessageParcel implements Parcelable {
    public static final Parcelable.Creator<MessageParcel> CREATOR = new Parcelable.Creator<MessageParcel>() { // from class: com.huawei.wearengine.p2p.MessageParcel.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: feg_, reason: merged with bridge method [inline-methods] */
        public MessageParcel createFromParcel(Parcel parcel) {
            MessageParcel messageParcel = new MessageParcel();
            if (parcel != null) {
                int readInt = parcel.readInt();
                messageParcel.setType(readInt);
                if (MessageParcel.isData(readInt)) {
                    messageParcel.setData(parcel.createByteArray());
                } else {
                    messageParcel.setParcelFileDescriptor(parcel.readFileDescriptor());
                }
                messageParcel.setFileName(parcel.readString());
                messageParcel.setDescription(parcel.readString());
                messageParcel.setFileSha256(parcel.readString());
            }
            return messageParcel;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public MessageParcel[] newArray(int i) {
            return (i > 65535 || i < 0) ? new MessageParcel[0] : new MessageParcel[i];
        }
    };
    public static final int MESSAGE_TYPE_DATA = 1;
    public static final int MESSAGE_TYPE_FILE = 2;
    private byte[] mData;
    private String mDescription;
    private String mFileName;
    private String mFileSha256;
    private ParcelFileDescriptor mParcelFileDescriptor;
    private int mType;

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isData(int i) {
        return i == 1;
    }

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

    public byte[] getData() {
        byte[] bArr = this.mData;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public void setData(byte[] bArr) {
        this.mData = bArr == null ? null : (byte[]) bArr.clone();
    }

    public ParcelFileDescriptor getParcelFileDescriptor() {
        return this.mParcelFileDescriptor;
    }

    public void setParcelFileDescriptor(ParcelFileDescriptor parcelFileDescriptor) {
        this.mParcelFileDescriptor = parcelFileDescriptor;
    }

    public String getFileName() {
        return this.mFileName;
    }

    public void setFileName(String str) {
        this.mFileName = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public String getFileSha256() {
        return this.mFileSha256;
    }

    public void setFileSha256(String str) {
        this.mFileSha256 = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        writeToParcelBase(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        readFromParcelBase(parcel);
    }

    protected void writeToParcelBase(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mType);
        if (this.mType == 1) {
            parcel.writeByteArray(this.mData);
        } else {
            ParcelFileDescriptor parcelFileDescriptor = this.mParcelFileDescriptor;
            if (parcelFileDescriptor != null) {
                parcel.writeFileDescriptor(parcelFileDescriptor.getFileDescriptor());
            }
        }
        parcel.writeString(this.mFileName);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mFileSha256);
    }

    protected void readFromParcelBase(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        int readInt = parcel.readInt();
        this.mType = readInt;
        if (readInt == 1) {
            this.mData = parcel.createByteArray();
        } else {
            this.mParcelFileDescriptor = parcel.readFileDescriptor();
        }
        this.mFileName = parcel.readString();
        this.mDescription = parcel.readString();
        this.mFileSha256 = parcel.readString();
    }
}
