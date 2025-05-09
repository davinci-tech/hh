package com.huawei.appmarket.service.externalservice.distribution.download;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public interface IDownloadCallback extends IInterface {
    String getDownloadRegisterKey() throws RemoteException;

    void refreshAppStatus(String str, int i, int i2, int i3) throws RemoteException;

    public static abstract class Stub extends Binder implements IDownloadCallback {
        private static final String DESCRIPTOR = "com.huawei.appmarket.service.externalservice.distribution.download.IDownloadCallback";
        static final int TRANSACTION_getDownloadRegisterKey = 1;
        static final int TRANSACTION_refreshAppStatus = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                String downloadRegisterKey = getDownloadRegisterKey();
                parcel2.writeNoException();
                parcel2.writeString(downloadRegisterKey);
                return true;
            }
            if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            refreshAppStatus(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        static class d implements IDownloadCallback {
            public static IDownloadCallback e;
            private IBinder d;

            @Override // com.huawei.appmarket.service.externalservice.distribution.download.IDownloadCallback
            public void refreshAppStatus(String str, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (this.d.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().refreshAppStatus(str, i, i2, i3);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.appmarket.service.externalservice.distribution.download.IDownloadCallback
            public String getDownloadRegisterKey() throws RemoteException {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.d.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        readString = obtain2.readString();
                    } else {
                        readString = Stub.getDefaultImpl().getDownloadRegisterKey();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            d(IBinder iBinder) {
                this.d = iBinder;
            }
        }

        public static boolean setDefaultImpl(IDownloadCallback iDownloadCallback) {
            if (d.e != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDownloadCallback == null) {
                return false;
            }
            d.e = iDownloadCallback;
            return true;
        }

        public static IDownloadCallback getDefaultImpl() {
            return d.e;
        }

        public static IDownloadCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IDownloadCallback)) ? new d(iBinder) : (IDownloadCallback) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }
    }
}
