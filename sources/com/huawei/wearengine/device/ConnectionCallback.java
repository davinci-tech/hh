package com.huawei.wearengine.device;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes9.dex */
public interface ConnectionCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.device.ConnectionCallback";

    void onConnected(String str) throws RemoteException;

    void onDisconnected(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ConnectionCallback {
        static final int TRANSACTION_onConnected = 1;
        static final int TRANSACTION_onDisconnected = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ConnectionCallback.DESCRIPTOR);
        }

        public static ConnectionCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ConnectionCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ConnectionCallback)) {
                return (ConnectionCallback) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ConnectionCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ConnectionCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onConnected(parcel.readString());
                parcel2.writeNoException();
            } else if (i == 2) {
                onDisconnected(parcel.readString());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class d implements ConnectionCallback {
            private IBinder b;

            d(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.wearengine.device.ConnectionCallback
            public void onConnected(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ConnectionCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.device.ConnectionCallback
            public void onDisconnected(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ConnectionCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    this.b.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
