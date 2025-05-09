package com.huawei.android.airsharing.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public interface IAidlHwServerManager extends IInterface {
    void deInit() throws RemoteException;

    void subscribServers(String str) throws RemoteException;

    void unsubscribServers() throws RemoteException;

    public static abstract class Stub extends Binder implements IAidlHwServerManager {
        private static final String DESCRIPTOR = "com.huawei.android.airsharing.client.IAidlHwServerManager";
        static final int TRANSACTION_deInit = 1;
        static final int TRANSACTION_subscribServers = 2;
        static final int TRANSACTION_unsubscribServers = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAidlHwServerManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAidlHwServerManager)) {
                return (IAidlHwServerManager) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                deInit();
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                subscribServers(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            unsubscribServers();
            parcel2.writeNoException();
            return true;
        }

        static class e implements IAidlHwServerManager {
            public static IAidlHwServerManager d;
            private IBinder e;

            e(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwServerManager
            public void deInit() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.e.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deInit();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwServerManager
            public void subscribServers(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.e.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribServers(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwServerManager
            public void unsubscribServers() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.e.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribServers();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAidlHwServerManager iAidlHwServerManager) {
            if (e.d != null || iAidlHwServerManager == null) {
                return false;
            }
            e.d = iAidlHwServerManager;
            return true;
        }

        public static IAidlHwServerManager getDefaultImpl() {
            return e.d;
        }
    }
}
