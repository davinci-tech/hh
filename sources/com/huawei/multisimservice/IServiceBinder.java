package com.huawei.multisimservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public interface IServiceBinder extends IInterface {
    IBinder getServiceBinder(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IServiceBinder {
        private static final String DESCRIPTOR = "com.huawei.multisimservice.IServiceBinder";
        static final int TRANSACTION_getServiceBinder = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IServiceBinder asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IServiceBinder)) {
                return (IServiceBinder) queryLocalInterface;
            }
            return new c(iBinder);
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
            IBinder serviceBinder = getServiceBinder(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeStrongBinder(serviceBinder);
            return true;
        }

        static class c implements IServiceBinder {
            public static IServiceBinder b;
            private IBinder e;

            c(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.multisimservice.IServiceBinder
            public IBinder getServiceBinder(String str) throws RemoteException {
                IBinder readStrongBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.e.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readStrongBinder = Stub.getDefaultImpl().getServiceBinder(str);
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

        public static boolean setDefaultImpl(IServiceBinder iServiceBinder) {
            if (c.b != null || iServiceBinder == null) {
                return false;
            }
            c.b = iServiceBinder;
            return true;
        }

        public static IServiceBinder getDefaultImpl() {
            return c.b;
        }
    }
}
