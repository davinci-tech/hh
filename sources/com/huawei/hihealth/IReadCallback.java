package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IReadCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IReadCallback";

    public static class Default implements IReadCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IReadCallback
        public void onResult(int i, String str, byte[] bArr) throws RemoteException {
        }
    }

    void onResult(int i, String str, byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IReadCallback {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IReadCallback.DESCRIPTOR);
        }

        public static IReadCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IReadCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IReadCallback)) {
                return (IReadCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IReadCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IReadCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                int readInt = parcel.readInt();
                String readString = parcel.readString();
                int readInt2 = parcel.readInt();
                byte[] bArr = readInt2 < 0 ? null : new byte[readInt2];
                onResult(readInt, readString, bArr);
                parcel2.writeNoException();
                parcel2.writeByteArray(bArr);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements IReadCallback {
            private IBinder e;

            Proxy(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.hihealth.IReadCallback
            public void onResult(int i, String str, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IReadCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(bArr.length);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    obtain2.readByteArray(bArr);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
