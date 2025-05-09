package com.huawei.wearengine.connect;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.wearengine.connect.ServiceConnectCallback;

/* loaded from: classes9.dex */
public interface ServiceConnectCallbackManager extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.connect.ServiceConnectCallbackManager";

    int registerConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException;

    int registerOtaServiceConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException;

    int unregisterConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException;

    int unregisterOtaServiceConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ServiceConnectCallbackManager {
        static final int TRANSACTION_registerConnectCallback = 1;
        static final int TRANSACTION_registerOtaServiceConnectCallback = 3;
        static final int TRANSACTION_unregisterConnectCallback = 2;
        static final int TRANSACTION_unregisterOtaServiceConnectCallback = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ServiceConnectCallbackManager.DESCRIPTOR);
        }

        public static ServiceConnectCallbackManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ServiceConnectCallbackManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ServiceConnectCallbackManager)) {
                return (ServiceConnectCallbackManager) queryLocalInterface;
            }
            return new b(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ServiceConnectCallbackManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ServiceConnectCallbackManager.DESCRIPTOR);
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
                int registerOtaServiceConnectCallback = registerOtaServiceConnectCallback(ServiceConnectCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(registerOtaServiceConnectCallback);
            } else if (i == 4) {
                int unregisterOtaServiceConnectCallback = unregisterOtaServiceConnectCallback(ServiceConnectCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(unregisterOtaServiceConnectCallback);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class b implements ServiceConnectCallbackManager {
            private IBinder d;

            b(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.wearengine.connect.ServiceConnectCallbackManager
            public int registerConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ServiceConnectCallbackManager.DESCRIPTOR);
                    obtain.writeStrongInterface(serviceConnectCallback);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.connect.ServiceConnectCallbackManager
            public int unregisterConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ServiceConnectCallbackManager.DESCRIPTOR);
                    obtain.writeStrongInterface(serviceConnectCallback);
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.connect.ServiceConnectCallbackManager
            public int registerOtaServiceConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ServiceConnectCallbackManager.DESCRIPTOR);
                    obtain.writeStrongInterface(serviceConnectCallback);
                    this.d.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.connect.ServiceConnectCallbackManager
            public int unregisterOtaServiceConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ServiceConnectCallbackManager.DESCRIPTOR);
                    obtain.writeStrongInterface(serviceConnectCallback);
                    this.d.transact(4, obtain, obtain2, 0);
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
