package com.huawei.wearkit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IWearBinderInterceptor extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearkit.IWearBinderInterceptor";

    IBinder getServiceBinder(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IWearBinderInterceptor {
        static final int TRANSACTION_getServiceBinder = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IWearBinderInterceptor.DESCRIPTOR);
        }

        public static IWearBinderInterceptor asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IWearBinderInterceptor.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IWearBinderInterceptor)) {
                return (IWearBinderInterceptor) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IWearBinderInterceptor.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IWearBinderInterceptor.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                IBinder serviceBinder = getServiceBinder(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(serviceBinder);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* loaded from: classes9.dex */
        static class d implements IWearBinderInterceptor {
            private IBinder b;

            d(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.wearkit.IWearBinderInterceptor
            public IBinder getServiceBinder(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearBinderInterceptor.DESCRIPTOR);
                    obtain.writeString(str);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
