package com.huawei.unitedevice.callback;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes7.dex */
public interface ITransferFileCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.unitedevice.callback.ITransferFileCallback";

    void onFailure(int i, String str) throws RemoteException;

    void onProgress(int i, String str) throws RemoteException;

    void onResponse(int i, String str) throws RemoteException;

    void onSuccess(int i, String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements ITransferFileCallback {
        static final int TRANSACTION_onFailure = 2;
        static final int TRANSACTION_onProgress = 3;
        static final int TRANSACTION_onResponse = 4;
        static final int TRANSACTION_onSuccess = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ITransferFileCallback.DESCRIPTOR);
        }

        public static ITransferFileCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ITransferFileCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ITransferFileCallback)) {
                return (ITransferFileCallback) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ITransferFileCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ITransferFileCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onSuccess(parcel.readInt(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
            } else if (i == 2) {
                onFailure(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else if (i == 3) {
                onProgress(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else if (i == 4) {
                onResponse(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class e implements ITransferFileCallback {
            private IBinder c;

            e(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITransferFileCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITransferFileCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITransferFileCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.c.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onResponse(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITransferFileCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.c.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
