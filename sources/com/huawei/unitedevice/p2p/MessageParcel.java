package com.huawei.unitedevice.p2p;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class MessageParcel implements Parcelable {
    public static final int ARRAY_MAX_SIZE = 65535;
    public static final Parcelable.Creator<MessageParcel> CREATOR = new Parcelable.Creator<MessageParcel>() { // from class: com.huawei.unitedevice.p2p.MessageParcel.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: ekh_, reason: merged with bridge method [inline-methods] */
        public MessageParcel createFromParcel(Parcel parcel) {
            MessageParcel messageParcel = new MessageParcel();
            messageParcel.setPrior(parcel.readInt());
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
            messageParcel.setEnableEncrypt(parcel.readByte() != 0);
            messageParcel.setFilePath(parcel.readString());
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
    private boolean enableEncrypt;
    private byte[] mData;
    private String mDescription;
    private String mFileName;
    private String mFilePath;
    private String mFileSha256;
    private ParcelFileDescriptor mParcelFileDescriptor;
    private int mPrior = 1;
    private int mType;

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isData(int i) {
        return i == 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setPrior(int i) {
        this.mPrior = i;
    }

    public int getPrior() {
        return this.mPrior;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public void setFilePath(String str) {
        this.mFilePath = str;
    }

    public boolean isEnableEncrypt() {
        return this.enableEncrypt;
    }

    public void setEnableEncrypt(boolean z) {
        this.enableEncrypt = z;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public byte[] getData() {
        byte[] bArr = this.mData;
        return bArr == null ? new byte[0] : bArr;
    }

    public void setData(byte[] bArr) {
        this.mData = bArr;
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
        parcel.writeInt(this.mPrior);
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
        parcel.writeByte(this.enableEncrypt ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mFilePath);
    }

    protected void readFromParcelBase(Parcel parcel) {
        this.mPrior = parcel.readInt();
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
        this.enableEncrypt = parcel.readByte() != 0;
        this.mFilePath = parcel.readString();
    }
}
