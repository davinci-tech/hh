package com.huawei.profile.profile;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.profile.profile.StreamDataReader;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public class StreamData implements Parcelable {
    public static final Parcelable.Creator<StreamData> CREATOR = new Parcelable.Creator<StreamData>() { // from class: com.huawei.profile.profile.StreamData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StreamData createFromParcel(Parcel parcel) {
            return new StreamData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StreamData[] newArray(int i) {
            return new StreamData[i];
        }
    };
    private static final int PACKET_SIZE = 262144;
    private byte[] firstPacket;
    private Object object;
    private byte[] objectData;
    private int objectSize;
    private StreamDataReader streamDataReader;
    private final int version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected StreamData(Parcel parcel) {
        this.version = parcel.readInt();
        this.objectSize = parcel.readInt();
        this.firstPacket = parcel.createByteArray();
        this.streamDataReader = StreamDataReader.Stub.asInterface(parcel.readStrongBinder());
    }

    public StreamData(Parcelable parcelable) {
        this.version = 1;
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeParcelable(parcelable, 0);
            byte[] marshall = obtain.marshall();
            this.objectData = marshall;
            this.objectSize = marshall.length;
            this.firstPacket = getFirstPacket();
            this.streamDataReader = getReader();
        } finally {
            obtain.recycle();
        }
    }

    private StreamDataReader getReader() {
        return new StreamDataReader.Stub() { // from class: com.huawei.profile.profile.StreamData.2
            @Override // com.huawei.profile.profile.StreamDataReader
            public byte[] read(int i) {
                return StreamData.nextPacket(StreamData.this.objectData, i);
            }
        };
    }

    private byte[] getFirstPacket() {
        byte[] bArr = this.objectData;
        return bArr.length < 262144 ? bArr : nextPacket(bArr, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] nextPacket(byte[] bArr, int i) {
        if (i >= bArr.length) {
            return new byte[0];
        }
        int min = Math.min(bArr.length - i, 262144);
        byte[] bArr2 = new byte[min];
        System.arraycopy(bArr, i, bArr2, 0, min);
        return bArr2;
    }

    public <T> T getData(Class<? extends T> cls) throws RemoteException {
        Object obj = this.object;
        if (obj != null && cls.isInstance(obj)) {
            return (T) this.object;
        }
        byte[] objectData = getObjectData();
        Parcel obtain = Parcel.obtain();
        try {
            obtain.unmarshall(objectData, 0, objectData.length);
            obtain.setDataPosition(0);
            Parcelable readParcelable = obtain.readParcelable(cls.getClassLoader());
            this.object = readParcelable;
            if (!cls.isInstance(readParcelable)) {
                throw new IllegalArgumentException("class cast exception");
            }
            return (T) this.object;
        } finally {
            obtain.recycle();
        }
    }

    private byte[] getObjectData() throws RemoteException {
        byte[] bArr = this.firstPacket;
        int length = bArr.length;
        int i = this.objectSize;
        if (length > i) {
            throw new RemoteException("first packet size is larger than actual size");
        }
        if (bArr.length == i) {
            return bArr;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.put(this.firstPacket);
        int length2 = this.firstPacket.length;
        while (true) {
            byte[] read = this.streamDataReader.read(length2);
            if (read.length == 0) {
                if (allocate.array().length != this.objectSize) {
                    throw new RemoteException("Data length got by server is different from got by client.");
                }
                return allocate.array();
            }
            length2 += read.length;
            if (length2 > this.objectSize) {
                throw new RemoteException("Offset is larger than actual size.");
            }
            allocate.put(read);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.version);
        parcel.writeInt(this.objectSize);
        parcel.writeByteArray(this.firstPacket);
        parcel.writeStrongBinder(this.streamDataReader.asBinder());
    }
}
