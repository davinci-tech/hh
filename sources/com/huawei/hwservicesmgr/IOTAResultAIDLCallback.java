package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IOTAResultAIDLCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.IOTAResultAIDLCallback";

    void onFileRespond(int i) throws RemoteException;

    void onFileTransferReport(String str) throws RemoteException;

    void onFileTransferState(int i) throws RemoteException;

    void onUpgradeFailed(int i, String str) throws RemoteException;

    /* loaded from: classes5.dex */
    public static abstract class Stub extends Binder implements IOTAResultAIDLCallback {
        static final int TRANSACTION_onFileRespond = 3;
        static final int TRANSACTION_onFileTransferReport = 4;
        static final int TRANSACTION_onFileTransferState = 1;
        static final int TRANSACTION_onUpgradeFailed = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IOTAResultAIDLCallback.DESCRIPTOR);
        }

        public static IOTAResultAIDLCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IOTAResultAIDLCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOTAResultAIDLCallback)) {
                return (IOTAResultAIDLCallback) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IOTAResultAIDLCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IOTAResultAIDLCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onFileTransferState(parcel.readInt());
                parcel2.writeNoException();
            } else if (i == 2) {
                onUpgradeFailed(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else if (i == 3) {
                onFileRespond(parcel.readInt());
                parcel2.writeNoException();
            } else if (i == 4) {
                onFileTransferReport(parcel.readString());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class c implements IOTAResultAIDLCallback {
            private IBinder c;

            c(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileTransferState(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOTAResultAIDLCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onUpgradeFailed(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOTAResultAIDLCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileRespond(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOTAResultAIDLCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.c.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileTransferReport(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOTAResultAIDLCallback.DESCRIPTOR);
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
