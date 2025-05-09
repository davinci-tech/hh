package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IAppTransferFileResultAIDLCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback";

    void onFileRespond(int i, String str) throws RemoteException;

    void onFileTransferState(int i, String str) throws RemoteException;

    void onUpgradeFailed(int i, String str) throws RemoteException;

    /* loaded from: classes5.dex */
    public static abstract class Stub extends Binder implements IAppTransferFileResultAIDLCallback {
        static final int TRANSACTION_onFileRespond = 3;
        static final int TRANSACTION_onFileTransferState = 1;
        static final int TRANSACTION_onUpgradeFailed = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IAppTransferFileResultAIDLCallback.DESCRIPTOR);
        }

        public static IAppTransferFileResultAIDLCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IAppTransferFileResultAIDLCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAppTransferFileResultAIDLCallback)) {
                return (IAppTransferFileResultAIDLCallback) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IAppTransferFileResultAIDLCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IAppTransferFileResultAIDLCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onFileTransferState(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else if (i == 2) {
                onUpgradeFailed(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else if (i == 3) {
                onFileRespond(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class c implements IAppTransferFileResultAIDLCallback {
            private IBinder d;

            c(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onFileTransferState(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppTransferFileResultAIDLCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onUpgradeFailed(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppTransferFileResultAIDLCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onFileRespond(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppTransferFileResultAIDLCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.d.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
