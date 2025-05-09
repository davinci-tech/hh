package com.huawei.profile.profile;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes6.dex */
public interface StreamDataReader extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements StreamDataReader {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.profile.profile.StreamDataReader
        public byte[] read(int i) throws RemoteException {
            return null;
        }
    }

    byte[] read(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements StreamDataReader {
        private static final String DESCRIPTOR = "com.huawei.profile.profile.StreamDataReader";
        static final int TRANSACTION_read = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static StreamDataReader asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof StreamDataReader)) {
                return (StreamDataReader) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            byte[] read = read(parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeByteArray(read);
            return true;
        }

        static class Proxy implements StreamDataReader {
            public static StreamDataReader sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.profile.profile.StreamDataReader
            public byte[] read(int i) throws RemoteException {
                byte[] createByteArray;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createByteArray = Stub.getDefaultImpl().read(i);
                    } else {
                        obtain2.readException();
                        createByteArray = obtain2.createByteArray();
                    }
                    return createByteArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public static boolean setDefaultImpl(StreamDataReader streamDataReader) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (streamDataReader == null) {
                return false;
            }
            Proxy.sDefaultImpl = streamDataReader;
            return true;
        }

        public static StreamDataReader getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
