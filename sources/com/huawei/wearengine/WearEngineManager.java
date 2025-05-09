package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.wearengine.connect.ServiceConnectCallback;

/* loaded from: classes9.dex */
public interface WearEngineManager extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.WearEngineManager";

    int registerConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException;

    int releaseConnection() throws RemoteException;

    int unregisterConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements WearEngineManager {
        static final int TRANSACTION_registerConnectCallback = 1;
        static final int TRANSACTION_releaseConnection = 3;
        static final int TRANSACTION_unregisterConnectCallback = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, WearEngineManager.DESCRIPTOR);
        }

        public static WearEngineManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(WearEngineManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof WearEngineManager)) {
                return (WearEngineManager) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(WearEngineManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(WearEngineManager.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                int registerConnectCallback = registerConnectCallback(ServiceConnectCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(registerConnectCallback);
            } else if (i == 2) {
                int unregisterConnectCallback = unregisterConnectCallback(ServiceConnectCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(unregisterConnectCallback);
            } else if (i == 3) {
                int releaseConnection = releaseConnection();
                parcel2.writeNoException();
                parcel2.writeInt(releaseConnection);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class a implements WearEngineManager {
            private IBinder e;

            a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.wearengine.WearEngineManager
            public int registerConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(WearEngineManager.DESCRIPTOR);
                    obtain.writeStrongInterface(serviceConnectCallback);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.WearEngineManager
            public int unregisterConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(WearEngineManager.DESCRIPTOR);
                    obtain.writeStrongInterface(serviceConnectCallback);
                    this.e.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.WearEngineManager
            public int releaseConnection() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(WearEngineManager.DESCRIPTOR);
                    this.e.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
