package com.huawei.unitedevice.callback;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes7.dex */
public interface IResultAIDLCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.unitedevice.callback.IResultAIDLCallback";

    void onFileRespond(int i, String str) throws RemoteException;

    void onFileTransferReport(String str) throws RemoteException;

    void onFileTransferState(int i, String str) throws RemoteException;

    void onTransferFailed(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IResultAIDLCallback {
        static final int TRANSACTION_onFileRespond = 3;
        static final int TRANSACTION_onFileTransferReport = 4;
        static final int TRANSACTION_onFileTransferState = 1;
        static final int TRANSACTION_onTransferFailed = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IResultAIDLCallback.DESCRIPTOR);
        }

        public static IResultAIDLCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IResultAIDLCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IResultAIDLCallback)) {
                return (IResultAIDLCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IResultAIDLCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IResultAIDLCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onFileTransferState(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else if (i == 2) {
                onTransferFailed(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else if (i == 3) {
                onFileRespond(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else if (i == 4) {
                onFileTransferReport(parcel.readString());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class a implements IResultAIDLCallback {
            private IBinder c;

            a(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onFileTransferState(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IResultAIDLCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onTransferFailed(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IResultAIDLCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onFileRespond(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IResultAIDLCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.c.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onFileTransferReport(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IResultAIDLCallback.DESCRIPTOR);
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
