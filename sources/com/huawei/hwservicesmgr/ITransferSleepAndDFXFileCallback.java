package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public interface ITransferSleepAndDFXFileCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback";

    void onFailure(int i, String str) throws RemoteException;

    void onProgress(int i, String str) throws RemoteException;

    void onSuccess(int i, String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements ITransferSleepAndDFXFileCallback {
        static final int TRANSACTION_onFailure = 2;
        static final int TRANSACTION_onProgress = 3;
        static final int TRANSACTION_onSuccess = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ITransferSleepAndDFXFileCallback.DESCRIPTOR);
        }

        public static ITransferSleepAndDFXFileCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ITransferSleepAndDFXFileCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ITransferSleepAndDFXFileCallback)) {
                return (ITransferSleepAndDFXFileCallback) queryLocalInterface;
            }
            return new b(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ITransferSleepAndDFXFileCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ITransferSleepAndDFXFileCallback.DESCRIPTOR);
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
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class b implements ITransferSleepAndDFXFileCallback {
            private IBinder e;

            b(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITransferSleepAndDFXFileCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITransferSleepAndDFXFileCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.e.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITransferSleepAndDFXFileCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.e.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
