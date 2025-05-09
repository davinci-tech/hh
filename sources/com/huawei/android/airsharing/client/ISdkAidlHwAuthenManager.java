package com.huawei.android.airsharing.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ISdkAidlHwAuthenManager extends IInterface {
    IBinder checkPermission() throws RemoteException;

    public static abstract class Stub extends Binder implements ISdkAidlHwAuthenManager {
        private static final String DESCRIPTOR = "com.huawei.android.airsharing.client.ISdkAidlHwAuthenManager";
        static final int TRANSACTION_checkPermission = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISdkAidlHwAuthenManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISdkAidlHwAuthenManager)) {
                return (ISdkAidlHwAuthenManager) queryLocalInterface;
            }
            return new b(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            IBinder checkPermission = checkPermission();
            parcel2.writeNoException();
            parcel2.writeStrongBinder(checkPermission);
            return true;
        }

        static class b implements ISdkAidlHwAuthenManager {
            public static ISdkAidlHwAuthenManager b;
            private IBinder d;

            b(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.android.airsharing.client.ISdkAidlHwAuthenManager
            public IBinder checkPermission() throws RemoteException {
                IBinder readStrongBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.d.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readStrongBinder = Stub.getDefaultImpl().checkPermission();
                    } else {
                        obtain2.readException();
                        readStrongBinder = obtain2.readStrongBinder();
                    }
                    return readStrongBinder;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISdkAidlHwAuthenManager iSdkAidlHwAuthenManager) {
            if (b.b != null || iSdkAidlHwAuthenManager == null) {
                return false;
            }
            b.b = iSdkAidlHwAuthenManager;
            return true;
        }

        public static ISdkAidlHwAuthenManager getDefaultImpl() {
            return b.b;
        }
    }
}
